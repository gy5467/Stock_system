package org.koreait.cmmnCode.repositories;

import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface CmmnCodeGroupRepository extends JpaRepository<CmmnCodeGroup, Long>, QuerydslPredicateExecutor {
    CmmnCodeGroup findByCmmnGroupNo(String cmmnGroupNo);

}
