package Service;

import DAO.DAOException;
import DAO.IUsuarioDAO;
import Entidades.Usuario;
import DAO.UsuarioDAOH2;

import java.util.ArrayList;

public class UsuarioService {

    private IUsuarioDAO usuarioDAO;

    public UsuarioService(){
        usuarioDAO = new UsuarioDAOH2();
    }

    public void guardarUsuario(Usuario usuario) throws ServiceException {
        try {
            usuarioDAO.guardar(usuario);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void eliminarUsuario(int DNI) throws ServiceException {
        try {
            usuarioDAO.eliminar(DNI);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public Usuario buscarUsuario(int DNI) throws ServiceException {
        try {
            return usuarioDAO.buscar(DNI);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public ArrayList buscarTodos() throws ServiceException {
        try {
            return usuarioDAO.buscarTodos();
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }

    public void modificarUsuario(Usuario usuario) throws ServiceException {
        try {
            usuarioDAO.modificar(usuario);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
    /*
    public void validarUsuario(Usuario usuario) throws ServiceException {
        try {
            usuarioDAO.validarUsuario(usuario);
        } catch (DAOException e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }*/

}
