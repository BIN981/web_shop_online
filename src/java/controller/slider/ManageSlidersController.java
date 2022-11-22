/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.slider;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.Slider;
import util.ProductCategoryUtil;

/**
 *
 * @author Administrator
 */
public class ManageSlidersController extends HttpServlet {

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
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }
        
        List<Slider> slider = dao.getAllSlider(Constants.STATUS_ALL, "");
        
        String search_value = ""; 
        int totalPage = ((int) (dao.countAllSliders(Constants.STATUS_ALL, search_value)
                / Constants.PAGE_COUNT_SLIDER)) * Constants.PAGE_COUNT_SLIDER 
                < dao.countAllSliders(Constants.STATUS_ALL,
                        search_value)
                ? (int) (dao.countAllSliders(Constants.STATUS_ALL,
                        search_value) / Constants.PAGE_COUNT_SLIDER) + 1
                : (int) (dao.countAllSliders(Constants.STATUS_ALL,
                        search_value) / Constants.PAGE_COUNT_SLIDER);
        
        
        request.setAttribute("sliders", slider);
        request.setAttribute("page", totalPage);
        request.setAttribute("current_page", page);
        ProductCategoryUtil.setAttributeProductCategory(request, dao);
        
        request.getRequestDispatcher("manage-sliders.jsp").forward(request, response);
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
