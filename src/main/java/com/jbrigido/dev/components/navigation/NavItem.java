package com.jbrigido.dev.components.navigation;

import com.jbrigido.dev.components.navigation.model.ItemModel;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;

public class NavItem extends JXPanel {

    private ItemModel itemModel;
    private JXLabel titleLbl, icon_lbl;
    private boolean hideTitle;

    public NavItem(ItemModel itemModel, boolean hiddeTitle) {
        this.itemModel = itemModel;
        this.hideTitle = hiddeTitle;
        initComponents();
        buildItem();

    }

    private void buildItem() {
        setLayout(new BorderLayout());
        setBackground(AdminColor.WHITE);
        if (!(itemModel.getIcon() == null)) {
            icon_lbl.setIcon(itemModel.getIcon());
            icon_lbl.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        }
        if (itemModel.getTitle() == null) {
            titleLbl.setText("");
        } else {
            titleLbl.setText(itemModel.getTitle());
        }
        titleLbl.setVisible(hideTitle);
        add(icon_lbl, BorderLayout.LINE_START);
        add(titleLbl, BorderLayout.CENTER);
    }

    private void initComponents() {
        this.titleLbl = new JXLabel();
        this.icon_lbl = new JXLabel();
    }

    public String getTitle() {
        return titleLbl.getText();
    }

    public void onClick(MouseListener m) {
        this.addMouseListener(m);
    }
}
