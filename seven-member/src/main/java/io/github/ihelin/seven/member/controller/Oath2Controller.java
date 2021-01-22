package io.github.ihelin.seven.member.controller;

import io.github.ihelin.seven.member.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2.0")
public class Oath2Controller {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MemberService memberService;




}
