package com.jjst.rentManagement.renthouse.module.members.repository;

import com.jjst.rentManagement.renthouse.module.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Member findByEmail(String email);

    Member findBySnsId(String SnsId);
    List<Member> findByNewUserTrue();
}
