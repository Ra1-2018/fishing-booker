package com.isa.project.service;

import com.isa.project.model.Client;
import com.isa.project.model.Image;
import com.isa.project.model.Reservation;
import com.isa.project.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public Image save(Image image) { return imageRepository.save(image); }

    public List<Image> findAll() { return  imageRepository.findAll(); }

    public Image findById(Long id) { return imageRepository.findById(id).orElse(null);}

    public void deleteById(Long id) { imageRepository.deleteById(id); }

    public List<Image> findByService(com.isa.project.model.Service service) { return imageRepository.findByService(service);}

}
