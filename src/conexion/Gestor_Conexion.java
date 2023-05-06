package conexion;

import java.io.File;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Gestor_Conexion {

	Connection ccn = null;
	Statement st = null;

	public Gestor_Conexion() {
		try {

			File docu = new File("src/conexion/Gestor_Hoteles.accdb");

			String rutafile = docu.getAbsolutePath();
			String Url = "jdbc:ucanaccess://" + rutafile;
			ccn = DriverManager.getConnection(Url);
			st = ccn.createStatement();
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null, "CONEXION ERRONEA " + e);
		}
	}

	public Connection getConnection() {
		return ccn;
	}

	public void Desconexion() {
		try {
			ccn.close();
			System.exit(0);
		} catch (SQLException ex) {
			Logger.getLogger(Gestor_Conexion.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

}
