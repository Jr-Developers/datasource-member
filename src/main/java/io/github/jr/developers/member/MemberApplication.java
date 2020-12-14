package io.github.jr.developers.member;

import io.github.jr.developers.member.jpa.member.Member;
import io.github.jr.developers.member.jpa.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@RestController
public class MemberApplication {

    @Autowired
    private MemberRepository memberRepository;

    public static void main(String[] args) {
        SpringApplication.run(MemberApplication.class, args);
    }

    @GetMapping("/member/{memberName}")
    public Member retrieveByMemberName(@PathVariable("memberName") String memberName) {
        return memberRepository.findById(memberName).orElseGet(() -> null);
    }

    @GetMapping("/club/{clubName}")
    public Object retrieveByClubName(@PathVariable("clubName") String clubName) {
        return new RestTemplate().getForObject("http://localhost:8081/hibernate/club/" + clubName, Object.class);
    }
}
