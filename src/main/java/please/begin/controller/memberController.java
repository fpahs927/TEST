package please.begin.controller;

import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import please.begin.DTO.ItemDTO;
import please.begin.DTO.ResponseDTO;
import please.begin.Service.MemberService;
import please.begin.domain.Member;

@RestController
@CrossOrigin
public class memberController {
    private final MemberService memberService;
    @GetMapping("/notion") //공지사항 노래 넣기
    public String Getnotion(@RequestParam(required = false)String song){
        String response = "눈물이 차올라서 고갤 들어\n흐르지 못하게 또 살짝 웃어\n내게 왜 이러는지 무슨 말을 하는지\n";
        if(song != null){
            return response+song;
        }
        else{
            return response;
        }
    }
    @PostMapping("/login")
    public String create(@Valid MemberForm form, BindingResult result) {

        if (result.hasErrors()) {
            return "members/createMemberForm";
        }
        Member member = new Member();
        member.setName(form.getName());
        memberService.join(member);

        return "redirect:/";
    }
}
