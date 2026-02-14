package com.jbrigido.dev.view.auth;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.APasswordField;
import com.jbrigido.dev.components.ATextField;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.*;

import javax.swing.*;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

public class Auth extends JXFrame {
    private AButton btnLogin;
    private ATextField txtUsername;
    private APasswordField txtpassword;
    private JXLabel lblicon;
    private JXPanel pnlContent;

    public Auth() {
        initComponents();
        buildWindow();
        pack();
    }

    private void initComponents() {
        pnlContent = new JXPanel();
        lblicon = new JXLabel("LOGIN");
        btnLogin = new AButton("LOG IN");
        txtUsername = new ATextField("Insert your username", AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        txtpassword = new APasswordField("Insert your Password", AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
    }

    private void buildWindow() {
        pnlContent.setLayout(new GridLayout(4, 1, 50, 30));
        pnlContent.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlContent.setBackground(AdminColor.WHITE);
        pnlContent.add(lblicon);
        lblicon.setHorizontalAlignment(SwingConstants.CENTER);
        lblicon.setForeground(AdminColor.PRIMARY);
        pnlContent.add(txtUsername);
        pnlContent.add(txtpassword);
        pnlContent.add(btnLogin);
        add(pnlContent);
    }

    public void addActionBtnLogin(ActionListener l) {
        btnLogin.addActionListener(l);
    }

    public String getUsername() {
        return txtUsername.getText();
    }

    public char[] getPassword() {
        return txtpassword.getPassword();
    }

}
