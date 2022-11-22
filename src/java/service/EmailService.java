/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.servlet.ServletContext;
import modal.Account;
import modal.OrderInfo;

/**
 *
 * @author BinhNH981
 */
public interface EmailService {
    void  senEmail(ServletContext context, Account recipient, String type, String toMail,String name, String otp);
    
    void  senEmailReset(ServletContext context, String toMail,String name, String otp);
     
    void sendEmailNewUser(ServletContext context, String toMail, String username, String password);
    
    void sendEmailNewFeedbackToSales(ServletContext context, String toMail, int orderId, String feedbackContent);
    
    void sendEmailOrderSumitted(ServletContext context, String toMail, OrderInfo orderInfo);
    
    
    
            
    
}
