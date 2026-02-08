package com.jbrigido.dev.dao.user;

import com.jbrigido.dev.dto.UserDTO;

public interface UserDAO {

     UserDTO getUser(String username, String password);

}
