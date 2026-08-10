package vista;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class VistaUsuario extends JFrame {
    //Atributos
    public JPanel encabezadoPanel;
    public JPanel contenidoPanel;
    public JPanel formularioPanel;

    public JLabel lblTituloUsuario;
    public JButton btnAgregarUsuario;
    public JButton btnEditarUsuario;
    public JButton btnEliminarUsuario;
    public JButton btnBuscarUsuario;
    public JButton btnSalir;
    public JTextField txtBuscarUsuario;

    public JLabel lblIdUsuario;
    public JLabel lblNombre;
    public JLabel lblApellidoPaterno;
    public JLabel lblApellidoMaterno;
    public JLabel lblCorreo;
    public JTextField txtIdUsuario;
    public JTextField txtNombre;
    public JTextField txtApellidoPaterno;
    public JTextField txtApellidoMaterno;
    public JTextField txtCorreo;

    public JTable tablaUsuarios;
    public DefaultTableModel modeloTablaUsuarios;


    //constructor vacio
    public VistaUsuario() {
        //configuracion de la ventana
        initComponents();
    }//Final del constructor

    //Metodo para inicializar los componentes de la ventana
    private void initComponents() {
        setTitle("Gestión de Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);//centrar la ventana
        //agregar  layout y componentes aqui segun sea necesario
        setLayout(null); // Usar un layout nulo para posicionamiento absoluto
        //Inicializar el panel de encabezado
        initEncabezadoPanel();
        //Inicializar el panel de formulario
        initFormularioPanel();
        //Inicializar el panel de contenido
        initContenidoPanel();
        //Cargar los datos de la tabla de usuarios desde la base de datos
        cargarDatosTablaUsuarios();
    }//Final de initComponents

    //Metodo para el panel de encabezado, que incluya los botones para agregar, editar y eliminar y buscar usuarios
    private void initEncabezadoPanel() {
      //crear los objetos de los componentes del panel de encabezado
        encabezadoPanel = new JPanel();
        lblTituloUsuario = new JLabel("Gestión de Usuarios");
        btnAgregarUsuario = new JButton("Agregar Usuario");
        btnEditarUsuario = new JButton("Editar Usuario");
        btnEliminarUsuario = new JButton("Eliminar Usuario");
        btnBuscarUsuario = new JButton("Buscar Usuario");
        txtBuscarUsuario = new JTextField(20);
        btnSalir = new JButton("Salir");

        //Agrega Layaout y comopnentes aqui segun sea necesario 
        encabezadoPanel.setBounds(0, 0, 800, 100);
        encabezadoPanel.setLayout(null);
        lblTituloUsuario.setBounds(10, 10, 200, 30);
        btnAgregarUsuario.setBounds(10, 50, 150, 30);
        btnEditarUsuario.setBounds(170, 50, 150, 30);
        btnEliminarUsuario.setBounds(330, 50, 150, 30);
        txtBuscarUsuario.setBounds(490, 50, 150, 30);
        btnBuscarUsuario.setBounds(650, 50, 120, 30);
        btnSalir.setBounds(650, 10, 120, 30);

        //Agregar los componentes al panel de encabezado
        encabezadoPanel.add(lblTituloUsuario);
        encabezadoPanel.add(btnAgregarUsuario);
        encabezadoPanel.add(btnEditarUsuario);
        encabezadoPanel.add(btnEliminarUsuario);
        encabezadoPanel.add(txtBuscarUsuario);
        encabezadoPanel.add(btnBuscarUsuario);
        encabezadoPanel.add(btnSalir);

        //Agregar color fondo azul claro al panel de encabezado
        encabezadoPanel.setBackground(new java.awt.Color(173, 216, 230));
        //Agregar el panel de encabezado a la ventana
        add(encabezadoPanel);

    }//Final de initEncabezadoPanel

    //Metodo para el panel del formulario
    private void initFormularioPanel() {
        //crear los objetos de los componentes del panel de formulario
        formularioPanel = new JPanel();
        lblIdUsuario = new JLabel("ID Usuario:");
        lblNombre = new JLabel("Nombre:");
        lblApellidoPaterno = new JLabel("Apellido Paterno:");
        lblApellidoMaterno = new JLabel("Apellido Materno:");
        lblCorreo = new JLabel("Correo:");
        txtIdUsuario = new JTextField(20);
        txtNombre = new JTextField(20);
        txtApellidoPaterno = new JTextField(20);
        txtApellidoMaterno = new JTextField(20);
        txtCorreo = new JTextField(20);
        //Agregar layout y componentes aqui segun sea necesario
        formularioPanel.setBounds(0, 100, 300, 500);    
        formularioPanel.setLayout(null);
        lblIdUsuario.setBounds(10, 10, 100, 30);
        txtIdUsuario.setBounds(120, 10, 150, 30);
        lblNombre.setBounds(10, 50, 100, 30);
        txtNombre.setBounds(120, 50, 150, 30);
        lblApellidoPaterno.setBounds(10, 90, 100, 30);
        txtApellidoPaterno.setBounds(120, 90, 150, 30);
        lblApellidoMaterno.setBounds(10, 130, 100, 30);
        txtApellidoMaterno.setBounds(120, 130, 150, 30);
        lblCorreo.setBounds(10, 170, 100, 30);
        txtCorreo.setBounds(120, 170, 150, 30);
        //Agregar los componentes al panel de formulario
        formularioPanel.add(lblIdUsuario);
        formularioPanel.add(txtIdUsuario);
        formularioPanel.add(lblNombre);
        formularioPanel.add(txtNombre);
        formularioPanel.add(lblApellidoPaterno);
        formularioPanel.add(txtApellidoPaterno);
        formularioPanel.add(lblApellidoMaterno);
        formularioPanel.add(txtApellidoMaterno);
        formularioPanel.add(lblCorreo);
        formularioPanel.add(txtCorreo);
       //Agregar color fondo azul claro al panel de encabezado
        formularioPanel.setBackground(new java.awt.Color(173, 216, 230));
        //Agregar el panel de formulario a la ventana
        add(formularioPanel);
        

        
    }//Final
    //Metodo para el panel de contenido, que incluya la tabla de usuarios
    private void initContenidoPanel() {
        //crear los objetos de los componentes del panel de contenido
        contenidoPanel = new JPanel();
        tablaUsuarios = new JTable();
        modeloTablaUsuarios = new DefaultTableModel();
        //Agregar layout y componentes aqui segun sea necesario
        contenidoPanel.setBounds(300, 100, 500, 500);
        contenidoPanel.setLayout(null);
        tablaUsuarios.setModel(modeloTablaUsuarios);
        //Agregar las columnas a la tabla de usuarios
        modeloTablaUsuarios.addColumn("ID Usuario");
        modeloTablaUsuarios.addColumn("Nombre");
        modeloTablaUsuarios.addColumn("Apellido Paterno");
        modeloTablaUsuarios.addColumn("Apellido Materno");
        modeloTablaUsuarios.addColumn("Correo");
        //Agregar las filas a la tabla de usuarios
        modeloTablaUsuarios.addRow(new Object[]{"1", "Juan", "Pérez", "Gómez", "juan.perez@email.com"});
        //Asinar el modelo ala tabla 
        tablaUsuarios.setModel(modeloTablaUsuarios);
        //Agregar la tabla de usuarios al panel de contenido
        tablaUsuarios.setBounds(10, 10, 480, 480);
        contenidoPanel.add(tablaUsuarios);
        //Agregar color fondo azul claro al panel de encabezado
        contenidoPanel.setBackground(new java.awt.Color(173, 216, 230));
        //Agregar el panel de contenido a la ventana
        add(contenidoPanel);
         

    }//Final de initContenidoPanel
    //Metodo para cargar los datos de la tabla de usuarios desde la base de datos
    public void cargarDatosTablaUsuarios() {
        //Limipiar la tabla antes de cargar los datos
        modeloTablaUsuarios.setRowCount(0);
        //crear una instancia de UsuarioDAO para obtener los datos de la base de datos
        modelo.UsuarioDAO usuarioDAO = new modelo.UsuarioDAO();
        //obtener la lista de usuarios desde la base de datos
        java.util.List<modelo.usuario> listaUsuarios = usuarioDAO.obtenerUsuarios();
        //Recorrer la lista de usuarios y agregarlos a la tabla
        for (modelo.usuario usuario : listaUsuarios) {
            modeloTablaUsuarios.addRow(new Object[]{
                usuario.getIdUsuario(),
                usuario.getNombre(),
                usuario.getApellidoPaterno(),
                usuario.getApellidoMaterno(),
                usuario.getCorreo()
            });
        }
        //agregar el modelo de la tabla a la tabla de usuarios
        tablaUsuarios.setModel(modeloTablaUsuarios);
    }
    //Metodo main para ejecutar la ventana
    public static void main(String[] args) {
        //Crear una instancia de VistaUsuario y hacerla visible
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VistaUsuario().setVisible(true);
            }
        });
    }//Final del metodo main
    
}//Final de la clase VistaUsuario
