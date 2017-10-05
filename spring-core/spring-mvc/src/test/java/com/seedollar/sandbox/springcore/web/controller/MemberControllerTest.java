package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.domain.Member;
import com.seedollar.sandbox.springcore.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MemberControllerTest {

    @Mock
    MemberService memberService;

    @Before
    public void initBeforeAll() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testShowRegistrationForm() throws Exception {

        MemberController memberController = new MemberController(memberService);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        mockMvc.perform(get("/member/registration"))
                .andExpect(view().name("member/register"));

    }

    @Test
    public void testRegister() throws Exception {

        MemberController memberController = new MemberController(memberService);

        when(memberService.addMember(any())).thenReturn(any());

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        mockMvc.perform(post("/member/register")
                .param("firstName", "Jimmy")
                .param("lastName", "Carter")
                .param("username", "jimbo")
                .param("password", "pres"))
                .andExpect(redirectedUrl("/member/profile/jimbo"));

        verify(memberService, atLeastOnce()).addMember(any());
    }

    @Test
    public void testShowProfile() throws Exception {
        MemberController memberController = new MemberController(memberService);

        Member mockMember = new Member();
        mockMember.setUsername("test883");

        when(memberService.findMemberByUserName(any())).thenReturn(mockMember);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();

        mockMvc.perform(get("/member/profile/test883"))
                .andExpect(view().name("member/profile"))
                .andExpect(model().attributeExists("targetMember"))
                .andExpect(model().attribute("targetMember", mockMember));
    }


}
