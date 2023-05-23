package com.example.blog.repository;

import com.example.blog.dto.ReplyDto;
import com.example.blog.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query(value = "INSERT INTO reply(content, boardId, usersId, depth, createDate) VALUES(?1, ?2, ?3, ?4, now())", nativeQuery = true)
    int replySave(String content, Long boardId, Long usersId, int depth);

}
