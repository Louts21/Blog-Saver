package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter
@RequiredArgsConstructor
@Document(collection = "blogs")
public class Blog {

    private @Id String id;

    private @NonNull String title;
    private @NonNull String content;
    private @NonNull List<String> categories;
    private @NonNull LocalDateTime inserted;
    private LocalDateTime updated;

    private @NonNull Author author;

    public Blog(String id, @NonNull String title, @NonNull String content, @NonNull List<String> categories, @NonNull Author author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.categories = categories;
        this.author = author;
        this.inserted = LocalDateTime.now();
    }

}
