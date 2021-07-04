package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 기본 프레임을 짜면 (컨트롤 어노테이션, 클래스명), 스프링 컨테이너 안에 컨트롤 어노테이션이 있으면
// 멤버컨트롤러 객체를 생성해서 컨테이너에 넣고 스프링이 관리함
@Controller
public class MemberController {

    // 하나만 생성해서 같이 공유해서 쓰기 위해 스프링 컨테이너에 등록할거임!
    private final MemberService memberService;

    // Autowired를 생성자에 붙이면 됨!
    // 이러면 스프링 컨테이너에서 memberservice를 가져옴
    // 연결되어 있는 클래스(MemberService)에는 Service 어노테이션 붙여줌
    // 해당 클래스에서 사용하는 리포지토리엔 Repository 어노테이션 붙여줌
    // 이게 컴포넌트 스캔 방식의 스프링 빈 등록 방법임
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
