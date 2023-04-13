package com.example.blog.service.impl;

import com.example.blog.entity.Board;
import com.example.blog.entity.Users;
import com.example.blog.repository.BoardRepository;
import com.example.blog.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;

    private final Logger LOGGER = LoggerFactory.getLogger(BoardServiceImpl.class);

    @Transactional
    public void write(Board board, Users users) {
        board.setCount(0);
        board.setUsers(users);
        boardRepository.save(board);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Board> writeList(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Board viewBoard(Long id) {
        return boardRepository.findById(id)
            .orElseThrow(() -> {
                throw new IllegalArgumentException(String.format("ID : %d 로 Board를 찾을 수 없습니다.", id));
            }
        );
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
        LOGGER.info("boardService의 deleteById 작업 완료");
    }

}
