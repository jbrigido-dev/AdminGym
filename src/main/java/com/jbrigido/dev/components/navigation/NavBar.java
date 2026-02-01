package com.jbrigido.dev.components.navigation;

import com.jbrigido.dev.components.navigation.model.ItemModel;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;


public class NavBar extends JXPanel {

    public NavBar() {
        buildWindow();
        initComponents();
    }

    private void initComponents() {
        this.setBackground(AdminColor.WHITE);
        FontIcon icon = FontIcon.of(MaterialDesign.MDI_HOME, 32, AdminColor.PRIMARY);
        ItemModel dashboard = new ItemModel("DASHBOARD", icon, ItemModel.ItemType.MENU);
        NavItem dashboardItem = new NavItem(dashboard, false);
        icon = FontIcon.of(MaterialDesign.MDI_CASH_USD, 32, AdminColor.PRIMARY);
        ItemModel customer = new ItemModel("CUSTOMERS", icon, ItemModel.ItemType.MENU);
        NavItem customerItem = new NavItem(customer, false);
        icon = FontIcon.of(MaterialDesign.MDI_HISTORY, 32, AdminColor.PRIMARY);
        ItemModel attendance = new ItemModel("ATTENDANCE", icon, ItemModel.ItemType.MENU);
        NavItem attendanceItem = new NavItem(attendance, false);
        icon = FontIcon.of(MaterialDesign.MDI_CASH, 32, AdminColor.PRIMARY);
        ItemModel transaction = new ItemModel("TRANSACTIONS", icon, ItemModel.ItemType.MENU);
        NavItem transactionItem = new NavItem(transaction, false);
        icon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_CHECK, 32, AdminColor.PRIMARY);
        ItemModel cashRegister = new ItemModel("CASH REGISTER", icon, ItemModel.ItemType.MENU);
        NavItem cashRegisterItem = new NavItem(cashRegister, false);
        icon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_SEARCH, 32, AdminColor.PRIMARY);
        ItemModel inventory = new ItemModel("INVENTORY", icon, ItemModel.ItemType.MENU);
        NavItem inventoryItem = new NavItem(inventory, false);
        icon = FontIcon.of(MaterialDesign.MDI_ACCOUNT_SWITCH, 32, AdminColor.PRIMARY);
        ItemModel users = new ItemModel("USERS", icon, ItemModel.ItemType.MENU);
        NavItem usersItem = new NavItem(users, false);
        JXPanel separatorTop = new JXPanel();
        separatorTop.setBorder(BorderFactory.createEmptyBorder(60, 0, 60, 0));
        separatorTop.setBackground(AdminColor.WHITE);
        JXPanel separatorBottom = new JXPanel();
        separatorBottom.setBorder(BorderFactory.createEmptyBorder(60, 0, 60, 0));
        separatorBottom.setBackground(AdminColor.WHITE);
        add(separatorTop);
        add(dashboardItem);
        add(customerItem);
        add(attendanceItem);
        add(transactionItem);
        add(cashRegisterItem);
        add(inventoryItem);
        add(usersItem);
        add(separatorBottom);
        repaint();
        revalidate();
    }

    private void buildWindow() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
