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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Product;
import org.apache.commons.lang3.StringUtils;
import util.ProductCategoryUtil;

/**
 *
 * @author ChauDM
 */
public class ManageProductsController extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        DAO dao = new DAO();

        List<Product> products = null;
        Float priceFrom = null;
        Float priceTo = null;

        if (StringUtils.isNotBlank(request.getQueryString())) {
            String productName = request.getParameter("productName").trim();
            try {
                priceFrom = Float.parseFloat(request.getParameter("priceFrom"));
            } catch (Exception e) {
            }
            try {
                priceTo = Float.parseFloat(request.getParameter("priceTo"));
            } catch (Exception e) {
            }
            String productStatus = request.getParameter("productStatus");
            Boolean status = productStatus.equals("ALL") ? Constants.PRODUCT_STATUS_ALL
                    : productStatus.equals("ACTIVE") ? Constants.PRODUCT_ACTIVE : Constants.PRODUCT_DEACTIVE;
            int productCategory = 0;
            try {
                productCategory = Integer.parseInt(request.getParameter("productCategory"));
            } catch (Exception e) {
            }
            products = dao.getManageProducts(productName, productCategory, priceFrom, priceTo, status);
        } else {
            products = dao.getManageProducts("", 0, null, null, null);
        }

        request.setAttribute("products", products);
        ProductCategoryUtil.setAttributeProductCategory(request, dao);
        request.getRequestDispatcher("manage-products.jsp").forward(request, response);
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
