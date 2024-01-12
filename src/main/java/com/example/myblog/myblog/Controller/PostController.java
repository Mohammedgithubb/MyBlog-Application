package com.example.myblog.myblog.Controller;

import com.example.myblog.myblog.Dto.PostDto;
import com.example.myblog.myblog.Dto.PostResponse;
import com.example.myblog.myblog.Repository.PostRepository;
import com.example.myblog.myblog.Service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult result){

       if (result.hasErrors()){         //Here, BindingResult if it has any error if it has then the if-else condition will work. And in return type we put ? (Bcz for if else condition to work we use ?).
           return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
        PostDto dto = postService.createPost(postDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePostById(@PathVariable long id){
        postService.deletePostById(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable long id){
        PostDto postDto = postService.getPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id,@RequestBody PostDto postDto){      //id is storerd in id and json object is stored in postDto.
        PostDto dto = postService.updatePost(id,postDto);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }

    @GetMapping
    public <name> PostResponse getAllPosts(         //Here the return type was List<PostDto> but we have to generate the response so we have created PostResponse as the return type.
            @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name= "pageSize",defaultValue = "5",required = false) int pageSize,
            @RequestParam(value="sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value ="sortDir",defaultValue = "asc",required = false)String sortDir){

        PostResponse postDtos = postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
        return postDtos;
    }

}
