/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authen;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Account;

/**
 *
 * @author ADMIN
 */
public class Recovery extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Recovery</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Recovery at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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

        String otp = request.getParameter("otp");
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("user_id");
        if (otp.equals(session.getAttribute("otp"))) {

            String newPass = request.getParameter("newPassword");
            String rePass = request.getParameter("rePassword");
            if (newPass.equals(rePass)) {
                //change pass
                DAO dao = new DAO();
                Account account = dao.getAccountByUserId(userId);
                if (dao.changePassword(account.getUsername(), newPass) == 1) {
                    request.setAttribute("mess", "Password had changed!");
                    request.setAttribute("mess_color", "green");
                } else {
                    request.setAttribute("mess", "Error when change password!");
                    request.setAttribute("mess_color", "red");
                }
                request.getRequestDispatcher("recovery.jsp").forward(request, response);
            } else {
                request.setAttribute("mess", "Confirm pass not match!");
                request.setAttribute("mess_color", "red");
                request.getRequestDispatcher("recovery.jsp").forward(request, response);
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
