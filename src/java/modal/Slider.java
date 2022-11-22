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
 * @author Administrator
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Slider {

    private int id;
    private String title;
    private String image;
    private String content;
    private Boolean isActive;
    private int publishBy;
    private Date publishAt;

    public String shortDes() {
        return content.length() > 200 ? content.substring(0, 200) : content;
    }

    public User getAuthor() {
        DAO dao = new DAO();
        return dao.getUserById(this.publishBy);
    }
}
