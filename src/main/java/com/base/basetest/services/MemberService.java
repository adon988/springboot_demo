package com.base.basetest.services;

import java.util.List;

import com.base.basetest.models.Member;
import com.base.basetest.repositories.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member saveMember(Member members){
        return memberRepository.save(members); 
    }

    public Member updateMember(Member members, Long id) {
        Member updateMember = memberRepository.findById(id).orElse(null);
       if( updateMember != null ) {
           updateMember.setFirstName(members.getFirstName());
           updateMember.setLastName(members.getLastName());
       } 
       final Member members_result = memberRepository.save(updateMember);
       return members_result;
    }

   public Boolean deleteMember (Long id) {
       Member delMembers = memberRepository.findById(id).orElse(null);
       if(delMembers != null){
           memberRepository.delete(delMembers);
           return true;
       }
       return false;
   } 
    
}