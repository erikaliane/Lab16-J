package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import datos.Inventario;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PRODUCTO extends JFrame {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textNombre;
	private JTextField textCantidad;
	private JTextField textPrecio;
	private JTextField textAlmacen;
    Inventario m=new Inventario();
    private JTextField textBuscCod;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PRODUCTO frame = new PRODUCTO();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PRODUCTO() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 509, 505);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CODIGO");
		lblNewLabel.setBounds(35, 38, 57, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("NOMBRE");
		lblNewLabel_1.setBounds(238, 38, 62, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("CANTIDAD");
		lblNewLabel_2.setBounds(26, 80, 66, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("ALMACEN");
		lblNewLabel_3.setBounds(26, 122, 71, 14);
		contentPane.add(lblNewLabel_3);
		
		textCodigo = new JTextField();
		textCodigo.setBounds(107, 35, 96, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);
		
		textNombre = new JTextField();
		textNombre.setBounds(310, 35, 120, 20);
		contentPane.add(textNombre);
		textNombre.setColumns(10);
		
		textCantidad = new JTextField();
		textCantidad.setBounds(107, 77, 96, 20);
		contentPane.add(textCantidad);
		textCantidad.setColumns(10);
		
		textPrecio = new JTextField();
		textPrecio.setBounds(310, 77, 120, 20);
		contentPane.add(textPrecio);
		textPrecio.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("PRECIO");
		lblNewLabel_4.setBounds(238, 80, 62, 14);
		contentPane.add(lblNewLabel_4);
		
		textAlmacen = new JTextField();
		textAlmacen.setBounds(107, 119, 96, 20);
		contentPane.add(textAlmacen);
		textAlmacen.setColumns(10);
		
		JTextArea textReporte = new JTextArea();
		textReporte.setBounds(26, 154, 439, 145);
		contentPane.add(textReporte);
		
		JLabel lblNewLabel_5 = new JLabel("PRODUCTO");
		lblNewLabel_5.setBounds(184, 11, 71, 14);
		contentPane.add(lblNewLabel_5);
		
		JButton btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cod_p=Integer.parseInt(textCodigo.getText());
				String nom=textNombre.getText();
				int can=Integer.parseInt(textCantidad.getText());
				double pre=Double.parseDouble(textPrecio.getText());
				int alm=Integer.parseInt(textAlmacen.getText());
				boolean w=m.registrar_prod(cod_p,nom,can,pre,alm);
				if(w==true) {
					JOptionPane.showMessageDialog(null," Producto Registrado Correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error al ingresar Producto");
				}	
				
			}
		});
		btnRegistrar.setBounds(22, 310, 112, 23);
		contentPane.add(btnRegistrar);
		
		JButton btnImprimir = new JButton("IMPRIMIR");
		btnImprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					textReporte.setText("Codigo \tNombre\tCantidad\tPrecio\tAlmacen");
					ResultSet data=m.consulta("select * from producto");
					try {
					while(data.next()) {
						textReporte.append("\n"+data.getInt(1)+"\t"+data.getString(2)+
								"\t"+data.getString(3)+"\t"+data.getDouble(4)+"\t"+data.getInt(5));
					}
					}catch(SQLException e1) {
					}
			}
		});
		btnImprimir.setBounds(338, 310, 89, 23);
		contentPane.add(btnImprimir);
		
		JButton btnBuscar = new JButton("BUSCAR");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textReporte.setText("Codigo \tNombre\tCantidad\tPrecio\tAlmacen");
				int codig =Integer.parseInt(textCodigo.getText());
				ResultSet data=m.consulta("select * from producto where prod_cod='"+codig+"'");
				try {
				while(data.next()) {
					textReporte.append("\n"+data.getInt(1)+"\t"+data.getString(2)+
							"\t"+data.getInt(3)+"\t"+data.getDouble(4)+"\t"+data.getInt(5));
				}
				}catch(SQLException e1) {
				}
			}
		});
		btnBuscar.setBounds(184, 310, 89, 23);
		contentPane.add(btnBuscar);
		
		JButton btnNewButton = new JButton("Ver Reporte");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventario x=new Inventario();
				x.reporte1("Reporte1", "Select * from inventario.producto");
			}
		});
		btnNewButton.setBounds(167, 356, 133, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_6 = new JLabel("Ingrese Codigo");
		lblNewLabel_6.setBounds(35, 400, 99, 14);
		contentPane.add(lblNewLabel_6);
		
		textBuscCod = new JTextField();
		textBuscCod.setBounds(144, 397, 129, 20);
		contentPane.add(textBuscCod);
		textBuscCod.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Ver Reporte (2)");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cod=Integer.parseInt(textBuscCod.getText());
				Inventario x=new Inventario();
				x.reporte2("Reporte2", "SELECT prod_cod,prod_nom,prod_stock,"
						+ "prod_prec,almc_direc,almc_dist FROM inventario.producto INNER JOIN inventario.almacen ON inventario.almacen.almc_cod=inventario.producto.almc_cod where prod_cod=$P{cod}", cod);
			}
		});
		btnNewButton_1.setBounds(310, 396, 120, 23);
		contentPane.add(btnNewButton_1);
	}
}
