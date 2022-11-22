/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Blog;
import modal.Product;
import modal.ProductCategory;
import modal.Slider;
import modal.User;
import util.CartUtil;
import util.ProductCategoryUtil;

/**
 *
 * @author ADMIN
 */
public class HomeController extends HttpServlet {

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
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        DAO dao = new DAO();
        
        int productCategoryId = 0;
        try {
            productCategoryId = Integer.parseInt(request.getParameter("productCategoryId"));
        } catch (Exception e) {
        }
        //search
        String search_value = request.getParameter("search");
        //page
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }

        if (search_value == null || search_value.trim().length() == 0) {
            search_value = "";
        } 
        
        
        //list product
        List<Product> products = dao.getAllProducts(search_value, productCategoryId, page);
        //total page of product
        int totalPage =  ((int)(dao.countAllProduct(search_value, productCategoryId, Constants.PRODUCT_ACTIVE) / Constants.PAGE_COUNT_PRODUCT)) * Constants.PAGE_COUNT_PRODUCT 
                < dao.countAllProduct(search_value, productCategoryId, Constants.PRODUCT_ACTIVE) 
                ? (int) (dao.countAllProduct(search_value, productCategoryId, Constants.PRODUCT_ACTIVE) / Constants.PAGE_COUNT_PRODUCT) + 1 : 
                (int) (dao.countAllProduct(search_value, productCategoryId, Constants.PRODUCT_ACTIVE) / Constants.PAGE_COUNT_PRODUCT);
        
        ProductCategoryUtil.setAttributeProductCategory(request, dao);
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null){
            User user = (User) session.getAttribute("user");
            CartUtil.setCounterCart(request, dao, user.getId());
        }
        
        //get 4 newest post
        List<Blog> newestBlogs = dao.getTop4Blog();
        
        List<Slider> sliders = dao.getAllSlider(Constants.STATUS_ACTIVE, "");
        
        request.setAttribute("newestBlogs", newestBlogs);
        request.setAttribute("sliders", sliders);
        request.setAttribute("products", products);
        request.setAttribute("page", totalPage);
        request.setAttribute("current_page", page);
        request.setAttribute("search", search_value);
        request.getRequestDispatcher("homepage.jsp").forward(request, response);
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
