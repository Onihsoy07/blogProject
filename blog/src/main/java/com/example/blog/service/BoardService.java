package com.example.blog.service;

import com.example.blog.entity.Board;
import com.example.blog.entity.Users;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BoardService {

        void write(Board board, Users users);

        Page<Board> writeList(Pageable pageable);

        Board viewBoard(Long id);

        void deleteById(Long id);

}
