package GUI;

import Entidades.Usuario;
import Service.ServiceException;
import Service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListaUsuarios extends JPanel {
    //Grilla
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    JButton jButtonEliminar;
    JButton jButtonModificar;
    JButton jButtonNuevo;
    JButton jButtonSalir;
    private JPanel panelBotonera;

    private PanelManager panelManager;

    public void armarPanelListaUsuario(PanelManager panelManager){
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        //Grilla
        contenidoTabla = new DefaultTableModel();
        jTable = new JTable(contenidoTabla);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setViewportView(jTable);

        contenidoTabla.addColumn("DNI");
        contenidoTabla.addColumn("Nombre");
        contenidoTabla.addColumn("Apellido");
        contenidoTabla.addColumn("Usuario");

        UsuarioService usuarioService = new UsuarioService();
        ArrayList usuarios = null;
        try {
            usuarios = usuarioService.buscarTodos();
        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
        }

        for(Object o : usuarios){
            Usuario us = (Usuario) o;
            Object [] row = new Object[4];
            row[0] = us.getDNI();
            row[1] = us.getNombre();
            row[2] = us.getApellido();
            row[3]= us.getUsuario();
            contenidoTabla.addRow(row);
        }

        //Botonera
        panelBotonera = new JPanel();
        jButtonEliminar = new JButton("Eliminar");
        jButtonModificar = new JButton("Modificar");
        jButtonNuevo = new JButton("Nuevo");
        jButtonSalir = new JButton("Salir");
        panelBotonera.add(jButtonEliminar);
        panelBotonera.add(jButtonModificar);
        panelBotonera.add(jButtonNuevo);
        panelBotonera.add(jButtonSalir);

        add(jScrollPane, BorderLayout.CENTER);
        add(panelBotonera, BorderLayout.SOUTH);


        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UsuarioService usuarioService = new UsuarioService();

                int DNIEliminar = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
                try {
                    usuarioService.eliminarUsuario(DNIEliminar);
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
                }
                refrezcarListado();
            }
        });

        jButtonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin(panelManager);
            }
        });

        jButtonModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int DNIModificar = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
                UsuarioService usuarioService = new UsuarioService();

                Usuario usuario = null;
                try {
                    usuario = usuarioService.buscarUsuario(DNIModificar);
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ha sucedido un error al traer un estudiante para modificarlo. Por favor contactar al administrador: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
                    panelManager.mostrarFormularioUsuario(usuario);
            }
        });

        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarFormularioUsuario();
            }
        });



    }

    public void refrezcarListado()
    {
        removeAll();
        armarPanelListaUsuario(panelManager);
        validate();
        repaint();
    }


}
