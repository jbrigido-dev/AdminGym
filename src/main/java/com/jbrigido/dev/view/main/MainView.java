package com.jbrigido.dev.view.main;

import com.jbrigido.dev.components.navigation.NavBar;
import com.jbrigido.dev.view.customer.CustomerView;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;

import java.awt.*;

public class MainView extends JXFrame {

    private NavBar navBar;
    private JXPanel navBarContainer, pnlMain;
    private CustomerView customerView;

    public MainView() {
        initComponents();
        buildWindow();
        pack();
    }

    private void initComponents() {
        this.navBar = new NavBar();
        this.pnlMain = new JXPanel();
        this.navBarContainer = new JXPanel();
        this.customerView = new CustomerView();
    }

    private void buildWindow() {
        pnlMain.setLayout(new BorderLayout());
        pnlMain.add(customerView, BorderLayout.CENTER);
        pnlMain.add(navBarContainer, BorderLayout.LINE_START);
        navBarContainer.setLayout(new BorderLayout());
        navBarContainer.add(navBar, BorderLayout.CENTER);
        add(pnlMain);
    }

}
