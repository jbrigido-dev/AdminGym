package com.jbrigido.dev.controller.customer;

import com.jbrigido.dev.view.customer.CustomerView;
import com.jbrigido.dev.view.customer.add.CustomerAddView;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerController {

    private CustomerView view;

    public CustomerController() {
        this.view = new CustomerView();
        addEvents();
    }

    private void addEvents() {
        view.addListenerBtnAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWindowAdd();
            }
        });
    }

    private void openWindowAdd() {
        CustomerAddView window = new CustomerAddView();
        window.setVisible(true);
    }

    public CustomerView getView() {
        return view;
    }
}
