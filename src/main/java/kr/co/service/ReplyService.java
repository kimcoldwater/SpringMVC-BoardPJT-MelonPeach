package kr.co.service;

import java.util.List;

import kr.co.vo.ReplyVO;

public interface ReplyService {
	public List<ReplyVO> readReply(int bno) throws Exception;
	
	public void WriterReply(ReplyVO vo)throws Exception;
	
	public void UpdateReply(ReplyVO vo)throws Exception;
	
	public void DeleteReply(ReplyVO vo)throws Exception;
	
	public ReplyVO selectReply(int rno)throws Exception;
}
