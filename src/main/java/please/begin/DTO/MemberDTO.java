package please.begin.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import please.begin.entity.MemberEntity;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private int memberId;
    private String Email;
    private String password; // 패스워드를 리스트로 변경
    private String Name;
    private String nickName;

    public static MemberDTO toMemberDTO(MemberEntity memberEntity) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setMemberId(memberEntity.getMemberId());
        memberDTO.setPassword(memberEntity.getPassword());
        memberDTO.setEmail(memberEntity.getEmail());
        memberDTO.setName(memberEntity.getName());
        memberDTO.setNickName(memberEntity.getNickName());
        return memberDTO;
    }

    private void setId(Long id) {
    }
}
