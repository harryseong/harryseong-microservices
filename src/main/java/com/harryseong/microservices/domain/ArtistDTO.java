package com.harryseong.microservices.domain;

public class ArtistDTO {
    private String name;

    public ArtistDTO() {
    }

    public ArtistDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
