package kr.co.dao;

import kr.co.vo.MemberVO;

public interface MemberDAO {
	//회원가입
	public void register(MemberVO vo) throws Exception;

	//로그인
	public MemberVO login(MemberVO vo)throws Exception;
	
	public void memberUpdate(MemberVO vo)throws Exception;
	
	public void memberDelete(MemberVO vo)throws Exception;
	
	//계정삭제시 비밀번호 확인
	public int passChk(MemberVO vo)throws Exception;
	
	//아이디 중복 체크
	public int idChk(MemberVO vo)throws Exception;
	
}
