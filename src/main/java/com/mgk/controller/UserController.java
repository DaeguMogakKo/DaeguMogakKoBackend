package com.mgk.controller;

import com.mgk.svc.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/user", method = {RequestMethod.POST, RequestMethod.GET})
@MapperScan("com.mgk.mapper")
public class UserController {

	@Autowired
	private UserService userService;
	/*
	 * 회원가입
	 * @param snsToken(not null)	소셜 토큰
	 * @param snsType(not null)		소셜 타입
	 * @param email(not null)		이메일
	 * @param nickname(not null)	닉네임
	 * @param favorList(not null)	선호하는 카테고리 리스트(관심목록) | 구분자: 콤마(,)
	 */
	@PostMapping("/signup")@GetMapping
	public String signup(@RequestParam("snsToken") String snsToken, @RequestParam("snsType") String snsType, @RequestParam("email") String email, @RequestParam("nickname") String nickname, @RequestParam("favorList") String favorList
	){
		return userService.setSignup(snsToken, email, nickname, snsType, favorList);
	}
}