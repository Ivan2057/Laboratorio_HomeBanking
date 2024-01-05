package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelAdmin extends JPanel {

    private JPanel panelBotonera;
    JButton jButtonUsuarios;
    JButton jButtonCuentas;
    JButton jButtonSalir;

    PanelManager panelManager;

    public void armarPanelAdmin(PanelManager panelManager){

        this.panelManager = panelManager;
        panelBotonera = new JPanel();
        jButtonUsuarios = new JButton("Usuarios");
        jButtonCuentas = new JButton("Cuentas");
        jButtonSalir = new JButton("Salir");

        panelBotonera.setLayout(new GridLayout(3, 1));

        panelBotonera.add(jButtonCuentas);
        panelBotonera.add(jButtonUsuarios);
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
                panelManager.mostrarListaCuenta();
            }
        });

        jButtonUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarListaUsuario();
            }
        });
    }

    public void armarPosta(){
        removeAll();
        armarPanelAdmin(panelManager);
        validate();
        repaint();
    }

}
