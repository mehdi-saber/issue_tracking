package com.atrosys.platform.model.service;

import com.atrosys.platform.model.da.repository.LeaveRepository;
import com.atrosys.platform.model.to.Leave;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kasra on 7/24/2018.
 */
@Service("leaveService")
public class LeaveServiceImpl implements LeaveService {
    @Autowired
    LeaveRepository leaveRepository;

    @Override
    public void save(Leave leave) {
        leaveRepository.save(leave);
    }

    @Override
    public Leave findByLeaveName(String name) {
        return leaveRepository.findByName(name);
    }

    @Override
    public Leave findByLeaveId(int id) {
        return leaveRepository.findById(id);
    }

    @Override
    public List<Leave> findAllLeave() {
        return leaveRepository.findAll();
    }
}
