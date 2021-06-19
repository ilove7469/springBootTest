package com.kosta.sbproject.lastproject;



import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.sbproject.model.MemberDTO;
import com.kosta.sbproject.model.MemberRoleEnumType;
import com.kosta.sbproject.security.MemberService;

@SpringBootTest
public class MemeberSecurityTest {

	@Autowired
	MemberService service;
	
	@Test
	public void test1() {
		
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = new MemberDTO();
			member.setMid("admin"+i);
			member.setMpassword("1234");
			member.setMname("홍어드민" + i);
			member.setMrole(MemberRoleEnumType.ADMIN);
			
			service.joinUser(member); //여기로 보내면 암호화시킨다.
		});
		
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = new MemberDTO();
			member.setMid("manager"+i);
			member.setMpassword("1234");
			member.setMname("김매니저" + i);
			member.setMrole(MemberRoleEnumType.MANAGER);
			
			service.joinUser(member); 
		});
		
		IntStream.range(1, 4).forEach(i->{
			MemberDTO member = new MemberDTO();
			member.setMid("user"+i);
			member.setMpassword("1234");
			member.setMname("이유저" + i);
			member.setMrole(MemberRoleEnumType.USER);
			
			service.joinUser(member); 
		});
		
		
	}
}
