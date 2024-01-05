package com.company;

import Entidades.Usuario;
import Service.ServiceException;
import Service.UsuarioService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        System.out.println("Boenas");

        UsuarioService usuarioService = new UsuarioService();

        Usuario usuario1 = new Usuario();
        usuario1.setDNI(44851637);
        usuario1.setNombre("Ivan");
        usuario1.setApellido("Szmelc");

        ArrayList usuarios = null;

        try {
            usuarios = usuarioService.buscarTodos();
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        for(Object obj : usuarios){
            Usuario us = (Usuario) obj;
            System.out.println("DNI: " + us.getDNI() + " Nombre y Apellido: " + us.getApellido() + ", " + us.getNombre());
        }

    }
}
