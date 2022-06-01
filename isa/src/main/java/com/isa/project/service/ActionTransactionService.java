package com.isa.project.service;

import com.isa.project.model.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class ActionTransactionService {

    @Autowired
    private ServiceService serviceService;

    @Autowired
    private ActionService actionService;

    @Transactional(readOnly = false)
    public Action createAction(Action action) throws OptimisticLockingFailureException {
        serviceService.RemoveFreePeriodAction(action);
        return actionService.save(action);
    }
}
