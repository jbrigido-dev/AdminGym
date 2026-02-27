package com.jbrigido.dev.dao.customer;

import com.jbrigido.dev.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerDao {

    void save(CustomerDTO customerDTO);

    List<CustomerDTO> all();

    Optional<CustomerDTO> getById(long id);

    void delete(long id);

    void update(CustomerDTO customer);
}
