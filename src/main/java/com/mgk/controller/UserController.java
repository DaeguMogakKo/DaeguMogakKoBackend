package com.mgk.controller;

import com.mgk.user.UserCreateForm;
import com.mgk.svc.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userService;
	//main공지사항 같은 경우는 get으로 해도 상관 X
	@PostMapping("/signup")@GetMapping
	public String signup(UserCreateForm userCreateForm) {
		System.out.println("signup");
		return "signup_form";
	}
	//숨겨야 하는 경우.
/*
	@PostMapping("/signup")
	public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "signup_form";
		}

		if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
			bindingResult.rejectValue("password2", "passwordInCorrect", "2개의 패스워드가 일치하지 않습니다.");
			return "signup_form";
		}
		try {
			userService.create(userCreateForm.getUsername(), userCreateForm.getEmail(), userCreateForm.getPassword1());
		} catch (DataIntegrityViolationException e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
			return "signup_form";
		} catch (Exception e) {
			e.printStackTrace();
			bindingResult.reject("signupFailed", e.getMessage());
			return "signup_form";
		}

		return "redirect:/";
	}
*/

	@GetMapping("/login")
	public String login() {
		return "login_form";
	}
}