package com.example.blog.repository;

import com.example.blog.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    Long countByBoard_id(Long id);

    Optional<Likes> findByBoard_idAndUsers_id(Long boardId, Long UsersId);

}
