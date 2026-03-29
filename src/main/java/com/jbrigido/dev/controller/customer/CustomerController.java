package com.jbrigido.dev.controller.customer;

import com.jbrigido.dev.components.ATable.CustomerTableListener;
import com.jbrigido.dev.controller.formcustomer.FormCustomerController;
import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.services.CustomerService;
import com.jbrigido.dev.utilities.LoaderUtil;
import com.jbrigido.dev.view.customer.CustomerView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Optional;

public class CustomerController {

    private CustomerView view;
    private CustomerService service;
    private Optional<CustomerDTO> customer;
    private List<CustomerDTO> list;

    public CustomerController() {
        this.view = new CustomerView();
        service = new CustomerService(LocalDB.getInstance());
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
        view.setTableListener(new CustomerTableListener() {
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
        view.setFindAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadData();
            }
        });
    }

    private void openWindowAdd() {
        FormCustomerController add = new FormCustomerController();
    }

    private void loadData() {
       view.resetTable();
        String parameter = view.getTextSearchField();
        LoaderUtil.runWithLoader(getParent(), () -> {
            if (parameter.isEmpty()) {
                list = service.getAll();
            } else {
                list = service.getByName(parameter);
            }
        }, () -> {
            for (CustomerDTO c : list) {
                view.addData(c);
            }
        });

    }

    private void show(int row) {
        LoaderUtil.runWithLoader(getParent(), () -> {
            long id = view.getCustomerIDat(row);
            customer = service.getById(id);
        }, () -> {
            if (customer.isPresent()) {
                CustomerDetailsController controller = new CustomerDetailsController(customer.get());
            }
        });

    }

    private void delete(int row) {

        long id = view.getCustomerIDat(row);
        LoaderUtil.runWithLoader(getParent(), () -> {
            service.remove(id);
        }, () -> {
            view.deleteRow(row);
        });

    }

    public CustomerView getView() {
        return view;
    }

    public JFrame getParent() {
        return (JFrame) SwingUtilities.getWindowAncestor(view);
    }

}
