package org.koreait.cmmnCode.Service;

import jakarta.transaction.Transactional;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupAdd;
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
    public List<CmmnCodeGroupAdd> getCmmnCodeGroupList(){
        List<CmmnCodeGroup> cmmnCodeGroups = repository.findAll();
        List<CmmnCodeGroupAdd> cmmnCodeGroupAdds = new ArrayList<>();

        for(CmmnCodeGroup code : cmmnCodeGroups){
            CmmnCodeGroupAdd cmmnCodeGroupAdd = CmmnCodeGroupAdd.builder()
                    .cmmnGroupCode(code.getCmmnGroupCode())
                    .cmmnGroupNm(code.getCmmnGroupNm())
                    .cmmnGroupDc(code.getCmmnGroupDc())
                    .build();
            cmmnCodeGroupAdds.add(cmmnCodeGroupAdd);
        }
        return cmmnCodeGroupAdds;
    }

    /** 공통 그룹 코드 추가 */
    public void add(CmmnCodeGroupAdd cmmnCodeGroupAdd){
        CmmnCodeGroup cmmnCodeGroup = CmmnCodeGroupAdd.of(cmmnCodeGroupAdd);
        repository.save(cmmnCodeGroup);
    }
}
