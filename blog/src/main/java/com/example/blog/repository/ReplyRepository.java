package com.example.blog.repository;

import com.example.blog.dto.ReplyDto;
import com.example.blog.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReplyRepository extends JpaRepository<Reply, Long> {

    @Modifying
    @Query(value = "INSERT INTO reply(content, boardId, usersId, replyId, depth, createDate) VALUES(?1, ?2, ?3, ?4, ?5, now())", nativeQuery = true)
    int replySave(String content, Long boardId, Long usersId, Long replyId, int depth);

    List<Reply> findByBoard_IdAndDepthOrderByCreateDateAsc(Long boardId, int depth);

}
