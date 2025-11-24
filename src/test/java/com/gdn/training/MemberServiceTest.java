package com.gdn.training;

import com.gdn.training.dummy.entity.Member;
import com.gdn.training.dummy.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

  @InjectMocks
  private MemberService memberService;

  @Mock
  private MemberRepository memberRepository;

  @Test
  public void suspendMember() {
    Mockito.when(memberRepository.getMember("member-id"))
        .thenReturn(Member.builder()
            .id("member-id")
            .name("name")
            .email("email")
            .suspended(false)
            .build());

    memberService.suspendMember("member-id");
    verify(memberRepository).getMember("member-id");

    ArgumentCaptor<Member> memberArgumentCaptor = ArgumentCaptor.forClass(Member.class);
    verify(memberRepository).save(memberArgumentCaptor.capture());
    Member member = memberArgumentCaptor.getValue();
    assertTrue(member.isSuspended());
    assertEquals("name", member.getName());
    assertEquals("member-id", member.getId());
    assertEquals("email", member.getEmail());
  }

  @Test
  void noArgsConstructorCoverage() {
    Member member = new Member();
    assertNotNull(member);
  }

  @Test
  public void memberAlreadySuspended() {
    Mockito.when(memberRepository.getMember("member-id"))
        .thenReturn(Member.builder()
            .id("member-id")
            .name("name")
            .email("email")
            .suspended(true)
            .build());

    Exception ex = assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
    assertEquals("Member already suspended", ex.getMessage());
  }

  @Test
  public void memberNotFound() {
    Mockito.when(memberRepository.getMember("member-id"))
        .thenReturn(null);

    Exception ex = assertThrows(RuntimeException.class, () -> memberService.suspendMember("member-id"));
    assertEquals("Member not found", ex.getMessage());
  }

  @AfterEach
  public void tearDown() {
    verifyNoMoreInteractions(memberRepository);
  }

}