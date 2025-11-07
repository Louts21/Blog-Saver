package com.example.demo;

import static org.assertj.core.api.Assertions.*;

import com.example.demo.mapper.Mapper;
import com.example.demo.models.Author;
import com.example.demo.models.AuthorDTO;
import com.example.demo.models.Blog;
import com.example.demo.models.BlogDTO;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

@SpringBootTest
class DemoApplicationTests {

    private final Author author = new Author("1", "Tomtom");
    private final AuthorDTO authorDTO = new AuthorDTO("2", "Timtim");
    private final Blog firstBlog = new Blog("1", "Das Leben in der freien Burg", "Es war einmal vor langer Zeit...", new LinkedList<>() {{add("Burg");add("Erfahrung");add("Reise");}}, author);
    private final Blog secondBlog = new Blog("2", "Die Freiheit des Einzelnen", "Erst gestern habe ich wieder daran gedacht...", new LinkedList<>() {{add("Reise");add("Bericht");}}, author);
    private final BlogDTO firstBlogDTO = new BlogDTO("3", "Am Ufer zu Teva", "Es war von großer Bedeutung, dass...", new LinkedList<>(){{add("Geschichte");}}, authorDTO);


    @Test
    public void TestNormalBehaviourToBlogDTO() {
        BlogDTO blogDTO = Mapper.toBlogDTO(firstBlog);
        assertThat(IsBlogAndBlogDtoEqual(firstBlog, blogDTO)).isTrue();
    }
    @Test
    public void TestWrongBlogsToBlogDTO() {
        BlogDTO blogDTO = Mapper.toBlogDTO(firstBlog);
        assertThat(IsBlogAndBlogDtoEqual(secondBlog, blogDTO)).isFalse();
    }

    @Test
    public void TestToBlogDTOs() {
        List<BlogDTO> blogDTOS = Mapper.toBlogDTOs(new LinkedList<>(){{add(firstBlog); add(secondBlog);}});
        for (BlogDTO value : blogDTOS) {
            if (value.getTitle().equals(firstBlog.getTitle())) {
                assertThat(IsBlogAndBlogDtoEqual(firstBlog, value)).isTrue();
            } else if (value.getTitle().equals(secondBlog.getTitle())) {
                assertThat(IsBlogAndBlogDtoEqual(secondBlog, value)).isTrue();
            } else {
                assertThat(firstBlog).isEqualTo(secondBlog); // This assert is created to fail. It should never be reached~
            }
        }
    }

    @Test
    public void TestToBlog() {
        Blog blog = Mapper.toBlog(firstBlogDTO);
        assertThat(IsBlogAndBlogDtoEqual(blog, firstBlogDTO)).isTrue();
    }

    @Test
    public void TestToAuthor() {
        Author author = Mapper.toAuthor(authorDTO);
        assertThat(IsAuthorAndAuthorDtoEqual(author, authorDTO)).isTrue();
    }

    @Test
    public void TestToAuthorDTO() {
        AuthorDTO authorDTO = Mapper.toAuthorDTO(author);
        assertThat(IsAuthorAndAuthorDtoEqual(author, authorDTO)).isTrue();
    }


    private boolean IsBlogAndBlogDtoEqual(Blog blog, BlogDTO blogDTO) {
        if (!Objects.equals(blog.getId(), blogDTO.getId())) {
            return false;
        } else if (!Objects.equals(blog.getTitle(), blogDTO.getTitle())) {
            return false;
        } else if (!Objects.equals(blog.getContent(), blogDTO.getContent())) {
            return false;
        } else if (!Objects.equals(blog.getCategories(), blogDTO.getCategories())) {
            return false;
        } else {
            return IsAuthorAndAuthorDtoEqual(blog.getAuthor(), blogDTO.getAuthorDTO());
        }
    }

    private boolean IsAuthorAndAuthorDtoEqual(Author author, AuthorDTO authorDTO) {
        if (!Objects.equals(author.getId(), authorDTO.getId())) {
            return false;
        } else {
            return Objects.equals(author.getUserName(), authorDTO.getUserName());
        }
    }

}
