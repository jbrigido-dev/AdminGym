package com.jbrigido.dev.services;

import com.jbrigido.dev.dao.user.UserDB;
import com.jbrigido.dev.dto.UserDTO;

import java.sql.Connection;

public class UserService {

    private Connection connection;
    private UserDB dao;

    public UserService(Connection connection) {
        this.connection = connection;
        this.dao = new UserDB(connection);
    }

    public UserDTO logIn(String user, String pass) {
        return dao.getUser(user, pass);
    }
}
