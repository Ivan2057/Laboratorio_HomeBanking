package GUI;

import Entidades.Cuenta;
import Entidades.Usuario;
import Service.CuentaService;
import Service.ServiceException;
import Service.UsuarioService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PanelListaCuenta extends JPanel {

    //Grilla
    private JTable jTable;
    private DefaultTableModel contenidoTabla;
    private JScrollPane jScrollPane;

    JButton jButtonEliminar;
    JButton jButtonNuevo;
    JButton jButtonSalir;
    private JPanel panelBotonera;

    private PanelManager panelManager;

    public void armarPanelListaCuenta(PanelManager panelManager){
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
        ArrayList cuentas = null;
        try {
            cuentas = cuentaService.buscarTodos();
        } catch (ServiceException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar" + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
        }

        for(Object o : cuentas){
            Cuenta cu = (Cuenta) o;
            Object [] row = new Object[6];
            row[0] = cu.getCBU();
            row[1] = cu.getAlias();
            row[2] = cu.getCredito();
            row[3]= cu.getDebito();
            row[4] = cu.getTipo();
            row[5] = cu.getDNI_PROP();
            contenidoTabla.addRow(row);
        }

        //Botonera
        panelBotonera = new JPanel();
        jButtonEliminar = new JButton("Eliminar");
        jButtonNuevo = new JButton("Nuevo");
        jButtonSalir = new JButton("Salir");
        panelBotonera.add(jButtonEliminar);
        panelBotonera.add(jButtonNuevo);
        panelBotonera.add(jButtonSalir);

        add(jScrollPane, BorderLayout.CENTER);
        add(panelBotonera, BorderLayout.SOUTH);


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
        });

    }

    public void refrezcarListado()
    {
        removeAll();
        armarPanelListaCuenta(panelManager);
        validate();
        repaint();
    }


}
