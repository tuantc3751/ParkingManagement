package com.mycompany.parking.controller;

import com.mycompany.parking.dao.UserDao;
import com.mycompany.parking.entity.User;
import com.mycompany.parking.view.Login;
import com.mycompany.parking.view.MainJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class LoginController {
    private UserDao userDao;
    private Login loginView;
    private MainJFrame mainJFrame;
    
    public LoginController(Login view) {
        this.loginView = view;
        this.userDao = new UserDao();
        view.addLoginListener(new LoginListener());
    }
    
    public void showLoginView() {
        loginView.setVisible(true);
    }
    
    /**
     * Lớp LoginListener 
     * chứa cài đặt cho sự kiện click button "Login"
     */
    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if (userDao.checkUser(user)) {
                // nếu đăng nhập thành công, mở màn hình quản lý sinh viên
                mainJFrame = new MainJFrame();
                HomeController homeController = new HomeController(mainJFrame);
//              homeController.showVehicleView();
                loginView.setVisible(false);
            } else {
                loginView.showMessage("username hoặc password không đúng.");
            }
        }
    }
}
