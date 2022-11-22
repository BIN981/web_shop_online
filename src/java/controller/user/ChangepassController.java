/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

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
 * @author BinhNH981
 */
public class ChangepassController extends HttpServlet {

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
            out.println("<title>Servlet ChangepassController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangepassController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("changepass.jsp").forward(request, response);
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
        HttpSession session = request.getSession();
        String oldPassword = request.getParameter("oldPassword");
        String newPassword = request.getParameter("newpassword");
        String rePassword = request.getParameter("re_password");
        Account account = (Account) session.getAttribute("account");
        if (account == null) {
            request.setAttribute("mess", "Account not found!");
            request.setAttribute("mess_color", "red");
        } else {
            if (oldPassword.equals(account.getPassword())) {
                //th đúng pass cũ -> đc đổi
                if (newPassword.length() < 7) {
                    //th pass mới chưa đủ 7 chars
                    request.setAttribute("mess", "Password must at least 7 characters!");
                    request.setAttribute("mess_color", "red");
                } else {
                    if (newPassword.equals(rePassword)) {
                        // dc change pass
                        if (dao.changePassword(account.getUsername(), newPassword) == 1) {
                            //update success
                            request.setAttribute("mess", "Password is changed successful!");
                            request.setAttribute("mess_color", "green");
                        } else {
                            //update failed
                            request.setAttribute("mess", "Change Password Failed!");
                            request.setAttribute("mess_color", "red");
                        }
                    } else {
                        request.setAttribute("mess", "Re Password not match!");
                        request.setAttribute("mess_color", "red");
                    }
                }
            } else {
                request.setAttribute("mess", "Old Password Is Fail!");
                request.setAttribute("mess_color", "red");
            }
            request.getRequestDispatcher("changepass.jsp").forward(request, response);
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
