package com.jbrigido.dev.controller.formcustomer;

import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dao.customer.CustomerDB;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.view.customer.add.CustomerAddView;

import javax.swing.*;
import java.awt.*;
import java.util.Date;

public class FormCustomerController {

    private CustomerAddView view;

    public FormCustomerController() {
        this.view = new CustomerAddView();
        addEvents();
        this.view.setSize(new Dimension(860, 680));
        this.view.setVisible(true);
    }


    private void addEvents() {
        view.addEventSave(e -> register());
    }

    private void register() {
        String name = view.getForm().getTextCustomerName();
        String lastname = view.getForm().getTextCustomerLastName();
        String motherlastname = view.getForm().getTextCustomerMotherLastName();
        Date date = view.getForm().getTextCustomerBirthday();
        String address = view.getForm().getTextCustomerAddress();
        String phone = view.getForm().getTextCustomerPhone();
        String email = view.getForm().getTextCustomerEmail();


        if (isEmpty(name, lastname)) {
            JOptionPane.showMessageDialog(null, "Field required");
            return;
        }
        if (date == null) {
            JOptionPane.showMessageDialog(null, "Insert a date");
            return;
        }

        if (!validateNames(name, lastname, motherlastname)) {
            JOptionPane.showMessageDialog(null, "There is a field that is invalid");
            return;
        }
        if (!email.isEmpty() && !validatePhone(phone)) {
            JOptionPane.showMessageDialog(null, "Enter a valid phone number");
            return;
        }
        if (!email.isEmpty() && !validateEmail(email)) {
            JOptionPane.showMessageDialog(null, "Enter a valid email");
            return;
        }

        CustomerDTO customer = new CustomerDTO(0, name, lastname, motherlastname, phone, email, date, address, true);
        int res = JOptionPane.showConfirmDialog(null, "Save?");
        if (res == JOptionPane.YES_OPTION) {
            new CustomerDB(LocalDB.getInstance()).save(customer);
            JOptionPane.showMessageDialog(null, "Customer Created");

        }

    }

    private boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }


    private boolean validatePhone(String number) {
        return number.matches("^[0-9]{10}");
    }

    private boolean validateNames(String... values) {
        boolean isValid = true;
        for (String value : values) {
            isValid = value.matches("[A-Za-z]*");
        }
        return isValid;
    }

    private boolean isEmpty(String... values) {
        boolean empty = false;
        for (String value : values) {
            if (value.isEmpty()) {
                empty = true;
                break;
            }
        }
        return empty;
    }

}
