package com.jbrigido.dev.components;

import com.jbrigido.dev.utilities.AdminColor;
import org.jdesktop.swingx.JXImagePanel;
import org.jdesktop.swingx.JXPanel;
import java.awt.*;
import java.awt.event.ActionListener;

public class AForm extends JXPanel {
    private ATextField txtCustomerID;
    private ATextField txtCustomerName;
    private ATextField txtCustomerLastName;
    private ATextField txtCustomerMotherLastName;
    private ATextField txtCustomerPhone;
    private ATextField txtCustomerEmail;
    private ATextField txtCustomerAddress;
    private ATextField txtCustomerBirthday;
    private AButton btnSave;
    private JXImagePanel picture;

    public AForm() {
        this.txtCustomerID = new ATextField("ID Customer",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.txtCustomerName = new ATextField("Insert your name",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.txtCustomerLastName = new ATextField("Insert your last name",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.txtCustomerMotherLastName = new ATextField("Insert your mother Last Name (Optional)",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.txtCustomerPhone = new ATextField("Insert your number phone",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.txtCustomerEmail = new ATextField("Insert your email",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.txtCustomerAddress = new ATextField("Insert your address",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.txtCustomerBirthday = new ATextField("Insert your Birthday",AdminColor.PRIMARY, AdminColor.TRANSPARENT_10);
        this.picture = new JXImagePanel();
        this.setBackground(AdminColor.WHITE);
        buildCustomerDataPanel();

    }

    private void buildCustomerDataPanel() {
        //picture.setImage(new ImageIcon(getClass().getResource("/img/logo.png")).getImage().getScaledInstance(200, 200, Image.SCALE_FAST));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.BOTH;
        add(picture, gbc);
        txtCustomerID.setEditable(false);
        txtCustomerID.setFocusable(false);
        txtCustomerID.setForeground(AdminColor.PRIMARY_100);
        gbc.gridx--;
        gbc.gridy++;
        add(txtCustomerID, gbc);
        gbc.gridy++;
        gbc.gridwidth = 1;
        add(txtCustomerName, gbc);
        gbc.gridx++;
        add(txtCustomerLastName, gbc);
        gbc.gridx++;
        add(txtCustomerMotherLastName, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 3;
        add(txtCustomerBirthday, gbc);
        gbc.gridy++;
        add(txtCustomerEmail, gbc);
        gbc.gridy++;
        add(txtCustomerAddress, gbc);
        gbc.gridy++;
        add(txtCustomerPhone, gbc);
        gbc.gridy++;

        Dimension fieldSize = new Dimension(140, 45);

        txtCustomerID.setPreferredSize(fieldSize);
        txtCustomerName.setPreferredSize(fieldSize);
        txtCustomerLastName.setPreferredSize(fieldSize);
        txtCustomerMotherLastName.setPreferredSize(fieldSize);
        txtCustomerBirthday.setPreferredSize(fieldSize);
        txtCustomerEmail.setPreferredSize(fieldSize);
        txtCustomerAddress.setPreferredSize(fieldSize);
        txtCustomerPhone.setPreferredSize(fieldSize);
    }

    public String getTextCustomerID() {
        return txtCustomerID.getText();
    }

    public String getTextCustomerName() {
        return txtCustomerName.getText();
    }

    public String getTextCustomerLastName() {
        return txtCustomerLastName.getText();
    }

    public String getTextCustomerMotherLastName() {
        return txtCustomerMotherLastName.getText();
    }

    public String getTextCustomerPhone() {
        return txtCustomerPhone.getText();
    }

    public String getTextCustomerEmail() {
        return txtCustomerEmail.getText();
    }

    public String getTextCustomerAddress() {
        return txtCustomerAddress.getText();
    }

    public String getTextCustomerBirthday() {
        return txtCustomerBirthday.getText();
    }

    public void addActionBtnSave(ActionListener l) {
        btnSave.addActionListener(l);
    }

}
