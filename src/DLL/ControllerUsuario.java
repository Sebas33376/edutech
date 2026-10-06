package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import repository.Hashing;

public class ControllerUsuario {
	
	private static Connection conexion = Conexion.getInstance().getConnection();

	public <T> T login(String email, String password) {

		T usuario = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT * CASE WHEN p.id IS NOT NULL THEN 'PROFESOR' WHEN a.id IS NOT NULL THEN 'ALUMNO' ELSE 'ADMIN' END AS rol FROM usuarios LEFT JOIN profesores p ON u.id = p.id LEFT JOIN alumnos a ON u.id = a.id WHERE u.email = ? AND u.activo = TRUE;");
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				
				int id  = rs.getInt("id");
				String userName = rs.getString("userName");
				String nombre = rs.getString("nombre");
				String apellido = rs.getString("apellido");
				String contrasena = rs.getString("contrasena");
				boolean avtivo = rs.getBoolean("activo");
				
				if (Hashing.verificar(password, contrasena)) {
					
				}
				
			} else {
				JOptionPane.showMessageDialog(null, "Contraseña incorrecta");
			}
			
		}catch (Exception e){
			
		}
		
		return null;
	};
}
