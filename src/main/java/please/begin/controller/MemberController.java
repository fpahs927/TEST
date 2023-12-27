package please.begin.controller;

import jakarta.servlet.http.HttpSession;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import please.begin.DTO.MemberDTO;
import please.begin.DTO.UserInfo;
import please.begin.Service.MemberService;

import org.slf4j.Logger;

import please.begin.entity.SampleResultEntity;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    //get은 paramate로 받아야한다
    //post는 json으로 받아야한다 body로 받고
    //둘 다 줄 때는 return 값은 json으로 준다
    private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
    //로그인
    @PostMapping("/member/save")   //회원 가입 완료
    public ResponseEntity<SampleResultEntity> save(
            @RequestBody MemberDTO memberDTO
            ,HttpSession session) {  //REQUESTBODY로 개발해라
        try {
            //           MemberDTO saveResult = memberService.save(member_id, email, name, password,Nickname);
            // 2. 세션에 저장
            MemberDTO saveResult = memberService.save(memberDTO);
            session.setAttribute("loginResult", saveResult);
            // 3. 로그 출력
            System.out.println("회원가입 완료");
            System.out.println("결과값" + saveResult);
            // 4. 응답 생성
            return ResponseEntity.ok(new SampleResultEntity(true, "정상적으로 회원가입이 되었습니다"));
        } catch (Exception e) {
            e.printStackTrace();
            return SampleResultEntity.ToResponse(HttpStatus.INTERNAL_SERVER_ERROR,false, "회원 가입 도중 오류 발생");
        }
    }

    @GetMapping("/member/login")//로그인
    public ResponseEntity<SampleResultEntity> login(@RequestParam("email") String email, @RequestParam("password") String password,
                                    HttpSession session) {
        Logger logger = LoggerFactory.getLogger(MemberController.class);
        logger.info("memberService.login>>>>>>>>>>>>>>>");
        MemberDTO loginResult = memberService.login(email,password);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getEmail());
            session.setAttribute("loginResult", loginResult);
            logger.info("success["+loginResult.getEmail()+"]");

            int memberid = loginResult.getMemberId() ;
            String name = loginResult.getName();
            String nickName = loginResult.getNickName();
            UserInfo userInfo = new UserInfo();
            userInfo.setMemberid(memberid);
            userInfo.setName(name);
            userInfo.setNickName(nickName);

            return SampleResultEntity.ToResponse(true,"정상적으로 로그인이 되었습니다",userInfo);
        } else {
            // login 실패

            logger.warn("Login failed for user with email: {}", email);
            System.out.println("fail ㅠㅠ");
            return SampleResultEntity.ToResponse(false,"로그인이 실패하였습니다(ID 혹은 비밀번호를 확인하세요)");
        }
    }

    @GetMapping("/member/email-check")
    public @ResponseBody ResponseEntity<SampleResultEntity> email_check(@RequestParam("email") String email) {
        System.out.println("이메일 중복 체크 Email = " + email);
        boolean checkResult = memberService.isEmailExist(email);
        if (checkResult) {
            return SampleResultEntity.ToResponse(checkResult,"이미 사용 중인 이메일입니다.");
        }
        return  SampleResultEntity.ToResponse(checkResult,"등록되지 않은 이메일입니다.");
}

    @GetMapping("/notice") //공지사항 노래 넣기
    public String GetNotice(@RequestParam(required = false) String song) {
        String response = "눈물이 차올라서 고갤 들어\n흐르지 못하게 또 살짝 웃어\n내게 왜 이러는지 무슨 말을 하는지\n";
        if (song != null) {
            return response + song;
        } else {
            return response;
        }
    }

    @GetMapping("/my-data")
    public MemberDTO getMemberByEmail(@RequestParam String email) {
        return memberService.getMemberByEmail(email);
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

//    @GetMapping("/member/{email}")
//    public String findAll(Model model) {
//        List<MemberDTO> memberDTOList = memberService.findAll();
//        model.addAttribute("memberList", memberDTOList);
//        return memberService.findAll().toString();
//    }
//    @GetMapping("/member/delete") //삭제해도 disable로 하고 update해라
//    public String deleteUser(@PathVariable String id) {
////        String myEmail = (String) session.getAttribute("loginEmail");
////        MemberDTO memberDTO = memberService.updateForm(myEmail);
////        model.addAttribute("updateMember", memberDTO);
//
//        memberService.disableUser(id);
//        return "update";
//    }
///////////////////////////////////////////////////////////////////////////////////////////////////////


    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

//    @GetMapping("/member/update")
//    public String updateForm(HttpSession session, Model model) {
//        String myEmail = (String) session.getAttribute("loginEmail");
//        MemberDTO memberDTO = memberService.updateForm(myEmail);
//        model.addAttribute("updateMember", memberDTO);
//        return "update";
//    }


}
