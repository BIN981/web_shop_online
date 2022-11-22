/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.impl;

import com.google.gson.Gson;
import config.Constants;
import dal.AdminDashboardDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import modal.admin.Statistic;
import service.AdminService;

/**
 *
 * @author ChauDM
 */
public class AdminServiceImpl implements AdminService{

    @Override
    public String getStatisticData(String fromDate, String toDate, String type) {
        Gson gsonObj = new Gson();
        Map<Object, Object> map = null;
        List<Map<Object, Object>> data = new ArrayList<Map<Object, Object>>();

        String xVal, yVal;
        AdminDashboardDAO dao = new AdminDashboardDAO();
        List<Statistic> result = null;
        switch(type){
                case Constants.ADMIN_STATISTIC_MONTHLY_REVENUE:
                    result = dao.getMonthlyRevenue(fromDate, toDate);
                    break;
                case Constants.ADMIN_STATISTIC_DAILY_REVENUE:
                    result = dao.getDailyRevenue(fromDate, toDate);
                    break;
                case Constants.ADMIN_STATISTIC_MONTHLY_ORDERS:
                    result = dao.getMonthlyOrders(fromDate, toDate);
                    break;
                case Constants.ADMIN_STATISTIC_DAILY_ORDERS:
                    result = dao.getDailyOrders(fromDate, toDate);
                    break;
                case Constants.ADMIN_STATISTIC_REVENUE_CATEGORY:
                    result = dao.getRevenueByCategory(fromDate, toDate);
                    break;
                case Constants.ADMIN_STATISTIC_QUANTITY_CATEGORY:
                    result = dao.getQuantityByCategory(fromDate, toDate);
                    break;
            }
        for (Statistic item : result) {
            xVal = item.getLabel();
            yVal = item.getValue();
            map = new HashMap<Object, Object>();
            map.put("label", xVal);
            map.put("y", Double.parseDouble(yVal));
            data.add(map);
        }
        return gsonObj.toJson(data);
    }
    
    public static void main(String[] args) {
        AdminService adminService = new AdminServiceImpl();
        System.out.println(adminService.getStatisticData(null, null, Constants.ADMIN_STATISTIC_MONTHLY_REVENUE));
    }
    
}
