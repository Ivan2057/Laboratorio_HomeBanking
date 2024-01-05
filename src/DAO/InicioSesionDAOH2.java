package DAO;

import Entidades.InicioSesion;

import java.sql.*;

public class InicioSesionDAOH2 implements InicioSesionDAO{
    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/pruebaclase";
    String DB_USER = "sa";
    String DB_PASSWORD = "";


    @Override
    public void ingresar(InicioSesion usuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE USUARIO=? AND PASSWORD=?");
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.setString(2, usuario.getPassword());
            ResultSet i = preparedStatement.executeQuery();

            if(i.next()){
                usuario.usuarioCorrecto();
            }
            else{
                usuario.usuarioIncorrecto();

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }
    }

    @Override
    public void ingresarAdmin(InicioSesion admin) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
            preparedStatement = connection.prepareStatement("SELECT * FROM administrador WHERE USUARIO=? AND PASSWORD=?");
            preparedStatement.setString(1, admin.getUsuario());
            preparedStatement.setString(2, admin.getPassword());
            ResultSet i = preparedStatement.executeQuery();

            if(i.next()){
                admin.usuarioCorrecto();
            }
            else{
                admin.usuarioIncorrecto();

            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e2) {
                e2.printStackTrace();
                throw new DAOException(e2.getMessage());
            }
        }
    }
}
