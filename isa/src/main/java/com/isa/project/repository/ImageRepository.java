package com.isa.project.repository;

import com.isa.project.model.Image;
import com.isa.project.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    public List<Image> findByService(Service service);
}
