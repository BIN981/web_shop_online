/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Product;
import modal.ProductSale;

/**
 *
 * @author ChauDM
 */
public class ProductSaleController extends HttpServlet {

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
            DAO dao = new DAO();
            int productId = Integer.parseInt(request.getParameter("productId"));
            Product product = dao.getProductById(productId);
            List<ProductSale> sales = dao.getAllSaleByProductId(productId);
            StringBuilder trSale = new StringBuilder();
            sales.forEach((sale) -> {
                trSale.append("<tr class=\"size\">\n"
                        + "                <td>" + sale.getFromDate()+ "</td>\n"
                        + "                <td>" + sale.getToDate()+ "</td>\n"
                        + "                <td>" + sale.getPercentage()+ "\n"
                        + "                </td>\n"
                        + "              </tr>\n");
            });
            
            out.print("<form class=\"edit-modal\" action=\"manageProductSale\" method=\"post\">\n"
                    + "       <i class=\"fa-solid fa-xmark\"></i>\n"
                    + "       <img src=\"" + product.getImage() + "\" alt=\"\">\n"
                    + "         <input type=\"text\" name=\"productId\" value=\"" + productId + "\" hidden/>\n"
                    + "       <h1>Product Sale</h1>\n"
                    + "       <table border=\"1px solid black\" class=\"product-size-list\">\n"
                    + "        <tr>\n"
                    + "          <th>From</th>\n"
                    + "          <th>To</th>\n"
                    + "          <th>Percentage</th>\n"
                    + "        </tr>\n"
                    + trSale.toString()
                    + "       </table>\n"
                    + "        <div class=\"product-sizes-input\">\n"
                    + "          <span>Start</span>\n"
                    + "          <input type=\"date\" name=\"start\" required/>\n"
                    + "          <span>End</span>\n"
                    + "          <input type=\"date\" name=\"end\" required/>\n"
                    + "          <span>Sale Percentage</span>\n"
                    + "          <input type=\"numeber\" name=\"percentage\" min=\"0\" value=\"10\" required/>\n"
                    + "        </div>\n"
                    + "        <div class=\"modal-action\">\n"
                    + "          <button type=\"submit\">Save</button>\n"
                    + "        </div>\n"
                    + "      </form>");
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
        
        try {
            DAO dao = new DAO();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-MM");
            
            int productId = Integer.parseInt(request.getParameter("productId"));
            String from = request.getParameter("start");
            String to = request.getParameter("end");
            Date fromDate = sdf.parse(from);
            Date toDate = sdf.parse(to);
            int percentage = Integer.parseInt(request.getParameter("percentage"));
            dao.insertProductSale(productId, sdf.format(fromDate), sdf.format(toDate), percentage);
            
            request.getRequestDispatcher("manageProducts").forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
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
