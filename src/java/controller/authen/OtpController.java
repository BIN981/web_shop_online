/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authen;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Account;
import modal.User;
import util.OtpUtils;

/**
 *
 * @author BinhNH981
 */
public class OtpController extends HttpServlet {

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
            out.println("<title>Servlet otpController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet otpController at " + request.getContextPath() + "</h1>");
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
        request.getRequestDispatcher("otp.jsp").forward(request, response);

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
        String otp = (String) session.getAttribute("otp");
//        session.setMaxInactiveInterval(30);
        String otpUser = request.getParameter("otpUser");
        String username = (String) session.getAttribute("username");
        String password = (String) session.getAttribute("password");
        String firstname = (String) session.getAttribute("firstname");
        String lastname = (String) session.getAttribute("lastname");
        Boolean gender = (Boolean) session.getAttribute("gender");
        String email = (String) session.getAttribute("email");
        String phone = (String) session.getAttribute("phone");
        String dob = (String) session.getAttribute("dob");
        String address = (String) session.getAttribute("address");
        // check otp user with otp system
        if (otp.equals(otpUser)) {
            dao.signup(username, password, firstname, lastname, gender, email, phone, dob, address);
            Account account = dao.login(username, password);
            User user = dao.getUserById(account.getUserId());
            session.setAttribute("account", account);
            session.setAttribute("user", user);
            session.setMaxInactiveInterval(3600);
            session.removeAttribute("otp");
            request.getRequestDispatcher("home").forward(request, response);}
         else {
            request.setAttribute("mess", "OTP Fail, please enter OTP!");
            request.setAttribute("mess_color", "green");
            request.getRequestDispatcher("otp.jsp").forward(request, response);
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
