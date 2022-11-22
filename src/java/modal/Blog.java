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
 * @author TRUNG
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {
    private int id;
    private String title;
    private String image;
    private int categoryId;
    private Date publishAt;
    private String content;
    private int publishBy;
    private Boolean isActive;
    
    public String shortDes(){
        return content.length() > 200 ? content.substring(0,200) : content;
    }
    
    public User getAuthor(){
        DAO dao = new DAO();
        return dao.getUserById(this.publishBy);
    }
}
