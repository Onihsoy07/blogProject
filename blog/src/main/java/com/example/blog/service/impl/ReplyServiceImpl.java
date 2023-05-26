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

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl {

    private final ReplyRepository replyRepository;

    private final BoardRepository boardRepository;

    private List<Reply> replyList = new ArrayList<>();

    @Transactional
    public void saveComment(Long boardId, Users users, ReplyDto replyDto) {
        replyRepository.replySave(replyDto.getContent(), boardId, users.getId(), replyDto.getReplyId(), replyDto.getDepth());
    }

    @Transactional
    public void deleteComment(Long id) {
        replyRepository.deleteById(id);
    }

    @Transactional
    public void modifyComment(Long id, ReplyDto replyDto) {
        Reply reply = getReply(id);
        reply.setContent(replyDto.getContent());
    }

    private Reply getReply(Long id) {
        Reply reply = replyRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException(String.format("Reply ID : %d 로 찾을 수 없습니다.", id));
        });
        return reply;
    }

    public List<Reply> replyListSet(Long boardId) {
        replyList.clear();
        List<Reply> rList = replyRepository.findByBoard_IdAndDepthOrderByCreateDateAsc(boardId, 0);
        for (Reply reply : rList) {
            def(reply);
        }
        return replyList;
    }

    private void def(Reply getReply) {
        replyList.add(getReply);
        List<Reply> rList = getReply.getReplyList();
        if (rList.size()!=0) {
            for (Reply reply : rList) {
                def(reply);
            }
        }
    }

}
