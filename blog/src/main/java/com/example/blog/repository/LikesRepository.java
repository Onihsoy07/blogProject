package com.example.blog.repository;

import com.example.blog.entity.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Query(value = "select count(*) from likes where likes.board_id :id", nativeQuery = true)
    Long countByLikes(Long id);

}
