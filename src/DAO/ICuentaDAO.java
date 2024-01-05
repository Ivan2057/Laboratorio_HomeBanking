package DAO;

import Entidades.Cuenta;
import Entidades.Usuario;

import java.util.ArrayList;


public interface ICuentaDAO{

    public void crear(Cuenta cuenta) throws DAOException;

    public void eliminar(int CBU) throws DAOException;

    public Cuenta buscar(int CBU) throws DAOException;

    public ArrayList buscarTodos() throws DAOException;

    public void modificar(Cuenta cuenta) throws DAOException;

}
