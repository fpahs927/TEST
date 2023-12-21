package please.begin.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberForm {
    private String name;
    public void setName(String name) {
        // Check for not empty
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("회원 이름은 필수 입니다.");
        }
        this.name = name;
    }
}