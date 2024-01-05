package DAO;

import Entidades.Cuenta;
import Entidades.Usuario;
import Service.CuentaService;

import java.sql.*;
import java.util.ArrayList;

public class CuentaDAOH2 implements ICuentaDAO{

    String DB_JDBC_DRIVER = "org.h2.Driver";
    String DB_URL = "jdbc:h2:~/pruebaclase";
    String DB_USER = "sa";
    String DB_PASSWORD = "";


    @Override
    public void crear(Cuenta cuenta) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("INSERT INTO cuenta VALUES(?,?,?,?,?,?)");
            preparedStatement.setInt(1,cuenta.getCBU());
            preparedStatement.setString(2, cuenta.getAlias());
            preparedStatement.setDouble(3, cuenta.getCredito());
            preparedStatement.setDouble(4, cuenta.getDebito());
            preparedStatement.setInt(5, cuenta.getTipo());
            preparedStatement.setInt(6, cuenta.getDNI_PROP());


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

    public void eliminar(int CBU) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("DELETE FROM cuenta WHERE CBU = ?");
            preparedStatement.setInt(1,CBU);

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


    public Cuenta buscar(int DNI) throws DAOException {
        //SELECT
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cuenta cuenta = null;

        try {
            //1 Levantar el driver y conectarnos
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            //2 Crear sentencia SQL
            preparedStatement = connection.prepareStatement("SELECT * FROM cuenta WHERE DNI_PROP = ?");
            preparedStatement.setInt(1, DNI);

            //3 Ejecutar la sentencia
            ResultSet rs = preparedStatement.executeQuery();

            //4 Evaluar resultados
            while(rs.next()){
                cuenta = new Cuenta();
                cuenta.setCBU(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
                cuenta.setCredito(rs.getInt("CREDITO"));
                cuenta.setDebito(rs.getInt("DEBITO"));
                cuenta.setTipo(rs.getInt("TIPO"));
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
        return cuenta;
    }

    public ArrayList buscarTodos() throws DAOException {
        //SELECT
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Cuenta cuenta = null;
        ArrayList cuentas = new ArrayList();

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("SELECT * FROM cuenta");

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                cuenta = new Cuenta();
                cuenta.setCBU(rs.getInt("CBU"));
                cuenta.setAlias(rs.getString("ALIAS"));
                cuenta.setCredito(rs.getInt("CREDITO"));
                cuenta.setDebito(rs.getInt("DEBITO"));
                cuenta.setTipo(rs.getInt("TIPO"));
                cuenta.setDNI_PROP(rs.getInt("DNI_PROP"));


                cuentas.add(cuenta);
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
        return cuentas;
    }


    public void modificar(Cuenta cuenta) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            Class.forName(DB_JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            preparedStatement = connection.prepareStatement("UPDATE cuenta SET ALIAS=?, CREDITO=?, DEBITO=?, TIPO=?, DNI_PROP=? WHERE CBU=?");
            preparedStatement.setString(1, cuenta.getAlias());
            preparedStatement.setDouble(2, cuenta.getCredito());
            preparedStatement.setDouble(3, cuenta.getDebito());
            preparedStatement.setInt(4, cuenta.getTipo());
            preparedStatement.setInt(5, cuenta.getDNI_PROP());
            preparedStatement.setInt(6,cuenta.getCBU());

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
