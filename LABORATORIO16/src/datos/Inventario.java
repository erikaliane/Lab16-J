package datos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Inventario {
	public Connection con;
	public PreparedStatement sen;
	public Statement sen2;
	public ResultSet datos;
	public String driver="com.mysql.jdbc.Driver";
	public String cadena="jdbc:mysql://localhost/Inventario";
	public String usuario="root";
	public String clave="";
	public Connection conectarBD() {
		try {
			Class.forName(driver);
			con=DriverManager.getConnection(cadena,usuario,clave);
		}catch(ClassNotFoundException e1) {
			JOptionPane.showMessageDialog(null,"Error en el driver");
		}catch(SQLException e2) {
			JOptionPane.showMessageDialog(null, "Error en la conexion");
		}return con;
	}
	public  boolean  registrar_prod(int cod, String nom, int stock, double prec,int alm_cod ) {
		try {
			String codigo="insert into producto values(?,?,?,?,?)";
			sen=conectarBD().prepareStatement(codigo);
			sen.setInt(1, cod);
			sen.setString(2, nom);
			sen.setInt(3, stock);
			sen.setDouble(4, prec);
			sen.setInt(5, alm_cod);
			sen.executeUpdate();		
		}catch(SQLException e3) {
		}
		return true;
	}
	public  boolean  registrar_alm(int alm_cod , String dist,String direc) {
		try {
			String codigo="insert into almacen values(?,?,?)";
			sen=conectarBD().prepareStatement(codigo);
			sen.setInt(1, alm_cod);
			sen.setString(2, dist);
			sen.setString(3, direc);
			sen.executeUpdate();		
		}catch(SQLException e3) {
		}
		return true;
	}
	public ResultSet consulta(String codigosql) {
		try {
			String codigo=codigosql;
			sen2=conectarBD().createStatement();
			datos=sen2.executeQuery(codigo);
		}catch(SQLException e3) {
			JOptionPane.showMessageDialog(null, "Error en la consulta ");
			
		}
		return datos;
	}
	public void imprimir(javax.swing.JTextArea caja) {
		caja.setText("Codigo \tDistrito\tDirecciòn");
		ResultSet data=consulta("select * from almacen");
		try {
		while(data.next()) {
			caja.append("\n"+data.getInt(1)+"\t"+data.getString(2)+
					"\t"+data.getString(3));
		}
		}catch(SQLException e) {
	    }
	}
	public void reporte1(String reporte ,String consulta) {
		try {
			con=conectarBD();
			String sql=consulta;
			String ruta="src/reportes/"+reporte+".jrxml";
			JasperDesign jd=JRXmlLoader.load(ruta);
			JRDesignQuery jrdq=new JRDesignQuery();
			jrdq.setText(sql);
			jd.setQuery(jrdq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr,null,con);
			JasperViewer.viewReport(jp,false);
        }catch(Exception e3) {
        	JOptionPane.showMessageDialog(null,"Error al generar reporte");
        }
	}
	public void reporte2(String reporte ,String consulta,int cod) {
		try {
			con=conectarBD();
			String sql=consulta;
			String ruta="src/reportes/"+reporte+".jrxml";
			JasperDesign jd=JRXmlLoader.load(ruta);
			Map<String,Object> map=new HashMap<String,Object>();
			map.clear();
			map.put("cod",cod);
			JRDesignQuery jrdq=new JRDesignQuery();
			jrdq.setText(sql);
			jd.setQuery(jrdq);
			JasperReport jr=JasperCompileManager.compileReport(jd);
			JasperPrint jp=JasperFillManager.fillReport(jr,map,con);
			JasperViewer.viewReport(jp,false);
        }catch(Exception e3) {
        	JOptionPane.showMessageDialog(null,"Error al generar reporte");
        }
	}
}
