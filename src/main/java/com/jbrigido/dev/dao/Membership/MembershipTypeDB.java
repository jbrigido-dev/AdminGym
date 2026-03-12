package com.jbrigido.dev.dao.Membership;

import com.jbrigido.dev.dto.MembershipTypeDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MembershipTypeDB implements MembershipTypeDAO {
    private Connection connection;

    public MembershipTypeDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<MembershipTypeDTO> all() {
        List<MembershipTypeDTO> list = new ArrayList<>();
        String sql = "select id, description, duration_months, price from membershipTypes";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MembershipTypeDTO retrieved = new MembershipTypeDTO(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getFloat(4));
                list.add(retrieved);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Something was wrong!" + e);
        }
        return list;
    }

}
