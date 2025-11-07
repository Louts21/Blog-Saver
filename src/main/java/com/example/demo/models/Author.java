package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class Author {

    private @Id String id;

    private @NonNull String userName;
    private @NonNull LocalDateTime inserted;
    private LocalDateTime updated;
    private LocalDateTime deleted;
    private boolean isDeleted;

    public Author(String id, @NonNull String userName) {
        this.id = id;
        this.userName = userName;
        this.inserted = LocalDateTime.now();
        this.isDeleted = false;
    }

}
