package GUI;

import Entidades.Cuenta;
import Entidades.Usuario;
import Service.CuentaService;
import Service.ServiceException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListaCuentaUsuario extends JPanel {
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    JButton jButtonEliminar;
    JButton jButtonNuevo;
    JButton jButtonSalir;
    private JPanel panelBotonera;

    private PanelManager panelManager;

    Usuario usuario = new Usuario();
    Cuenta cuenta = new Cuenta();
    private int DNI;

    public void armarPanelListaCuentaUsuario(Usuario usuario){
        this.usuario = usuario;
    }

    public void armarPanelListaCuentaUsuario(Cuenta cuenta){
        this.cuenta = cuenta;
    }


    public void armarPanelListaCuentaUsuario(PanelManager panelManager){
        this.panelManager = panelManager;
        setLayout(new BorderLayout());

        //Grilla
        contenidoTabla = new DefaultTableModel();
        jTable = new JTable(contenidoTabla);
        jScrollPane = new JScrollPane(jTable);
        jScrollPane.setViewportView(jTable);

        contenidoTabla.addColumn("CBU");
        contenidoTabla.addColumn("Alias");
        contenidoTabla.addColumn("Credito");
        contenidoTabla.addColumn("Debito");
        contenidoTabla.addColumn("Tipo de Cuenta");
        contenidoTabla.addColumn("DNI Propietario");

        CuentaService cuentaService = new CuentaService();
        Cuenta cuentas = null;
        /*
        try {
            cuentas = cuentaService.buscarCuenta(cuenta.getDNI_PROP());
        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
        }*/

            DNI = cuenta.getDNI_PROP();
            try {
            cuenta = cuentaService.buscarCuenta(DNI);
            } catch (ServiceException ex) {
            ex.printStackTrace();
            }

            cuenta = new Cuenta();
            //Cuenta cu = new Cuenta();
            Object [] row = new Object[6];
            row[0] = cuenta.getCBU();
            row[1] = cuenta.getAlias();
            row[2] = cuenta.getCredito();
            row[3]= cuenta.getDebito();
            row[4] = cuenta.getTipo();
            row[5] = cuenta.getDNI_PROP();
            contenidoTabla.addRow(row);



        /*
        if (usuario.getDNI() != 0)
        {Object [] row = new Object[6];
            row[0] = cuenta.getCBU();
            row[1] = cuenta.getAlias();
            row[2] = cuenta.getCredito();
            row[3]= cuenta.getDebito();
            row[4] = cuenta.getTipo();
            row[5] = cuenta.getDNI_PROP();
            contenidoTabla.addRow(row);
        }*/

        /*
        panelBotonera = new JPanel();
        jButtonEliminar = new JButton("Eliminar");
        jButtonNuevo = new JButton("Nuevo");
        jButtonSalir = new JButton("Salir");
        panelBotonera.add(jButtonEliminar);
        panelBotonera.add(jButtonNuevo);
        panelBotonera.add(jButtonSalir);*/

        add(jScrollPane, BorderLayout.CENTER);
        //add(panelBotonera, BorderLayout.SOUTH);

        //int DNIbuscar = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
        //Cuenta cuenta = null;
        //Usuario usuario = new Usuario();

        /*
        jButtonEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CuentaService cuentaService = new CuentaService();

                int CBUeliminar = (int) jTable.getValueAt(jTable.getSelectedRow(), 0);
                try {
                    cuentaService.eliminarCuenta(CBUeliminar);
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al eliminar" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
                }
                refrezcarListado();
            }
        });



        jButtonNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarFormularioCuenta();
            }
        });

        jButtonSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelManager.mostrarPanelAdmin(panelManager);
            }
        });*/

    }

    public void refrezcarListado()
    {
        removeAll();
        armarPanelListaCuentaUsuario(panelManager);
        validate();
        repaint();
    }

}
