package please.begin.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import please.begin.Service.MemberService;
import please.begin.domain.Member;

//@RequiredArgsConstructor
@RestController
public class memberController {
    @Autowired
    private MemberService memberService;

//    public memberController(MemberService memberService) {
//        this.memberService = memberService;
//    }
    @PostMapping("/login")
    public ResponseEntity<String> create(@RequestBody MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        // 성공적으로 생성되었을 경우
        String successMessage = "Member created successfully!";
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
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
