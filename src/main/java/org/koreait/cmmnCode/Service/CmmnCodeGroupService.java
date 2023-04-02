package org.koreait.cmmnCode.Service;

import jakarta.transaction.Transactional;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CmmnCodeGroupService {
    @Autowired
    private CmmnCodeGroupRepository repository;

    /** 공통 그룹 코드 조회 */
    @Transactional
    public List<CmmnCodeGroupDto> getCmmnCodeGroupList(){
        List<CmmnCodeGroup> cmmnCodeGroups = repository.findAll();
        List<CmmnCodeGroupDto> cmmnCodeGroupAdds = new ArrayList<>();

        for(CmmnCodeGroup code : cmmnCodeGroups){
            CmmnCodeGroupDto cmmnCodeGroupAdd = CmmnCodeGroupDto.builder()
                    .cmmnGroupCode(code.getCmmnGroupCode())
                    .cmmnGroupNm(code.getCmmnGroupNm())
                    .cmmnGroupDc(code.getCmmnGroupDc())
                    .build();
            cmmnCodeGroupAdds.add(cmmnCodeGroupAdd);
        }
        return cmmnCodeGroupAdds;
    }

    /** 공통 그룹 코드 추가 */
    public void add(CmmnCodeGroupDto cmmnCodeGroupAdd){
        CmmnCodeGroup cmmnCodeGroup = CmmnCodeGroupDto.of(cmmnCodeGroupAdd);
        repository.save(cmmnCodeGroup);
    }
}
