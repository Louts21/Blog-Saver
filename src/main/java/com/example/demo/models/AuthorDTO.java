package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter @Setter
@NoArgsConstructor
@RequiredArgsConstructor
public class AuthorDTO {

    private @Id String id;

    private @NonNull String userName;

    public AuthorDTO(String id, @NonNull String userName) {
        this.id = id;
        this.userName = userName;
    }

}
