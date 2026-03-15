package com.jbrigido.dev.services;

import com.jbrigido.dev.dao.attendance.AttendanceDB;
import com.jbrigido.dev.dto.AttendanceDTO;
import com.jbrigido.dev.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class AttendanceService {
    private Connection connection;
    private AttendanceDB service;

    public AttendanceService(Connection connection) {
        this.connection = connection;
        this.service = new AttendanceDB(connection);
    }

    public void save(long id) {

        Optional<CustomerDTO> found = new CustomerService(connection).getById(id);

        if (found.isPresent()) {
            CustomerDTO founded = found.get();
            AttendanceDTO request = new AttendanceDTO(founded.id(), 1);
            service.save(request);
        } else {
            throw new RuntimeException("ID not found");
        }
    }

    public List<AttendanceDTO> getAll() {
        return service.getAll();
    }

}
