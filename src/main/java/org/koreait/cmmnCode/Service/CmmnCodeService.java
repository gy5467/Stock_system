package org.koreait.cmmnCode.Service;

import jakarta.transaction.Transactional;
import org.koreait.cmmnCode.entities.CmmnCode;
import org.koreait.cmmnCode.entities.CmmnCodeDto;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.koreait.cmmnCode.repositories.CmmnCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    public CmmnCodeGroupDto groupCode(String cmmnGroupCode){
        CmmnCodeGroup cmmnCodeGroup = groupRepository.findByCmmnGroupCode(cmmnGroupCode);

        CmmnCodeGroupDto cmmnCodeGroupDto = CmmnCodeGroupDto.builder()
                .cmmnGroupCode(cmmnCodeGroup.getCmmnGroupCode())
                .build();
        return cmmnCodeGroupDto;
    }

    @Transactional
    public CmmnCode listPS(String cmmnGroupCode){
        CmmnCode cmmnCode = repository.findByCmmnCode(cmmnGroupCode);
        return cmmnCode;
    }
}
