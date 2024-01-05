package DAO;

import Entidades.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAOH2 implements IUsuarioDAO {

    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/pruebaclase";
    String DB_USER = "sa";
    String DB_PASSWORD = "";


    @Override
    public void guardar(Usuario usuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO usuarios VALUES(?,?,?,?,?)");
            preparedStatement.setInt(1,usuario.getDNI());
            preparedStatement.setString(2, usuario.getNombre());
            preparedStatement.setString(3, usuario.getApellido());
            preparedStatement.setString(4, usuario.getUsuario());
            preparedStatement.setString(5, usuario.getPassword());


            int i = preparedStatement.executeUpdate();

            System.out.println("Registros insertados: " + i);

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        }finally{
            try {
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
                throw new DAOException(error.getMessage());
            }
        }
    }


    @Override
    public void eliminar(int DNI) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM usuarios WHERE DNI = ?");
            preparedStatement.setInt(1,DNI);

            int i = preparedStatement.executeUpdate();

            System.out.println("Registros eliminados: " + i);

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());
        }finally{
            try {
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
                throw new DAOException(error.getMessage());
            }
        }

    }

    @Override
    public Usuario buscar(int DNI) throws DAOException {
        //SELECT
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Usuario usuario = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE DNI = ?");
            preparedStatement.setInt(1, DNI);

            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluar resultados
            while(rs.next()){
                usuario = new Usuario();
                usuario.setDNI(rs.getInt("DNI"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setApellido(rs.getString("APELLIDO"));
                usuario.setUsuario(rs.getString("USUARIO"));
                usuario.setPassword(rs.getString("PASSWORD"));
            }

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        }finally{
            try {
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
                throw new DAOException(error.getMessage());
            }
        }
        return usuario;
    }


    @Override
    public ArrayList buscarTodos() throws DAOException {
        //SELECT
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Usuario usuario = null;
        ArrayList usuarios = new ArrayList();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM usuarios");

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                usuario = new Usuario();
                usuario.setDNI(rs.getInt("DNI"));
                usuario.setNombre(rs.getString("NOMBRE"));
                usuario.setApellido(rs.getString("APELLIDO"));
                usuario.setUsuario(rs.getString("USUARIO"));
                usuario.setPassword(rs.getString("PASSWORD"));


                usuarios.add(usuario);
            }

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        }finally{
            try {
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
                throw new DAOException(error.getMessage());
            }
        }
        return usuarios;
    }

    /*
    @Override
    public void validarUsuario(Usuario usuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;c

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM usuarios WHERE USUARIO = ? AND PASSWORD = ?");
            preparedStatement.setString(1, usuario.getUsuario());
            preparedStatement.setString(2, usuario.getPassword());

            int i = preparedStatement.executeUpdate();

            System.out.println("Registros validados: " + i);

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        }finally{
            try {
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
                throw new DAOException(error.getMessage());
            }
        }
    }*/

    @Override
    public void modificar(Usuario usuario) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE usuarios SET NOMBRE=?, APELLIDO=?, USUARIO=? WHERE DNI=?");
            preparedStatement.setString(1, usuario.getNombre());
            preparedStatement.setString(2, usuario.getApellido());
            preparedStatement.setString(3, usuario.getUsuario());
            preparedStatement.setInt(4, usuario.getDNI());

            int i = preparedStatement.executeUpdate();

            System.out.println("Registros modificados: " + i);

        }catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new DAOException(e.getMessage());

        }finally{
            try {
                preparedStatement.close();
            } catch (SQLException error) {
                error.printStackTrace();
                throw new DAOException(error.getMessage());
            }
        }
    }



}
