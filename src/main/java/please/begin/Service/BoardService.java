package please.begin.Service;

//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import please.begin.DTO.BoardDTO;
import please.begin.DTO.BoardResponseDTO;
import please.begin.DTO.MemberDTO;
import please.begin.Repository.BoardRepository;
import please.begin.entity.Board;
import please.begin.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
    public void saveBoard(int memberid, String author, String title, String contents) {
        // 필요한 유효성 검사 수행
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }

        // Board 엔터티 객체 생성
        Board board = new Board(new BoardDTO(memberid,title, contents, author));

        // 데이터베이스에 저장
        boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public List<BoardResponseDTO> getPosts() {
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDTO::new).toList();
    }
    @Transactional
    public BoardResponseDTO createPost(BoardDTO requestsDto) {
        //게시글 작성
        Board board = new Board(requestsDto);
        board.setMemberid(requestsDto.getMemberid());
        board.setContents(requestsDto.getContent());
        board.setTitle(requestsDto.getTitle());
        boardRepository.save(board);
        return new BoardResponseDTO(board);
    }
//    public BoardDTO findById(Long id) {
//        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
//        if (optionalMemberEntity.isPresent()) {
//            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
//        } else {
//            return null;
//        }
//
//    }

    @Transactional
    public BoardResponseDTO getPost(Long id) {
        return boardRepository.findById(id).map(BoardResponseDTO::new).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
    }
    @Transactional //게시글수정
    public BoardResponseDTO updatePost(Long id, BoardDTO requestsDto) throws Exception {
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다.")
        );
        board.setTitle(requestsDto.getTitle());
        board.setContents(requestsDto.getContent());
        return new BoardResponseDTO(board);
    }


    @Transactional
    public BoardDTO findById(Long id) {
        Optional<Board> BoardEntity = boardRepository.findById(id);
        if(BoardEntity.isPresent()){
            return BoardDTO.toBoardDTO(BoardEntity.get());
        }else{
            return null;
        }
    }
}