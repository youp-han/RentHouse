package com.jjst.rentManagement.renthouse.module.Members.repository;

import com.jjst.rentManagement.renthouse.module.Members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String email);
    Member findByEmail(String email);
}
