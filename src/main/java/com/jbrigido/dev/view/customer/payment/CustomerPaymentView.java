package com.jbrigido.dev.view.customer.payment;

import com.jbrigido.dev.components.AButton;
import com.jbrigido.dev.components.AComboBox;
import com.jbrigido.dev.components.ATextField;
import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXLabel;
import org.jdesktop.swingx.JXPanel;
import org.kordamp.ikonli.materialdesign.MaterialDesign;
import org.kordamp.ikonli.materialdesign2.MaterialDesignC;
import org.kordamp.ikonli.materialdesign2.MaterialDesignP;
import org.kordamp.ikonli.materialdesign2.MaterialDesignW;
import org.kordamp.ikonli.swing.FontIcon;

import javax.swing.*;
import java.awt.*;

public class CustomerPaymentView extends JXPanel {
    private ATextField txtName, txtStart, txtEnd, txtPrice;
    private JXLabel lblName, lblStart, lblEnd, lblPrice, lblMembership;
    private AComboBox cbMembership;
    private AButton btnSave;
    private JXPanel pnlContainer;

    public CustomerPaymentView() {
        initComponents();
        buildWindow();
    }

    private void initComponents() {
        this.txtName = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtStart = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtEnd = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.txtPrice = new ATextField(AdminColor.TRANSPARENT_10, AdminColor.PRIMARY_50);
        this.lblName = new JXLabel(FontIcon.of(MaterialDesign.MDI_ACCOUNT, 32, AdminColor.PRIMARY));
        this.lblStart = new JXLabel(FontIcon.of(MaterialDesign.MDI_CALENDAR, 32, AdminColor.PRIMARY));
        this.lblEnd = new JXLabel(FontIcon.of(MaterialDesign.MDI_CALENDAR, 32, AdminColor.PRIMARY));
        this.lblPrice = new JXLabel(FontIcon.of(MaterialDesign.MDI_CASH, 32, AdminColor.PRIMARY));
        this.lblMembership = new JXLabel(FontIcon.of(MaterialDesign.MDI_ACCOUNT_KEY, 32, AdminColor.PRIMARY));
        this.cbMembership = new AComboBox();
        this.btnSave = new AButton(FontIcon.of(MaterialDesignW.WALLET, 32, AdminColor.WHITE));
        this.pnlContainer = new JXPanel();
    }

    private void buildWindow() {
        setLayout(new BorderLayout());
        add(pnlContainer, BorderLayout.CENTER);
        setBackground(AdminColor.WHITE);
        pnlContainer.setLayout(new GridBagLayout());
        pnlContainer.setBackground(AdminColor.WHITE);
        buildForm();
        changeBackground();
        addItem();
    }

    private void addItem() {
        cbMembership.addItem(new String("Hola"));
    }

    private void buildForm() {
        JXLabel lblTitle = new JXLabel("Paid menu");
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(AdminColor.PRIMARY);
        add(lblTitle, BorderLayout.NORTH);
        GridBagConstraints gb = new GridBagConstraints();
        gb.gridx = 0;
        gb.gridy = 0;
        gb.insets = new Insets(10, 10, 10, 10);
        pnlContainer.add(lblName, gb);
        gb.gridx++;
        pnlContainer.add(txtName, gb);

        gb.gridx = 0;
        gb.gridy++;
        pnlContainer.add(lblMembership, gb);
        gb.gridx++;
        pnlContainer.add(cbMembership, gb);

        gb.gridx = 0;
        gb.gridy++;
        pnlContainer.add(lblStart, gb);
        gb.gridx++;
        pnlContainer.add(txtStart, gb);

        gb.gridx = 0;
        gb.gridy++;
        pnlContainer.add(lblEnd, gb);
        gb.gridx++;
        pnlContainer.add(txtEnd, gb);

        gb.gridx = 0;
        gb.gridy++;
        pnlContainer.add(lblPrice, gb);
        gb.gridx++;
        pnlContainer.add(txtPrice, gb);
        gb.gridy++;
        pnlContainer.add(btnSave, gb);


        Dimension fieldSize = new Dimension(140, 45);

        txtName.setPreferredSize(fieldSize);
        cbMembership.setPreferredSize(fieldSize);
        txtStart.setPreferredSize(fieldSize);
        txtEnd.setPreferredSize(fieldSize);
        txtPrice.setPreferredSize(fieldSize);
        btnSave.setPreferredSize(fieldSize);
    }

    private void changeBackground() {
        Component[] components = {txtName, txtPrice, txtStart, txtEnd};
        for (Component component : components) {
            component.setBackground(AdminColor.WHITE);
        }
    }
}
