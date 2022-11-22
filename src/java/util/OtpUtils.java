/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import static config.Constants.MAX_RANDOM;
import static config.Constants.MIN_RANDOM;

/**
 *
 * @author BinhNH981
 */
public class OtpUtils {
    public static String randomOtp(){
       return String.valueOf((int)(Math.random() * ((MAX_RANDOM - MIN_RANDOM) + 1)) + MIN_RANDOM);
    }
    
}
