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
import java.util.Collection;
import java.util.HashSet;

@RestController
@RequestMapping("/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private ServiceService serviceService;

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
        //String path = System.getProperty("user.dir");
        //String filePath = path + "\\images\\" + fileName;
        String filePath = "tmp/" + fileName;

        imageService.save(new Image(null, filePath, service));

        try {
            image.transferTo( new File(filePath));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
