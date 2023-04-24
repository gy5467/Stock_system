package org.koreait.cmmnCode.repositories;

import org.koreait.cmmnCode.entities.CmmnCode;
import org.koreait.cmmnCode.entities.CmmnCodeDto;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CmmnCodeRepository extends JpaRepository<CmmnCode, Long>, QuerydslPredicateExecutor {
    CmmnCode findByCmmnCode(String cmmnGroupCode);
    @Query("select a from CmmnCode a where a.cmmnGroupCode = :cmmn_group_code")
    List<CmmnCode> search(@Param("cmmn_group_code") String cmmnGroupCode);
}
