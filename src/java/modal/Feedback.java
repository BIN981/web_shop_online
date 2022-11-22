/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modal;

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
public class Feedback {
    private int id;
    private String content;
    private Date publishAt;
    private int publishBy;
    private int rateStar;
    private int orderId;
    private String fullName;
    private String avatar;
    private int productId;
    private String productName;
    private List<String> images;
}
