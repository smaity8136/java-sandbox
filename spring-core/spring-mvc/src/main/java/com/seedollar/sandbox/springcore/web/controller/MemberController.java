package com.seedollar.sandbox.springcore.web.controller;

import com.seedollar.sandbox.springcore.domain.Member;
import com.seedollar.sandbox.springcore.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/member")
public class MemberController {

    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


    @RequestMapping(path = "/registration", method = RequestMethod.GET)
    public String showRegistrationForm(Model model) {
        model.addAttribute("regForm", new Member());
        return "member/register";
    }

    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public String registration(@Valid @ModelAttribute("regForm") Member member, Errors errors) {
        if (errors.hasErrors()) {
            return "member/register";
        }
        memberService.addMember(member);
        return "redirect:/member/profile/" + member.getUsername();
    }

    @RequestMapping(path = "/profile/{username}", method = RequestMethod.GET)
    public String showProfile(@PathVariable("username") String username,  Model model) {
        Member memberByUserName = memberService.findMemberByUserName(username);
        model.addAttribute("targetMember", memberByUserName);
        return "member/profile";

    }
}
