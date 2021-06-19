package com.kosta.sbproject.security;
 
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kosta.sbproject.model.MemberDTO;
import com.kosta.sbproject.persistence.MemberRepository;

@Service
public class MemberService implements UserDetailsService{


	@Autowired
	private MemberRepository memberRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder; // security config에서 Bean생성
	// 회원가입
	@Transactional
	public MemberDTO joinUser(MemberDTO member) {
		// 비밀번호 암호화...암호화되지않으면 로그인되지않는다.
		member.setMpassword(passwordEncoder.encode(member.getMpassword()));  // 사용자가 입력한 암호를 암호화 시켜라...
		System.out.println(passwordEncoder.encode(member.getMpassword()));
		return memberRepository.save(member);
	}
	//반드시 구현해야한다. 
	@Override
	public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {
		//filter는 조건에 맞는것만 선택
		//map: 변형한다. 
		UserDetails member = memberRepository.findById(mid)
				.filter(m -> m != null).map(m -> new SecurityUser(m)).get();
		return member;
	}

}
 
