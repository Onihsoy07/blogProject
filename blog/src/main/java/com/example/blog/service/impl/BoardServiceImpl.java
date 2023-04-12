package com.example.blog.service.impl;

import com.example.blog.entity.Board;
import com.example.blog.entity.Users;
import com.example.blog.repository.BoardRepository;
import com.example.blog.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void write(Board board, Users users) {
        board.setCount(0);
        board.setUsers(users);
        boardRepository.save(board);
    }

    @Override
    public Page<Board> writeList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

}
