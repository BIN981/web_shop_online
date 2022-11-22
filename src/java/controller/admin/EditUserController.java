/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Account;
import modal.User;

/**
 *
 * @author ChauDM
 */
public class EditUserController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new DAO();
            int userId = Integer.parseInt(request.getParameter("userId"));
            User user = dao.getUserById(userId);
            if (user != null) {
                Account account = dao.getAccountByUserId(userId);
                StringBuilder roleSelect = new StringBuilder();
                switch (account.getRoleId()) {
                    case 2:
                        roleSelect.append("             <option value=\"2\" selected>Marketing</option>\n"
                                + "                    <option value=\"3\">Sale</option>\n"
                                + "                    <option value=\"4\">Customer</option>\n"
                                + "                    <option value=\"5\">Marketing Maneger</option>\n");
                        break;
                    case 3:
                        roleSelect.append("             <option value=\"2\">Marketing</option>\n"
                                + "                    <option value=\"3\" selected>Sale</option>\n"
                                + "                    <option value=\"4\">Customer</option>\n"
                                + "                    <option value=\"5\">Marketing Maneger</option>\n");
                        break;
                    case 4:
                        roleSelect.append("             <option value=\"2\">Marketing</option>\n"
                                + "                    <option value=\"3\">Sale</option>\n"
                                + "                    <option value=\"4\" selected>Customer</option>\n"
                                + "                    <option value=\"5\">Marketing Maneger</option>\n");
                        break;
                    case 5:
                        roleSelect.append("             <option value=\"2\">Marketing</option>\n"
                                + "                    <option value=\"3\" >Sale</option>\n"
                                + "                    <option value=\"4\">Customer</option>\n"
                                + "                    <option value=\"5\" selected>Marketing Maneger</option>\n");
                        break;
                }
                out.print("<form class=\"edit-modal\" action=\"editUser\" method=\"post\">\n"
                        + "                <i class=\"fa-solid fa-xmark\"></i>\n"
                        + "                <h2>Edit User</h2>\n"
                        + "                 <input type=\"text\" name=\"userId\" value=\"" + user.getId() + "\" hidden/>"
                        + "                <img class=\"modal-avatar\" src=\"data:image/jpg;base64," + user.getImage() + "\" alt=\"\" />\n"
                        + "                <div class=\"modal-name\">Full Name: " + user.getFullName() + "</div>\n"
                        + "                <div class=\"modal-name\">Gender: " + (user.getGender() ? "Male" : "Female") + "</div>\n"
                        + "                <div class=\"modal-name\">Birth of Date: " + user.getDob() + "</div>\n"
                        + "                <select name=\"role\">\n"
                        + roleSelect.toString()
                        + "                </select>\n"
                        + "                <button class=\"btn-save disable\" type=\"submit\" disabled>Save</button>\n"
                        + "            </form>");
            } else {
                out.print("Occurs when processing... Try again later!");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        int roleId = Integer.parseInt(request.getParameter("role"));
        
        DAO dao = new DAO();
        if(dao.updateRole(userId, roleId) == 1){
            request.getRequestDispatcher("manageUsers").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("Occurs when edit role of user...");
        }
       
        if(dao.updateRole(roleId, userId)==1){
            request.getRequestDispatcher("manageUsers").forward(request, response);
        }else{
            PrintWriter out = response.getWriter();
            out.print("edit failse!");
        }
        
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
