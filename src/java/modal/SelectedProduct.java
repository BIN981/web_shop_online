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
public class SelectedProduct {
    private int productSizeId;
    private int size;
    private int quantity;
    private float price;
    private Product product;
}
