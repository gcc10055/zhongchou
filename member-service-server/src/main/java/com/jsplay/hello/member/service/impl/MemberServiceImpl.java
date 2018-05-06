package com.jsplay.hello.member.service.impl;

import java.util.List;

import com.jsplay.hello.member.dao.MemberDao;
import com.jsplay.hello.member.service.MemberService;
import com.jsplay.hello.utils.bean.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly=true)
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDao memberDao;

	public Member queryByLoginacct(String loginacct) {
		return memberDao.queryByLoginacct(loginacct);
	}

	@Override
	public Ticket queryTicketByMember(Member member) {
		return memberDao.queryTicketByMember(member);
	}

	@Transactional
	public void insertTicket(Ticket ticket) {
		memberDao.insertTicket(ticket);
	}

	@Transactional
	public int updateAccttype(Member member) {
		return memberDao.updateAccttype(member);
	}

	@Transactional
	public void updateTicketProcessStep(Ticket t) {
		memberDao.updateTicketProcessStep(t);
	}

	@Transactional
	public int updateBasicinfo(Member sessMember) {
		return memberDao.updateBasicinfo(sessMember);
	}

	@Override
	public List<Cert> queryCertsByAccttype(Member member) {
		return memberDao.queryCertsByAccttype(member);
	}

	@Transactional
	public void insertMemerCertFile(List<MemberCertFile> mcfs) {
		memberDao.insertMemerCertFile(mcfs);
	}

	@Transactional
	public void updateEmail(Member member) {
		memberDao.updateEmail(member);
	}

	@Transactional
	public void updateTicketAuthcode(Ticket ticket) {
		memberDao.updateTicketAuthcode(ticket);
	}

	@Transactional
	public void updateMemberAuthStatus(Member member) {
		memberDao.updateMemberAuthStatus(member);
	}

	@Override
	public Member queryMemberByPiid(String piid) {
		return memberDao.queryMemberByPiid(piid);
	}

	@Override
	public List<MemberCertFile> queryMemberCertFileByMemberid(Integer memberid) {
		return memberDao.queryMemberCertFileByMemberid(memberid);
	}

	@Override
	public Member queryMemberById(Integer memberid) {
		return memberDao.queryMemberById(memberid);
	}

}
