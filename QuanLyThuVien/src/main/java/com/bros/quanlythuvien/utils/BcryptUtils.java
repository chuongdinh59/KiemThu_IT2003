/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.utils;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Dinh Chuong
 */
public class BcryptUtils {
    private static final int SALT_ROUNDS = 10; // Lặp lại 10 lần mã hóa

  // function to encrypt a password
  public static String encryptPassword(String password, String salt) {
    String hashedPassword = null;
    try {
      String saltedPassword = password + salt;
      hashedPassword = BCrypt.hashpw(saltedPassword, BCrypt.gensalt(SALT_ROUNDS));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return hashedPassword;
  }

  // function to match a password
  public static boolean matchPassword(String password, String hashedPassword, String salt) {
    try {
      String saltedPassword = password + salt;
      return BCrypt.checkpw(saltedPassword, hashedPassword);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
    public static void main(String[] args) {
        String pass = "123";
        String salt = "chuong"; // user name
        String encryptPass = encryptPassword(pass, salt);
        System.err.println(encryptPass);
        if (matchPassword(pass, encryptPass, salt)) {
            System.out.print("Mật khẩu khớp");
        }
    }
}
