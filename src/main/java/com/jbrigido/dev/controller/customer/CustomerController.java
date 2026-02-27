package com.jbrigido.dev.controller.customer;

import com.jbrigido.dev.controller.formcustomer.FormCustomerController;
import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dao.customer.CustomerDB;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.view.customer.CustomerView;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class CustomerController {

    private CustomerView view;

    public CustomerController() {
        this.view = new CustomerView();
        addEvents();
        loadData();
    }

    private void addEvents() {
        view.addListenerBtnAdd(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openWindowAdd();
            }
        });
        view.setListenerTbl(new CustomerView.CustomerTableListener() {
            @Override
            public void onShow(int row) {
                show(row);
            }

            @Override
            public void onDelete(int row) {
                int res = JOptionPane.showConfirmDialog(null, "Are you sure to remove it?");
                if (res == JOptionPane.YES_OPTION) {
                    delete(row);
                }
            }
        });
    }

    private void openWindowAdd() {
        FormCustomerController add = new FormCustomerController();
    }

    private void loadData() {
        List<CustomerDTO> list = new CustomerDB(LocalDB.getInstance()).all();
        for (CustomerDTO c : list) {
            view.addData(c);
        }
    }

    private void show(int row) {
        long id = view.getCustomerIDat(row);
        Optional<CustomerDTO> customer = new CustomerDB(LocalDB.getInstance()).getById(id);
        if (customer.isPresent()) {
            CustomerDetailsController controller = new CustomerDetailsController(customer.get());
        }
    }

    private void delete(int row) {
        long id = view.getCustomerIDat(row);
        new CustomerDB(LocalDB.getInstance()).delete(id);
        view.deleteRow(row);
    }

    public CustomerView getView() {
        return view;
    }


}
