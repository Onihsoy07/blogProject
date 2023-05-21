package com.example.blog.controller.api;

import com.example.blog.config.auth.PrincipalDetail;
import com.example.blog.dto.ReplyDto;
import com.example.blog.entity.Reply;
import com.example.blog.service.ReplyService;
import java.net.Inet4Address;
import javax.persistence.criteria.CriteriaBuilder.In;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply")
@RequiredArgsConstructor
public class ReplyApiController {

    private final ReplyService replyService;

    @PostMapping("/board/{id}")
    public ResponseEntity<Integer> saveComment(@PathVariable final Long id,
                                               @RequestBody final ReplyDto replyDto,
                                               @AuthenticationPrincipal PrincipalDetail principal) {
        replyService.saveComment(id, principal.getUsers(), replyDto);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    @DeleteMapping("/{replyId}/board/{boardId}")
    public ResponseEntity<Integer> deleteComment(@PathVariable final Long replyId) {
        replyService.deleteComment(replyId);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

    @PutMapping("/{replyId}")
    public ResponseEntity<Integer> modifyComment(@PathVariable final Long replyId,
                                                 @RequestBody final ReplyDto replyDto) {
        replyService.modifyComment(replyId, replyDto);
        return ResponseEntity.status(HttpStatus.OK).body(1);
    }

}
