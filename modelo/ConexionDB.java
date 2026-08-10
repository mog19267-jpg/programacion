package modelo;

public class ConexionDB {
  //Atributos
  private String url;
  private String usuario;
  private String contrasena;
  private java.sql.Connection conexion;
  //constructor
  public ConexionDB(String url, String usuario, String contrasena) {
    this.url = url;
    this.usuario = usuario;
    this.contrasena = contrasena;
  }
  //constructor vacio
  public ConexionDB() {
    //Inicializar con valores por defecto
    this.url = "jdbc:mysql://localhost:3306/bd_sistema_login";
    this.usuario = "root";
    this.contrasena = "root12345678";
    this.conexion = null;
  }   
  //setters y getters
   public String getUrl() {
    return url;
  }
  public void setUrl(String url) {
    this.url = url;
  }
  public String getUsuario() {
    return usuario;
  }
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }
  public String getContrasena() {
    return contrasena;
  }
  public void setContrasena(String contrasena) {
    this.contrasena = contrasena;
  }
  public java.sql.Connection getConexion() {
    return conexion;
  }
  public void setConexion(java.sql.Connection conexion) {
    this.conexion = conexion;
  }
  //Metodo para cerrar la conexion
  public boolean desconectar() {
    try {
      if (conexion != null && !conexion.isClosed()) {
        conexion.close();
      }
      return true;
    } catch (java.sql.SQLException e) {
      System.out.println("Error al desconectar de la base de datos: " + e.getMessage());
      return false;
    }
  }   
  //Metodo para establecer la conexion
  public boolean conectar() {
    try {
      //Cargar el driver de MySQL
      Class.forName("com.mysql.cj.jdbc.Driver");
      conexion = java.sql.DriverManager.getConnection(url, usuario, contrasena);
      return true;
    } catch (ClassNotFoundException | java.sql.SQLException e) {
      System.out.println("Error al conectar a la base de datos: " + e.getMessage());
      return false;
    }
  }
 
}
