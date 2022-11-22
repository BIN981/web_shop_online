/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Feedback;
import modal.Product;
import modal.User;
import util.CartUtil;
import util.ProductCategoryUtil;

/**
 *
 * @author Admin
 */
@WebServlet(name = "ProductDetailController", urlPatterns = {"/productDetail"})
public class ProductDetailController extends HttpServlet {

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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        DAO dao = new DAO();
        if (user != null) {
            CartUtil.setCounterCart(request, dao, user.getId());
        }

        //page
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }
        
        int productId = Integer.parseInt(request.getParameter("productId"));
        Product product = dao.getProductById(productId);
        List<Feedback> feedbacks = dao.getAllFeedbackByProductId(productId, page);
        for (Feedback feedback : feedbacks) {
            List<String> images = dao.getFeedbackImageByFeedbackId(feedback.getId());
            if (images != null && !images.isEmpty()) {
                feedback.setImages(images);
            }
        }

        //total page of product
        int totalPage = ((int) (dao.countFeedbackImageByProductId(productId) / Constants.PAGE_COUNT_FEEDBACK)) * Constants.PAGE_COUNT_FEEDBACK
                < dao.countFeedbackImageByProductId(productId)
                ? (int) (dao.countFeedbackImageByProductId(productId) / Constants.PAGE_COUNT_FEEDBACK) + 1
                : (int) (dao.countFeedbackImageByProductId(productId) / Constants.PAGE_COUNT_FEEDBACK);

        request.setAttribute("detail", product);
        request.setAttribute("feedbacks", feedbacks);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("current_page", page);

        ProductCategoryUtil.setAttributeProductCategory(request, dao);

        request.getRequestDispatcher("product-details.jsp").forward(request, response);

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
        processRequest(request, response);
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
