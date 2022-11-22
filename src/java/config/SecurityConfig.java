/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Dell
 */
public class SecurityConfig {

    private static final List<String> urlAdmin = new ArrayList<>();
    private static final List<String> urlMkt = new ArrayList<>();
    private static final List<String> urlMktManager = new ArrayList<>();
    private static final List<String> urlSale = new ArrayList<>();
    private static final List<String> urlCustomer = new ArrayList<>();
    private static final List<String> userGeneralURL = new ArrayList<>();

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<Integer, List<String>> mapConfig = new HashMap<Integer, List<String>>();

    static {
        init();
    }

    private static void init() {
        // Cấu hình cho vai trò "EMPLOYEE".
        mapConfig.put(Constants.ROLE_ADMIN, urlAdmin());
        mapConfig.put(Constants.ROLE_MKT, urlMkt());
        mapConfig.put(Constants.ROLE_CUSTOMER, urlCustomer());
        mapConfig.put(Constants.ROLE_SALE, urlSale());
        mapConfig.put(Constants.ROLE_MKTMANAGER, urlMktManager());

    }

    public static Set<Integer> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(int role) {
        List<String> listUrl = mapConfig.get(role);
        return listUrl;
    }

    //URL for ROLE_ADMIN
    public static List<String> urlAdmin() {
        urlAdmin.add("/manageUsers");
        urlAdmin.add("/editUser");
        urlAdmin.add("/admin-dashboard.jsp");
        urlAdmin.addAll(userGeneralURL());
        return urlAdmin;
    }
    
    //URL for ROLE_MKT
    public static List<String> urlMkt() {
        urlMkt.add("/managePosts");
        urlMkt.add("/addPost");
        urlMkt.add("/updateStatusPost");
        urlMkt.add("/manageSliders");
        urlMkt.add("/addSlider");
        urlMkt.add("/updateStatusSlider");
        urlMkt.add("/MktDashboard");
        urlMkt.add("/mktDashboard.jsp");
        urlMkt.addAll(userGeneralURL());
        return urlMkt;
    }
    
    //URL for ROLE_CUSTOMER
    public static List<String> urlCustomer() {
        urlCustomer.add("/cart");
        urlCustomer.add("/addToCart");
        urlCustomer.add("/favorite");
        urlCustomer.add("/order.jsp");
        urlCustomer.add("/order");
        urlCustomer.add("/orderInfo");
        urlCustomer.add("/editOrder");
        urlCustomer.add("/receivedOrder");
        urlCustomer.add("/cancelOrder");
        urlCustomer.add("/removeFeedback");
        urlCustomer.addAll(userGeneralURL());
        return urlCustomer;
    }
    
    //URL for ROLE_SALE
    public static List<String> urlSale() {
        urlSale.add("/manageOrder");
        urlSale.add("/confirmOrder");
        urlSale.add("/orderInfoSale");
        urlSale.add("/cancelOrder");
        urlSale.add("/SaleDashboard");
        urlSale.add("/manageProducts");
        urlSale.add("/updateProductStatus");
        urlSale.add("/manageProductSize");
        urlSale.add("/manageProductSale");
        urlSale.add("/editProduct");
        urlSale.add("/addProduct");
        urlSale.add("/saleDashboard.jsp");
        urlSale.addAll(userGeneralURL());
        return urlSale;
    }
    
    //URL for ROLE_MKTMANAGER
    public static List<String> urlMktManager() {
        urlMktManager.addAll(userGeneralURL());
        return urlMktManager;
    }

    //URL for user when logined
    public static List<String> userGeneralURL() {
        userGeneralURL.add("/user-profile.jsp");
        userGeneralURL.add("/profile");
        userGeneralURL.add("/updateProfile");
        userGeneralURL.add("/changepass.jsp");
        userGeneralURL.add("/ChangepassController");
        return userGeneralURL;
    }

}

