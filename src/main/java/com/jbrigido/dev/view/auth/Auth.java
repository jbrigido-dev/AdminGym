package com.jbrigido.dev.view.auth;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.APasswordField;
import com.jbrigido.dev.components.ATextField;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.*;

import javax.swing.*;
import java.awt.GridLayout;

public class Auth extends JXPanel {
    private AButton btnLogin;
    private ATextField txtUsername;
    private APasswordField txtpassword;
    private JXLabel lblicon;

    public Auth() {
        initComponents();
        buildWindow();
    }

    private void initComponents() {
        lblicon = new JXLabel("LOGIN");
        btnLogin = new AButton("Auth");
        txtUsername = new ATextField("Insert your username", AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        txtpassword = new APasswordField("Insert your Password", AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
    }

    private void buildWindow() {
        setLayout(new GridLayout(4, 1, 50, 30));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(AdminColor.WHITE);
        add(lblicon);
        lblicon.setHorizontalAlignment(SwingConstants.CENTER);
        lblicon.setForeground(AdminColor.PRIMARY);
        add(txtUsername);
        add(txtpassword);
        add(btnLogin);
    }
}
