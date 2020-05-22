package com.example.blogapi.repository;

import com.example.blogapi.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>{

    //Searches through blogs by title search query
    List<Blog> findByTitleContainingIgnoreCase(String title);

    //Orders blogs by dates in descending order
    List<Blog> findAllByOrderByCreatedAtDesc();

}
