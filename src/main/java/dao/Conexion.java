/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Usuario
 */
public class Conexion {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true&characterEncoding=latin1";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "admin";
    private static BasicDataSource dataSource;

    public static DataSource getDataSource() {
        System.out.println("llegue al data source");
        if (dataSource == null) {
            dataSource = new BasicDataSource();
            dataSource.setUrl(JDBC_URL);
            dataSource.setUsername(JDBC_USER);
            dataSource.setPassword(JDBC_PASSWORD);
            dataSource.setInitialSize(50);
        }
        return dataSource;
    }

    public static Connection getConnection() throws SQLException {
        System.out.println("Aqui llegue");
        return getDataSource().getConnection();
    }
    
//    public static Connection getConnection() {
//        try {
//            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public static void close(ResultSet rs) {
        if(rs != null){
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        }else{
            System.out.println("ResulSet null");
        }
    }

    public static void close(PreparedStatement stmt) {
        if(stmt != null){
        try {
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        }else{
            System.out.println("PreparedStatement null");
        }
    }
        

    public static void close(Connection conn) {
        if(conn != null){
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        }else{
            System.out.println("Conexion null");
        }
    }
}
