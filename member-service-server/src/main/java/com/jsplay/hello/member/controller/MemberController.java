package com.jsplay.hello.member.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsplay.hello.member.service.MemberService;
import com.jsplay.hello.utils.bean.*;
import com.jsplay.hello.utils.common.BaseController;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class MemberController extends BaseController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private RepositoryService repositoryService;
	
	@RequestMapping("/queryMemberById")
	public Member queryMemberById(@RequestBody Integer memberid) {
		return memberService.queryMemberById(memberid);
	}
	
	@RequestMapping("/queryMemberCertFileByMemberid")
	public List<MemberCertFile> queryMemberCertFileByMemberid(@RequestBody Integer memberid) {
		return memberService.queryMemberCertFileByMemberid(memberid);
	}
	
	@RequestMapping("/queryMemberByPiid")
	public Member queryMemberByPiid(@RequestBody String piid) {
		return memberService.queryMemberByPiid(piid);
	}
	
	@RequestMapping("/updateMemberAuthStatus")
	public void updateMemberAuthStatus(@RequestBody Member member) {
		memberService.updateMemberAuthStatus(member);
	}
	
	@RequestMapping("/updateEmail")
	public void updateEmail(@RequestBody Member member) {
		memberService.updateEmail(member);
		Ticket t = memberService.queryTicketByMember(member);
		t.setPstep("confirmapply");
		memberService.updateTicketProcessStep(t);
	}

	@RequestMapping("/updateTicketAuthcode")
	public void updateTicketAuthcode(@RequestBody Ticket ticket) {
		memberService.updateTicketAuthcode(ticket);
	}
	
	@RequestMapping("/queryByLoginacct")
	public Member queryByLoginacct( @RequestBody Member member ) {
		return memberService.queryByLoginacct(member.getLoginacct());
	}
	
	@RequestMapping("/queryTicketByMember")
	public Ticket queryTicketByMember(@RequestBody Member member) {
		return memberService.queryTicketByMember(member);
	}

	@RequestMapping("/insertTicket")
	public void insertTicket(@RequestBody Ticket ticket) {
		memberService.insertTicket(ticket);
	}
	
	@RequestMapping("/updateAccttype")
	public int updateAccttype(@RequestBody Member member) {
		int count = memberService.updateAccttype(member);
		if ( count == 1 ) {
			Ticket t = memberService.queryTicketByMember(member);
			t.setPstep("basicinfo");
			memberService.updateTicketProcessStep(t);
		}
		return count;
	}
	
	@RequestMapping("/updateBasicinfo")
	public int updateBasicinfo(@RequestBody Member sessMember) {
		int count = memberService.updateBasicinfo(sessMember);
		if ( count == 1 ) {
			Ticket t = memberService.queryTicketByMember(sessMember);
			t.setPstep("uploadcert");
			memberService.updateTicketProcessStep(t);
		}
		return count;
	}
	
	@RequestMapping("/queryCertsByAccttype")
	public List<Cert> queryCertsByAccttype(@RequestBody Member member) {
		return memberService.queryCertsByAccttype(member);
	}
	
	@RequestMapping("/insertMemerCertFile")
	public void insertMemerCertFile(@RequestBody List<MemberCertFile> mcfs) {
		memberService.insertMemerCertFile(mcfs);
		Member member = new Member();
		member.setId(mcfs.get(0).getMemberid());
		Ticket t = memberService.queryTicketByMember(member);
		t.setPstep("checkemail");
		memberService.updateTicketProcessStep(t);
	}
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(@RequestBody Page pageParam) {
		start();

		try {
			int pageno = pageParam.getPageno();
			int pagesize = pageParam.getPagesize();
			// 查询流程定义分页数据
			ProcessDefinitionQuery query =
					repositoryService.createProcessDefinitionQuery();
			List<ProcessDefinition> pds = query.listPage((pageno-1)*pagesize, pagesize);
			int totalsize = (int)query.count();

			Page<Map<String, Object>> page = new Page<Map<String, Object>>();
			List<Map<String, Object>> pdMaps = new ArrayList<Map<String, Object>>();

			for ( ProcessDefinition pd : pds ) {
				Map<String, Object> pdMap = new HashMap<String, Object>();
				pdMap.put("id", pd.getId());
				pdMap.put("name", pd.getName());
				pdMap.put("key", pd.getKey());
				pdMap.put("version", pd.getVersion());
				pdMaps.add(pdMap);
			}

			page.setDatas(pdMaps);
			page.setTotalsize(totalsize);
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			page.setTotalno(totalno);
			data(page);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}

		return end();
	}
	@ResponseBody
	@PutMapping("/upload")
	public Object upload(@Param("file") MultipartFile file ) {
		start();

		try {



			// 部署流程定义
			repositoryService
			    .createDeployment()
			    .addInputStream(file.getOriginalFilename(), file.getInputStream())
			    //.addClasspathResource(file.getOriginalFilename())
			    .deploy();
			//System.out.println(file.getName());
			//System.out.println(file.getOriginalFilename());
			// 文件上传
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}

		return end();
	}
}
