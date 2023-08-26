package dat3.car.api;

import dat3.car.dto.MemberRequest;
import dat3.car.dto.MemberResponse;
import dat3.car.service.MemberService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {

  MemberService memberService;

  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }
  //  @Autowired  //Deliberately added via Autowired, remove this endpoint when you know why it's bad
//  MemberRepository memberRepository;
//  @GetMapping("/bad")
//  public List<Member> getMembersBad(){
//    return memberRepository.findAll();
//  }

  //Security Admins Only
  @GetMapping
  List<MemberResponse> getMembers(){
    return memberService.getMembers(false);
  }

  //Security Admins Only
  @GetMapping
  @RequestMapping("/simple")
  List<MemberResponse> getMembersSimple(){
    return memberService.getMembersSimple();
  }

  //Security Admins Only
  @GetMapping(path = "/{username}")
  MemberResponse getMemberById(@PathVariable String username) throws Exception {
    return memberService.getMemberById(username);
  }

  //Security --> Anyone (Anonymous)
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  MemberResponse addMember(@RequestBody MemberRequest body){
    return memberService.addMember(body);
  }

  //Security --> Admins Only
  @PutMapping("/{username}")
  ResponseEntity<Boolean> editMember(@RequestBody MemberRequest body, @PathVariable String username){
    return memberService.editMember(body, username);
  }

  //Security ????
  @PatchMapping("/ranking/{username}/{value}")
  ResponseEntity<Boolean> setRankingForUser(@PathVariable String username, @PathVariable int value) {
    return memberService.setRankingForUser(username, value);
  }

  // Security ????
  @DeleteMapping("/{username}")
  ResponseEntity<Boolean> deleteMemberByUsername(@PathVariable String username) {
    return memberService.deleteMemberByUsername(username);
  }


}
