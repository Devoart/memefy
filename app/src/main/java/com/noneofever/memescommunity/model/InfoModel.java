package com.noneofever.memescommunity.model;

public class InfoModel {

    String name;
    String position;
    String description;
    String image;

    public InfoModel(String name, String position, String description, String image) {
        this.name = name;
        this.position = position;
        this.description = description;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
