/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import dal.DAO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import modal.ProductCategory;

/**
 *
 * @author ADMIN
 */
public class ProductCategoryUtil {
    public static void setAttributeProductCategory(HttpServletRequest request, DAO dao){
        int productCategoryId = 0;
        try {
            productCategoryId = Integer.parseInt(request.getParameter("productCategoryId"));
        } catch (Exception e) {
        }
        
        //get all product category
        List<ProductCategory> productCategories = dao.getAllProductCategory();
        request.setAttribute("product_categories", productCategories);
    }
}
