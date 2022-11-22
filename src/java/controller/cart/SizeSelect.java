/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.cart;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Product;

/**
 *
 * @author ADMIN
 */
public class SizeSelect extends HttpServlet {

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
        int productId = Integer.parseInt(request.getParameter("productId"));

        DAO dao = new DAO();
        //get all size in stock
        List<Integer> sizes = dao.getListSizeByProductId(productId);
        //get Product
        Product product = dao.getProductById(productId);

        PrintWriter out = response.getWriter();
        out.println("<div class=\"modal__detail\">\n"
                + "      <form id=\"modal__product\">\n"
                + "        <span class=\"modal__close\"><i class=\"fa-solid fa-xmark\"></i></span>\n"
                + "        <div class=\"modal__title\">Product Detail</div>\n"
                + "        <img src=\"" + product.getImage() + " alt=\"Product Image\">\n"
                + "        <h2 class=\"modal__product-name\">" + product.getName() + "</h2>\n"
                + "        <input type='text' name=\"productCode\" class=\"modal__product-code\" value='"+ product.getCode() +"'  />\n"
                + "        <div class=\"modal__product-price\">$" + (product.getSalePrice() == 0 ? product.getPrice() : product.getSalePrice()) + "</div>\n"
                + "        <div class=\"modal__product-size\">\n");

        for (int i = 0; i < sizes.size(); i++) {
            if(i == 0){
                out.println("<input class=\"size\" type=\"radio\" name=\"productSize\" value=\"" + sizes.get(i) + "\" checked>" + sizes.get(i));
            } else {
                out.println("<input class=\"size\" type=\"radio\" name=\"productSize\" value=\"" + sizes.get(i) + "\">" + sizes.get(i));
            }
            
        }

        out.print("</div>\n"
                + "        <span class=\"button-add\" onclick=\"AddToCart('"+ product.getCode()+"')\">\n"
                + "          Add to cart\n"
                + "        </span>\n"
                + "      </form>\n"
                + "    </div>");
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
