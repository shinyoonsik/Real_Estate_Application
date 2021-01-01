package com.ssafy.happyhouse.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.happyhouse.model.MemberDto;
import com.ssafy.happyhouse.model.service.JwtService;
import com.ssafy.happyhouse.model.service.LoginService;
import com.ssafy.happyhouse.model.service.MemberService;

@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/member")
public class mainController {
	
	final String SUCC = "SUCCESS";
	final String FAIL = "FAIL";
	
	@Autowired
	private LoginService loginSvc;
	
	@Autowired
	private MemberService memberMgr;
	
	@Autowired
	private JwtService jwtService;
	
	public static final Logger logger = LoggerFactory.getLogger(mainController.class);


	@ExceptionHandler
	public String handlerEx(Exception ex, Model m) { // Model객체는 view에 함께 보내고 싶은 data를 담는 객체
		m.addAttribute("msg", ex.getMessage());
		return "Error";
	}
	
	@GetMapping({"/","/index"})
	public String index() {
		return "index";
	}
	
	@PostMapping("/confirm/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody MemberDto memberDto, HttpServletResponse response, HttpSession session) {
		Map<String, Object> resultMap = new HashMap<>();
		System.out.println(memberDto.toString() + " 왔나??");
		HttpStatus status = null;
		try {
			MemberDto loginUser = loginSvc.login(memberDto);
			
			if(loginUser != null) {
//				jwt.io에서 확인
//				로그인 성공했다면 토큰을 생성한다.
				String token = jwtService.create(loginUser);
				logger.trace("로그인 토큰정보 : {}", token);
				
//				토큰 정보는 response의 헤더로 보내고 나머지는 Map에 담는다.
//				response.setHeader("auth-token", token);
				resultMap.put("auth-token", token);
				resultMap.put("user-id", loginUser.getId());
				resultMap.put("user-name", loginUser.getName());
				resultMap.put("user-pw", loginUser.getPw());
				resultMap.put("user-email", loginUser.getEmail());
				resultMap.put("user-joindate", loginUser.getBirth());
				resultMap.put("user-phone", loginUser.getPhone());
				resultMap.put("user-interLoc", loginUser.getInterestLocation());
//				resultMap.put("status", true);
//				resultMap.put("data", loginUser);
				status = HttpStatus.ACCEPTED;
			} else {
				resultMap.put("message", "로그인 실패");
				status = HttpStatus.ACCEPTED;
			}
		} catch (Exception e) {
			logger.error("로그인 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	
	/*
	@GetMapping("/info")
	public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest req) {
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = HttpStatus.ACCEPTED;
		System.out.println(req.toString() + "/////////////");
		System.out.println(req.getHeader("auth-token") + "   " + req.getHeader("user-id"));
		System.out.println(">>>>>> " + jwtService.get(req.getHeader("auth-token")));
		try {
			// 사용자에게 전달할 정보이다.
//			String info = memberService.getServerInfo();
			
			resultMap.putAll(jwtService.get(req.getHeader("auth-token")));
//
//			resultMap.put("status", true);
//			resultMap.put("info", info);
			status = HttpStatus.ACCEPTED;
		} catch (RuntimeException e) {
			logger.error("정보조회 실패 : {}", e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String, Object>>(resultMap, status);
	}
	*/
	
//	@PostMapping("/login")
//	public String login(String pid,String pwd, Model m,HttpSession hs){
//		String user=loginSvc.login(pid,pwd);
//		if(user!=null) {
//			hs.setAttribute("user", user);
//		}else{
//			m.addAttribute("msg", "아이디 또는 패스워드를 확인하세요!");
//		}
//		return "index";
//	}
//	
//	@GetMapping("/logout")
//	public String logout(HttpSession hs){
//		hs.invalidate();
//		return "redirect:index";
//	}
/*	
	@GetMapping("/updmember")
	public String updmember(){
		return "member/MemberUpdate";
	}
*/
	@GetMapping("/search")
	public String search() {
		return "search";
	}
	
	@PutMapping("/update")
	public String register(@RequestBody MemberDto mem, Model m, HttpSession hs){
		memberMgr.memberUpdate(mem);
		System.out.println("업데이트:   " +mem.toString());
		m.addAttribute("msg", "정상적으로 업데이트 되었습니다.");
		hs.invalidate();
		return "success";
	}
	
	@GetMapping("/mypage")
	public String mypage(){
		return "member/MemberView";
	}
	
	@GetMapping("/regmember")
	public String register(){
		return "member/Member";
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") String id, Model m, HttpSession hs){
		memberMgr.memberDelete(id);
		System.out.println(id + "  삭제중!!!!!!!!!!!!!!!!");
		m.addAttribute("msg", "정상적으로 삭제 되었습니다.");
		hs.invalidate();
		return "success";
	}
	
	@PostMapping("/register")
	public Map<String, Object> register(@RequestBody MemberDto mem){
		System.out.println(mem.toString());
		Map<String, Object> map=new HashMap<>();
		try{
			memberMgr.memberInsert(mem);			
			map.put("status", SUCC);
		}catch(Exception e){
			map.put("status", FAIL);
			map.put("msg", e.getLocalizedMessage());
		}
		
		return map;
	}
}
