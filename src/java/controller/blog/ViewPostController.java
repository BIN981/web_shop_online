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
import modal.User;

/**
 *
 * @author Nhat Anh
 */
public class ViewPostController extends HttpServlet {

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
                User author = dao.getUserById(post.getPublishBy());

                out.print("<div class=\"post__view\">\n"
                        + "                <span class='close-icon'><i class=\"fa-solid fa-xmark\"></i></span>\n"
                        + "                <div class=\"post__view-publish\">" + post.getPublishAt() + " </div>\n"
                        + "                <div class=\"post__view-author\">" + (author != null ? author.getFullName() : "Admin") + "</div>\n"
                        + "                <h3 class=\"post__view-title\">" + post.getTitle()
                        + "                </h3>\n"
                        + "                <img src=\"" + post.getImage() + "\" alt=\"\" />\n"
                        + "                <span class=\"post__view-description\">\n" + post.getContent() + "</span>\n"
                        + "            </div>"
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
