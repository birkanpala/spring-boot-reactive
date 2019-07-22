package com.pala.reactive.controller;

import com.pala.reactive.data.Blog;
import com.pala.reactive.service.BlogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/api/v1/blog")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public Flux<Blog> findAll() {
        log.debug("findAll Blog");
        return blogService.findAll();
    }

    @GetMapping("/find")
    public Flux<Blog> findByAuthor(@RequestParam String author) {
        log.debug("findByAuthor Blog with author : {}", author);
        return blogService.findByAuthor(author);
    }

    @GetMapping("/{id}")
    public Mono<Blog> findOne(@PathVariable String id) {
        log.debug("findOne Blog with id : {}", id);
        return blogService.findOne(id);
    }

    @GetMapping("/{title}")
    public Mono<Blog> findByTitle(@PathVariable String title) {
        log.debug("findByTitle Blog with title : {}", title);
        return blogService.findByTitle(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Blog> create(@RequestBody Blog blog) {
        log.debug("create Blog with blog : {}", blog);
        return blogService.createBlog(blog);
    }

    @DeleteMapping("/{id}")
    public Mono<Boolean> delete(@PathVariable String id) {
        log.debug("delete Blog with id : {}", id);
        return blogService.delete(id);
    }

    @PutMapping("/{id}")
    public Mono<Blog> updateBlog(@RequestBody Blog blog, @PathVariable String id) {
        log.debug("updateBlog Blog with id : {} and blog : {}", id, blog);
        return blogService.updateBlog(blog, id);
    }
}