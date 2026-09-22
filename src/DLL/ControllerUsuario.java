package DLL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerUsuario {
	
	private static Connection conexion = Conexion.getInstance().getConnection();

	public <T> T login(String email, String contrasena) {

		T usuario = null;
		try {
			PreparedStatement stmt = conexion.prepareStatement("SELECT * FROM usuario WHERE email = ?");
			stmt.setString(1, email);
			
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()){
				
			} else {
				
			}
			
		}catch (Exception e){
			
		}
		
		return null;
	};
}
