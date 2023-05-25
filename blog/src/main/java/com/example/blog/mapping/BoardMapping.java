package com.example.blog.mapping;

import com.example.blog.dto.BoardDto;
import com.example.blog.entity.Board;

public class BoardMapping {

    public static BoardDto convertToDto(Board board, int replyCnt) {
        return new BoardDto().builder()
                .title(board.getTitle())
                .content(board.getContent())
                .replyCnt(replyCnt)
                .build();
    }
}
