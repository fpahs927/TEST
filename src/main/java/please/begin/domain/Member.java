package please.begin.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member{
    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

    private String email;
    private String password;
    private String nickname;

}