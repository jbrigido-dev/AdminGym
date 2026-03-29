package com.jbrigido.dev.controller.customer;

import com.jbrigido.dev.components.AComboBox;
import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dao.Membership.MembershipTypeDB;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.dto.MembershipDTO;
import com.jbrigido.dev.dto.MembershipTypeDTO;
import com.jbrigido.dev.services.MembershipService;
import com.jbrigido.dev.utilities.LoaderUtil;
import com.jbrigido.dev.view.customer.payment.CustomerPaymentView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import javax.swing.JOptionPane;

public class CustomerPaymentController {

    private CustomerPaymentView view;
    private CustomerDTO customer;
    private MembershipTypeDTO selected;
    private MembershipService service;
    private MembershipDTO membership;
    private Optional<MembershipDTO> optional;

    public CustomerPaymentController(CustomerDTO customer) {
        this.customer = customer;
        this.view = new CustomerPaymentView();
        addEvents();
        loadInformation();
        this.view.setSize(720, 720);
        this.view.setLocationRelativeTo(null);
        this.view.setVisible(true);

    }

    private void addEvents() {
        this.view.addEventCbx(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AComboBox x = (AComboBox) e.getSource();
                selected = (MembershipTypeDTO) x.getSelectedItem();
                if (selected != null) {
                    changeField(selected);
                }
            }
        });
        this.view.addSaveEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });
    }

    private void loadInformation() {
        loadLastPayment();
        loadMemberships();
        setName();
    }

    private void setName() {
        this.view.setTxtName(customer.name());
    }

    private void loadMemberships() {
        List<MembershipTypeDTO> list = new MembershipTypeDB(LocalDB.getInstance()).all();
        for (MembershipTypeDTO item : list) {
            this.view.addItem(item);
        }
    }

    private void loadLastPayment() {
        LoaderUtil.runWithLoader(view, () -> {
            optional = new MembershipService(LocalDB.getInstance()).getLastMembership(customer.id());
        }, () -> {
            if (optional.isPresent()) {
                MembershipDTO retrieved = optional.get();
                if (retrieved.endDate().isBefore(getCurrentLocalDate())) {
                    this.view.setTxtStart(getLocalDateFormatted(getCurrentLocalDate()));
                } else {
                    this.view.setTxtStart(getLocalDateFormatted(retrieved.endDate()));
                }
            } else {
                this.view.setTxtStart(getLocalDateFormatted(getCurrentLocalDate()));
            }
        });

    }

    private void changeField(MembershipTypeDTO m) {
        this.view.setTxtEnd(sumDate(getFromString(view.getTxtStart()), m.duration()));
        this.view.setTxtPrice(String.valueOf(m.price()));
    }

    private String sumDate(LocalDate date, int duration) {
        switch (duration) {
            case 12, 1:
                date = date.plusMonths(duration);
                break;
            default:
                date = date.plusDays(duration);
        }
        return getLocalDateFormatted(date);
    }

    private void save() {
        LoaderUtil.runWithLoader(view, () -> {
            service = new MembershipService(LocalDB.getInstance());
            membership = new MembershipDTO(
                    customer.id(),
                    selected.id(),
                    getFromString(view.getTxtStart()),
                    getFromString(view.getTxtEnd()));
            service.createMembership(membership);
        }, () -> {
            JOptionPane.showMessageDialog(view, "The membership has been registered succesfully!");
        });

    }

    private LocalDate getFromString(String date) {
        return LocalDate.parse(date);
    }

    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    private String getLocalDateFormatted(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }
}
