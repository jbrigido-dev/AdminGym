package com.jbrigido.dev.dao.customer;

import com.jbrigido.dev.dto.CustomerDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class CustomerDB implements CustomerDao {
    private Connection connection;

    public CustomerDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(CustomerDTO customerDTO) {
        String sql = "insert into customers(name, last_name, mother_name, phone_number, email, birth_date, address) values (?, ?, ?, ?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, customerDTO.name());
            ps.setString(2, customerDTO.lastName());
            ps.setString(3, customerDTO.motherName());
            ps.setString(4, customerDTO.phoneNumber());
            ps.setString(5, customerDTO.email());
            ps.setString(6, getTextCustomerBirthday(customerDTO.birthday()));
            ps.setString(7, customerDTO.address());

            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private String getTextCustomerBirthday(Date date) {
        String patter = "YYYY-MM-dd";
        return new SimpleDateFormat(patter).format(date);
    }

    @Override
    public List<CustomerDTO> all() {
        String sql = "select id, name, last_name, mother_name, phone_number, status from customers";
        List<CustomerDTO> list = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CustomerDTO response = new CustomerDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getBoolean(6)
                );
                list.add(response);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong! " + e);
        }
        return list;
    }

    @Override
    public Optional<CustomerDTO> getById(long id) {
        String sql = "Select * from customers where id = ?";
        CustomerDTO customer = null;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next())
                customer = new CustomerDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6),
                        rs.getDate(7),
                        rs.getString(8),
                        rs.getBoolean(9)
                );
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong! " + e);
        }
        return Optional.ofNullable(customer);
    }

    @Override
    public void delete(long id) {
        String sql = "delete from customers where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong! " + e);
        }
    }

    @Override
    public void update(CustomerDTO customer) {
        String sql = "update customers set name = ?, last_name = ?, mother_name = ?, phone_number = ?, email = ?, birth_date = ?, address = ?, status = ? where id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,customer.name());
            ps.setString(2,customer.lastName());
            ps.setString(3,customer.motherName());
            ps.setString(4,customer.phoneNumber());
            ps.setString(5,customer.email());
            ps.setString(6,getTextCustomerBirthday(customer.birthday()));
            ps.setString(7,customer.address());
            ps.setBoolean(8,customer.status());
            ps.setLong(9,customer.id());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong! " + e);
        }
    }
}
