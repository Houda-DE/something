package com.example.something.article.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddArticleDTO {

    private String name;

    private String description;

    private int price;

    private int quantite;

    private String imagePath;

}
