package org.koreait.cmmnCode.repositories;

import com.querydsl.core.BooleanBuilder;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.QCmmnCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

public interface CmmnCodeGroupRepository extends JpaRepository<CmmnCodeGroup, Long>, QuerydslPredicateExecutor {
    CmmnCodeGroup findByCmmnGroupCode(String cmmnGroupCode);

    CmmnCodeGroup findByCmmnGroupNo(Long cmmnGroupNo);
}
