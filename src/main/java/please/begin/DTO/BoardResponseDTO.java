package please.begin.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import please.begin.entity.Board;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String title;
    private boolean isSuccess;
    private String message;
//    private String contents;
//    private String author;
//    private LocalDateTime createdAt;
//    private LocalDateTime modifiedAt;
    public BoardResponseDTO(String message) {
        this.id = -1L;
        this.title = "실패";
        this.message=message;
    }
    public BoardResponseDTO(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        isSuccess=true;
        message="새 게시물 생성 성공";
//        this.contents = entity.getContents();
//        this.author = entity.getAuthor();
//        this.createdAt = entity.getCreatedAt();
//        this.modifiedAt = entity.getModifiedAt();
    }

}