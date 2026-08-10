package modelo;

import java.util.List;

public class TestUsuarioDAO {
    public static void main(String[] args) {
         // obtener todos los usuarios 
          UsuarioDAO usuarioDAO = new UsuarioDAO();
         List<usuario> usuarios = usuarioDAO.obtenerUsuarios();
         for (usuario usuario : usuarios) {
             System.out.println("ID: " + usuario.getIdUsuario() );
             System.out.println("Nombre: " + usuario.getNombre());
             System.out.println("Apellido Paterno: " + usuario.getApellidoPaterno());
                System.out.println("Apellido Materno: " + usuario.getApellidoMaterno());
                System.out.println("Correo: " + usuario.getCorreo());
            System.out.println("---------------------------");
         }
         //imprimir el mensaje devuelto por el procedimiento almacenado
         System.out.println(usuarioDAO.getMensaje());


        //UsuarioDAO usuarioDAO = new UsuarioDAO();

        // agregar un nuevo usuario al objeto UsuarioDAO
        /* 
        usuarioDAO.setNombre("Juan");
        usuarioDAO.setApellidoPaterno("Pérez");
        usuarioDAO.setApellidoMaterno("Gómez");
        usuarioDAO.setCorreo("25301492@uttt.edu.mx");

        //Llamar al método agregarUsuario para insertar el usuario en la base de datos
        boolean exito = usuarioDAO.agregarUsuario(usuarioDAO);
        if (!exito) {
            System.out.println("Usuario agregado correctamente.");
        } else {
            System.out.println("Error al agregar el usuario.");
        }
            */

       

    }   
}
