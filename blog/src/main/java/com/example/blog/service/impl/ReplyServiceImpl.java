package com.example.blog.service.impl;

import com.example.blog.dto.ReplyDto;
import com.example.blog.entity.Board;
import com.example.blog.entity.Reply;
import com.example.blog.entity.Users;
import com.example.blog.repository.BoardRepository;
import com.example.blog.repository.ReplyRepository;
import com.example.blog.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

    private final ReplyRepository replyRepository;

    private final BoardRepository boardRepository;

    @Override
    @Transactional
    public void saveComment(Long boardId, Users users, ReplyDto replyDto) {
        Board board = boardRepository.findById(boardId).orElseThrow(() ->{
            throw new IllegalArgumentException(String.format("BoardId : %d 로 찾을 수 없습니다.", boardId));
        });
        Reply reply = new Reply().builder()
            .content(replyDto.getContent())
            .board(board)
            .users(users)
            .build();

        replyRepository.save(reply);

    }
}
