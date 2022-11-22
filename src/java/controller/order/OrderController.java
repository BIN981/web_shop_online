/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.order;

import dal.DAO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modal.OrderInfo;
import modal.SelectedProduct;
import modal.User;
import service.EmailService;
import service.impl.EmailServiceImpl;
import util.CartUtil;
import util.ProductCategoryUtil;

/**
 *
 * @author ChauDM
 */
public class OrderController extends HttpServlet {

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
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("signin.jsp");
            return;
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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");
        String fullName = request.getParameter("fullName").trim().isEmpty() ? user.getFullName() : request.getParameter("fullName").trim();
        String phone = request.getParameter("phone").trim().isEmpty() ? user.getPhoneNumber() : request.getParameter("phone").trim();
        String address = request.getParameter("address").trim().isEmpty() ? user.getAddress() : request.getParameter("address").trim();
        String note = request.getParameter("note").trim();
        List<SelectedProduct> products = (List<SelectedProduct>) session.getAttribute("products");
        float totalPrice = (float) session.getAttribute("totalPrice");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");
        DAO dao = new DAO();

        boolean isOutStock = false;
        for (SelectedProduct product : products) {
            if (!dao.isInStockItem(product.getProductSizeId(), product.getQuantity())) {
                isOutStock = true;
                break;
            }
        }

        if (isOutStock) {
            response.sendRedirect("out-stock.jsp");
        } else {
            String orderAt = sdf.format(new Date());
            if (dao.createOrder(user.getId(), totalPrice, orderAt, 0, phone, address, fullName, note) == 1) {
                //insert thành công vào order -> insert order detail
                int orderId = dao.getLastestOrderIdByUserId(user.getId());

                for (SelectedProduct product : products) {
                    //insert từng sản phẩm vào order detail
                    if (dao.createOrderDetail(orderId, product.getProductSizeId(), product.getQuantity(), product.getPrice()) == 1) {
                        //sau khi insert vào order_detail -> xóa các sản phẩm đã order thành công trong cart
                        dao.removeCartItem(user.getId(), product.getProductSizeId());

                        //giảm stock_quantity
                        dao.updateStockQuantity(product.getProductSizeId(), product.getQuantity());
                    }
                }
                OrderInfo orderInfo = dao.getOrderInfo(orderId);
                orderInfo.setProducts(dao.getOrderProducts(orderId));
                
                EmailService emailService = new EmailServiceImpl();
                emailService.sendEmailOrderSumitted(getServletContext(), user.getEmail(), orderInfo);
                
                request.setAttribute("orderInfo", orderInfo);
                request.setAttribute("orderAt", orderAt);
                ProductCategoryUtil.setAttributeProductCategory(request, dao);
                CartUtil.setCounterCart(request, dao, user.getId());
                request.getRequestDispatcher("invoice.jsp").forward(request, response);
            }
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
