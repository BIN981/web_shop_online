/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.user;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.User;

/**
 *
 * @author ADMIN
 */
public class UpdateProfile extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet UpdateProfile</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet UpdateProfile at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
        processRequest(request, response);
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        if (currentUser == null) {
            request.setAttribute("mess", "Please sign in to edit profile!");
            request.getRequestDispatcher("loginController").forward(request, response);
            return;
        }

        String firstName = request.getParameter("first-name");
        String lastName = request.getParameter("last-name");
        String imageBase64String = request.getParameter("avatarBase64String");
        Boolean gender = request.getParameter("gender").equals(Constants.GENDER_MALE);
        String phoneNumber = request.getParameter("phone");
        String dob = request.getParameter("dob");
        String address = request.getParameter("address");
        DAO dao = new DAO();
        
        String base64Image = null;
        if(imageBase64String != null && !imageBase64String.isEmpty()){
            base64Image = imageBase64String.split(",")[1];
        } 
        int updateResult = dao.updatetUser(currentUser.getId(), firstName, lastName, base64Image, gender, phoneNumber, dob, address);
        if (updateResult == 1) {
            request.setAttribute("mess", "Update profile successful");
            request.setAttribute("colorMess", "green");
            //update lại session user
            User updatedUser = dao.getUserById(currentUser.getId());
            session.setAttribute("user", updatedUser);
            System.out.println("success");
        } else {
            request.setAttribute("mess", "Update profile failed!");
            request.setAttribute("colorMess", "red");
            System.out.println("faild");
        }

        request.getRequestDispatcher("user-profile.jsp").forward(request, response);
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
