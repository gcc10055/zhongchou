/*
package com.jsplay.hello.portal.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import com.jsplay.hello.portal.service.MemberService;
import com.jsplay.hello.utils.bean.Member;
import com.jsplay.hello.utils.bean.MemberCertFile;
import com.jsplay.hello.utils.common.BaseController;
import com.jsplay.hello.utils.utils.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/member")
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;

	@ResponseBody
	@RequestMapping("/confirmAuthcode")
	public Object confirmAuthcode( HttpSession session, String authcode ) {
		start();
		
		try {
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			// 对验证码进行校验
			Ticket ticket = memberService.queryTicketByMember(member);
			if ( ticket.getAuthcode().equals(authcode) ) {
				// 流程继续执行
				TaskQuery query =
						taskService.createTaskQuery();
				
				Task task =
				   query
				    .processDefinitionKey("authprocess")
				    .taskAssignee(member.getLoginacct())
				    .singleResult();
						
				taskService.complete(task.getId());
				
				// 修改会员的申请状态。
				member.setAuthstatus("1");
				session.setAttribute(Const.LOGIN_MEMBER, member);
				memberService.updateMemberAuthStatus(member);
				
				success();
			} else {
				fail();
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/updateEmail")
	public Object updateEmail( HttpSession session, String email ) {
		start();
		
		try {
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			member.setEmail(email);
			session.setAttribute(Const.LOGIN_MEMBER, member);
			// 更新会员邮箱地址
			memberService.updateEmail(member);
			
			// 流程继续执行
			TaskQuery query =
					taskService.createTaskQuery();
			
			Task task =
			   query
			    .processDefinitionKey("authprocess")
			    .taskAssignee(member.getLoginacct())
			    .singleResult();
			
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("flg", true);
			varMap.put("memberEmail", member.getEmail());
			StringBuilder builder = new StringBuilder();
			for ( int i = 0; i < 4; i++ ) {
				builder.append(new Random().nextInt(10));
			}
			varMap.put("authcode", builder.toString());
			
			Ticket ticket = memberService.queryTicketByMember(member);
			ticket.setAuthcode(builder.toString());
			memberService.updateTicketAuthcode(ticket);
					
			taskService.complete(task.getId(), varMap);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	*/
