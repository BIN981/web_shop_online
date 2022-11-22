/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dal.DAO;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author ADMIN
 */
public class CartUtil {
    public static void setCounterCart(HttpServletRequest request, DAO dao, int userId){
        int counterCartItem = dao.countItemInCart(userId);
        
        request.setAttribute("counterCartItem", counterCartItem);
    }
}
