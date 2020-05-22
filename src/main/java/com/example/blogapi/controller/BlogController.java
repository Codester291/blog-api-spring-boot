package com.example.blogapi.controller;

import com.example.blogapi.model.Blog;
import com.example.blogapi.model.CustomResponse;
import com.example.blogapi.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/blog")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/save")
    public ResponseEntity<CustomResponse> save_blog(@RequestBody Blog blog) {
        try {
            Blog blog1 = blogService.saveBlog(blog);
            return new ResponseEntity<>(new CustomResponse("00", "success", blog1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save/multiple")
    public ResponseEntity<CustomResponse> saveAll(@RequestBody List<Blog> blog) {
        try {
            List<Blog> blog1 = blogService.saveMultipleBlogs(blog);
            return new ResponseEntity<>(new CustomResponse("00", "success", blog1), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieve")
    public ResponseEntity<CustomResponse> getBlogs() {
        try {
            List<Blog> blogs = blogService.retrieveBlogs();
            return new ResponseEntity<>(new CustomResponse("00", "success", blogs), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieve/{id}")
    public ResponseEntity<CustomResponse> getBlog(@PathVariable(value = "id") Long id) {
        try {
            Optional<Blog> optionalBlog = blogService.findBlog(id);
            return new ResponseEntity<>(new CustomResponse("00", "success", optionalBlog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieve/filter/by/{title}")
    public ResponseEntity<CustomResponse> getBlogByTitle(@PathVariable(value = "title") String title) {
        try {
            List<Blog> blogs = blogService.findByTitle(title);
            return new ResponseEntity<>(new CustomResponse("00", "success", blogs), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<CustomResponse> editBlog(@PathVariable(value = "id")Long id, @RequestBody Blog blog) {
        try {
            Blog updateBlog = blogService.updateBlog(id, blog);
            return new ResponseEntity<>(new CustomResponse("00", "updated blog successfully", updateBlog), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/retrieve/filter/by/date")
    public ResponseEntity<List<Blog>> getByDate() {
        return ResponseEntity.ok().body(blogService.getByDate());
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<CustomResponse> deleteAll() {
        try {
            return new ResponseEntity<>(new CustomResponse("00", "deleted all blogs successfully"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<CustomResponse> deleteBlog(@PathVariable(value = "id") Long id) {
        try {
            blogService.delete(id);
            return new ResponseEntity<>(new CustomResponse("00", "deleted blog"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new CustomResponse("99", "Error processing request", e.toString()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
