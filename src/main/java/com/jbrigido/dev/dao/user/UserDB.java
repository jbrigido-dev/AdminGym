package com.jbrigido.dev.dao.user;

import com.jbrigido.dev.core.storage.local.LocalDB;
import com.jbrigido.dev.dto.UserDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDB implements UserDAO {

    private Connection connection;

    public UserDB(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserDTO getUser(String username, String password) {
        String query = "Select username, password from users where username = (?) and password =(?)";
        UserDTO found = null;
        connection = LocalDB.getInstance();
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                found = new UserDTO(
                        null
                        , null
                        , rs.getString(1)
                        , rs.getString(2)
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return found;
    }
}
