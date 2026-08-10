package modelo;

public class TestConexionDB {
    public static void main(String[] args) {
        ConexionDB conexionDB = new ConexionDB();
        if (conexionDB.conectar()) {
            System.out.println("Conexión exitosa a la base de datos.");
            conexionDB.desconectar();
        } else {
            System.out.println("Error al conectar a la base de datos.");
        }
    }
}
