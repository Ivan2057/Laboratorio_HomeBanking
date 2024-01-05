package GUI;

import Entidades.Usuario;
import Service.ServiceException;
import Service.UsuarioService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioUsuarios extends PanelFormulario {

    //Componentes
    private JPanel panelComponentes;
    JLabel jLabelDNI;
    JLabel jLabelNombre;
    JLabel jLabelApellido;
    JLabel jLabelUsuario;
    JLabel jLabelPassword;
    JTextField jTextFieldDNI;
    JTextField jTextFieldNombre;
    JTextField jTextFieldApellido;
    JTextField jTextFieldUsuario;
    JTextField jTextFieldPassword;

    //Botones
    private JPanel panelBotonera;
    JButton jButtonGuardar;
    JButton jButtonCancelar;

    private boolean modificacion;

    PanelManager panelManager;

    public void armarPanelFormularioUsuario(Usuario usuario){

        jTextFieldDNI.setText(String.valueOf(usuario.getDNI()));
        jTextFieldNombre.setText(usuario.getNombre());
        jTextFieldApellido.setText(usuario.getApellido());
        jTextFieldUsuario.setText(usuario.getUsuario());
        jTextFieldPassword.setText(usuario.getPassword());
        modificacion = true;
    }


    public void vaciarComponentes(){
        jTextFieldDNI.setText("");
        jTextFieldNombre.setText("");
        jTextFieldApellido.setText("");
        jTextFieldUsuario.setText("");
        jTextFieldPassword.setText("");
        modificacion = false;
    }


    public void armarPanelFormulario(PanelManager panelManager){

        this.panelManager = panelManager;
        jLabelDNI = new JLabel("DNI: ");
        jLabelNombre = new JLabel("Nombre: ");
        jLabelApellido = new JLabel("Apellido: ");
        jLabelUsuario = new JLabel("Nombre de Usuario: ");
        jLabelPassword = new JLabel("Contraseña: ");
        jTextFieldDNI = new JTextField(20);
        jTextFieldNombre = new JTextField(20);
        jTextFieldApellido = new JTextField(20);
        jTextFieldUsuario = new JTextField(20);
        jTextFieldPassword = new JTextField(20);

        panelComponentes = new JPanel(new GridLayout(5,2));
        panelComponentes.add(jLabelDNI);
        panelComponentes.add(jTextFieldDNI);
        panelComponentes.add(jLabelNombre);
        panelComponentes.add(jTextFieldNombre);
        panelComponentes.add(jLabelApellido);
        panelComponentes.add(jTextFieldApellido);
        panelComponentes.add(jLabelUsuario);
        panelComponentes.add(jTextFieldUsuario);
        panelComponentes.add(jLabelPassword);
        panelComponentes.add(jTextFieldPassword);

        setLayout(new BorderLayout());
        add(panelComponentes, BorderLayout.CENTER);


        panelBotonera = new JPanel();
        jButtonGuardar = new JButton("Guardar");
        jButtonCancelar = new JButton("Cancelar");

        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);

        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario usuario = new Usuario();
                usuario.setDNI(Integer.parseInt(jTextFieldDNI.getText()));
                usuario.setNombre(jTextFieldNombre.getText());
                usuario.setApellido(jTextFieldApellido.getText());
                usuario.setUsuario(jTextFieldUsuario.getText());
                usuario.setPassword(jTextFieldPassword.getText());

                UsuarioService usuarioService = new UsuarioService();
                try {
                    if(modificacion){
                        usuarioService.modificarUsuario(usuario);
                    }else {
                        usuarioService.guardarUsuario(usuario);
                    }
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
                }

                panelManager.mostrarListaUsuario();
            }
        });

        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarListaUsuario();
            }
        });




    }

}
