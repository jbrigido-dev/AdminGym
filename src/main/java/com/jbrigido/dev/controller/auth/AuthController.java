package com.jbrigido.dev.controller.auth;

import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dto.UserDTO;
import com.jbrigido.dev.services.UserService;
import com.jbrigido.dev.utilities.LoaderUtil;
import com.jbrigido.dev.view.auth.Auth;
import com.jbrigido.dev.view.main.MainView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AuthController {

    private Auth view;
    private UserService service;
    private UserDTO userDTO;

    public AuthController() {
        view = new Auth();
        view.setVisible(500, 300);
        addEvents();
    }

    public void addEvents() {
        this.view.addActionBtnLogin(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                LoaderUtil.runWithLoader(view, () -> {
                    service = new UserService(LocalDB.getInstance());
                    userDTO = service.logIn(view.getUsername(), String.copyValueOf(view.getPassword()));
                }, () -> {
                    if (userDTO != null) {
                        openMain();
                    } else {
                        JOptionPane.showMessageDialog(null, "Credentials incorrect!");
                        view.setVisible(true);
                    }
                });
            }
        });
    }

    private void openMain() {
        view.dispose();
        MainView mainView = new MainView();
        mainView.setVisible(true);
    }
}
