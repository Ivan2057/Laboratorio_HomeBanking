package GUI;

import Entidades.Cuenta;
import Entidades.Usuario;
import Service.CuentaService;
import Service.ServiceException;
import Service.UsuarioService;
import org.h2.util.json.JSONByteArrayTarget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFormularioCuenta extends PanelFormulario{

    //Componentes
    private JPanel panelComponentes;
    JLabel jLabelCBU;
    JLabel jLabelAlias;
    JLabel jLabelCredito;
    JLabel jLabelDebito;
    JLabel jLabelTipo;
    JLabel jLabelDNI;
    JTextField jTextFieldCBU;
    JTextField jTextFieldAlias;
    JTextField jTextFieldCredito;
    JTextField jTextFieldDebito;
    JTextField jTextFieldTipo;
    JTextField jTextFieldDNI;

    //Botones
    private JPanel panelBotonera;
    JButton jButtonGuardar;
    JButton jButtonCancelar;

    private JPanel panelLabel;
    JLabel jLabelCtaCte;
    JLabel jLabelCajaAhorroDol;
    JLabel jLabelCajaAhorroPes;


    private boolean modificacion;

    PanelManager panelManager;

    public void armarPanelFormularioCuenta(Cuenta cuenta){

        jLabelCBU.setText(String.valueOf(cuenta.getCBU()));
        jLabelAlias.setText(cuenta.getAlias());
        jLabelCredito.setText(String.valueOf(cuenta.getCredito()));
        jLabelDebito.setText(String.valueOf(cuenta.getDebito()));
        jLabelTipo.setText(String.valueOf(cuenta.getTipo()));
        modificacion = true;
    }

    public void vaciarComponentes(){
        jTextFieldCBU.setText("");
        jTextFieldAlias.setText("");
        jTextFieldCredito.setText("");
        jTextFieldDebito.setText("");
        jTextFieldTipo.setText("");
        jTextFieldDNI.setText("");
        modificacion = false;
    }


    public void armarPanelFormulario(PanelManager panelManager){

        this.panelManager = panelManager;
        jLabelCBU = new JLabel("CBU: ");
        jLabelAlias = new JLabel("Alias: ");
        jLabelCredito = new JLabel("Credito: ");
        jLabelDebito = new JLabel("Debito: ");
        jLabelTipo = new JLabel("Tipo de Cuenta: ");
        jLabelDNI = new JLabel("DNI del Propietario: ");
        jTextFieldCBU = new JTextField(30);
        jTextFieldAlias = new JTextField(20);
        jTextFieldCredito = new JTextField(20);
        jTextFieldDebito = new JTextField(20);
        jTextFieldTipo = new JTextField(20);
        jTextFieldDNI = new JTextField(20);
        /*
        menuBar = new JMenuBar();
        menu1 = new JMenu("Tipo");


        tipo1 = new JMenuItem("Cuenta Corriente");
        tipo2 = new JMenuItem("Caja de Ahorro en Dolares");
        tipo3 = new JMenuItem("Caja de Ahorro en Pesos");
        menuBar.add(menu1);
        menuBar.add(tipo1);
        menuBar.add(tipo2);
        menuBar.add(tipo3);*/


        panelComponentes = new JPanel(new GridLayout(6,2));
        panelComponentes.add(jLabelCBU);
        panelComponentes.add(jTextFieldCBU);
        panelComponentes.add(jLabelAlias);
        panelComponentes.add(jTextFieldAlias);
        panelComponentes.add(jLabelCredito);
        panelComponentes.add(jTextFieldCredito);
        panelComponentes.add(jLabelDebito);
        panelComponentes.add(jTextFieldDebito);
        panelComponentes.add(jLabelTipo);
        panelComponentes.add(jTextFieldTipo);
        panelComponentes.add(jLabelDNI);
        panelComponentes.add(jTextFieldDNI);



        setLayout(new BorderLayout());
        add(panelComponentes, BorderLayout.CENTER);


        panelBotonera = new JPanel();
        jButtonGuardar = new JButton("Guardar");
        jButtonCancelar = new JButton("Cancelar");

        panelBotonera.add(jButtonGuardar);
        panelBotonera.add(jButtonCancelar);

        add(panelBotonera, BorderLayout.SOUTH);

        panelLabel = new JPanel();
        jLabelCtaCte = new JLabel("1 = Cuenta Corriente");
        jLabelCajaAhorroDol = new JLabel("2 = Caja de Ahorro en Dolares");
        jLabelCajaAhorroPes = new JLabel("3 = Caja de Ahorro en Pesos");
        panelLabel.add(jLabelCtaCte);
        panelLabel.add(jLabelCajaAhorroDol);
        panelLabel.add(jLabelCajaAhorroPes);

        add(panelLabel, BorderLayout.NORTH);





        jButtonGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cuenta cuenta = new Cuenta();
                cuenta.setCBU(Integer.parseInt(jTextFieldCBU.getText()));
                cuenta.setAlias(jTextFieldAlias.getText());
                cuenta.setCredito(Integer.parseInt(jTextFieldCredito.getText()));
                cuenta.setDebito(Integer.parseInt(jTextFieldDebito.getText()));
                cuenta.setTipo(Integer.parseInt(jTextFieldTipo.getText()));
                cuenta.setDNI_PROP(Integer.parseInt(jTextFieldDNI.getText()));
                if((Integer.parseInt(jTextFieldTipo.getText()) <= 0 || (Integer.parseInt(jTextFieldTipo.getText()) > 3))){
                    JOptionPane.showMessageDialog(null, "Las opciones son 1, 2 o 3", "ERROR", JOptionPane.ERROR_MESSAGE );
                }

                CuentaService cuentaService = new CuentaService();
                try {
                    if(modificacion){
                        cuentaService.modificarCuenta(cuenta);
                    }else {
                        cuentaService.crearCuenta(cuenta);
                    }
                } catch (ServiceException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Ha ocurrido un error al guardar" + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE );
                }

                panelManager.mostrarListaCuenta();
            }
        });

        jButtonCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vaciarComponentes();
                panelManager.mostrarPanelAdmin(panelManager);
            }
        });




    }

}
