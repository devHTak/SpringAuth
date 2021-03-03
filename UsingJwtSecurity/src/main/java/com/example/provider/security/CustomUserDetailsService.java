package com.example.provider.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.core.entity.Member;
import com.example.core.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
	
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return memberRepository.findByEmail(email)
				.map(this::createSpringSecurityUser)
				.orElseThrow(RuntimeException::new);
	}
	
	private User createSpringSecurityUser(Member member) {
		List<GrantedAuthority> authority = Collections.singletonList(new SimpleGrantedAuthority(member.getRole()));
		
		return new User(member.getEmail(), member.getPassword(), authority);
	}
	
	

}
