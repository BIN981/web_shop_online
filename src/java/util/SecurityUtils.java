/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import config.SecurityConfig;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Dell
 */
public class SecurityUtils {

    // Kiểm tra 'urlPattern' này có bắt buộc phải đăng nhập hay không.
    public static boolean isSecurityPage(String urlPattern) {
        Set<Integer> roles = SecurityConfig.getAllAppRoles();

        for (int role : roles) {
            List<String> urlPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (urlPatterns != null && urlPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }
}
