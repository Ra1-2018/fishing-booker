package com.isa.project.repositoryImpl;

import com.isa.project.model.Cottage;
import com.isa.project.repository.CottageRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class InMemoryCottageRepository implements CottageRepository {

    private static AtomicLong counter = new AtomicLong();

    private final ConcurrentMap<Long, Cottage> cottages = new ConcurrentHashMap<Long, Cottage>();

    cottages.

    @Override
    public Collection<Cottage> findAll() {
        return this.cottages.values();
    }

    @Override
    public Cottage findOne(Long id) {
        return this.cottages.get(id);
    }

    @Override
    public void delete(Long id) {
        this.cottages.remove(id);
    }

}
