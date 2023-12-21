package please.begin.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter(onMethod_ = @Autowired)
public class MemberForm {
    private String name;
    private String email;
    private String password;
    private String nickname;
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("회원 이름은 필수 입니다.");
        }
        this.name = name;
    }
}