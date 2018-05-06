package com.jsplay.hello.member.dao;

import java.util.List;

import com.jsplay.hello.utils.bean.Cert;
import com.jsplay.hello.utils.bean.Member;
import com.jsplay.hello.utils.bean.MemberCertFile;
import com.jsplay.hello.utils.bean.Ticket;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface MemberDao {

	//@Select("select * from t_member where loginacct = #{loginacct}")
	Member queryByLoginacct(String loginacct);

	@Insert("insert into t_ticket ( memberid, piid, status ) values ( #{memberid}, #{piid}, #{status} )")
	void insertTicket(Ticket ticket);

	@Select("select * from t_ticket where memberid = #{id} and status = '0'")
	Ticket queryTicketByMember(Member member);

	@Update("update t_member set accttype = #{accttype} where id = #{id}")
	int updateAccttype(Member member);

	@Update("update t_ticket set pstep = #{pstep} where id = #{id}")
	void updateTicketProcessStep(Ticket t);

	@Update("update t_member set realname = #{realname}, cardnum = #{cardnum}, tel = #{tel} where id = #{id}")
	int updateBasicinfo(Member sessMember);

	List<Cert> queryCertsByAccttype(Member member);

	void insertMemerCertFile(@Param("mcfs") List<MemberCertFile> mcfs);

	@Update("update t_member set email = #{email} where id = #{id}")
	void updateEmail(Member member);

	@Update("update t_ticket set authcode = #{authcode} where id = #{id}")
	void updateTicketAuthcode(Ticket ticket);

	@Update("update t_member set authstatus = #{authstatus} where id = #{id}")
	void updateMemberAuthStatus(Member member);

	Member queryMemberByPiid(String piid);

	List<MemberCertFile> queryMemberCertFileByMemberid(Integer memberid);

	@Select("select * from t_member where id = #{id}")
	Member queryMemberById(Integer memberid);

}
