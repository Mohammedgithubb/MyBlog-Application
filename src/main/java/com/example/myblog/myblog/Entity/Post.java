package com.example.myblog.myblog.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", unique = true)
    private String title;
    @Column(name = "description",unique = true)
    private String description;
    @Column(name = "content",unique = true)
    private String content;



    //This below line will be doesn't need to rememnber, instead they ask about mapping techniques.
//    @OneToMany(mappedBy="post",cascade = CascadeType.ALL,orphanRemoval=true)  //Cascade All: means any changes you made in post then it will reflect to comment also.
//    List<Comment> comments = new ArrayList<>();



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
