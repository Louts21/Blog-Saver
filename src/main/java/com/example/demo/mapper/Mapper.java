package com.example.demo.mapper;

import com.example.demo.models.Author;
import com.example.demo.models.AuthorDTO;
import com.example.demo.models.Blog;
import com.example.demo.models.BlogDTO;
import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Mapper {

    public static BlogDTO toBlogDTO(Blog blog) {
        return new BlogDTO(blog.getId(), blog.getTitle(), blog.getContent(), blog.getCategories(), toAuthorDTO(blog.getAuthor()));
    }

    public static Blog toBlog(BlogDTO blogDTO) {
        return new Blog(blogDTO.getId(), blogDTO.getTitle(), blogDTO.getContent(), blogDTO.getCategories(), toAuthor(blogDTO.getAuthorDTO()));
    }

    public static List<BlogDTO> toBlogDTOs(List<Blog> blogs) {
        return blogs.stream().map(Mapper::toBlogDTO).toList();
    }

    public static Author toAuthor(AuthorDTO authorDTO) {
        return new Author(authorDTO.getId(), authorDTO.getUserName());
    }

    public static AuthorDTO toAuthorDTO(Author author) {
        return new AuthorDTO(author.getId(), author.getUserName());
    }

}
