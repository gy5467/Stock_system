package org.koreait.cmmnCode.Service;

import jakarta.transaction.Transactional;
import org.koreait.cmmnCode.entities.CmmnCode;
import org.koreait.cmmnCode.entities.CmmnCodeDto;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.koreait.cmmnCode.repositories.CmmnCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CmmnCodeService {
    @Autowired
    private CmmnCodeRepository repository;

    @Autowired
    private CmmnCodeGroupRepository groupRepository;

    /** 공통 코드 추가 */
    @Transactional
    public void add(CmmnCodeDto cmmnCodeDto){
        CmmnCode cmmnCode = CmmnCodeDto.of(cmmnCodeDto);
        repository.save(cmmnCode);
    }

    /**
     * 공통 코드 수정
     */
    @Transactional
    public CmmnCodeDto edit(Long cmmnCodeNo){
        CmmnCode code = repository.findByCmmnCodeNo(cmmnCodeNo);

        CmmnCodeDto cmmnCodeDto = CmmnCodeDto.builder()
                .cmmnCodeNo(code.getCmmnCodeNo())
                .cmmnGroupCode(code.getCmmnGroupCode())
                .cmmnCode(code.getCmmnCode())
                .cmmnCodeNm(code.getCmmnCodeNm())
                .cmmnCodeDc(code.getCmmnCodeDc())
                .sort(code.getSort())
                .useAt(code.getUseAt())
                .build();
        return cmmnCodeDto;
    }

    /** 공통 코드 삭제 */
    public void delete(Long cmmnCodeNo){
        CmmnCode cmmnCode = repository.findByCmmnCodeNo(cmmnCodeNo);
        repository.delete(cmmnCode);
    }
}
