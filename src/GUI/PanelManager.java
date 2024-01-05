package GUI;

import Entidades.Cuenta;
import Entidades.Usuario;

import javax.swing.*;

public class PanelManager {

    private PanelListaUsuarios panelListaUsuarios;
    private PanelFormularioUsuarios panelFormularioUsuario;
    private PanelLogIn panelLogIn;
    private PanelUsuario panelUsuario;
    private PanelFormularioCuenta panelFormularioCuenta;
    private PanelListaCuenta panelListaCuenta;
    private PanelAdmin panelAdmin;
    private PanelListaCuentaUsuario panelListaCuentaUsuario;

    private JFrame frame;

    public void armarPanelManager(){
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panelLogIn = new PanelLogIn();
        panelLogIn.armarLoginUsuarios(this);

        panelUsuario = new PanelUsuario();
        panelUsuario.armarPanelUsuario(this);

        panelFormularioUsuario = new PanelFormularioUsuarios();
        panelFormularioUsuario.armarPanelFormulario(this);

        panelListaUsuarios = new PanelListaUsuarios();
        panelListaUsuarios.armarPanelListaUsuario(this);

        panelFormularioCuenta = new PanelFormularioCuenta();
        panelFormularioCuenta.armarPanelFormulario(this);

        panelListaCuenta = new PanelListaCuenta();
        panelListaCuenta.armarPanelListaCuenta(this);

        panelAdmin = new PanelAdmin();
        panelAdmin.armarPanelAdmin(this);

        panelListaCuentaUsuario = new PanelListaCuentaUsuario();
        panelListaCuentaUsuario.armarPanelListaCuentaUsuario(this);


        frame.setVisible(true);
        frame.pack();
    }

    public void mostrarLoginUsuario(PanelManager panelManager){
        panelLogIn.armarLoginUsuarios(panelManager);
        mostrar(panelLogIn);
    }

    public void mostrarPanelUsuario(PanelManager panelManager){
        panelUsuario.armarPanelUsuario(panelManager);
        mostrar(panelUsuario);
    }

    public void mostrarPanelUsuario(){
        panelUsuario.armarPosta();
        mostrar(panelUsuario);
    }

    public void mostrarFormularioUsuario(){
        panelFormularioUsuario.vaciarComponentes();
        mostrar(panelFormularioUsuario);
    }

    public void mostrarFormularioUsuario(Usuario usuario){
        panelFormularioUsuario.armarPanelFormularioUsuario(usuario);
        mostrar(panelFormularioUsuario);
    }

    public void mostrarListaUsuario(){
        panelListaUsuarios.refrezcarListado();
        mostrar(panelListaUsuarios);
    }

    public void mostrarFormularioCuenta(){
        panelFormularioCuenta.vaciarComponentes();
        mostrar(panelFormularioCuenta);
    }

    public void mostrarFormularioCuenta(Cuenta cuenta){
        panelFormularioCuenta.armarPanelFormularioCuenta(cuenta);
        mostrar(panelFormularioCuenta);
    }

    public void mostrarListaCuenta(){
        panelListaCuenta.refrezcarListado();
        mostrar(panelListaCuenta);
    }

    public void mostrarPanelAdmin(PanelManager panelManager){
        panelAdmin.armarPanelAdmin(panelManager);
        mostrar(panelAdmin);
    }

    public void mostrarPanelListaCuentaUsuario(Cuenta cuenta, Usuario usuario){
        panelListaCuentaUsuario.armarPanelListaCuentaUsuario(cuenta);
        panelListaCuentaUsuario.armarPanelListaCuentaUsuario(usuario);
        panelListaCuentaUsuario.refrezcarListado();
        mostrar(panelListaCuentaUsuario);
    }

    public void mostrarPanelListaCuentaUsuario(PanelManager panelManager){
        panelListaCuentaUsuario.armarPanelListaCuentaUsuario(panelManager);
        panelListaCuentaUsuario.refrezcarListado();
        mostrar(panelListaCuentaUsuario);
    }

    private void mostrar(JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
        frame.getContentPane().validate();
        frame.getContentPane().repaint();
        frame.pack();
    }
}
