/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.parking;

import com.mycompany.parking.controller.LoginController;
import com.mycompany.parking.view.Login;
import java.awt.EventQueue;
import java.text.ParseException;

/**
 *
 * @author Ad
 */
public class App {
    public static void main(String[] args) throws ParseException {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                Login view = new Login();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}
