package DAO;

import Entidades.Usuario;

import java.util.ArrayList;

public interface IUsuarioDAO {

    public void guardar(Usuario cliente) throws DAOException;

    public void eliminar(int DNI) throws DAOException;

    public Usuario buscar(int DNI) throws DAOException;

    public ArrayList buscarTodos() throws DAOException;

    public void modificar(Usuario cliente) throws DAOException;

    //void validarUsuario(Usuario usuario) throws DAOException;
}
