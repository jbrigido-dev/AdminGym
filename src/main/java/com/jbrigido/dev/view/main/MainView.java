package com.jbrigido.dev.view.main;

import com.jbrigido.dev.components.navigation.NavBar;
import com.jbrigido.dev.controller.attendance.AttendanceController;
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
    private AttendanceController attendanceController;

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
        this.pnlMain.setLayout(new BorderLayout());
        this.pnlMain.add(this.navBarContainer, BorderLayout.LINE_START);
        this.navBarContainer.setLayout(new BorderLayout());
        this.navBarContainer.add(navBar, BorderLayout.CENTER);
        this.customerController = new CustomerController();
        this.attendanceController = new AttendanceController();
        this.pnlMain.add(this.pnlCards, BorderLayout.CENTER);
        loadCard();
        addEvents();
        this.add(pnlMain);
    }

    private Dimension getScreenSize() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }


    private void loadCard() {
        this.pnlCards.setLayout(new CardLayout());
        this.pnlCards.add(customerController.getView(), navBar.getCustomerItem().getTitle());
        this.pnlCards.add(attendanceController.getView(), navBar.getAttendanceItem().getTitle());
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
        navBar.getAttendanceItem().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cl = (CardLayout) pnlCards.getLayout();
                cl.show(pnlCards, navBar.getAttendanceItem().getTitle());
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
