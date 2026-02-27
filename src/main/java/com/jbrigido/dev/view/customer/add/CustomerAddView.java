package com.jbrigido.dev.view.customer.add;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.AForm;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXFrame;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CustomerAddView extends JXFrame {

    private AForm form;
    private JXPanel pnlContainer, pnlFooter;
    private AButton btnSave;

    public CustomerAddView() {
        this.form = new AForm();
        this.pnlFooter = new JXPanel();
        this.pnlContainer = new JXPanel();
        this.btnSave = new AButton("Save", FontIcon.of(MaterialDesignC.CONTENT_SAVE, 32, AdminColor.WHITE));
        buildWindow();
        setSize(new Dimension(820, 720));
        setLocationRelativeTo(null);
    }

    private void buildWindow() {
        pnlContainer.setLayout(new BorderLayout());
        pnlContainer.add(form, BorderLayout.CENTER);
        pnlContainer.setBackground(AdminColor.WHITE);
        pnlFooter.setLayout(new FlowLayout());
        pnlFooter.setBackground(AdminColor.WHITE);
        pnlContainer.add(pnlFooter, BorderLayout.SOUTH);
        pnlContainer.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        pnlFooter.add(btnSave);
        add(pnlContainer);
        repaint();
        pack();
    }

    public void addEventSave(ActionListener a) {
        btnSave.addActionListener(a);
    }

    public AForm getForm() {
        return form;
    }
}
