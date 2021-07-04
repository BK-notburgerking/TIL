package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// 컨트롤 쉬프트 T -> 테스트 만들어짐..
public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버서비스 입장에서 멤버리포지토리를 외부에서 넣어주도록 만듦(테스트코드 참고)
    // 이게 디펜던시 인젝션(DI)
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 X

        /*
        Optional<Member> result = memberRepository.findByName(member.getName()); // 컨트롤 알트 v 하면 자동으로 리턴 만들어줌;
        // ifPresent -> result가 null이 아니면 동작해라라는 뜻 Optional이기 때문에 가능!!
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // 위처럼 작성도 가능하지만, Optional로 바로 받는게 권장되지 않음, result에 담긴 값이 Optional이기 때문에 그냥 바로 ifPresent를 사용하는 방식으로 구현
        // 근데 이런식으로 뭔가 로직이 쭉 있잖아..? 이런 경우엔 메서드로 뽑는게 좋음, 단축키는 컨트롤+알트+M -> Extract method
        /*
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
        */

        validateDuplicateMember(member); // 중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 특정 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

}
