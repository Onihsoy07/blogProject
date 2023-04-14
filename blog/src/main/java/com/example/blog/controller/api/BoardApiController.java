package com.example.blog.controller.api;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.dto.ResponseDto;
import com.example.blog.entity.Board;
import com.example.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class BoardApiController {

    private final BoardService boardService;

    private final Logger LOGGER = LoggerFactory.getLogger(BoardApiController.class);

    @PostMapping("/boards")
    public ResponseEntity<Integer> save(@RequestBody Board board,
                                     @AuthenticationPrincipal PrincipalDetail principal) {
        boardService.write(board, principal.getUsers());
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.value());
    }

    @DeleteMapping(value="/board/{id}")
    public ResponseEntity<Integer> deleteById(@PathVariable final Long id) {
        LOGGER.info("[delete] /api/board/{} 호출됨", id);
        boardService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body(HttpStatus.OK.value());
    }

}
