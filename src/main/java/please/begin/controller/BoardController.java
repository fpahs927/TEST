package please.begin.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import please.begin.DTO.BoardDTO;
import please.begin.DTO.BoardResponseDTO;

import java.util.List;
import java.util.Optional;

import please.begin.DTO.MemberDTO;
import please.begin.Repository.BoardRepository;
import please.begin.Repository.MemberRepository;
import please.begin.Service.BoardService;
import please.begin.Service.MemberService;
import please.begin.entity.MemberEntity;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @GetMapping("/board/posts") //전체목록조회
    public List<BoardResponseDTO> getPosts() {
        return boardService.getPosts();
    }

    @PostMapping("/board/post")  //게시글작성
    public ResponseEntity<BoardResponseDTO> createPost(@RequestBody BoardDTO requestsDto) {
        int memberId = requestsDto.getMemberid();
        String nickName = requestsDto.getNickName();
        MemberDTO memberDTO = memberService.findById((long) memberId);
        if (memberDTO != null) {
            //String nickName = memberDTO.getNickName();
            if(!memberDTO.getNickName().equals(nickName)){
                return ResponseEntity.badRequest().body(new BoardResponseDTO("닉네임이 일치하지않습니다"));
            }
            System.out.println("닉네임이 넘어오나?!" + nickName);
            System.out.println("아이디가 있다!" + requestsDto.getMemberid());
            return ResponseEntity.ok(boardService.createPost(requestsDto));
        } else {
            System.out.println("아이디가 없다!");
            return ResponseEntity.badRequest().build();
        }
    }
    @GetMapping("/post/{id}") //선택 게시글 조회 id만 조회하게
    public BoardResponseDTO getPost(@PathVariable Long id) {
        return boardService.getPost(id);
    }
    @PostMapping("/post/{id}") //선택 게시글 수정
    public BoardResponseDTO updatePost(@PathVariable Long id, @RequestBody BoardDTO requestsDto) throws Exception {
        BoardDTO existingPost = boardService.findById(id);

        existingPost.setTitle(requestsDto.getTitle());
        existingPost.setContent(requestsDto.getContent());
        return boardService.updatePost(id, existingPost);
    }
}



