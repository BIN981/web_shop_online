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
 * @author ADMIN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    private String username;
    private String password;
    private int userId;
    private int roleId;

    public String getRole() {
        switch (roleId) {
            case 1:
                return "ADMIN";
            case 2:
                return "Marketing";
            case 3:
                return "Sale";
            case 4:
                return "Customer";
            case 5:
                return "Manage MKT";
            default:
                return "UNKNOW";
        }
    }
}
