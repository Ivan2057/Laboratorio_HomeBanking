package Service;

import DAO.CuentaDAOH2;
import DAO.DAOException;
import DAO.ICuentaDAO;
import Entidades.Cuenta;
import Entidades.Usuario;

import java.util.ArrayList;

public class CuentaService {

    private ICuentaDAO cuentaDAO;

    public CuentaService(){
        cuentaDAO = new CuentaDAOH2();
    }

    public void crearCuenta(Cuenta cuenta) throws ServiceException {
        try {
            cuentaDAO.crear(cuenta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminarCuenta(int CBU) throws ServiceException {
        try {
            cuentaDAO.eliminar(CBU);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Cuenta buscarCuenta(int DNI) throws ServiceException {
        try {
            return cuentaDAO.buscar(DNI);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList buscarTodos() throws ServiceException {
        try {
            return cuentaDAO.buscarTodos();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarCuenta(Cuenta cuenta) throws ServiceException {
        try {
            cuentaDAO.modificar(cuenta);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

}
