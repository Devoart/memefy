package com.noneofever.memescommunity.model;

public class CategoryModel {
    private String categoryName;
    private String categoryImgageLink;
    private int categoryNumber;


    public CategoryModel(String categoryName, String categoryImgageLink, int categoryNumber) {
        this.categoryName = categoryName;
        this.categoryImgageLink = categoryImgageLink;
        this.categoryNumber = categoryNumber;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImgageLink() {
        return categoryImgageLink;
    }

    public void setCategoryImgageLink(String categoryImgageLink) {
        this.categoryImgageLink = categoryImgageLink;
    }

    public int getCategoryNumber() {
        return categoryNumber;
    }

    public void setCategoryNumber(int categoryNumber) {
        this.categoryNumber = categoryNumber;
    }
}
