package com.example.myblog.myblog.Service.Impl;

import com.example.myblog.myblog.Dto.PostDto;
import com.example.myblog.myblog.Dto.PostResponse;
import com.example.myblog.myblog.Entity.Post;
import com.example.myblog.myblog.Repository.PostRepository;
import com.example.myblog.myblog.Service.PostService;
import com.example.myblog.myblog.execption.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;

    private ModelMapper modelMapper;

    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepo = postRepository;
        this.modelMapper = modelMapper;
    }



    //This method will used for creation of new Post.
    @Override
    public PostDto createPost(PostDto postDto) {

        Post post = mapToEntity(postDto);
        Post savedpost = postRepo.save(post);
        PostDto dto = mapToDto(savedpost);
        return dto;
    }



    //This method will delete the post by PostId.
    @Override
    public void deletePostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post Not Found with this id" + id));
        postRepo.deleteById(id);
    }



    //This method is used to get Posts by PostId.
    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Post Not Found with this id" + id));
        return mapToDto(post);
    }



    //This method will used for updating the post.
    @Override
    public PostDto updatePost(long id, PostDto postDto) {
        Post post = postRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Post not fond with id" + id));

        post.setTitle(postDto.getTitle());              //These 3 lines for updating the data.
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepo.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    //URL
    //http://localhost:8080/api/posts?pageNo=0&pageSize=3?sortBy=id&sortDir=ASC



    //This method will getAllPosts and print the posts in the form of dtos.(And in this we have used stream API concept of map to convert that post to postDto).
    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();   //This line is representing ternary operator Ex: (result = condition ? valueIfTrue : valueIfFalse); means it is representing like if-else conditioon.
        //In above line equalsIgnoreCase represents as it will ignore the upperCase and LowerCase.

        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);   //This line is for requesting pageNo and pageSize, And storing it in pageable object.
        Page<Post> pagepostObject = postRepo.findAll(pageable);  //And from that object we are getting posts in pages.
        List<Post> posts = pagepostObject.getContent();     //This line explains that the(.getcontent()) method will convert page to list of posts format.
        List<PostDto> dtos = posts.stream().map(post -> mapToDto(post)).collect(Collectors.toList());

        PostResponse response = new PostResponse();     //This is the object creation of PostResponse Class in Dto Package, where it is used to get the response of which pageNo,totalpages and so on to be displayed.
        response.setDto(dtos);
        response.setPageNo(pagepostObject.getNumber());     //Here, pagepostObject is used because it will exactly show  pageSize": 4,"pageNo": 0,"lastPage": true,"totalPages": 1
        response.setTotalPages(pagepostObject.getTotalPages());
        response.setLastPage(pagepostObject.isLast());
        response.setPageSize(pagepostObject.getSize());
        return response;
    }



    //This method is used for converting PostDto to entity i.e(Post)
    public Post mapToEntity(PostDto postDto){
        
//        Post post = modelMapper.map(postDto,Post.class);
        //It Define a mapping between two classes
        //modelMapper.typeMap(SourceClass.class, DestinationClass.class)


        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;
   }



   //This method will convert entity(Post) into PostDto.
   public PostDto mapToDto(Post savedpost){

//       PostDto postDto = modelMapper.map(savedpost, PostDto.class);
       PostDto postDto = new PostDto();
        postDto.setId(savedpost.getId());
        postDto.setTitle(savedpost.getTitle());
        postDto.setDescription(savedpost.getDescription());
        postDto.setContent(savedpost.getContent());
        return postDto;
   }
}
