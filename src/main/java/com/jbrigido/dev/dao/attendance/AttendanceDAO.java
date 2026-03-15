package com.jbrigido.dev.dao.attendance;

import com.jbrigido.dev.dto.AttendanceDTO;

import java.util.List;

public interface AttendanceDAO {

    void save(AttendanceDTO dto);
    List<AttendanceDTO> getAll();
    
}
