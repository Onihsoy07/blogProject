package com.example.blog.service;

import com.example.blog.entity.Board;
import com.example.blog.entity.Users;

public interface BoardService {

        void write(Board board, Users users);

}
