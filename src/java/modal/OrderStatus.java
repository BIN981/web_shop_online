/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author BinhNH981
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderStatus {

    private int id;
    private int totalPrice;
    private Date order_at;
    private int discount;
    private String fullName;
}
