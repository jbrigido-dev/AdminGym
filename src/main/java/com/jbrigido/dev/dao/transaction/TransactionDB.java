package com.jbrigido.dev.dao.transaction;

import com.jbrigido.dev.dto.TransactionDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionDB implements TransactionDAO {
    private Connection connection;

    public TransactionDB(Connection connection) {
        this.connection = connection;
    }


    @Override
    public void save(TransactionDTO transaction) {
        String sql = "insert into transactions(concept, amount, user_id, customer_id) values (?, ?, ?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, transaction.concept());
            ps.setFloat(2, transaction.amount());
            ps.setLong(3, transaction.userId());
            ps.setLong(4, transaction.customerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong " + e);
        }
    }
}
