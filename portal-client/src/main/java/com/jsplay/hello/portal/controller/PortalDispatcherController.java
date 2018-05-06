package com.jsplay.hello.portal.controller;

import javax.servlet.http.HttpSession;

import com.jsplay.hello.portal.service.MemberService;
import com.jsplay.hello.utils.bean.Member;
import com.jsplay.hello.utils.common.BaseController;
import com.jsplay.hello.utils.utils.Const;
import com.jsplay.hello.utils.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class PortalDispatcherController extends BaseController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value={"", "/"})
	public String index() {
		return "index";
	}
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@RequestMapping("/member")
	public String member() {
		return "member";
	}
	
	@ResponseBody
	@RequestMapping("/doLogin")
	public Object doLogin(Member member, HttpSession session ) {
		start();
		
		try {
			
			Member dbMember = memberService.queryByLoginacct(member);
			if ( dbMember == null ) {
				fail();
			} else {
				if ( dbMember.getMemberpswd().equals(MD5Util.digest(member.getMemberpswd())) ) {
					session.setAttribute(Const.LOGIN_MEMBER, dbMember);
					success();
				} else {
					fail();
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
}
