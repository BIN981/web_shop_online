/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

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
public class OrderSale {
    private String id;
    private String name;
    private Integer amount;

}
