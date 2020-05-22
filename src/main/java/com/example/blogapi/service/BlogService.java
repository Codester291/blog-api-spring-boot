package com.example.blogapi.service;

import com.example.blogapi.model.Blog;
import com.example.blogapi.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }

    public List<Blog> saveMultipleBlogs(List<Blog> blogs) {
        return blogRepository.saveAll(blogs);
    }

    public List<Blog> retrieveBlogs() {
        return blogRepository.findAll();
    }

    public Optional<Blog> findBlog(Long id) {
        return blogRepository.findById(id);
    }

    public List<Blog> findByTitle(String title) {
        return blogRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Blog> getByDate() {
        return blogRepository.findAllByOrderByCreatedAtDesc();
    }


    public Blog updateBlog(Long id, Blog blog) {
        Blog blogToUpdate = blogRepository.getOne(id);
        blogToUpdate.setTitle(blog.getTitle());
        blogToUpdate.setContent(blog.getContent());
        return blogRepository.save(blogToUpdate);
    }

    public void deleteBlogs() {
        blogRepository.deleteAll();
    }



    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

}
