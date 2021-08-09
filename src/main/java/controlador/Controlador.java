/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import dao.ClienteDAO;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import modelo.Cliente;

/**
 *
 * @author Usuario
 */
@WebServlet("/Controlador")
public class Controlador extends HttpServlet {
    
    private static ClienteDAO cDAO = new ClienteDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                default:
                    this.list(request, response);
            }
        } else {
            this.list(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "edit":
                    this.modificarCliente(request, response);
                    break;
                default:
                    this.list(request, response);
            }
        } else {
            this.list(request, response);
        }
    }

    private double calcularTotal(ArrayList<Cliente> clientes) {
        double total = 0;
        for (Cliente cliente : clientes) {
            total += cliente.getSaldo();
        }
        return total;
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ClienteDAO cDAO = new ClienteDAO();
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");

        //Validacion de saldo != null
        if (saldoString != null && !saldoString.equals("")) {
            saldo = Double.parseDouble(saldoString);
        }

        if (cDAO.insert(new Cliente(nombre, apellido, email, telefono, saldo))) {
            this.list(request, response);
        } else {
            System.out.println("Error, no se pudo insertar.");
        }

    }

    private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Cliente> clientes = new ClienteDAO().select();
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularTotal(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);
        response.sendRedirect("clientes.jsp");
    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        Cliente cliente = new ClienteDAO().selectById(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEdit = "/WEB-INF/paginas/clientes/editarCliente.jsp";
        request.getRequestDispatcher(jspEdit).forward(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ClienteDAO cDAO = new ClienteDAO();
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");

        //Validacion de saldo != null
        if (saldoString != null && !saldoString.equals("")) {
            saldo = Double.parseDouble(saldoString);
        }

        if (cDAO.update(new Cliente(idCliente, nombre, apellido, email, telefono, saldo))) {
            this.list(request, response);
        } else {
            System.out.println("Error, no se pudo actualizar.");
        }

    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //ClienteDAO cDAO = new ClienteDAO();
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));


        if (cDAO.delete(new Cliente(idCliente))) {
            this.list(request, response);
        } else {
            System.out.println("Error, no se pudo eliminar.");
        }
    }
}
