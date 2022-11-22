/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.blog;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Blog;

/**
 *
 * @author ChauDM
 */
public class EditPostController extends HttpServlet {

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
            DAO dao = new DAO();

            int postId = Integer.parseInt(request.getParameter("postId"));

            Blog post = dao.getBlogById(postId);

            if (post != null) {

                StringBuilder categorySelect = new StringBuilder();
                switch (post.getCategoryId()) {
                    case 1:
                        categorySelect.append("<option value=\"1\" selected>Thời trang</option>\n");
                        categorySelect.append("<option value=\"2\">Đời sống</option>\n");
                        categorySelect.append("<option value=\"3\">Thể thao</option>\n");
                        break;
                    case 2:
                        categorySelect.append("<option value=\"1\" >Thời trang</option>\n");
                        categorySelect.append("<option value=\"2\" selected>Đời sống</option>\n");
                        categorySelect.append("<option value=\"3\">Thể thao</option>\n");
                        break;
                    case 3:
                        categorySelect.append("<option value=\"1\">Thời trang</option>\n");
                        categorySelect.append("<option value=\"2\">Đời sống</option>\n");
                        categorySelect.append("<option value=\"3\" selected>Thể thao</option>\n");
                        break;
                }
                out.print("<form action=\"editPost\" method=\"post\" class=\"edit-modal\">\n"
                        + "                <i class=\"fa-solid fa-xmark modal__close\"></i>\n"
                        + "                <img id='previewThumbnail' src=\"" + post.getImage() + "\" alt=\"\">\n"
                        + "                <span>Thumbnail</span>\n"
                        + "                <input class=\"modal__blog-image\" type=\"file\">\n"
                        + "                <input type=\"text\" name=\"id\" hidden value=\"" + post.getId() + "\">\n"
                        + "                <input id=\"image-base64\" type=\"text\" name=\"image\" hidden>\n"
                        + "                <span>Category</span>\n"
                        + "                 <select name=\"category\">\n"
                        + categorySelect.toString()
                        + "                 </select>  "
                        + "                <span>Title</span>\n"
                        + "                <input type=\"text\" class=\"modal__blog-title\" name=\"title\" value=\"" + post.getTitle() + "\" title=\"Blog Title\">\n"
                        + "                <span>Description</span>\n"
                        + "                <textarea class=\"modal__blog-description\" name=\"description\" \n"
                        + "                title=\"Blog Description\" rows=\"15\">" + post.getContent() + "</textarea>\n"
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

        int postId = Integer.parseInt(request.getParameter("id"));
        String thumbnail = request.getParameter("image");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        String title = request.getParameter("title").trim();
        String description = request.getParameter("description").trim();

        if (dao.updateBlog(postId, thumbnail, title, categoryId, description) == 1) {
            request.getRequestDispatcher("managePosts").forward(request, response);
        } else {
             PrintWriter out = response.getWriter();
            out.print("Edit Post Failed! Occurs when update post...");
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
