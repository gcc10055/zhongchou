package com.jsplay.hello.portal.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsplay.hello.portal.service.MemberService;
import com.jsplay.hello.utils.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/manager/auth")
public class AuthController extends BaseController {

	/*@Autowired
	private MemberService memberService;*/
	
	/*@Autowired
	private TaskService taskService;
	@Autowired
	private RepositoryService repositoryService;*/

	@RequestMapping("/list")
	public String list() {
		return "manager/auth/list";
	}
	
	/*@ResponseBody
	@RequestMapping("/pass")
	public Object pass( String taskid, Integer memberid ) {
		start();
		
		try {
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("flg", true);
			varMap.put("memberid", memberid);
			taskService.complete(taskid, varMap);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}*/
	
	/*@ResponseBody
	@RequestMapping("/refuse")
	public Object refuse( String taskid, Integer memberid ) {
		start();
		
		try {
			Map<String, Object> varMap = new HashMap<String, Object>();
			varMap.put("flg", false);
			varMap.put("memberid", memberid);
			taskService.complete(taskid, varMap);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}*/
	
	/*@RequestMapping("/detail")
	public String detail( String taskid, Integer memberid, Model model ) {
		model.addAttribute("taskid", taskid);
		model.addAttribute("memberid", memberid);
		// 查询会员上传的证明文件
		List<MemberCertFile> mcfs = memberService.queryMemberCertFileByMemberid(memberid);
		model.addAttribute("mcfs", mcfs);
		return "manager/auth/detail";
	}*/
	
	/*@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( Integer pageno, Integer pagesize ) {
		start();
		
		try {
			
			// 分页查询审核任务的数据
			TaskQuery query = taskService.createTaskQuery();
			List<Task> tasks =
			  query
			    .processDefinitionKey("authprocess")
			    .taskCandidateGroup("authgroup")
			    .listPage((pageno-1)*pagesize,pagesize);
			List<Map<String, Object>> taskMaps = new ArrayList<Map<String, Object>>();
			for ( Task task : tasks ) {
				Map<String, Object> taskMap = new HashMap<String,Object>();
				
				// task <== ==> procDef
				String pdid = task.getProcessDefinitionId();
				ProcessDefinition pd =
					repositoryService
					    .createProcessDefinitionQuery()
					    .processDefinitionId(pdid)
					    .singleResult();
				
				taskMap.put("pdname", pd.getName());
				taskMap.put("pdversion", pd.getVersion());
				taskMap.put("taskname", task.getName());
				taskMap.put("taskid", task.getId());
				// task ==> procinst ==> ticket ==> member
				String piid = task.getProcessInstanceId();
				Member member = memberService.queryMemberByPiid(piid);
				taskMap.put("membername", member.getMembername());
				taskMap.put("memberid", member.getId());
				taskMaps.add(taskMap);
			}
			
			
			int totalsize = (int)
			  query
			    .processDefinitionKey("authprocess")
			    .taskCandidateGroup("authgroup")
					.count();
			
			int totalno = 0;
			if ( totalsize % pagesize == 0 ) {
				totalno = totalsize / pagesize;
			} else {
				totalno = totalsize / pagesize + 1;
			}
			
			Page<Map<String, Object>> taskPage = new Page<Map<String, Object>>();
			taskPage.setDatas(taskMaps);
			taskPage.setTotalsize(totalsize);
			taskPage.setTotalno(totalno);
			data(taskPage);
			success();
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}*/
}
