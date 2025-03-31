package com.jjst.rentManagement.renthouse.module.Members.repository;

import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Member findByEmail(String email);

    Member findBySnsId(String SnsId);
    List<Member> findByisNewTrue();
}
