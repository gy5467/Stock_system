package org.koreait.cmmnCode.repositories;

import org.koreait.cmmnCode.entities.CmmnCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CmmnCodeRepository extends JpaRepository<CmmnCode, Long>, QuerydslPredicateExecutor {
    CmmnCode findByCmmnCode(String cmmnCode);
}
