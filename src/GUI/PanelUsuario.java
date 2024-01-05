package GUI;

import Entidades.Cuenta;
import Entidades.Usuario;
import Service.CuentaService;
import Service.ServiceException;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.*;

public class PanelUsuario extends JPanel {

    private JPanel panelBotonera;
    JButton jButtonCuentas;
    JButton jButtonTarjetas;
    JButton jButtonTransferencias;
    JButton jButtonSalir;

    PanelManager panelManager;

    public void armarPanelUsuario(PanelManager panelManager){

        this.panelManager = panelManager;
        panelBotonera = new JPanel();
        jButtonCuentas = new JButton("Cuentas");
        jButtonTarjetas = new JButton("Tarjetas");
        jButtonTransferencias = new JButton("Transferencias");
        jButtonSalir = new JButton("Salir");

        panelBotonera.setLayout(new GridLayout(4, 1));

        panelBotonera.add(jButtonCuentas);
        panelBotonera.add(jButtonTarjetas);
        panelBotonera.add(jButtonTransferencias);
        panelBotonera.add(jButtonSalir);

        setLayout(new BorderLayout());

        add(panelBotonera, BorderLayout.CENTER);

        panelBotonera.setSize(1000, 1000);
        panelBotonera.setBounds(100, 100, 200, 50);
        setVisible(true);



        jButtonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarLoginUsuario(panelManager);
            }
        });

        jButtonCuentas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int DNIbuscar = (int) j.getValueAt(jtable.getSelectedRow(), 0);
                Cuenta cuenta = new Cuenta();
                Usuario usuario = new Usuario();
                CuentaService cuentaService = new CuentaService();
                try {
                    cuenta = cuentaService.buscarCuenta(cuenta.getDNI_PROP());
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                }

                panelManager.mostrarPanelListaCuentaUsuario(cuenta, usuario);

            }
        });


    }

    public void armarPosta(){
        removeAll();
        armarPanelUsuario(panelManager);
        validate();
        repaint();
    }

}
