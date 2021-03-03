package com.example.core.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.core.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
	
	Optional<Member> findByEmail(String email);

}
