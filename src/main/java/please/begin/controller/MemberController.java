package please.begin.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import please.begin.DTO.BoardDTO;
import please.begin.DTO.MemberDTO;
import please.begin.Service.MemberService;

import java.util.List;

@RestController
public class MemberController {
    @Autowired
    private MemberService memberService;
    //로그인
    @PostMapping("/member/_save")
    public String save(@RequestBody MemberDTO memberDTO) {  //REQUESTBODY로 개발해라
        System.out.println("MemberController.save");
        System.out.println("memberDTO = " + memberDTO.getNickName());
        System.out.println("memberDTO = " + memberDTO);
        memberService.save(memberDTO);
        return "login";
    }

    @GetMapping("/member/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/member/login")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpSession session) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult != null) {
            // login 성공
            session.setAttribute("loginEmail", loginResult.getEmail());
            return "main";
        } else {
            // login 실패
            return "login";
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
///////////////////////////////////////////////////////////////////////////////////////////////////////
    @GetMapping("/member/")
    public String findAll(Model model) {
        List<MemberDTO> memberDTOList = memberService.findAll();
        // 어떠한 html로 가져갈 데이터가 있다면 model사용
        model.addAttribute("memberList", memberDTOList);
        return "list";
    }

    @GetMapping("/member/{id}")
    public String findById(@PathVariable Long id, Model model) {
        MemberDTO memberDTO = memberService.findById(id);
        model.addAttribute("member", memberDTO);
        return "detail";
    }

    @GetMapping("/member/update")
    public String updateForm(HttpSession session, Model model) {
        String myEmail = (String) session.getAttribute("loginEmail");
        MemberDTO memberDTO = memberService.updateForm(myEmail);
        model.addAttribute("updateMember", memberDTO);
        return "update";
    }

    @GetMapping("/member/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

    @PostMapping("/member/email-check")
    public @ResponseBody String emailCheck(@RequestParam("Email") String memberEmail) {
        System.out.println("Email = " + memberEmail);
        String checkResult = memberService.emailCheck(memberEmail);
        return checkResult;
    }


}
