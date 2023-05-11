package org.koreait.cmmnCode.Validator;

import com.querydsl.core.BooleanBuilder;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.koreait.cmmnCode.entities.QCmmnCodeGroup;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CmmnCodeGroupAddValidator implements Validator {

    @Autowired
    private CmmnCodeGroupRepository repository;

    @Override
    public boolean supports(Class<?> clazz) { return CmmnCodeGroupDto.class.isAssignableFrom(clazz); }

    @Override
    public void validate(Object target, Errors errors) {
        CmmnCodeGroupDto cmmnCodeGroupAdd = (CmmnCodeGroupDto) target;
        String cmmnGroupCode = cmmnCodeGroupAdd.getCmmnGroupCode();

        /** 그룹 코드명 중복 여부 */
        if(cmmnGroupCode != null && !cmmnGroupCode.isBlank()){
            BooleanBuilder builder = new BooleanBuilder();
            QCmmnCodeGroup cmmnCodeGroup = QCmmnCodeGroup.cmmnCodeGroup;
            builder.and(cmmnCodeGroup.cmmnGroupCode.eq(cmmnGroupCode));
            long count = repository.count(builder);
            if(count > 0){
                errors.rejectValue("cmmnGroupCode", "groupCode", "이미 추가된 그룹코드입니다.");
            }
        }

    }
}
