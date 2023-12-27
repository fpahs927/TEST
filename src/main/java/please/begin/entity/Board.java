package please.begin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import please.begin.DTO.BoardDTO;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "board")
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private int memberid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String author;

    public Board(BoardDTO requestsDto) {
        this.title = requestsDto.getTitle();
        this.contents=requestsDto.getContent();
        this.author = requestsDto.getAuthor();
        this.memberid = requestsDto.getMemberid();
  //    this.password = requestsDto.getPassword();

    }
    public void update(BoardDTO boardDTO) {
        // BoardDTO의 데이터를 사용하여 필드를 업데이트

        if(memberid==boardDTO.getMemberid()) {
            this.setTitle(boardDTO.getTitle());
            this.setContents(boardDTO.getContent());
        }
        // BoardDTO를 받는 생성자 추가


    }
    }