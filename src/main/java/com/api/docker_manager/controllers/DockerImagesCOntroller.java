package com.api.docker_manager.controllers;

import com.api.docker_manager.services.DockerService;
import com.github.dockerjava.api.model.Image;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/images")
public class DockerImagesCOntroller {

    private final DockerService dockerService;

    @GetMapping("")
    public List<Image> listImages() {
        return dockerService.imageList();
    }

    @GetMapping("/filter")
    public List<Image> listImages(@RequestParam(required = true, defaultValue = "image-") String filterName) {
        return dockerService.filterImages(filterName);
    }
}
