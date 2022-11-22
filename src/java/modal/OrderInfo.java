/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Dell
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private String fullName;
    private String phone;
    private String address;
    private double totalPrice;
    private String note;
    private String status;
    private Boolean isFeedback;
    private List<OrderProduct> products;
}
