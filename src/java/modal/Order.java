/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author ChauDM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private int orderId;
    private String fullName;
    private String orderAt;
    private float totalPrice;
    private List<OrderProduct> products; 
    private String status;
    private Boolean isFeedback;
    
    public String getOrderStatusStyleClass(){
        return this.status.toLowerCase();
    }
    
    public OrderProduct getFirstProduct(){
        return this.products.get(0);
    }
    
    public int getQuantityMore(){
        return this.products.size() - 1;
    }
    
//    public String getDateTimeOrderAt(){
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        return format.format(orderAt);
//    }
}
