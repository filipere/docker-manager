package com.api.docker_manager.services;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.exception.NotModifiedException;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DockerService {

    private final DockerClient dockerClient;

    public DockerService (DockerClient client ) {
        this.dockerClient = client;
    }

    public List<Container> listContainer(boolean all) {
        return dockerClient.listContainersCmd().withShowAll(all).exec();
    }

    public List<Image> imageList() {
        return dockerClient.listImagesCmd().exec();
    }

    public List<Image> filterImages(String filterName) {
        return dockerClient.listImagesCmd().withImageNameFilter(filterName).exec();
    }

    public void startContainer(String containerId) {

        var container = dockerClient
                .inspectContainerCmd(containerId)
                .exec();

        if (container.getState().getRunning()) {
            return;
        }

        dockerClient
                .startContainerCmd(containerId)
                .exec();
    }

    public void stopContainer(String containerId) {
        dockerClient.stopContainerCmd(containerId).exec();
    }

    public void deleteContainer(String containerId) {
        dockerClient.removeContainerCmd(containerId).exec();
    }

    public void createContainer(String containerName) {
        dockerClient.createContainerCmd(containerName).exec();
    }
}
