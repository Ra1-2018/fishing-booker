package com.isa.project.serviceImpl;

import com.isa.project.model.Adventure;
import com.isa.project.repositoryImpl.InMemoryAdventureRepository;
import com.isa.project.service.AdventureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class AdventureServiceImpl implements AdventureService {
    @Autowired
    private InMemoryAdventureRepository adventureRepository;

    @Override
    public Collection<Adventure> findAll() {
        Collection<Adventure> adventures = adventureRepository.findAll();
        return adventures;
    }

    @Override
    public Adventure findOne(long id) {
        Adventure adventure = adventureRepository.findOne(id);
        return adventure;
    }

    @Override
    public Adventure create(Adventure adventure) throws Exception {
        return null;
    }

    @Override
    public Adventure update(Adventure adventure) throws Exception {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
