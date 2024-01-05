package Service;

import DAO.DAOException;
import DAO.InicioSesionDAO;
import DAO.InicioSesionDAOH2;
import Entidades.InicioSesion;

public class InicioSesionService {
    private InicioSesionDAO InicioDAO;

    public InicioSesionService(){
        InicioDAO = new InicioSesionDAOH2();
    }

    public void ingresarUsuario(InicioSesion usuario) throws ServiceException{

        try{
            InicioDAO.ingresar(usuario);
        } catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }
    public void ingresarAdmin(InicioSesion usuario) throws ServiceException{

        try{
            InicioDAO.ingresarAdmin(usuario);
        } catch (DAOException e){
            e.printStackTrace();
            throw new ServiceException(e.getMessage());
        }
    }


}
