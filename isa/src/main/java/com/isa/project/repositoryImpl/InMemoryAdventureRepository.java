package com.isa.project.repositoryImpl;

import com.isa.project.model.Adventure;
import com.isa.project.model.Reservation;
import com.isa.project.repository.AdventureRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryAdventureRepository implements AdventureRepository {

    private final ConcurrentHashMap<Long, Adventure> adventures = new ConcurrentHashMap<Long, Adventure>();

    public InMemoryAdventureRepository() {
        this.adventures.put(1L, new Adventure(1, "Avantura 1", "Bul. Cara Lazara 58", "Mnogo zabavno", "Lepi Djordje", 3, new ArrayList<Reservation>(), "Some rules", "Some gear idk", "Gimme the money baby", null));
    }

    @Override
    public Collection<Adventure> findAll() {
        return this.adventures.values();
    }

    @Override
    public Adventure create(Adventure adventure) {
        return null;
    }

    @Override
    public Adventure findOne(long id) {
        return this.adventures.get(id);
    }

    @Override
    public Adventure update(Adventure adventure) {
        return null;
    }

    @Override
    public void delete(long id) {

    }
}
