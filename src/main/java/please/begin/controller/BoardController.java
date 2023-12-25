package please.begin.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import please.begin.DTO.BoardDTO;

import java.lang.reflect.Member;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final HttpSession session;
    @GetMapping("/save")
    @PostMapping("/boards")
    public String writeBoard(@ModelAttribute BoardDTO writeDTO) {
        Member principal = (Member) session.getAttribute("principal");

        BoardDTO boards = new BoardDTO();
        boards.setTitle(boards.getTitle());
        boards.setContent(boards.getContent());
        return "redirect/:";
    }
}


