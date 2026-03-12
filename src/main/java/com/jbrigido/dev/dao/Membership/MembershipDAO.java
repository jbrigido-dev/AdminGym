package com.jbrigido.dev.dao.Membership;

import com.jbrigido.dev.dto.MembershipDTO;

import java.util.List;
import java.util.Optional;

public interface MembershipDAO {


    List<MembershipDTO> all();

    Optional<MembershipDTO> getLastMembershipById(long id);

    List<MembershipDTO> allById(long id);

    void save(MembershipDTO membershipDTO);

    void deactivateById(long id);

}
