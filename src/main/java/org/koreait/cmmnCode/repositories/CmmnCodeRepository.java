package org.koreait.cmmnCode.repositories;

import org.koreait.cmmnCode.entities.CmmnCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CmmnCodeRepository extends JpaRepository<CmmnCode, Long>, QuerydslPredicateExecutor {

    CmmnCode findByCmmnCodeNo(Long cmmnCodeNo);

    @Query("select a from CmmnCode a where a.cmmnGroupCode = :cmmn_group_code order by a.sort")
    List<CmmnCode> search(@Param("cmmn_group_code") String cmmnGroupCode);

}
