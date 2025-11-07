package com.example.demo.controller;

import com.example.demo.mapper.Mapper;
import com.example.demo.models.Blog;
import com.example.demo.models.BlogDTO;
import com.example.demo.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class BlogController {

    @Autowired
    private BlogRepository repository;

    @PostMapping("/blog")
    @PreAuthorize("hasRole('role_user')")
    public ResponseEntity<BlogDTO> create(@RequestBody BlogDTO blogDTO) {
        repository.save(Mapper.toBlog(blogDTO));
        return new ResponseEntity<>(blogDTO, HttpStatus.CREATED);
    }

    @GetMapping("/blog")
    public ResponseEntity<List<BlogDTO>> readAll() {
        return new ResponseEntity<>(Mapper.toBlogDTOs(repository.findAll()), HttpStatus.OK);
    }

    @GetMapping("/blog/{id}")
    public ResponseEntity<BlogDTO> readById(@PathVariable String id) {
        Optional<Blog> optionalBlog = repository.findById(id);
        return optionalBlog.map(blog ->
                new ResponseEntity<>(Mapper.toBlogDTO(blog), HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PutMapping("/blog/{id}")
    @PreAuthorize("hasRole('role_user')")
    public ResponseEntity<BlogDTO> update(@RequestBody BlogDTO blogDTO, @PathVariable String id) {
        Optional<Blog> optionalBlog = repository.findById(id).map(blog -> {
            blog = Mapper.toBlog(blogDTO);
            blog.setUpdated(LocalDateTime.now());
            return repository.save(blog);
        });
        if (optionalBlog.isPresent()) {
            return new ResponseEntity<>(blogDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("blog/{id}")
    @PreAuthorize("hasRole('role_user')")
    public void delete(@PathVariable String id) {
        repository.deleteById(id);
    }

}