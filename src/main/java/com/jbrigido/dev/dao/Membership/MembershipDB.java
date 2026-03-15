package com.jbrigido.dev.dao.Membership;

import com.jbrigido.dev.dto.MembershipDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MembershipDB implements MembershipDAO {

    private Connection connection;

    public MembershipDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<MembershipDTO> all() {
        return List.of();
    }

    @Override
    public Optional<MembershipDTO> getLastMembershipById(long id) {
        MembershipDTO m = null;
        String sql = "select id,end_date from memberships where customer_id = ? order by end_date DESC limit 1";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                m = new MembershipDTO(
                        rs.getLong(1),
                        rs.getDate(2).toLocalDate()
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong!" + e);
        }
        return Optional.ofNullable(m);
    }

    @Override
    public List<MembershipDTO> allById(long id) {
        String sql = "select id, start_date, end_date, status from memberships where customer_id = ? limit 10";
        List<MembershipDTO> list = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new MembershipDTO(rs.getLong(1),
                        rs.getDate(2).toLocalDate(),
                        rs.getDate(3).toLocalDate(),
                        rs.getBoolean(4)));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong!" + e);
        }
        return list;
    }

    @Override
    public void save(MembershipDTO membershipDTO) {
        String sql = "insert into memberships(customer_id, membership_type, start_date, end_date) values (?,?,?,?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, membershipDTO.customer_id());
            ps.setInt(2, membershipDTO.type());
            ps.setDate(3, java.sql.Date.valueOf(membershipDTO.startDate()));
            ps.setDate(4, java.sql.Date.valueOf(membershipDTO.endDate()));
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong! " + e);
        }
    }

    @Override
    public void deactivateById(long id) {
        String sql = "update memberships set status = 0 where id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.execute();
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong! " + e);
        }
    }


}
