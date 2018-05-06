package com.jsplay.hello.portal.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsplay.hello.portal.service.MemberService;
import com.jsplay.hello.utils.bean.Page;
import com.jsplay.hello.utils.common.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Controller
@RequestMapping("/manager/process")
public class ProcessController extends BaseController {

	/*@Autowired
	private RepositoryService repositoryService;*/
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/list")
	public String list() {
		return "manager/process/list";
	}
	
	@RequestMapping("/view")
	public String view(String id, Model model) {
		model.addAttribute("id", id);
		return "manager/process/view";
	}
	
	/*@ResponseBody
	@RequestMapping("/delete")
	public Object delete( String id ) {
		start();
		
		try {
			// 删除流程定义
			ProcessDefinition pd =
				  repositoryService
				    .createProcessDefinitionQuery()
				    .processDefinitionId(id)
				    .singleResult();
			
			repositoryService.deleteDeployment(pd.getDeploymentId(), true);
			success();
		} catch ( Exception e ) {
			e.printStackTrace();
			fail();
		}
		
		return end();
	}*/
	
	/*@RequestMapping("/loadImg")
	public void loadImg( String id, HttpServletResponse resp ) throws Exception {
		// 通过ID获取流程定义图形
		
		ProcessDefinition pd =
		  repositoryService
		    .createProcessDefinitionQuery()
		    .processDefinitionId(id)
		    .singleResult();
		
		InputStream in =
			repositoryService
			    .getResourceAsStream(pd.getDeploymentId(), pd.getDiagramResourceName());
		
		// 通过响应对象将图形发送给浏览器
		OutputStream out = resp.getOutputStream();
		
		int i = -1;
		while ( (i = in.read()) != -1 ) {
			out.write(i);
		}
	}*/
	
	@ResponseBody
	@RequestMapping("/upload")
	public Object upload( HttpServletRequest req ) {
		MultipartHttpServletRequest request =
				(MultipartHttpServletRequest)req;

		MultipartFile file = request.getFile("procDefFile");
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		String filename = file.getOriginalFilename();
		return memberService.upload(file);
		/*start();
		
		try {
			
			MultipartHttpServletRequest request =
				(MultipartHttpServletRequest)req;
			
			MultipartFile file = request.getFile("procDefFile");
			
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
		
		return end();*/
	}
	
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery( Integer pageno, Integer pagesize ) {
		Page page = new Page();
		page.setPageno(pageno);
		page.setPagesize(pagesize);
		return memberService.pageQuery(page);

	}
}
