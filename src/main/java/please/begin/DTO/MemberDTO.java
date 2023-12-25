package please.begin.DTO;

import lombok.*;
import please.begin.entity.MemberEntity;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {
    private int memberId;
    private String Email;
    private String Password;
    private String Name;
    private String NickName;

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
