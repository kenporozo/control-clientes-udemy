/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import modelo.Cliente;

/**
 *
 * @author Usuario
 */
public class ClienteDAO {

    private static final String SQL_SELECT = "SELECT * FROM cliente ORDER BY id";
    private static final String SQL_SELECT_BY_ID = "SELECT * FROM cliente WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) VALUES(?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre = ?, apellido = ?, email = ?, telefono = ?, saldo = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id = ?";

    public ArrayList<Cliente> select() {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Cliente> list = new ArrayList<>();

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while(rs.next()){
                int idCliente = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);
                list.add(cliente);
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return list;
    }
    
    public Cliente selectById(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, cliente.getIdCliente());
            rs = stmt.executeQuery();
            if(rs.next()){
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                
                cliente.setNombre(nombre);
                cliente.setApellido(apellido);
                cliente.setEmail(email);
                cliente.setTelefono(telefono);
                cliente.setSaldo(saldo);
            }else{
                cliente = null;
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
    
    public boolean insert(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean estado = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.executeUpdate();
            estado = true;
                        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        }finally{
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estado;
    }
    
    public boolean update(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean estado = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(6, cliente.getIdCliente());
            stmt.executeUpdate();
            estado = true;
                        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estado;
    }
    
    public boolean delete(Cliente cliente){
        Connection conn = null;
        PreparedStatement stmt = null;
        boolean estado = false;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, cliente.getIdCliente());
            stmt.executeUpdate();
            estado = true;
                        
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);

        }finally{
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return estado;
    }
}
