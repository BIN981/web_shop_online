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
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Blog;
import modal.BlogCategory;
import modal.ProductCategory;

/**
 *
 * @author TRUNG
 */
public class blogListController extends HttpServlet {

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
        response.setCharacterEncoding("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet blogListController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet blogListController at " + request.getContextPath() + "</h1>");
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
//        processRequest(request, response);
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();
        List<BlogCategory> blogCateList = dao.getAllBlogCategory();
        //search
        String search_value = request.getParameter("search_post");

        int blogCategoryId = 0;
        try {
            blogCategoryId = Integer.parseInt(request.getParameter("blogCategoryId"));
        } catch (Exception e) {
        }

        //page
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }

        if (search_value == null || search_value.trim().length() == 0) {
            search_value = " ";
        }

        int totalPage = ((int) (dao.countAllBlog(Constants.STATUS_ACTIVE, search_value, blogCategoryId) / Constants.PAGE_COUNT_BLOG)) * Constants.PAGE_COUNT_BLOG < dao.countAllBlog(Constants.STATUS_ACTIVE, search_value, blogCategoryId)
                ? (int) (dao.countAllBlog(Constants.STATUS_ACTIVE, search_value, blogCategoryId) / Constants.PAGE_COUNT_BLOG) + 1
                : (int) (dao.countAllBlog(Constants.STATUS_ACTIVE, search_value, blogCategoryId) / Constants.PAGE_COUNT_BLOG);

        //get all product category
        List<ProductCategory> productCategories = dao.getAllProductCategory();

        List<Blog> blogList = dao.getAllBlog(Constants.STATUS_ACTIVE, search_value, blogCategoryId, page);
        request.setAttribute("blogs", blogList);
        request.setAttribute("page", totalPage);
        request.setAttribute("current_page", page);
        request.setAttribute("search_post", search_value);
        request.setAttribute("blogCategory", blogCateList);
        request.setAttribute("product_categories", productCategories);

        request.setAttribute("blogCategoryId", blogCategoryId);

        request.getRequestDispatcher("blogs.jsp").forward(request, response);

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
