package com.example.blog.controller.api;

import com.example.blog.dto.LikesDto;
import com.example.blog.service.impl.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LikesApiController {

    private final LikesService likesService;

    @PostMapping("/likes")
    public ResponseEntity<Integer> insertLike(@RequestBody final LikesDto likesDto) {
        String msg = likesService.insertLike(likesDto);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }
}
