package com.example.something.article.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateArticleDetailsDTO {

    private String name;

    private String description;

    private int price;

    private String imagePath;
}
