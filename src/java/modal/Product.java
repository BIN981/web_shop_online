/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import dal.DAO;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ADMIN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private int id;
    private String name;
    private String image;
    private double price;
    private String description;
    private int categoryId;
    private String code;
    private Date publishAt;
    private Boolean isActive;
    private int soldQuantity;
    private int percentage;
    private double salePrice;

    
    public String getProductCategoryName(){
        DAO dao = new DAO();
        return dao.getCategoryNameByProductId(this.id);
    }
    
    public String shortDesc(){
        return description.length() > 100 ? description.substring(0, 100) : description;
    }

}
