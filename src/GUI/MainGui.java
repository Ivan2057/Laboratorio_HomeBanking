package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGui {
    public static void main(String[] args){
        System.out.println("Empieza");

        PanelManager panelManager = new PanelManager();
        panelManager.armarPanelManager();
        panelManager.mostrarLoginUsuario(panelManager);


    }
}
