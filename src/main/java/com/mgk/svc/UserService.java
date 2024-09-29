package com.mgk.svc;

import com.mgk.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
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
}