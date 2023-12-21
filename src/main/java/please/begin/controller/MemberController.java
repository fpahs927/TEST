package please.begin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import please.begin.LoginRequest;
import please.begin.Service.MemberService;
import please.begin.domain.Member;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> create(@RequestBody MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        member.setEmail(form.getEmail());
        member.setPassword(form.getPassword());
        member.setNickname(form.getNickname());

        memberService.join(member);

        // 성공적으로 생성되었을 경우
        String successMessage = "Member created successfully!";
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        // 실제로는 비밀번호를 안전하게 처리하는 방법을 사용해야 합니다 (예: BCryptPasswordEncoder 사용)
        Member member = memberService.findByEmail(email);
        if (member != null && member.getPassword().equals(password)) {
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/notion") //공지사항 노래 넣기
    public String Getnotion(@RequestParam(required = false) String song) {
        String response = "눈물이 차올라서 고갤 들어\n흐르지 못하게 또 살짝 웃어\n내게 왜 이러는지 무슨 말을 하는지\n";
        if (song != null) {
            return response + song;
        } else {
            return response;
        }
    }
    //    @PostMapping("/login")
//    public String create(@Valid MemberForm form, BindingResult result) {
//
//        if (result.hasErrors()) {
//            return "members/createMemberForm";
//        }
//        Member member = new Member();
//        member.setName(form.getName());
//        memberService.join(member);
//
//        return "redirect:/";
//    }
}
