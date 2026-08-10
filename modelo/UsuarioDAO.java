package modelo;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO extends usuario implements interfaceCRUDusuario {
    //Atributos
    private ConexionDB conexionDB;
    private CallableStatement callableStatement;
    private ResultSet resultSet;
    
    private ArrayList<usuario> listaUsuarios;
    private String mensaje; 

    //constructor
    public UsuarioDAO() {
        this.conexionDB = new ConexionDB();
        callableStatement = null;
        resultSet = null;
        this.listaUsuarios = new ArrayList<>();
        this.mensaje = "";
    }
//metodo getter and setter

    public String getMensaje() {
        return mensaje;
    }


    private void cerrarRecursos() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar ResultSet: " + e.getMessage());
        }

        try {
            if (callableStatement != null) {
                callableStatement.close();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar CallableStatement: " + e.getMessage());
        }

        conexionDB.desconectar();
    }

    private usuario mapearUsuario(ResultSet rs) throws Exception {
        usuario usuarioEncontrado = new usuario();
        usuarioEncontrado.setIdUsuario(rs.getInt("idUsuario"));
        usuarioEncontrado.setNombre(rs.getString("nombreUsuario"));
        usuarioEncontrado.setApellidoPaterno(rs.getString("apPaternoUsuario"));
        usuarioEncontrado.setApellidoMaterno(rs.getString("apMaternoUsuario"));
        usuarioEncontrado.setCorreo(rs.getString("email"));
        return usuarioEncontrado;
    }

    @Override
    public boolean agregarUsuario(usuario usuario) {
       //validar si exite la conexion ala base de datos
        if (conexionDB.conectar()) {
            try {
               //preparar la llamada al procedimiento almacenado
                callableStatement = conexionDB.getConexion().prepareCall("call bd_sistema_login.sp_insertar_Usuario(?, ?, ?, ?)");
                //Establecer los parametros del procedimiento almacenado
                callableStatement.setString(1, usuario.getNombre());
                callableStatement.setString(2, usuario.getApellidoPaterno());
                callableStatement.setString(3, usuario.getApellidoMaterno());
                callableStatement.setString(4, usuario.getCorreo());
                //Ejecutar el procedimiento almacenado y obtener el resultado
                int filasAfectadas = callableStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    mensaje = "Usuario agregado correctamente.";
                    return true;
                } else {
                    mensaje = "No se pudo agregar el usuario.";
                }
            } catch (Exception e) {
                mensaje = "Error al agregar el usuario: " + e.getMessage();
            
        }
        return false;
    } else {
            mensaje = "Error al conectar a la base de datos.";
            return false;
        }

    }

    @Override
    public boolean actualizarUsuario(usuario usuario) {
        if (conexionDB.conectar()) {
            try {
                callableStatement = conexionDB.getConexion().prepareCall("call bd_sistema_login.sp_modificar_Usuario(?, ?, ?, ?, ?)");
                callableStatement.setInt(1, usuario.getIdUsuario());
                callableStatement.setString(2, usuario.getNombre());
                callableStatement.setString(3, usuario.getApellidoPaterno());
                callableStatement.setString(4, usuario.getApellidoMaterno());
                callableStatement.setString(5, usuario.getCorreo());

                int filasAfectadas = callableStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    mensaje = "Usuario actualizado correctamente.";
                    return true;
                } else {
                    mensaje = "No se pudo actualizar el usuario.";
                }
            } catch (Exception e) {
                mensaje = "Error al actualizar el usuario: " + e.getMessage();
            } finally {
                cerrarRecursos();
            }
            return false;
        } else {
            mensaje = "Error al conectar a la base de datos.";
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(int idUsuario) {
        if (conexionDB.conectar()) {
            try {
                callableStatement = conexionDB.getConexion().prepareCall("call bd_sistema_login.sp_eliminar_Usuario(?");
                callableStatement.setInt(1, idUsuario);

                int filasAfectadas = callableStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    mensaje = "Usuario eliminado correctamente.";
                    return true;
                } else {
                    mensaje = "No se pudo eliminar el usuario.";
                }
            } catch (Exception e) {
                mensaje = "Error al eliminar el usuario: " + e.getMessage();
            } finally {
                cerrarRecursos();
            }
            return false;
        } else {
            mensaje = "Error al conectar a la base de datos.";
            return false;
        }
    }

    @Override
    public usuario obtenerUsuario(int idUsuario) {
        usuario usuarioEncontrado = null;

        if (conexionDB.conectar()) {
            try {
                callableStatement = conexionDB.getConexion().prepareCall("call bd_sistema_login.sp_consultarid(?);");
                callableStatement.setInt(1, idUsuario);
                resultSet = callableStatement.executeQuery();

                if (resultSet.next()) {
                    usuarioEncontrado = mapearUsuario(resultSet);
                    mensaje = "Usuario obtenido correctamente.";
                } else {
                    mensaje = "No se encontró el usuario solicitado.";
                }
            } catch (Exception e) {
                mensaje = "Error al obtener el usuario: " + e.getMessage();
            } finally {
                cerrarRecursos();
            }
        } else {
            mensaje = "Error al conectar a la base de datos.";
        }

        return usuarioEncontrado;
    }

    @Override
    public List<usuario> obtenerUsuarios() {
        listaUsuarios.clear();

        if (conexionDB.conectar()) {
            try {
                callableStatement = conexionDB.getConexion().prepareCall("call bd_sistema_login.sp_consultar()");
                resultSet = callableStatement.executeQuery();

                while (resultSet.next()) {
                    listaUsuarios.add(mapearUsuario(resultSet));
                }

                if (!listaUsuarios.isEmpty()) {
                    mensaje = "Usuarios obtenidos correctamente.";
                } else {
                    mensaje = "No hay usuarios registrados.";
                }
            } catch (Exception e) {
                mensaje = "Error al obtener los usuarios: " + e.getMessage();
            } finally {
                cerrarRecursos();
            }
        } else {
            mensaje = "Error al conectar a la base de datos.";
        }

        return listaUsuarios;
    }


}
