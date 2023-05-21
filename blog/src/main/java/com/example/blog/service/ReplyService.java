package com.example.blog.service;

import com.example.blog.dto.ReplyDto;
import com.example.blog.entity.Users;

public interface ReplyService {

    void saveComment(Long BoardId, Users users, ReplyDto replyDto);

    void deleteComment(Long id);

    void modifyComment(Long id, ReplyDto replyDto);

}
