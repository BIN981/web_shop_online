/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import config.Constants;
import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import modal.Account;
import modal.Order;
import modal.OrderInfo;
import service.EmailService;
import util.EmailUtils;

/**
 *
 * @author BinhNH981
 */
public class EmailServiceImpl implements EmailService {
//    subject mail

    @Override
    public void senEmail(ServletContext context, Account recipient, String type, String toMail, String name, String otp) {
        try {
            String content = null;
            String subject = null;
            switch (type) {
                case Constants.MAIL_TYPE_REGISTER:
                    subject = Constants.EMAIL_WELL_COME;
                    content = "Dear " + name + " thank for register!" + "\n opt is: " + otp;
                    break;
                default:
                    subject = "Online shop";
                    content = "maybe email wrong";
            }
            EmailUtils.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASSWORD, toMail, subject, content);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void senEmailReset(ServletContext context, String toMail, String name, String otp) {

        String subject = Constants.EMAIL_WELL_COME;
        String content = "Dear " + name + "\n You want reset your password?" + "\nYour OTP is: " + otp;
        try {
            EmailUtils.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASSWORD, toMail, subject, content);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }
   
    @Override
    public void sendEmailNewUser(ServletContext context, String toMail, String username, String password) {
        
        String subject = Constants.EMAIL_WELL_COME;
        String content = "Dear you "  + "\n Your username is: " +username+ "\nYour password is: " + password;
        try {
            EmailUtils.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASSWORD, toMail, subject, content);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void sendEmailNewFeedbackToSales(ServletContext context, String toMail, int orderId, String feedbackContent) {
        String subject = Constants.EMAIL_NEW_FEEDBACK;
        String content = "Dear all sales, an customer sent feedback on order with ID: " + orderId + "\nFeedback: " + feedbackContent;
        try {
            EmailUtils.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASSWORD, toMail, subject, content);
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendEmailOrderSumitted(ServletContext context, String toMail, OrderInfo orderInfo) {
        String subject = Constants.EMAIL_ORDER_SUBMITTED;
        StringBuilder content = new StringBuilder("You have successfully placed your order\n"
                + "Order information:\n"
                + "\t\tFull Name: " + orderInfo.getFullName()
                + "\n\t\tAddress: " + orderInfo.getAddress()
                + "\n\t\tPhone Number: " + orderInfo.getPhone()
                + "\n\t\tProducts:");
        orderInfo.getProducts().stream().forEach(item -> {
            content.append("\n\t\t\t\t").append(item.getName()).append(" x ").append(item.getQuantity());
        });
        
        content.append("\n\t\tTotal Cost: $").append(orderInfo.getTotalPrice());
        content.append("\n\t\tYou will receive the item in about 5 to 7 days\nThank you for ordering...\n\n-----ONLINE SHOP - SWP391 G6-----");
        
        try {
            EmailUtils.sendEmail(Constants.HOST, Constants.PORT, Constants.USER, Constants.PASSWORD, toMail, subject, content.toString());
        } catch (MessagingException ex) {
            ex.printStackTrace();
        }
    }

}
