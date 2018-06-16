package com.iuminov;

import com.iuminov.dao.GenericDaoImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Factory {

    private static Connection connection;
    private static final String DRIVER_NAME = "org.h2.Driver";
    private static final String JDBC_URL = "jdbc:h2:tcp://localhost/~/java-apr-18";
    private static final String USER_NAME = "sa";
    private static final String PASSWORD = "";

    static {
        try {
            Class.forName(DRIVER_NAME);
            connection = DriverManager.getConnection(JDBC_URL, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    public static GenericDaoImpl getGenericDao() {
        return new GenericDaoImpl(connection);
    }
}
