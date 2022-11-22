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
 * @author ChauDM
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductSale {
    private int productId;
    private Date fromDate;
    private Date toDate;
    private int percentage;
}
