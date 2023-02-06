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

public class ALMACEN extends JFrame {

	private JPanel contentPane;
	private JTextField textAlmacen;
	private JTextField textDistrito;
	private JTextField textDireccion;
    Inventario x=new Inventario();
    private JTextField textBCodigo;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ALMACEN frame = new ALMACEN();
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
	public ALMACEN() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 492, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("CODIGO ");
		lblNewLabel.setBounds(125, 36, 66, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DISTRITO");
		lblNewLabel_1.setBounds(125, 77, 86, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("DIRECCION");
		lblNewLabel_2.setBounds(123, 118, 86, 14);
		contentPane.add(lblNewLabel_2);
		
		textAlmacen = new JTextField();
		textAlmacen.setBounds(221, 32, 116, 20);
		contentPane.add(textAlmacen);
		textAlmacen.setColumns(10);
		
		textDistrito = new JTextField();
		textDistrito.setBounds(221, 74, 116, 20);
		contentPane.add(textDistrito);
		textDistrito.setColumns(10);
		
		textDireccion = new JTextField();
		textDireccion.setBounds(219, 115, 118, 20);
		contentPane.add(textDireccion);
		textDireccion.setColumns(10);
		
		JTextArea textReporte = new JTextArea();
		textReporte.setBounds(31, 159, 396, 125);
		contentPane.add(textReporte);
		
		JButton btnNewButton = new JButton("Registrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cod=Integer.parseInt(textAlmacen.getText());
				String dist=textDistrito.getText();
				String direc=textDireccion.getText();
				boolean w=x.registrar_alm(cod,dist, direc);
				if(w==true) {
					JOptionPane.showMessageDialog(null,"Nuevo Almacen Registrado Correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error al ingresar almacen");
				}	
			}
		});
		btnNewButton.setBounds(45, 307, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Buscar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textReporte.setText("Codigo \tDistrito\tDirecciòn");
				int cod =Integer.parseInt(textAlmacen.getText());
				ResultSet data=x.consulta("select * from almacen where almc_cod='"+cod+"'");
				try {
				while(data.next()) {
					textReporte.append("\n"+data.getInt(1)+"\t"+data.getString(2)+
							"\t"+data.getString(3));
				}
				}catch(SQLException e2) {
			    }
			}
		});
		btnNewButton_1.setBounds(170, 307, 99, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Imprimir");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				x.imprimir(textReporte);
			}
		});
		btnNewButton_2.setBounds(309, 307, 101, 23);
		contentPane.add(btnNewButton_2);
		
		JLabel lblNewLabel_3 = new JLabel("ALMACEN");
		lblNewLabel_3.setBounds(180, 11, 99, 14);
		contentPane.add(lblNewLabel_3);
		
		JButton btnReporte1 = new JButton("Ver Reporte");
		btnReporte1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Inventario x=new Inventario();
				x.reporte1("ReporteA1","Select * from inventario.almacen ");
			}
		});
		btnReporte1.setBounds(170, 352, 148, 23);
		contentPane.add(btnReporte1);
		
		JLabel lblNewLabel_4 = new JLabel("Ingrese Codigo");
		lblNewLabel_4.setBounds(37, 401, 86, 14);
		contentPane.add(lblNewLabel_4);
		
		textBCodigo = new JTextField();
		textBCodigo.setBounds(148, 398, 128, 20);
		contentPane.add(textBCodigo);
		textBCodigo.setColumns(10);
		
		JButton btnNewButton_3 = new JButton("Ver Reporte (2)");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int cod=Integer.parseInt(textBCodigo.getText());
				x.reporte2("ReporteA2","SELECT almacen.almc_cod,almc_dist,almc_direc,"
						+ "prod_nom,prod_stock,prod_prec"
						+ " FROM inventario.almacen INNER JOIN inventario.producto"
						+ " ON inventario.almacen.almc_cod=inventario.producto.almc_cod "
						+ "where almacen.almc_cod=$P{cod}",cod);
			}
		});
		btnNewButton_3.setBounds(293, 397, 148, 23);
		contentPane.add(btnNewButton_3);
	}

}
