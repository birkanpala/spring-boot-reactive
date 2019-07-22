package com.pala.reactive.service;

import com.pala.reactive.data.Blog;
import com.pala.reactive.repo.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    @Autowired
    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Mono<Blog> createBlog(Blog blog) {
        return blogRepository.insert(blog);
    }

    public Flux<Blog> findAll() {
        return blogRepository.findAll();
    }

    public Mono<Blog> updateBlog(Blog blog, String id) {
        return findOne(id).doOnSuccess(found -> {
            found.setContent(blog.getContent());
            found.setTitle(blog.getTitle());
            found.setAuthor(blog.getAuthor());
            blogRepository.save(found).subscribe();
        });
    }

    public Mono<Blog> findOne(String id) {
        return blogRepository.findById(id).
                switchIfEmpty(Mono.error(new Exception("No Blog found with Id: " + id)));
    }

    public Mono<Blog> findByTitle(String title) {
        return blogRepository.findByTitle(title).
                switchIfEmpty(Mono.error(new Exception("No Blog found with title: " + title)));
    }

    public Flux<Blog> findByAuthor(String author) {
        return blogRepository.findByAuthor(author)
                .switchIfEmpty(Mono.error(new Exception("No Blog found with author : " + author)));
    }

    public Mono<Boolean> delete(String id) {
        return findOne(id).doOnSuccess(blog ->
                blogRepository.delete(blog).subscribe()
        ).flatMap(blog -> Mono.just(Boolean.TRUE));
    }
}
