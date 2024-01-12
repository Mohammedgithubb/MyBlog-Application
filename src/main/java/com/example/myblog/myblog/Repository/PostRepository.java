package com.example.myblog.myblog.Repository;

import com.example.myblog.myblog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostRepository extends JpaRepository<Post, Long> {


}
