package please.begin.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import please.begin.DTO.MemberDTO;
import please.begin.Repository.MemberRepository;
import please.begin.entity.MemberEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;

    public MemberDTO save(MemberDTO memberDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메서드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(memberDTO);
<<<<<<< HEAD

       // memberEntity.setPassword(passwordEncoder.encode(memberEntity.getPassword()));

        // 2. repository의 save 메서드 호출
        MemberEntity savedMemberEntity = memberRepository.save(memberEntity);

        // 3. entity -> dto 변환
        return MemberDTO.toMemberDTO(savedMemberEntity);
    }

    public MemberDTO save(String email, String name, String password, String Nickname) {
        // 1. dto -> entity 변환
        MemberEntity memberEntity = MemberEntity.toMemberEntity(name, email,password,Nickname);
        // 2. repository의 save 메서드 호출
        MemberEntity savedMemberEntity = memberRepository.save(memberEntity);

        // 3. entity -> dto 변환
        return MemberDTO.toMemberDTO(savedMemberEntity);
    }
    private boolean __authenticateUser(String enteredPassword, String storedHashedPassword) {
        return passwordEncoder.matches(enteredPassword, storedHashedPassword);
    }
=======
        memberRepository.save(memberEntity);
        // repository의 save메서드 호출 (조건. entity객체를 넘겨줘야 함)
        return memberDTO;
    }

>>>>>>> parent of d3fcbdd (1227_수요일)
    public MemberDTO login(String email, String password) {
        /*
            1. 회원이 입력한 이메일로 DB에서 조회를 함
            2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(email);
        if (byMemberEmail.isPresent()) {
            // 조회 결과가 있다(해당 이메일을 가진 회원 정보가 있다)
            MemberEntity memberEntity = byMemberEmail.get();
<<<<<<< HEAD
//            if (memberEntity.getPassword().equals(password))
     //       if(__authenticateUser(password,memberEntity.getPassword()))
            if(__authenticateUser(password, memberEntity.getPassword().toString()))
            { //__auth 암호인데 만약 안될 시 61번째 주석 풀고 사용해라
=======
            if (memberEntity.getPassword().equals(password)) {
>>>>>>> parent of d3fcbdd (1227_수요일)
                // 비밀번호 일치
                // entity -> dto 변환 후 리턴
                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
                return dto;
            } else {
                // 비밀번호 불일치(로그인실패)
                return null;
            }
        } else {
            // 조회 결과가 없다(해당 이메일을 가진 회원이 없다)
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for (MemberEntity memberEntity : memberEntityList) {
            memberDTOList.add(MemberDTO.toMemberDTO(memberEntity));
        }
        return memberDTOList;
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }

    }

    public MemberDTO updateForm(String myEmail) {
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return MemberDTO.toMemberDTO(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    public void update(MemberDTO memberDTO) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(memberDTO));
    }

//    public boolean isEmailDuplicate(String memberEmail) {
//        Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(memberEmail);
//        return byMemberEmail.isPresent();
//    }
    public boolean isEmailExist(String email) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByEmail(email);
        if (byMemberEmail.isPresent()) {
            MemberEntity memberEntity = byMemberEmail.get();
//            if (memberEntity.deleteddate) {
//                MemberDTO dto = MemberDTO.toMemberDTO(memberEntity);
//                return true;
//            }
            return true;
       //     else{ //이메일 불일치
        //        return false;
       // }
        }else{
            return false;
        }
    }
}