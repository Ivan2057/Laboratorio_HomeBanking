package DAO;

import Entidades.InicioSesion;

public interface InicioSesionDAO {

    public void ingresar(InicioSesion usuario) throws DAOException;
    public void ingresarAdmin(InicioSesion usuario) throws DAOException;
}
