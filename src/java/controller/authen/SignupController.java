/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authen;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Account;
import service.EmailService;
import service.impl.EmailServiceImpl;
import util.OtpUtils;

/**
 *
 * @author BinhNH981
 */
public class SignupController extends HttpServlet {

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
        //check account tren session
        HttpSession session = request.getSession();
        if (session.getAttribute("account") != null) {
            System.out.println("loged in");
            request.getRequestDispatcher("home").forward(request, response);
            return;
        }
        response.sendRedirect("signup.jsp");
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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String username = request.getParameter("username");
        Boolean gender = request.getParameter("gender").equals("male");
        String email = request.getParameter("email");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        String re_password = request.getParameter("re_password");
        String phone = request.getParameter("phone");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        HttpSession session = request.getSession();
        session.setAttribute("username", username);
        session.setAttribute("password", password);
        session.setAttribute("firstname", firstname);
        session.setAttribute("lastname", lastname);
        session.setAttribute("gender", gender);
        session.setAttribute("email", email);
        session.setAttribute("phone", phone);
        session.setAttribute("dob", dob);
        session.setAttribute("address", address);

        if (username.trim().isEmpty() || password.trim().isEmpty() || address.trim().isEmpty() || firstname.trim().isEmpty() || lastname.trim().isEmpty() || phone.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("mess", "The input is not have space, please re-enter.");
            request.setAttribute("mess_color", "red");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        } else {
            if (!password.equals(re_password)) {
                request.setAttribute("mess", "Please confirm password");
                request.setAttribute("mess_color", "red");

            } else {
                if (password.length() <= 6) {
                    request.setAttribute("mess", "Password must at least 6 characters!");
                    request.setAttribute("mess_color", "red");
                } else {
                    DAO dao = new DAO();
                    if (!dao.checkAccountExit(username, email)) { // dc sign up
                        Account account = dao.getAccountByUsername(username);
                        String otp = OtpUtils.randomOtp();
                        session.setAttribute("otp", otp);
                        EmailService emailService = new EmailServiceImpl();
                        emailService.senEmail(getServletContext(), account, Constants.MAIL_TYPE_REGISTER, email, lastname, otp);
                        request.getRequestDispatcher("otp.jsp").forward(request, response);
                    } else {
                        request.setAttribute("mess", "Account had existed!");
                        request.setAttribute("mess_color", "red");
                        request.getRequestDispatcher("signup.jsp").forward(request, response);
                    }
                }
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
