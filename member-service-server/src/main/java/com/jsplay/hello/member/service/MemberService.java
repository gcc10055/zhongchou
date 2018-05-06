package com.jsplay.hello.member.service;

import com.jsplay.hello.utils.bean.*;

import java.util.List;


public interface MemberService {

	Member queryByLoginacct(String loginacct);

	Ticket queryTicketByMember(Member member);

	void insertTicket(Ticket ticket);

	int updateAccttype(Member member);

	void updateTicketProcessStep(Ticket t);

	int updateBasicinfo(Member sessMember);

	List<Cert> queryCertsByAccttype(Member member);

	void insertMemerCertFile(List<MemberCertFile> mcfs);

	void updateEmail(Member member);

	void updateTicketAuthcode(Ticket ticket);

	void updateMemberAuthStatus(Member member);

	Member queryMemberByPiid(String piid);

	List<MemberCertFile> queryMemberCertFileByMemberid(Integer memberid);

	Member queryMemberById(Integer memberid);

}
