package com.jbrigido.dev.services;

import com.jbrigido.dev.dao.Membership.MembershipDB;
import com.jbrigido.dev.dto.MembershipDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class MembershipService {

    private Connection connection;
    private MembershipDB dao;

    public MembershipService(Connection connection) {
        this.connection = connection;
        this.dao = new MembershipDB(connection);
    }

    public Optional<MembershipDTO> getLastMembership(long id) {
        return dao.getLastMembershipById(id);
    }

    public void createMembership(MembershipDTO dto) {
        try {
            connection.setAutoCommit(false);
            Optional<MembershipDTO> last = dao.getLastMembershipById(dto.customer_id());
            if (last.isPresent()) {
                MembershipDTO retrieved = last.get();
                dao.deactivateById(retrieved.id());
            }
            dao.save(dto);
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);

        }
    }
}
