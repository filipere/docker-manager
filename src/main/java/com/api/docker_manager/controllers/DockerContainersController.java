package com.api.docker_manager.controllers;

import com.api.docker_manager.services.DockerService;
import com.github.dockerjava.api.model.Container;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/containers")
public class DockerContainersController {

    private final DockerService dockerService;

    @GetMapping("")
    public List<Container> listContainers(@RequestParam(required = true, defaultValue = "true") boolean showAll) {
        return dockerService.listContainer(showAll);
    }

    @PostMapping("/{id}/start")
    public void startContainer(@PathVariable String id) {
        dockerService.startContainer(id);
    }

    @PostMapping("/{id}/stop")
    public void stopContainer(@PathVariable String id) {
        dockerService.stopContainer(id);
}

    @DeleteMapping("/{id}")
    public void deleteContainer(@PathVariable String id) {
        dockerService.deleteContainer(id);
    }

    @PostMapping("/create")
    public void createContainer(@RequestParam(required = true, defaultValue = "container-") String containerName) {
        dockerService.createContainer(containerName);
    }
}
