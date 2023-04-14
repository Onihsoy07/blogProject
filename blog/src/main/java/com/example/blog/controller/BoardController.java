package com.example.blog.controller;

import com.example.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping({"/", ""})
    public String index(Model model,
                        @PageableDefault(size = 3, sort = "id", direction = Direction.DESC) Pageable pageable) {
        model.addAttribute("boards", boardService.writeList(pageable));
        return "index";
    }

    @GetMapping("/board/writeForm")
    public String saveForm() {
        return "board/writeForm";
    }

    @GetMapping("/board/{id}")
    public String viewBoard(@PathVariable final Long id, Model model) {
        model.addAttribute("board", boardService.viewBoard(id));
        return "board/detail";
    }

    @GetMapping("/board/{id}/updateForm")
    public String updateForm(@PathVariable final Long id, Model model) {
        model.addAttribute("board", boardService.viewBoard(id));
        return "board/updateForm";
    }

}
