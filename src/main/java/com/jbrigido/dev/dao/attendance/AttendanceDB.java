package com.jbrigido.dev.dao.attendance;

import com.jbrigido.dev.dto.AttendanceDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDB implements AttendanceDAO {

    private Connection connection;

    public AttendanceDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(AttendanceDTO dto) {
        String sql = "insert into attendance(customer_id, user_id) values (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, dto.customerId());
            ps.setLong(2, dto.userId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong" + e);
        }

    }

    @Override
    public List<AttendanceDTO> getAll() {
        List<AttendanceDTO> list = new ArrayList<>();
        String sql = "select customer_id, currentdate from attendance where date(currentdate) = curdate()";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new AttendanceDTO(
                        rs.getLong(1),
                        rs.getTimestamp(2).toLocalDateTime().toLocalTime()
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong " + e);
        }
        return list;
    }
}
