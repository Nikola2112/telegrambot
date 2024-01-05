package com.goit.excetpion;


public class ResourcesNotFound extends RuntimeException {
    public ResourcesNotFound(Long id) {
        super("Resources with ID: " + id + "not found!");
    }
}
