package com.example.myblog.myblog.Repository;

import com.example.myblog.myblog.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
