package com.atrosys.platform.model.service;


import com.atrosys.platform.model.to.Leave;

import java.util.List;

public interface LeaveService {
    void save(Leave leave);
    Leave findByLeaveName(String name);
    Leave findByLeaveId(int id);
    List<Leave> findAllLeave();
}
