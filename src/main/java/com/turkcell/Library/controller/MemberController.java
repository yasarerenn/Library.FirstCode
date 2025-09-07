package com.turkcell.Library.controller;

import com.turkcell.Library.dto.member.request.CreateMemberRequest;
import com.turkcell.Library.dto.member.response.CreatedMemberResponse;
import com.turkcell.Library.entity.Member;
import com.turkcell.Library.service.MemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<CreatedMemberResponse>> getAllMembers() {
        List<Member> members = memberService.getAllMembers();
        List<CreatedMemberResponse> response = members.stream()
                .map(this::convertToMemberResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreatedMemberResponse> getMemberById(@PathVariable int id) {
        return memberService.getMemberById(id)
                .map(member -> ResponseEntity.ok(convertToMemberResponse(member)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CreatedMemberResponse> createMember(@RequestBody CreateMemberRequest request) {
        try {
            Member member = convertToMember(request);
            Member savedMember = memberService.createMember(member);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToMemberResponse(savedMember));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreatedMemberResponse> updateMember(@PathVariable int id, @RequestBody CreateMemberRequest request) {
        try {
            Member memberDetails = convertToMember(request);
            Member updatedMember = memberService.updateMember(id, memberDetails);
            return ResponseEntity.ok(convertToMemberResponse(updatedMember));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable int id) {
        try {
            memberService.deleteMember(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<CreatedMemberResponse>> searchMembers(@RequestParam String searchTerm) {
        List<Member> members = memberService.searchMembers(searchTerm);
        List<CreatedMemberResponse> response = members.stream()
                .map(this::convertToMemberResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<CreatedMemberResponse> getMemberByEmail(@PathVariable String email) {
        return memberService.findMemberByEmail(email)
                .map(member -> ResponseEntity.ok(convertToMemberResponse(member)))
                .orElse(ResponseEntity.notFound().build());
    }

    private CreatedMemberResponse convertToMemberResponse(Member member) {
        return new CreatedMemberResponse(
                member.getId(),
                member.getFirstName(),
                member.getLastName(),
                member.getEmail(),
                member.getPhone(),
                member.getRegistrationDate()
        );
    }

    private Member convertToMember(CreateMemberRequest request) {
        Member member = new Member();
        member.setFirstName(request.getFirstName());
        member.setLastName(request.getLastName());
        member.setEmail(request.getEmail());
        member.setPhone(request.getPhone());
        return member;
    }
}
