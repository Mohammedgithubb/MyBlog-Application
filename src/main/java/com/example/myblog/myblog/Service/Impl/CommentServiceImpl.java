package com.example.myblog.myblog.Service.Impl;

import com.example.myblog.myblog.Dto.CommentDto;
import com.example.myblog.myblog.Entity.Comment;
import com.example.myblog.myblog.Entity.Post;
import com.example.myblog.myblog.Repository.CommentRepository;
import com.example.myblog.myblog.Repository.PostRepository;
import com.example.myblog.myblog.Service.CommentService;
import com.example.myblog.myblog.execption.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepo;

    private PostRepository postRepo;

    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepo, PostRepository postRepo,ModelMapper modelMapper) {
        this.commentRepo = commentRepo;
        this.postRepo = postRepo;
        this.modelMapper=modelMapper;
    }


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {

        Post post = postRepo.findById(postId).orElseThrow(                                                                                    //These two lines are used for identifying post by Id.
                () -> new ResourceNotFoundException("Post Not Found with id"+postId));


        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment c = commentRepo.save(comment);

        return mapToDto(c);

    }

    @Override
    public void deleteCommentById(long postid,long commentId) {

        postRepo.findById(postid).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found By Id"+postid));

        commentRepo.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {      //To return anything we use CommentDto class, not Entity class.

        List<Comment> comments = commentRepo.findByPostId(postId);
        List<CommentDto> dto = comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
        return dto;
    }

    @Override
    public CommentDto updateComment(long commentId, CommentDto commentDto) {
        Comment com = commentRepo.findById(commentId).get();
        Post post = postRepo.findById(com.getPost().getId()).get();     //This will
        Comment comment = mapToEntity(commentDto);  //This will convert Dto to Entity.
        comment.setId(commentId);   //This will set only commentId
        com.setPost(post);      //This will set postId also in comments columm.
        
        Comment savedComment = commentRepo.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;
    }


    Comment mapToEntity(CommentDto dto){

        Comment comment = modelMapper.map(dto, Comment.class);
        //Comment comment = new Comment();
        //comment.setName(dto.getName());
        //comment.setEmail(dto.getEmail());
        //comment.setBody(dto.getBody());
        return comment;
    }

    CommentDto mapToDto(Comment comment) {

        CommentDto dto = modelMapper.map(comment, CommentDto.class);
        //CommentDto dto = new CommentDto();
        //dto.setName(comment.getName());
        //dto.setEmail(comment.getEmail());
        //dto.setBody(comment.getBody());
        return dto;
    }
}
