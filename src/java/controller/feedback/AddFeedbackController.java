/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.feedback;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.User;
import org.apache.commons.lang3.StringUtils;
import service.EmailService;
import service.impl.EmailServiceImpl;

/**
 *
 * @author ChauDM
 */
public class AddFeedbackController extends HttpServlet {

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
            out.println("<title>Servlet AddFeedbackController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddFeedbackController at " + request.getContextPath() + "</h1>");
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        HttpSession session = request.getSession();
        try {
            PrintWriter out = response.getWriter();
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            User user = (User) session.getAttribute("user");
            int rating = Integer.parseInt(request.getParameter("rate"));
            String imageRequest = request.getParameter("images");
            String content = request.getParameter("content").trim();

            List<String> images = Arrays.asList(imageRequest.split("&")).stream()
                    .filter(item -> StringUtils.isNotBlank(item))
                    .collect(Collectors.toList());

            if (dao.insertFeedback(orderId, user.getId(), rating, content) == 1) {
                int feedbackId = dao.getLastestFeedbackByUserIdAndOrderId(user.getId(), orderId);
                for (String image : images) {
                    dao.insertFeedbackImage(feedbackId, image);
                }
                
                //send mail to all sale 
                List<User> saleUsers = dao.getAllUserByRole(Constants.ROLE_SALE);
                dao.updateFeedbackOrder(orderId, Constants.ORDER_FEEDBACKED);
                EmailService emailService = new EmailServiceImpl();
                saleUsers.stream().forEach(item -> {
                    emailService.sendEmailNewFeedbackToSales(getServletContext(), item.getEmail(), orderId, content);
                });
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Feedback</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("Sent Feedback successful<br/>");
                out.println("<a href='myOrder'>Back to my order</a>");
                out.println("</body>");
                out.println("</html>");
                
            } else {
                out.println("Error when send request...");
            }

        } catch (Exception e) {
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