/**
	 * 上传资质文件
	 * 1）将上传的多文件进行封装（包装类）
	 * 1-1) 如果多层次数据封装出现了问题，可以在方法中增加一个参数 ： BindingResult bindingResult
	 * 2）将上传的文件保存到指定的位置（静态资源存储位置，为了能够让其他用户可以访问）
	 * 3）保证多文件保存时，不会被覆盖（UUID）
	 * 4）文件保存（SpringMVC框架）
	 * 5）保存多数据（Spring Cloue Service）
	 * 6）流程继续执行（Activiti Task）
	 *//*

	@ResponseBody
	@RequestMapping("/uploadcerts")
	public Object uploadcerts( HttpSession session, Datas ds, BindingResult bindingResult ) {
		start();
		
		try {
			Member sessMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			// 执行文件处理
			List<MemberCertFile> mcfs = new ArrayList<MemberCertFile>();
			for ( MemberCert mc : ds.getMcs() ) {
				MemberCertFile mcf = new MemberCertFile();
				mcf.setMemberid(sessMember.getId());
				mcf.setCertid(mc.getCertid());
				
				MultipartFile mf = mc.getCertfile();
				//InputStream in = mf.getInputStream();
				String fileName = mf.getOriginalFilename();
				// pic.jpg ==> .jpg
				String suffix = fileName.substring(fileName.lastIndexOf("."));
				fileName = UUID.randomUUID().toString() + suffix;
				
				mf.transferTo(new File("E:\\resources\\atcrowdfunding\\certs\\" + fileName));
				mcf.setIconpath(fileName);
				mcfs.add(mcf);
//				FileOutputStream out =
//					new FileOutputStream(new File("E:\\resources\\atcrowdfunding\\certs\\" + fileName));
//				
//				int i = 0;
//				while ( (i = in.read()) != -1 ) {
//					out.write(i);
//				}
			}
			memberService.insertMemerCertFile(mcfs);
			
			TaskQuery query =
					taskService.createTaskQuery();
			
			Task task =
			   query
			    .processDefinitionKey("authprocess")
			    .taskAssignee(sessMember.getLoginacct())
			    .singleResult();
			
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("flg", true);
					
			taskService.complete(task.getId(), varMap);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/updateBasicinfo")
	public Object updateBasicinfo( HttpSession session, Member member ) {
		start();
		
		try {
			Member sessMember = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			sessMember.setRealname(member.getRealname());
			sessMember.setCardnum(member.getCardnum());
			sessMember.setTel(member.getTel());
			session.setAttribute(Const.LOGIN_MEMBER, sessMember);
			int count = memberService.updateBasicinfo(sessMember);
			// 让流程继续执行，完成任务
			TaskQuery query =
				taskService.createTaskQuery();
			
			Task task =
			   query
			    .processDefinitionKey("authprocess")
			    .taskAssignee(sessMember.getLoginacct())
			    .singleResult();
			
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("loginacct", sessMember.getLoginacct());
			varMap.put("flg", true);
			
			taskService.complete(task.getId(), varMap);
			success(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@ResponseBody
	@RequestMapping("/updateAccttype")
	public Object updateAccttype( HttpSession session, String accttype ) {
		start();
		
		try {
			Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
			member.setAccttype(accttype);
			session.setAttribute(Const.LOGIN_MEMBER, member);
			int count = memberService.updateAccttype(member);
			// 让流程继续执行，完成任务
			TaskQuery query =
				taskService.createTaskQuery();
			
			Task task =
			   query
			    .processDefinitionKey("authprocess")
			    .taskAssignee(member.getLoginacct())
			    .singleResult();
			
			taskService.complete(task.getId());
			success(count == 1);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}
	
	@RequestMapping("/apply")
	public String apply( HttpSession session, Model model ) {
		
		Member member = (Member)session.getAttribute(Const.LOGIN_MEMBER);
		// 根据登陆会员查询流程审批单
		Ticket ticket = memberService.queryTicketByMember(member);
		
		if ( ticket == null ) {
			// 如果流程审批单不存在，说明没有申请，那么开始申请流程
			// 启动实名认证申请流程
			ProcessDefinition pd =
				repositoryService
				    .createProcessDefinitionQuery()
				    .processDefinitionKey("authprocess")
				    .latestVersion()
				    .singleResult();
			
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("loginacct", member.getLoginacct());
			
			ProcessInstance pi = runtimeService.startProcessInstanceById(pd.getId(), varMap);
			
			ticket = new Ticket();
			ticket.setStatus("0");
			ticket.setMemberid(member.getId());
			ticket.setPiid(pi.getId());
			memberService.insertTicket(ticket);
		}
		// 如果流程审批单存在，说明正在执行申请，那么继续执行流程
		// 根据流程步骤跳转页面
		if ( StringUtil.isEmpty(ticket.getPstep()) ) {
			return "portal/member/selectaccout";
		} else if ( "basicinfo".equals(ticket.getPstep()) ) {
			return "portal/member/basicinfo";
		} else if ( "uploadcert".equals(ticket.getPstep()) ) {
			
			// 查询证明文件
			List<Cert> certs = memberService.queryCertsByAccttype(member);
			model.addAttribute("certs", certs);
			return "portal/member/uploadcert";
		} else if ( "checkemail".equals(ticket.getPstep()) ) {
			return "portal/member/checkemail";
		} else if ( "confirmapply".equals(ticket.getPstep()) ) {
			return "portal/member/confirmapply";
		}
		
		return "portal/member/apply";
	}
}
*/
