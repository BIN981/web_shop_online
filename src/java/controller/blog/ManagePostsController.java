/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.blog;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Blog;
import util.ProductCategoryUtil;

public class ManagePostsController extends HttpServlet {

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
        
        DAO dao = new DAO();
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }
        
        List<Blog> posts = dao.getAllBlog(Constants.STATUS_ALL, "", 0, page);
        
        String search_value = "";
        int blogCategoryId = 0;
        int totalPage = ((int) (dao.countAllBlog(Constants.STATUS_ALL, search_value, blogCategoryId)
                / Constants.PAGE_COUNT_BLOG)) * Constants.PAGE_COUNT_BLOG < dao.countAllBlog(Constants.STATUS_ALL,
                        search_value, blogCategoryId)
                ? (int) (dao.countAllBlog(Constants.STATUS_ALL,
                        search_value, blogCategoryId) / Constants.PAGE_COUNT_BLOG) + 1
                : (int) (dao.countAllBlog(Constants.STATUS_ALL,
                        search_value, blogCategoryId) / Constants.PAGE_COUNT_BLOG);
        
        
        request.setAttribute("posts", posts);
        request.setAttribute("page", totalPage);
        request.setAttribute("current_page", page);
        ProductCategoryUtil.setAttributeProductCategory(request, dao);
        
        request.getRequestDispatcher("manage-posts.jsp").forward(request, response);
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
