package com.isa.project.controller;

import com.isa.project.dto.ImageDTO;
import com.isa.project.model.Image;
import com.isa.project.model.Service;
import com.isa.project.service.ImageService;
import com.isa.project.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ServiceService serviceService;

    private static final String BASE_PATH ="/tmp";

    @GetMapping(value="/{serviceId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<ImageDTO>> getImagesByService(@PathVariable long serviceId) {

        Service service = serviceService.findById(serviceId);

        Collection<Image> images = imageService.findByService(service);

        Collection<ImageDTO> imageDTOS = new HashSet<>();

        for(Image image: images) {
            imageDTOS.add(new ImageDTO(image));
        }

        return new ResponseEntity<>(imageDTOS, HttpStatus.OK);
    }

    @GetMapping(value="/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable long id){

        Image image = imageService.findById(id);
        if(image == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        File file = new File(image.getPath());
        if(!file.delete()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        imageService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @PostMapping("/{id}")
    public ResponseEntity<?> uploadImage(@PathVariable long id, @RequestBody MultipartFile image ) {

        Service service = serviceService.findById(id);
        if(service == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String fileName = image.getOriginalFilename();
        String normalizedPath = Paths.get(fileName).normalize().toString();
        File file = new File(BASE_PATH, normalizedPath);
        try {
            if (file.getCanonicalPath().startsWith(BASE_PATH)) {
                imageService.save(new Image(null, file.getPath(), service));
                image.transferTo(file);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
