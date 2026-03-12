package com.jbrigido.dev.view.main;

import com.jbrigido.dev.components.navigation.NavBar;
import com.jbrigido.dev.controller.customer.CustomerController;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MainView extends JXFrame {

    private NavBar navBar;
    private JXPanel navBarContainer, pnlMain, pnlCards;
    private CustomerController customerController;

    public MainView() {
        initComponents();
        buildWindow();
        pack();
        setSize(new Dimension(getScreenSize()));
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        this.navBar = new NavBar();
        this.pnlMain = new JXPanel();
        this.pnlCards = new JXPanel();
        this.navBarContainer = new JXPanel();
    }

    private void buildWindow() {
        pnlMain.setLayout(new BorderLayout());
        pnlMain.add(navBarContainer, BorderLayout.LINE_START);
        navBarContainer.setLayout(new BorderLayout());
        navBarContainer.add(navBar, BorderLayout.CENTER);
        customerController = new CustomerController();
        pnlMain.add(pnlCards, BorderLayout.CENTER);
        loadCard();
        addEvents();
        add(pnlMain);
    }

    private Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }


    private void loadCard() {
        pnlCards.setLayout(new CardLayout());
        pnlCards.add(customerController.getView(), navBar.getCustomerItem().getTitle());
    }

    private void addEvents() {
        navBar.getCustomerItem().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cl = (CardLayout) pnlCards.getLayout();
                cl.show(pnlCards, navBar.getCustomerItem().getTitle());
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }


}
