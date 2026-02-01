package com.jbrigido.dev.view.main;

import com.jbrigido.dev.components.navigation.NavBar;
import com.jbrigido.dev.view.customer.CustomerView;
import org.jdesktop.swingx.JXPanel;

import java.awt.*;

public class MainView extends JXPanel {

    private NavBar navBar;
    private JXPanel navBarContainer;
    private CustomerView customerView;

    public MainView() {
        initComponents();
        buildWindow();
    }

    private void initComponents() {
        this.navBar = new NavBar();
        this.navBarContainer = new JXPanel();
        this.customerView = new CustomerView();
    }

    private void buildWindow() {
        setLayout(new BorderLayout());
        add(customerView, BorderLayout.CENTER);
        add(navBarContainer, BorderLayout.LINE_START);
        navBarContainer.setLayout(new BorderLayout());
        navBarContainer.add(navBar, BorderLayout.CENTER);
    }

}
