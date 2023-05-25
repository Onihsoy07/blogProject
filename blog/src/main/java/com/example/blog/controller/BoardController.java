package com.example.blog.controller;

import com.example.blog.entity.Board;
import com.example.blog.service.BoardService;
import com.example.blog.service.impl.BoardServiceImpl;
import com.example.blog.service.impl.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardServiceImpl boardService;
    private final LikesService likesService;

    @GetMapping({"/", ""})
    public String index(Model model,
                        Pageable pageable) {
        model.addAttribute("boards", boardService.writeList(customPage(pageable)));
        return "index";
    }

    @GetMapping("/board/writeForm")
    public String saveForm() {
        return "board/writeForm";
    }

    @GetMapping("/board/{id}")
    public String viewBoard(@PathVariable final Long id, Model model) {
        model.addAttribute("board", boardService.viewBoard(id));
        model.addAttribute("likes", likesService.countLikes(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable final Long id, Model model) {
        model.addAttribute("board", boardService.viewBoard(id));
        return "board/updateForm";
    }

    private PageRequest customPage(Pageable pageable) {
        return PageRequest.of((pageable.getPageNumber()==0)?0:pageable.getPageNumber()-1, 7, Sort.by("createDate").descending());
    }

}
