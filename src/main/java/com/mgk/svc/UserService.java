package com.mgk.svc;

import com.mgk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.net.http.HttpRequest;
import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {
	@Autowired
	public UserMapper userMapper;

	public String setSignup(String snsToken, String email, String nickname, String snsType, String favorList) {
		String result = "NO";
		int insertResult = userMapper.insertUser(snsToken, email, nickname, snsType, favorList);
		if (insertResult > 0) {
			result = "OK";
		}
		return result;
	}


	public String setLogin(String snsToken, String snsType, HttpRequest request) {
		System.out.println("snsToken: " + snsToken);
		System.out.println("snsType: " + snsType);
		return snsType+snsType;
	}
}