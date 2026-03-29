package com.jbrigido.dev.controller.attendance;

import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dto.AttendanceDTO;
import com.jbrigido.dev.dto.CustomerDTO;
import com.jbrigido.dev.dto.MembershipDTO;
import com.jbrigido.dev.services.AttendanceService;
import com.jbrigido.dev.services.CustomerService;
import com.jbrigido.dev.services.MembershipService;
import com.jbrigido.dev.utilities.LoaderUtil;
import com.jbrigido.dev.view.attendance.AttendanceView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceController {

    private AttendanceView view;
    private AttendanceService AttendanceService;
    private CustomerService customerService;
    private MembershipService membershipService;
    private Optional<MembershipDTO> membership;
    private List<String[]> customerDTOList;

    public AttendanceController() {
        this.AttendanceService = new AttendanceService(LocalDB.getInstance());
        this.customerService = new CustomerService(LocalDB.getInstance());
        this.membershipService = new MembershipService(LocalDB.getInstance());
        this.view = new AttendanceView();
        addEvents();
        loadData();
    }

    public AttendanceView getView() {
        return view;
    }


    private void addEvents() {
        view.addSearcherEvent(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerAttendance();
                view.setTxtSearcher("");
                loadData();
            }
        });
    }

    private void registerAttendance() {
        long id = Long.parseLong(view.getSearcherText());
        LoaderUtil.runWithLoader(getParent(), () -> {
            membership = membershipService.getLastMembership(id);
        }, () -> {
            if (membership.isPresent()) {
                MembershipDTO retrieved = membership.get();
                if (retrieved.endDate().isAfter(getCurrentLocalDate())) {
                    AttendanceService.save(id);
                }
            }
        });
    }

    private void loadData() {
        view.resetTableRows();

        LoaderUtil.runWithLoader(getParent(), () -> {
            List<AttendanceDTO> list = AttendanceService.getAll();
            customerDTOList = new ArrayList<>();
            for (AttendanceDTO x : list) {
                Optional<CustomerDTO> customer = customerService.getById(x.customerId());
                if (customer.isPresent()) {
                    CustomerDTO retrieved = customer.get();
                    Optional<MembershipDTO> membership = membershipService.getLastMembership(retrieved.id());
                    if (membership.isPresent()) {
                        MembershipDTO m = membership.get();
                        customerDTOList.add(new String[]{retrieved.name() + " " + retrieved.lastName() + " " +
                                retrieved.motherName(), x.localDate().toString(), m.endDate().toString()});
                    }
                }
            }
        }, () -> {
            for (String[] data : customerDTOList){
                view.addElement(data);
            }
        });

    }

    public JFrame getParent() {
        return (JFrame) SwingUtilities.getWindowAncestor(view);
    }

    public LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

}
