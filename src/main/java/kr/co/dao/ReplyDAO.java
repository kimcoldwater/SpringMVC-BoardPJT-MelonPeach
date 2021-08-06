package kr.co.dao;

import java.util.List;

import kr.co.vo.ReplyVO;

public interface ReplyDAO {
		
	public List<ReplyVO> readReply(int bno) throws Exception;
	
	public void WriterReply(ReplyVO vo)throws Exception;
	
	public void UpdateReply(ReplyVO vo)throws Exception;
	
	public void DeleteReply(ReplyVO vo)throws Exception;
	
	//선택된 댓글 조회
	public ReplyVO selectReply(int rno) throws Exception;
}
