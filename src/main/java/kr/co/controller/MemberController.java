package kr.co.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.service.MemberService;
import kr.co.vo.MemberVO;

@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Inject
	MemberService service;

	@Inject
	BCryptPasswordEncoder pwdEncoder;
	
	// 회원가입 get
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void getRegister() throws Exception {
		logger.info("get register");
	}

	// 회원가입 post 
	//회원가입 요청이 들어오면 결과가 1이면 아이디가 중복된 것이기 때문에
	//다시 회원가입 페이지로 보내고 0이면 회원가입 실행
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String postRegister(MemberVO vo) throws Exception {
		logger.info("post register");
		int result = service.idChk(vo);
		try {
			if(result == 1) {
				return "/home";
			}else if(result ==0) {
				String inputPass = vo.getUserPass();
				String pwd=pwdEncoder.encode(inputPass);
				vo.setUserPass(pwd);
				service.register(vo);
			}
		}catch(Exception e) {
			throw new RuntimeException();
		}

		return "redirect:/";
	}
	
	// 로그인 post
		@RequestMapping(value = "/login", method = RequestMethod.POST)
		public String login(MemberVO vo, HttpSession session, RedirectAttributes rttr) throws Exception{
			logger.info("post login");
		
			session.getAttribute("member");
			MemberVO login = service.login(vo);
			boolean pwdMatch = pwdEncoder.matches(vo.getUserPass(), login.getUserPass());

			if(login != null && pwdMatch == true) {
				session.setAttribute("member", login);
			} else {
				session.setAttribute("member", null);
				rttr.addFlashAttribute("msg", false);
			}
			
			
			return "redirect:/";
		}
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/";
	}
	//회원정보 수정 view (get)
	@RequestMapping(value="/memberUpdateView", method = RequestMethod.GET)
	public String registerUpdateView() throws Exception{
		return "member/memberUpdateView";
	}
	//post
	@RequestMapping(value = "/memberUpdate", method=RequestMethod.POST)
	public String registerUpdate(MemberVO vo,HttpSession session) throws Exception{
		service.memberUpdate(vo);
		session.invalidate();
		return "redirect:/";
	}
	//회원탈퇴 view (GET)
	@RequestMapping(value = "/memberDeleteView",method=RequestMethod.GET)
	public String memberDelete() throws Exception{
		return "member/memberDeleteView";
	}
	
	@RequestMapping(value = "/memberDelete",method=RequestMethod.POST)
	public String memberDelete(MemberVO vo,HttpSession session,RedirectAttributes rttr)throws Exception{
		/*MemberVO member = (MemberVO) session.getAttribute("member");

		String sessionPass = member.getUserPass();
		String voPass = vo.getUserPass();
		
		if(!(sessionPass.equals(voPass))) {
			rttr.addFlashAttribute("msg",false);
			return"redirect:/member/memberDeleteView";
		}*/
		service.memberDelete(vo);
		session.invalidate();
		return "redirect:/";
	}
	
	//패스워드 체크
	@ResponseBody
	@RequestMapping(value="/passChk",method=RequestMethod.POST)
	public boolean passChk(MemberVO vo)throws Exception{
		MemberVO login = service.login(vo);
		boolean pwdChk = pwdEncoder.matches(vo.getUserPass(), login.getUserPass());
		return pwdChk;
	}
	
	@ResponseBody
	@RequestMapping(value="/idChk",method=RequestMethod.POST)
	public int idChk(MemberVO vo) throws Exception{
		int result = service.idChk(vo);
		return result;
	}
}