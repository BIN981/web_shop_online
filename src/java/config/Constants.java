/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

/**
 *
 * @author ADMIN
 */
public class Constants {
    //CONFIG EMAIL
    public final static String HOST = "smtp.gmail.com";
    public final static String PORT = "587";
    public final static String USER = "chaudmhe141283@fpt.edu.vn";
    public final static String PASSWORD = "Minhchau9a@12345";

    //page item
    public final static int PAGE_COUNT_PRODUCT = 12;
    public final static int PAGE_COUNT_MANAGE_ORDER = 20;
    public final static int PAGE_COUNT_BLOG = 5;  
    public final static int PAGE_COUNT_SLIDER = 5;
    public final static int PAGE_COUNT_FEEDBACK = 5;

    //role user
    public final static int ROLE_ADMIN = 1;
    public final static int ROLE_MKT = 2;
    public final static int ROLE_SALE = 3;
    public final static int ROLE_CUSTOMER = 4;
    public final static int ROLE_MKTMANAGER = 5;
    //verify
    public final static Boolean VERIFIED = true;
    public final static Boolean NOT_VERIFIED = false;
    //gender
    public final static String GENDER_MALE = "male";
    public final static String GENDER_FEMALE = "female";

    //product
    public final static int INITAL_QUANTITY = 1;
    public final static int INCREASE_QUANTITY = 1; 
    public final static int DECREASE_QUANTITY = -1;
    public final static Boolean PRODUCT_STATUS_ALL = null;
    public final static Boolean PRODUCT_ACTIVE = true;
    public final static Boolean PRODUCT_DEACTIVE = false;
    // send mail
    public final static String EMAIL_WELL_COME = "Online Shop!!";
    public final static String EMAIL_NEW_FEEDBACK = "New Feedback";
    public final static String EMAIL_ORDER_SUBMITTED = "Order Successful";
    public final static int MIN_RANDOM = 100000;
    public final static int MAX_RANDOM = 999999;
    public final static String MAIL_TYPE_REGISTER = "Register";
    public final static String MAIL_TYPE_RESET_PASSWORD = "Reset";
    
    //order
    public final static float DELIVERY_FEE = 2;
    public final static String ORDER_STATUS_ALL = "ALL";
    public final static String ORDER_STATUS_SUBMITTED = "SUBMITTED";
    public final static String ORDER_STATUS_CONFIRMED = "CONFIRMED";
    public final static String ORDER_STATUS_COMPLETED = "COMPLETED";
    public final static String ORDER_STATUS_CANCELED = "CANCELED";
    public final static Boolean ORDER_FEEDBACKED = true;
    public final static Boolean ORDER_UNFEEDBACKED = false;
    
    //blog and slider
    public final static Boolean STATUS_ACTIVE = true;
    public final static Boolean STATUS_DEACTIVE = false;
    public final static Boolean STATUS_ALL = null;
    
    //Date
    public final static String DATE_PATTERN = "MM-dd-yyyy";
    public final static String DATETIME_PATTERN = "MM-dd-yyyy HH:mm:ss";
    
    //Admin Statistic
    public final static String ADMIN_STATISTIC_MONTHLY_REVENUE = "Monthly Revenue";
    public final static String ADMIN_STATISTIC_DAILY_REVENUE = "Daily Revenue";
    public final static String ADMIN_STATISTIC_MONTHLY_ORDERS = "Monthly Orders";
    public final static String ADMIN_STATISTIC_DAILY_ORDERS = "Daily Orders";
    public final static String ADMIN_STATISTIC_REVENUE_CATEGORY = "Revenue by category";
    public final static String ADMIN_STATISTIC_QUANTITY_CATEGORY = "Quantity by category";
    
    //slider
    public final static Boolean SLIDER_STATUS_ACTIVE = true;
    public final static Boolean SLIDER_STATUS_DEACTIVE = false;
}
