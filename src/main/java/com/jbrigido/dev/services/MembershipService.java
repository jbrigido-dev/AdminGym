package com.jbrigido.dev.services;

import com.jbrigido.dev.dao.Membership.MembershipDB;
import com.jbrigido.dev.dao.Membership.MembershipTypeDB;
import com.jbrigido.dev.dao.transaction.TransactionDB;
import com.jbrigido.dev.dto.MembershipDTO;
import com.jbrigido.dev.dto.MembershipTypeDTO;
import com.jbrigido.dev.dto.TransactionDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class MembershipService {

    private Connection connection;
    private TransactionDB transactionDao;
    private MembershipDB membershipDao;
    private MembershipTypeDB membershipTypeDao;

    public MembershipService(Connection connection) {
        this.connection = connection;
        this.membershipDao = new MembershipDB(connection);
        this.transactionDao = new TransactionDB(connection);
        this.membershipTypeDao = new MembershipTypeDB(connection);
    }

    public Optional<MembershipDTO> getLastMembership(long id) {
        return membershipDao.getLastMembershipById(id);
    }

    public void createMembership(MembershipDTO dto) {
        try {
            connection.setAutoCommit(false);
            Optional<MembershipTypeDTO> type = membershipTypeDao.getByID(dto.type());
            Optional<MembershipDTO> last = membershipDao.getLastMembershipById(dto.customer_id());
            if (last.isPresent()) {
                MembershipDTO retrieved = last.get();
                membershipDao.deactivateById(retrieved.id());
            }
            if (type.isPresent()) {
                MembershipTypeDTO info = type.get();
                TransactionDTO transaction = new TransactionDTO(info.description(), info.price(), 1, dto.customer_id());
                transactionDao.save(transaction);
                membershipDao.save(dto);
            }
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
