/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.admin;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import service.EmailService;
import service.impl.EmailServiceImpl;

/**
 *
 * @author BinhNH981
 */
public class AddUserController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

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
        DAO dao = new DAO();
        HttpSession session= request.getSession();
        String username= request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String roleId = request.getParameter("role");
        String userId =request.getParameter("user_id");
        String strDate = "yyyy-MM-dd";
        DateFormat dateFormat = new SimpleDateFormat(strDate);
        Date date = Calendar.getInstance().getTime();
        String todayString = dateFormat.format(date);
        

        if(username.trim().isEmpty()|| password.trim().isEmpty()){
            PrintWriter out = response.getWriter();
            out.print("Add User Failed! Occurs when insert product...");
        }else{
            if (dao.checkAccountExit(username, email)) {
             PrintWriter out = response.getWriter();
            out.print("Add User Failed! Occurs when insert product...");
        } else {
            dao.signup(null, null, null, null, true, email, null, todayString, null);
            dao.insertAccount(username, password, dao.getUserSignedUp().getId(), Integer.parseInt(roleId));
            EmailService emailService = new EmailServiceImpl();
            emailService.sendEmailNewUser(getServletContext(), email, username, password);
            request.getRequestDispatcher("manageUsers").forward(request, response);
        }
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
