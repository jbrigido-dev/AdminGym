package com.jbrigido.dev.services;

import com.jbrigido.dev.dao.customer.CustomerDB;
import com.jbrigido.dev.dto.CustomerDTO;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class CustomerService {
    private Connection connection;
    private CustomerDB dao;

    public CustomerService(Connection connection) {
        this.connection = connection;
        this.dao = new CustomerDB(connection);
    }

    public void save(CustomerDTO customer) {
        dao.save(customer);
    }

    public void update(CustomerDTO customerDTO) {
        dao.update(customerDTO);
    }

    public Optional<CustomerDTO> getById(long id) {
        return dao.getById(id);
    }

    public void remove(long id) {
        dao.delete(id);
    }

    public List<CustomerDTO> getAll() {
        return dao.all();
    }

    public List<CustomerDTO> getByName(String name) {
        return dao.getByName(name);
    }
}
