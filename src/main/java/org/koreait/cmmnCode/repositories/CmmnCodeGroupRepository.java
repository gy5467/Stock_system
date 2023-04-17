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

    @Query("select a from CmmnCodeGroup a where a.cmmnGroupCode = :cmmnGroupCode")
    CmmnCodeGroup findCmmnCodeGroup(@Param("cmmnGroupCode") String cmmnGroupCode);

    /** 등록된 공통 그룹 코드 체크 */
    default boolean isExists(String cmmnGroupCode){
        QCmmnCodeGroup code = QCmmnCodeGroup.cmmnCodeGroup;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(code.cmmnGroupCode.eq(cmmnGroupCode));
        Long cnt = this.count(builder);

        return cnt > 0;
    }
}
