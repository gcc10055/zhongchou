package com.jsplay.hello.portal.service;

import java.util.List;

import com.jsplay.hello.utils.bean.*;
import feign.Headers;
import feign.Param;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name = "eureka-member-service" ,configuration = MemberService.MultipartSupportConfig.class)
public interface MemberService {

	@RequestMapping("/queryByLoginacct")
	public Member queryByLoginacct(@RequestBody Member member);

	@RequestMapping("/queryTicketByMember")
	public Ticket queryTicketByMember(@RequestBody Member member);

	@RequestMapping("/insertTicket")
	public void insertTicket(@RequestBody Ticket ticket);

	@RequestMapping("/updateAccttype")
	public int updateAccttype(@RequestBody Member member);

	@RequestMapping("/updateBasicinfo")
	public int updateBasicinfo(@RequestBody Member sessMember);

	@RequestMapping("/queryCertsByAccttype")
	public List<Cert> queryCertsByAccttype(@RequestBody Member member);

	@RequestMapping("/insertMemerCertFile")
	public void insertMemerCertFile(@RequestBody List<MemberCertFile> mcfs);

	@RequestMapping("/updateEmail")
	public void updateEmail(@RequestBody Member member);

	@RequestMapping("/updateTicketAuthcode")
	public void updateTicketAuthcode(@RequestBody Ticket ticket);

	@RequestMapping("/updateMemberAuthStatus")
	public void updateMemberAuthStatus(@RequestBody Member member);

	@RequestMapping("/queryMemberByPiid")
	public Member queryMemberByPiid(@RequestBody String piid);

	@RequestMapping("/queryMemberCertFileByMemberid")
	public List<MemberCertFile> queryMemberCertFileByMemberid(@RequestBody Integer memberid);

	@RequestMapping("/queryMemberById")
	public Member queryMemberById(@RequestBody Integer memberid);

	@RequestMapping("/pageQuery")
	public Object pageQuery(@RequestBody Page page );
	@PutMapping("/upload")
	@Headers("Content-Type: multipart/form-data")
	public Object upload(@Param("file") MultipartFile file);

	/**
	 * 引用配置类MultipartSupportConfig.并且实例化
	 */
	class MultipartSupportConfig {
		@Bean
		public Encoder feignFormEncoder() {
			return new SpringFormEncoder();
		}
	}
}
