package com.isa.project.dto;

import com.isa.project.model.Image;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class ImageDTO {
    private Long id;
    private String base64;

    public ImageDTO() { }

    public ImageDTO(Long id, String base64) {
        this.id = id;
        this.base64 = base64;
    }

    public ImageDTO(Image image) {
        this.id = image.getId();
        File file = new File(image.getPath());

        try {
            FileInputStream fl = new FileInputStream(file);
            byte[] arr = new byte[(int)file.length()];
            fl.read(arr);
            fl.close();
            this.base64 = Base64.getEncoder().encodeToString(arr);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Long getId() {
        return id;
    }

    public String getBase64() {
        return base64;
    }
}
