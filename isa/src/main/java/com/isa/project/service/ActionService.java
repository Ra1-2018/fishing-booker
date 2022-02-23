package com.isa.project.service;

import com.isa.project.model.Action;
import com.isa.project.repository.ActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActionService {
    @Autowired
    ActionRepository actionRepository;

    public List<Action> findByService(com.isa.project.model.Service service) {return actionRepository.findByService(service); }

    public Action findById(Long id) { return actionRepository.findById(id).orElse(null); }

    public void deleteById(Long id) { actionRepository.deleteById(id);}
}
