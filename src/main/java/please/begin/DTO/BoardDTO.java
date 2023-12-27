package please.begin.DTO;

import lombok.*;
import please.begin.entity.Board;


@Getter
@Setter
public class BoardDTO {
    private int memberid;
    private String title;
    private String content;
    private String author;
//    public BoardDTO(Board board) {
//        this.author = board.getAuthor();
//        this.title = board.getTitle();
//        this.content= board.getContents();
//}

    public BoardDTO(int memberid, String title, String contents, String author) {
        this.title = title;
        this.content = contents;
        this.author = author;
        this.memberid=memberid;
    }

}

