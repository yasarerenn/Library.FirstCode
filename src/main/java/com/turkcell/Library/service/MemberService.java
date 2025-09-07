package com.turkcell.Library.service;

import com.turkcell.Library.entity.Member;
import com.turkcell.Library.repository.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> getMemberById(int id) {
        return memberRepository.findById(id);
    }

    public Member createMember(Member member) {
        // Check if email already exists
        if (memberRepository.findByEmail(member.getEmail()).isPresent()) {
            throw new RuntimeException("Member with email " + member.getEmail() + " already exists");
        }

        // Set registration date if not provided
        if (member.getRegistrationDate() == null) {
            member.setRegistrationDate(new Date());
        }

        return memberRepository.save(member);
    }

    public Member updateMember(int id, Member memberDetails) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + id));

        // Check if new email already exists (if different from current)
        if (!member.getEmail().equals(memberDetails.getEmail()) &&
                memberRepository.findByEmail(memberDetails.getEmail()).isPresent()) {
            throw new RuntimeException("Member with email " + memberDetails.getEmail() + " already exists");
        }

        member.setFirstName(memberDetails.getFirstName());
        member.setLastName(memberDetails.getLastName());
        member.setEmail(memberDetails.getEmail());
        member.setPhone(memberDetails.getPhone());

        return memberRepository.save(member);
    }

    public void deleteMember(int id) {
        if (!memberRepository.existsById(id)) {
            throw new RuntimeException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }

    public Optional<Member> findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public List<Member> searchMembers(String searchTerm) {
        return memberRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(
                searchTerm, searchTerm);
    }

    public List<Member> searchMembersByPhone(String phone) {
        try {
            int phoneNumber = Integer.parseInt(phone);
            return memberRepository.findByPhone(phoneNumber);
        } catch (NumberFormatException e) {
            // Return empty list if phone is not a valid integer
            return List.of();
        }
    }
}
