/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.slider;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Slider;

/**
 *
 * @author Administrator
 */
public class EditSliderController extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            DAO dao = new DAO();

            int sliderId = Integer.parseInt(request.getParameter("sliderId"));

            Slider slider = dao.getSliderById(sliderId, null);

            if (slider != null) {

                out.print("<form action=\"editSlider\" method=\"post\" class=\"edit-modal\">\n"
                        + "                <i class=\"fa-solid fa-xmark modal__close\"></i>\n"
                        + "                <img id='previewThumbnail' src=\"" + slider.getImage() + "\" alt=\"\">\n"
                        + "                <span>Thumbnail</span>\n"
                        + "                <input class=\"modal__blog-image\" type=\"file\">\n"
                        + "                <input type=\"text\" name=\"id\" hidden value=\"" + slider.getId() + "\">\n"
                        + "                <input id=\"image-base64\" type=\"text\" name=\"image\" hidden>\n"
                        + "                <span>Title</span>\n"
                        + "                <input type=\"text\" class=\"modal__slider-title\" name=\"title\" value=\"" + 
                        slider.getTitle() + "\" title=\"Slider Title\">\n"
                        + "                <span>Description</span>\n"
                        + "                <textarea class=\"modal__slider-description\" name=\"description\" \n"
                        + "                title=\"Blog Description\" rows=\"15\">" + slider.getContent()+ "</textarea>\n"
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

        int sliderId = Integer.parseInt(request.getParameter("id"));
        String thumbnail = request.getParameter("image");
        String title = request.getParameter("title").trim();
        String content = request.getParameter("description").trim();

        if (dao.updateSlider(sliderId, thumbnail, title, content) == 1) {
            request.getRequestDispatcher("manageSliders").forward(request, response);
        } else {
             PrintWriter out = response.getWriter();
            out.print("Edit Slider Failed! Occurs when update slider...");
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
