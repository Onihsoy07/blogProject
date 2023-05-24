package com.example.blog.service.impl;

import com.example.blog.dto.LikesDto;
import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Likes;
import com.example.blog.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikesService {

    private final LikesRepository likesRepository;
    private final UsersServiceImpl usersService;
    private final BoardServiceImpl boardService;

    @Transactional
    public ResponseDto<String> insertLike(LikesDto likesDto) {
        if (duplicationLikesCheck(likesDto)) {
            Likes likes = new Likes().builder()
                    .board(boardService.findById(likesDto.getBoardId()))
                    .users(usersService.findById(likesDto.getUsersId()))
                    .build();
            likesRepository.save(likes);
            return new ResponseDto<>(HttpStatus.OK.value(), "좋아요를 하였습니다.");
        }
        return new ResponseDto<>(HttpStatus.PRECONDITION_REQUIRED.value(), "이미 좋아요를 하였습니다.");
    }

    @Transactional
    public Long countLikes(Long id) {
        return likesRepository.countByBoard_id(id);
    }

    
    //true면 like없음
    @Transactional(readOnly = true)
    private Boolean duplicationLikesCheck(LikesDto likesDto) {
        Optional<Likes> likes = likesRepository.findByBoard_idAndUsers_id(likesDto.getBoardId(), likesDto.getUsersId());
        return likes.isEmpty();
    }


}
