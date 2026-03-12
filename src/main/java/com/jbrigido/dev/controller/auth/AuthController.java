package com.jbrigido.dev.controller.auth;

import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dto.UserDTO;
import com.jbrigido.dev.services.UserService;
import com.jbrigido.dev.view.auth.Auth;
import com.jbrigido.dev.view.main.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController {

    private Auth view;
    private UserService service;

    public AuthController() {
        view = new Auth();
        addEvents();
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
        service = new UserService(LocalDB.getInstance());
        UserDTO userDTO = service.logIn(username, password);
        if (userDTO != null) {
            if (!userDTO.username().equals(username)) {
                JOptionPane.showMessageDialog(null, "User not found");
                return;
            }
            if (!userDTO.password().equals(password)) {
                JOptionPane.showMessageDialog(null, "Password incorrect");
                return;
            }
            openMain();
        } else {
            JOptionPane.showMessageDialog(null, "User not found");
        }
    }

    private void openMain() {
        view.dispose();
        MainView mainView = new MainView();
        mainView.setVisible(true);
    }
}
