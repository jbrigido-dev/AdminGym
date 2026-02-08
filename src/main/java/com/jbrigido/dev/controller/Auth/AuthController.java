package com.jbrigido.dev.controller.Auth;

import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dao.user.UserDB;
import com.jbrigido.dev.dto.UserDTO;
import com.jbrigido.dev.view.auth.Auth;
import com.jbrigido.dev.view.main.MainView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController {

    private Auth view;

    public AuthController() {
        view = new Auth();
        addEvents();
        view.setSize(new Dimension(500, 350));
        view.setLocationRelativeTo(null);
        view.setVisible(true);
    }

    public void addEvents() {
        this.view.addActionBtnLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticate();
            }
        });
    }

    private void authenticate() {
        String username = view.getUsername();
        String password = String.copyValueOf(view.getPassword());
        UserDTO userDTO = new UserDB(LocalDB.getInstance()).getUser(username, password);
        if (userDTO != null) {
            openMain();
        } else {
            JOptionPane.showMessageDialog(null, "User not found");
        }
    }

    private void openMain() {
        view.dispose();
        MainView mainView = new MainView();
        mainView.setSize(new Dimension(1080, 720));
        mainView.setLocationRelativeTo(null);
        mainView.setVisible(true);
    }

}
