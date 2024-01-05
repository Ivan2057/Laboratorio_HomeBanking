package GUI;

import DAO.DAOException;
import Entidades.InicioSesion;
import Entidades.Usuario;
import Service.InicioSesionService;
import Service.ServiceException;
import Service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;

public class PanelLogIn extends JPanel {

    private String DB_JDBC_DRIVER = "org.h2.Driver";
    private String DB_URL = "jdbc:h2:~/test";
    private String DB_USER ="sa";
    private String DB_PASSWORD = "";


    private JPanel panelComponentes;
    JLabel jLabelUsuario;
    JLabel jLabelPassword;
    JTextField jTextFieldUsuario;
    JPasswordField jPasswordField;
    JLabel jLabelMensaje;

    private JPanel panelBotonera;
    JButton jButtonUsuario;
    JButton jButtonAdmin;
    JButton jButtonCancelar;

    PanelManager panelManager;
    private boolean modificacion;

    public void vaciarComponentes(){
        jTextFieldUsuario.setText("");
        jPasswordField.setText("");
        modificacion = true;
    }

    public void armarLoginUsuarios(PanelManager panelManager){

        this.panelManager = panelManager;
        jLabelUsuario = new JLabel("Usuario: ");
        jLabelPassword = new JLabel("Password");
        jTextFieldUsuario = new JTextField(20);
        jPasswordField = new JPasswordField();
        jLabelMensaje = new JLabel();

        panelComponentes = new JPanel(new GridLayout(4, 1));
        panelComponentes.add(jLabelUsuario);
        panelComponentes.add(jTextFieldUsuario);
        panelComponentes.add(jLabelPassword);
        panelComponentes.add(jPasswordField);
        panelComponentes.add(jLabelMensaje);


        setLayout(new BorderLayout());
        add(panelComponentes, BorderLayout.CENTER);

        panelBotonera = new JPanel();
        jButtonUsuario = new JButton("Log In Usuario");
        jButtonAdmin = new JButton("Log In Admin");
        jButtonCancelar = new JButton("Cancelar");

        panelBotonera.add(jButtonUsuario);
        panelBotonera.add(jButtonAdmin);
        panelBotonera.add(jButtonCancelar);

        setSize(500, 500);
        setBounds(100, 100, 200, 50);

        add(panelBotonera, BorderLayout.SOUTH);

        /*
        jButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = jTextFieldUsuario.getText();
                String password = jPasswordField.getText();

                if (user.trim().equals("Administrador") && password.trim().equals("admin")) {
                    panelManager.mostrarPanelAdmin(panelManager);
                    vaciarComponentes();
                } else {
                   jLabelMensaje.setText(" Usuario Incorrecto");
                   vaciarComponentes();
                }

            }
        });*/

        jButtonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesionService inicioService = new InicioSesionService();
                InicioSesion inicio = new InicioSesion();
                //Profesor profesor= new Profesor();
                inicio.setUsuario(jTextFieldUsuario.getText());
                inicio.setPassword(String.valueOf(jPasswordField.getPassword()));
                try{
                    inicioService.ingresarAdmin(inicio);


                }catch (ServiceException serviceException) {
                    JOptionPane.showMessageDialog(null,"Ha sucesido un error al ingresar un usuario administrador." + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (inicio.isUsuarioCorrecto()){
                    panelManager.mostrarPanelAdmin(panelManager);
                    vaciarComponentes();

                }
                else{
                    JOptionPane.showMessageDialog(null,"La contraseña es incorrecta");
                    //panelManager.mostrarInicioSesion();
                    vaciarComponentes();
                };
            }

        });



        jButtonUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InicioSesionService inicioService = new InicioSesionService();
                UsuarioService usuarioService = new UsuarioService();
                InicioSesion inicio = new InicioSesion();
                Usuario usuario = new Usuario();
                //Profesor profesor= new Profesor();
                inicio.setUsuario(jTextFieldUsuario.getText());
                inicio.setPassword(String.valueOf(jPasswordField.getPassword()));
                try{
                    inicioService.ingresarUsuario(inicio);
                    //usuario= usuarioService.guardarUsuario(inicio.getUsuario());
                    //profesor = alumnoService.buscarDatos(alumno);


                }catch (ServiceException serviceException) {
                    JOptionPane.showMessageDialog(null,"Ha sucesido un error al ingresar un alumno. Por favor contactar al administrador:" + serviceException.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                if (inicio.isUsuarioCorrecto()){
                    panelManager.mostrarPanelUsuario();
                    vaciarComponentes();

                }
                else{
                    JOptionPane.showMessageDialog(null,"La contraseña es incorrecta");
                    //panelManager.mostrarInicioSesion();
                    vaciarComponentes();
                };
            }
        });


        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
            }
        });


        /*
        jButtonUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = jTextFieldUsuario.getText();
                String password = String.valueOf(jPasswordField.getPassword());
                Usuario usuario= new Usuario();
                UsuarioService usuarioService = new UsuarioService();


                ArrayList usuarios = null;
                try {
                    usuarios = usuarioService.buscarTodos();
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
                }



                if (user.trim().equals("Usuario") && password.trim().equals("hola")) {
                    panelManager.mostrarPanelUsuario();
                    vaciarComponentes();
                } else {
                    jLabelMensaje.setText("Usuario incorrecto");
                    vaciarComponentes();
                }

            }
        });*/





    }




}
