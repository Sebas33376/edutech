package repository;

public interface Validaciones {

	public static boolean noVacio(String texto) {
		return texto != null && !texto.trim().isEmpty();
	}

	public static boolean esNombre(String texto) {
		if (!noVacio(texto)) {
			return false;
		}
		String t = texto.trim();
		if (t.length() < 2 || t.length() > 40) {
			return false;
		}
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			if (!Character.isLetter(c) && c != ' ' && c != '-' && c != '\'') {
				return false;
			}
		}
		return true;
	}

	public static boolean esProfesion(String texto) {
		if (!noVacio(texto)) {
			return false;
		}
		String t = texto.trim();
		if (t.length() < 3 || t.length() > 50) {
			return false;
		}
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			if (!Character.isLetter(c) && c != ' ' && c != '.' && c != '-') {
				return false;
			}
		}
		return true;
	}

	public static boolean esUserName(String texto) {
		if (!noVacio(texto)) {
			return false;
		}
		String t = texto.trim();
		if (t.length() < 4 || t.length() > 20) {
			return false;
		}
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			if (!Character.isLetterOrDigit(c) && c != '_' && c != '.') {
				return false;
			}
		}
		return true;
	}

	public static boolean esEmail(String texto) {
		if (!noVacio(texto)) {
			return false;
		}
		String t = texto.trim();
		if (t.length() > 100 || t.indexOf(' ') != -1) {
			return false;
		}
		int arroba = t.indexOf('@');
		if (arroba < 1 || arroba != t.lastIndexOf('@')) {
			return false;
		}
		String dominio = t.substring(arroba + 1);
		int punto = dominio.lastIndexOf('.');
		return punto > 0 && punto < dominio.length() - 2 && dominio.indexOf("..") == -1;
	}

	public static boolean esContrasena(String texto) {
		if (texto == null || texto.length() < 8 || texto.length() > 30) {
			return false;
		}
		boolean hayLetra = false;
		boolean hayNumero = false;
		for (int i = 0; i < texto.length(); i++) {
			char c = texto.charAt(i);
			if (c == ' ') {
				return false;
			}
			if (Character.isLetter(c)) {
				hayLetra = true;
			}
			if (Character.isDigit(c)) {
				hayNumero = true;
			}
		}
		return hayLetra && hayNumero;
	}

	public static boolean coinciden(String texto, String repetido) {
		return texto != null && texto.equals(repetido);
	}

	public static boolean esPrecioValido(String texto) {
		if (!noVacio(texto)) {
			return false;
		}
		String t = texto.trim();
		int separadores = 0;
		int digitos = 0;
		for (int i = 0; i < t.length(); i++) {
			char c = t.charAt(i);
			if (c == ',' || c == '.') {
				separadores++;
			} else if (Character.isDigit(c)) {
				digitos++;
			} else {
				return false;
			}
		}
		if (separadores > 1 || digitos == 0) {
			return false;
		}
		double precio = convertirPrecio(t);
		return precio > 0 && precio <= 10000000;
	}


	public static double convertirPrecio(String texto) {
		return Double.parseDouble(texto.trim().replace(',', '.'));
	}

}

