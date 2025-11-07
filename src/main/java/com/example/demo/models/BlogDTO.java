package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.List;

@Getter @Setter
public class BlogDTO {

    private @Id String id;

    private @NonNull String title;
    private @NonNull String content;
    private @NonNull List<String> categories;

    private @NonNull AuthorDTO authorDTO;

    public BlogDTO(String id, @NonNull String title, @NonNull String content, @NonNull List<String> categories, @NonNull AuthorDTO authorDTO) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categories = categories;
        this.authorDTO = authorDTO;
    }

}
