package please.begin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import please.begin.DTO.MemberDTO;

@Entity
@Setter
@Getter
@Table(name = "company")
public class MemberEntity {
    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment

    @Column(unique = true) // unique 제약조건 추가
    private int memberId;

    @Column(unique = true) // unique 제약조건 추가
    private String email;

    @Column

    private String Password;

    @Column
    private String Name;

    @Column
    private String NickName;

    public static MemberEntity toMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setMemberId(memberDTO.getMemberId());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setNickName(memberDTO.getNickName());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(MemberDTO memberDTO) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(memberDTO.getEmail());
        memberEntity.setPassword(memberDTO.getPassword());
        memberEntity.setName(memberDTO.getName());
        memberEntity.setNickName(memberDTO.getNickName());
        return memberEntity;
    }

    public static MemberEntity toMemberEntity(String name, String email, String password, String nickName) {
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setEmail(email);
        memberEntity.setName(name);
        memberEntity.setPassword(password);
        memberEntity.setNickName(nickName);
        return memberEntity;
    }

}
