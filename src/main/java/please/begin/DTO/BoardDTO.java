package please.begin.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BoardDTO {
    private String title;
    private String content;
<<<<<<< HEAD
    private String nickName;

    public BoardDTO(int memberid, String title, String contents, String nickName) {
        this.title = title;
        this.content = contents;
        this.nickName = nickName;
        this.memberid=memberid;
    }
    public BoardDTO() {

    }
    public static BoardDTO toBoardDTO(Board boardEntity) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setMemberid(boardEntity.getMemberid());
        boardDTO.setTitle(boardEntity.getTitle());
        boardDTO.setContent(boardEntity.getContents());
  //      boardDTO.setAuthor(boardEntity.getAuthor());
        return boardDTO;
    }
    public Board toBoardEntity() {
        Board boardEntity = new Board();
        boardEntity.setMemberid(this.getMemberid());
        boardEntity.setTitle(this.getTitle());
        boardEntity.setContents(this.getContent());
     //   boardEntity.setAuthor(this.getAuthor());
        return boardEntity;
    }

    //public void setAuthor(String author){
    //    this.author=author;
 //   }
=======
>>>>>>> parent of d3fcbdd (1227_수요일)

}
