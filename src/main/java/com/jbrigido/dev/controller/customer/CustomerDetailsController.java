package com.jbrigido.dev.controller.customer;

import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dao.Membership.MembershipDB;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.dto.MembershipDTO;
import com.jbrigido.dev.services.CustomerService;
import com.jbrigido.dev.view.customer.details.CustomerDetailsView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public class CustomerDetailsController {

    private CustomerDetailsView view;
    private CustomerDTO model;
    private CustomerService service;

    public CustomerDetailsController(CustomerDTO model) {
        this.model = model;
        this.view = new CustomerDetailsView();
        setInformation();
        setEvents();
        view.setSize(new Dimension(1080, 720));
        view.setLocationRelativeTo(null);
        view.setVisible(true);

    }

    private void setInformation() {
        view.getForm().setCustomerData(model);
        view.setTextToLblHistory(model.name());
        loadData();
    }

    private void loadData() {
        List<MembershipDTO> list = new MembershipDB(LocalDB.getInstance()).allById(model.id());
        for (MembershipDTO dto : list) {
            view.setItem(dto);
        }
    }

    private void setEvents() {
        view.addEventSave(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res = JOptionPane.showConfirmDialog(null, "Are you sure to update it?");
                if (res == JOptionPane.YES_OPTION) {
                    updateCustomer();
                }
            }
        });
        view.addEventPay(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPaymentController controller = new CustomerPaymentController(model);
            }
        });
    }

    private void updateCustomer() {

        service = new CustomerService(LocalDB.getInstance());
        long id = Long.parseLong(view.getForm().getTextCustomerID());
        String name = view.getForm().getTextCustomerName();
        String lastname = view.getForm().getTextCustomerLastName();
        String motherlastname = view.getForm().getTextCustomerMotherLastName();
        LocalDate date = view.getForm().getTextCustomerBirthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
        CustomerDTO request = new CustomerDTO(id, name, lastname, motherlastname, phone, email, date, address, true);
        service.update(request);
        JOptionPane.showMessageDialog(null, "Customer Updated");

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
