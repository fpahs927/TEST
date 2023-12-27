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
    private String contents;
    private String author;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BoardResponseDTO(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.contents = entity.getContents();
        this.author = entity.getAuthor();
        this.createdAt = entity.getCreatedAt();
        this.modifiedAt = entity.getModifiedAt();
    }

}