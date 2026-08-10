package modelo;

public interface interfaceCRUDusuario {
    //Metodos para el CRUD de usuario
    public boolean agregarUsuario(usuario usuario);
    public boolean actualizarUsuario(usuario usuario);
    public boolean eliminarUsuario(int idUsuario);
   // metodo para obtener un usuario por su id
    public usuario obtenerUsuario(int idUsuario);
    //metodo para obtener todos los usuarios
    public java.util.List<usuario> obtenerUsuarios();

    
}
