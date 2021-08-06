package kr.co.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.co.dao.ReplyDAO;
import kr.co.vo.ReplyVO;


@Service
public class ReplyServiceImpl implements ReplyService {
	@Inject
	private ReplyDAO dao;
	
	@Override
	public List<ReplyVO> readReply(int bno) throws Exception{
		return dao.readReply(bno);
	}
	@Override
	public void WriterReply(ReplyVO vo)throws Exception{
		dao.WriterReply(vo);
	}
	
	@Override
	public void UpdateReply(ReplyVO vo)throws Exception{
	dao.UpdateReply(vo);
	}
	
	@Override
	public void DeleteReply(ReplyVO vo) throws Exception{
	dao.DeleteReply(vo);
	}
	
	@Override
	public ReplyVO selectReply(int rno) throws Exception{
	return dao.selectReply(rno);
	}
}
