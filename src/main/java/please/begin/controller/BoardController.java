package please.begin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import please.begin.DTO.BoardDTO;
import please.begin.DTO.BoardResponseDTO;

import java.util.List;

import please.begin.DTO.MemberDTO;
import please.begin.Repository.BoardRepository;
import please.begin.Service.BoardService;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;


    @GetMapping("/board/posts") //전체목록조회
    public List<BoardResponseDTO> getPosts() {
        return boardService.getPosts();
    }

    @PostMapping("/board/post")  //게시글작성
    public BoardResponseDTO createPost(@RequestBody BoardDTO requestsDto) {
        System.out.print("성공했다");
        System.out.println("title:"+requestsDto.getTitle());
        //멤버 테이블에서 멤버 아이디로 닉네임을 찾아서 setAuthor ()// if(requestsDto.getMemberid())
        //닉네임을 찾았는데 없다? --> 가짜다! 에러를 리턴
        return boardService.createPost(requestsDto);
    }
    @PostMapping("/post/{id}") //선택 게시글 조회
    public BoardResponseDTO getPost(@PathVariable Long id) {
        return boardService.getPost(id);
    }

    @PutMapping("/post/{id}") //선택 게시글 수정
    public BoardResponseDTO updatePost(@PathVariable Long id, @RequestBody BoardDTO requestsDto) throws Exception {
        return boardService.updatePost(id, requestsDto);
    }
}



