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
 * @author ChauDM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderProduct {
    private int productId;
    private String img;
    private String name;
    private int size;
    private double price;
    private int quantity;
}
