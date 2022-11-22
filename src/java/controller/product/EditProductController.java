/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.product;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Product;
import modal.ProductCategory;

/**
 *
 * @author ChauDM
 */
public class EditProductController extends HttpServlet {

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

            if (product != null) {

                StringBuilder categorySelect = new StringBuilder();
                List<ProductCategory> categories = dao.getAllProductCategory();
                categories.forEach(item -> {
                    categorySelect.append("<option value=\"").append(item.getId()).append("\" ").append((item.getId() == product.getCategoryId()) ? "selected>" : ">").append(item.getName()).append("</option>\n");
                });
                out.print("<form action=\"editProduct\" method=\"post\" class=\"edit-modal\">\n"
                        + "                <i class=\"fa-solid fa-xmark modal__close\"></i>\n"
                        + "                <img id='previewThumbnail' src=\"" + product.getImage() + "\" alt=\"\">\n"
                        + "                <span>Image</span>\n"
                        + "                <input class=\"modal__blog-image\" type=\"file\" accept='.jpg, .jpge, .png'>\n"
                        + "                <input type=\"text\" name=\"productId\" hidden value=\"" + product.getId() + "\">\n"
                        + "                <input id=\"image-base64\" type=\"text\" name=\"image\" hidden>\n"
                        + "                <span>Category</span>\n"
                        + "                 <select name=\"category\">\n"
                        + categorySelect.toString()
                        + "                 </select>  "
                        + "                <span>Name</span>\n"
                        + "                <input type=\"text\" class=\"modal__blog-title\" name=\"name\" value=\"" + product.getName() + "\" title=\"Product Name\">\n"
                        + "                <span>Code</span>\n"
                        + "                <input type=\"text\" class=\"modal__blog-title\" name=\"code\" value=\"" + product.getCode()+ "\" title=\"Product Code\">\n"
                        + "                <span>Original Price</span>\n"
                        + "                <input type=\"number\" min=\"0.00\" max=\"any\" step=\"0.5\" class=\"modal__blog-title\" name=\"price\" value=\"" + product.getPrice() + "\" title=\"Original price\">\n"
                        + "                <span>Detail</span>\n"
                        + "                <textarea class=\"modal__blog-description\" name=\"detail\" \n"
                        + "                title=\"Product Detail\" rows=\"15\">" + product.getDescription() + "</textarea>\n"
                        + "                <button type=\"submit\">Save</button>\n"
                        + " </form>"
                );
            }
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

        int productId = Integer.parseInt(request.getParameter("productId"));
        String productCode = request.getParameter("code");
        double originalPrice = Double.parseDouble(request.getParameter("price"));
        String image = request.getParameter("image");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String productName = request.getParameter("name").trim();
        String detail = request.getParameter("detail").trim();

        if (dao.editProduct(productId, productName, originalPrice, image, productCode, categoryId, detail) == 1) {
            request.getRequestDispatcher("manageProducts").forward(request, response);
        } else {
            PrintWriter out = response.getWriter();
            out.print("Edit Product Failed! Occurs when edit product...");
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
