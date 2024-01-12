package com.example.myblog.myblog.Repository;

import com.example.myblog.myblog.Dto.CommentDto;
import com.example.myblog.myblog.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByPostId(long postId);
}
