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
 * @author ADMIN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String firstName;
    private String lastName;
    private Boolean gender;
    private String image;
    private String phoneNumber;
    private String email;
    private Date dob;
    private String address;

    public String getFullName() {
        StringBuilder fullName = new StringBuilder("");
        if (this.firstName != null) {
            fullName.append(this.firstName).append(" ");
        }
        if (this.lastName != null) {
            fullName.append(this.lastName);
        }
        return fullName.toString();
    }
    
    public Account getAccount(){
        DAO dao = new DAO();
        return dao.getAccountByUserId(id);
    }

}
