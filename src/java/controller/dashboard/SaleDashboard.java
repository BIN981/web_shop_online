/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.dashboard;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modal.OrderSale;
import modal.OrderStatus;
import util.ProductCategoryUtil;

/**
 *
 * @author BinhNH981
 */
public class SaleDashboard extends HttpServlet {

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
        DAO dao = new DAO();
        ProductCategoryUtil.setAttributeProductCategory(request, dao);
        request.getRequestDispatcher("saleDashboard.jsp").forward(request, response);

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
        DAO dao = new DAO();
        ProductCategoryUtil.setAttributeProductCategory(request, dao);
        request.getRequestDispatcher("saleDashboard.jsp").forward(request, response);
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

        DAO dao = new DAO();
        String startDate = request.getParameter("from");
        String endDate = request.getParameter("to");
        //get all order -- ddang loi
        List<OrderSale> listOrder = dao.getAllOrderCategory(startDate, endDate);
        request.setAttribute("listOrder", listOrder);
        // total order
        int totalOrder = dao.countTotalOrder(startDate, endDate);
        request.setAttribute("totalOrder", totalOrder);
        // revenues
        double revenues = dao.totalRevenues(startDate, endDate);
        request.setAttribute("revenues", revenues);
        // sum of category
        List sumCategory = dao.sumOfCategory(startDate, endDate);
        request.setAttribute("sumCategory", sumCategory);
        // success order
        int successOrder = dao.successOrder(startDate, endDate);
        request.setAttribute("successOrder", successOrder);
//        // get all status for list
//        List<OrderStatus> statusOrder = dao.listStatusOrder(startDate, endDate);
//        request.setAttribute("statusOrder", statusOrder);
        // update status
        request.getRequestDispatcher("saleDashboard.jsp").forward(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
