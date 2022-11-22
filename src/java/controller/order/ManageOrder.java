/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.order;

import config.Constants;
import dal.DAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.Order;
import modal.User;
import org.apache.commons.lang3.StringUtils;
import util.ProductCategoryUtil;

/**
 *
 * @author ChauDM
 */
public class ManageOrder extends HttpServlet {

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
        List<Order> orders = null;
        int counter = 0;
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (Exception e) {
        }
        
        if (StringUtils.isNotBlank(request.getQueryString())) {

            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");

            String orderAtFrom = request.getParameter("order_at_from").trim();
            String orderAtTo = request.getParameter("order_at_to").trim();
            String customerName = request.getParameter("customer_name").trim();
            Float totalCostFrom = null;
            Float totalCostTo = null;
            try {
                totalCostFrom = Float.parseFloat(request.getParameter("total_cost_from").trim());
            } catch (NumberFormatException e) {
            }
            try {
                totalCostTo = Float.parseFloat(request.getParameter("total_cost_to").trim());
            } catch (NumberFormatException e) {
            }
            String status = request.getParameter("order_status");

            orders = dao.getAllOrders(null, orderAtFrom, orderAtTo, customerName, totalCostFrom, totalCostTo, status.equals(Constants.ORDER_STATUS_ALL) ? null : status, page);
            counter = dao.countAllOrders(null, orderAtFrom, orderAtTo, customerName, totalCostFrom, totalCostTo, status.equals(Constants.ORDER_STATUS_ALL) ? null : status);
            
            
            request.setAttribute("param", request.getQueryString());
        } else {
            orders = dao.getAllOrders(null, null, null, null, null, null, null, page);
            counter = dao.countAllOrders(null, null, null, null, null, null, null);
        }
        
        int totalPage = ((int)(counter / Constants.PAGE_COUNT_MANAGE_ORDER))
                        * Constants.PAGE_COUNT_MANAGE_ORDER 
                < counter 
                ? (int) (counter / Constants.PAGE_COUNT_MANAGE_ORDER) + 1 : 
                (int) (counter / Constants.PAGE_COUNT_MANAGE_ORDER);

        request.setAttribute("orders", orders);
        request.setAttribute("total_page", totalPage);
        request.setAttribute("current_page", page);
        ProductCategoryUtil.setAttributeProductCategory(request, dao);
        request.getRequestDispatcher("manage-order.jsp").forward(request, response);

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
