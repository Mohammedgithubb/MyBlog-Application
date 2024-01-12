package com.example.myblog.myblog.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comments")
@Entity
public class Comment {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "body", unique = true)
    private String body;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "name",unique = true)
    private String name;

    
    @ManyToOne
    @JoinColumn(name = "post_id")   //JoinColumn: means it declare as Foriegn key.
    private Post post;

    public Post getPost() {
        return post;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPost(Post post) {
        this.post=post;
    }
}
