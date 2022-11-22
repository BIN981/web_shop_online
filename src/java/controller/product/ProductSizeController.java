/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import controller.mapper.ModalMapper;
import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Product;
import modal.ProductSize;

/**
 *
 * @author ChauDM
 */
public class ProductSizeController extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new DAO();
            int productId = Integer.parseInt(request.getParameter("productId"));
            List<ProductSize> sizes = dao.getAllSizeByProductId(productId);
            Product product = dao.getProductById(productId);
            StringBuilder trSize = new StringBuilder();
            sizes.forEach((size) -> {
                trSize.append("<tr class=\"size\">\n"
                        + "                <td>" + size.getSize() + "</td>\n"
                        + "                <td class=\"stock-quantity\">" + size.getStockQuantity() + "\n"
                        + "                  <i class=\"fa-solid fa-xmark\"></i>\n"
                        + "                </td>\n"
                        + "              </tr>\n");
            });
            out.println("<div class=\"edit-modal\">\n"
                    + "             <i class=\"fa-solid fa-xmark\"></i>\n"
                    + "             <img src=\"" + product.getImage() + " alt=\"\">\n"
                    + "             <h1>Product Sizes - " + product.getName() + " - " + product.getCode() + "</h1>\n"
                    + "<div style='overflow-y:scroll; height: fit-content; max-height: 40rem; width: 100%;'>\n"
                    + "             <table border=\"1px solid black\" class=\"product-size-list\">\n"
                    + "              <tr>\n"
                    + "                <th>Size</th>\n"
                    + "                <th>Stock Quantity</th>\n"
                    + "              </tr>\n"
                    + trSize.toString()
                    + "             </table>\n"
                    + "</div>\n"
                    + "              <div class=\"product-sizes-input\">\n"
                    + "                <span>Size</span>\n"
                    + "                <input type=\"number\" name=\"size\" min=\"35\" max=\"50\"/>\n"
                    + "                <span>Quantity</span>\n"
                    + "                <input type=\"number\" name=\"quantity\" min=\"0\"/>\n"
                    + "              </div>\n"
                    + "              <div class=\"modal-action\">\n"
                    + "                <span class=\"btn-add-size\" onclick=\"AddSize()\">Add Size</span>\n"
                    + "                <span class=\"btn-save-size\" onclick=\"SaveSize(" + productId + ")\">Save</span>\n"
                    + "              </div>\n"
                    + "            </div>");
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

        PrintWriter out = response.getWriter();
        DAO dao = new DAO();
        String productSizesJSON = request.getParameter("productSizes");
        List<ProductSize> productSizes = ModalMapper.mapToListProductSizes(productSizesJSON);
        System.out.println("JSON:\n" + productSizes);
        productSizes.forEach(item -> {
            if (dao.isExistSize(item.getProductId(), item.getSize())) {
                dao.updateProductSize(item.getProductId(), item.getSize(), item.getStockQuantity());
            } else {
                dao.insertProductSize(item.getProductId(), item.getSize(), item.getStockQuantity());
            }
        });
        out.print("done");
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
