package org.koreait.cmmnCode.Validator;

import com.querydsl.core.BooleanBuilder;
import org.koreait.cmmnCode.entities.CmmnCodeDto;
import org.koreait.cmmnCode.entities.QCmmnCode;
import org.koreait.cmmnCode.repositories.CmmnCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class CmmnCodeAddValidator implements Validator {

    @Autowired
    private CmmnCodeRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return CmmnCodeDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CmmnCodeDto cmmnCodeDto = (CmmnCodeDto) target;
        String cmmnCode = cmmnCodeDto.getCmmnCode();

        /** 코드명 중복 여부 */
        if(cmmnCode != null && !cmmnCode.isBlank()){
            BooleanBuilder builder = new BooleanBuilder();
            QCmmnCode cmmnCode1 = QCmmnCode.cmmnCode1;
            builder.and(cmmnCode1.cmmnCode.eq(cmmnCode));
            long count = repository.count(builder);
            if(count > 0){
                errors.rejectValue("cmmnCode", "code", "이미 추가된 코드");
            }
        }
    }
}
