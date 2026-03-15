package com.jbrigido.dev.dao.Membership;

import com.jbrigido.dev.dto.MembershipTypeDTO;

import java.util.List;
import java.util.Optional;

public interface MembershipTypeDAO {

    List<MembershipTypeDTO> all();

    Optional<MembershipTypeDTO> getByID(int id);

}
