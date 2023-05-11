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
                    .cmmnGroupNo(code.getCmmnGroupNo())
                    .cmmnGroupCode(code.getCmmnGroupCode())
                    .cmmnGroupNm(code.getCmmnGroupNm())
                    .useAt(code.getUseAt())
                    .build();
            cmmnCodeGroupAdds.add(cmmnCodeGroupAdd);
        }
        return cmmnCodeGroupAdds;
    }

    /** 공통 그룹 코드 추가 */
    @Transactional
    public void add(CmmnCodeGroupDto cmmnCodeGroupDto){
        CmmnCodeGroup cmmnCodeGroup = CmmnCodeGroupDto.of(cmmnCodeGroupDto);
        repository.save(cmmnCodeGroup);
     }

    /** 공통 그룹 코드 수정 데이터 */
    @Transactional
    public CmmnCodeGroupDto edit(Long cmmnGroupNo){
        CmmnCodeGroup cmmnCodeGroup = repository.findByCmmnGroupNo(cmmnGroupNo);

        CmmnCodeGroupDto cmmnCodeGroupDto = CmmnCodeGroupDto.builder()
                .cmmnGroupNo(cmmnCodeGroup.getCmmnGroupNo())
                .cmmnGroupCode(cmmnCodeGroup.getCmmnGroupCode())
                .cmmnGroupNm(cmmnCodeGroup.getCmmnGroupNm())
                .cmmnGroupDc(cmmnCodeGroup.getCmmnGroupDc())
                .useAt(cmmnCodeGroup.getUseAt())
                .build();
        return cmmnCodeGroupDto;
    }

    /** 임시 */
    @Transactional
    public CmmnCodeGroupDto search(String cmmnGroupCode){
        CmmnCodeGroup cmmnCodeGroup = repository.findByCmmnGroupCode(cmmnGroupCode);

        CmmnCodeGroupDto cmmnCodeGroupDto = CmmnCodeGroupDto.builder()
                .cmmnGroupNo(cmmnCodeGroup.getCmmnGroupNo())
                .cmmnGroupCode(cmmnCodeGroup.getCmmnGroupCode())
                .cmmnGroupNm(cmmnCodeGroup.getCmmnGroupNm())
                .cmmnGroupDc(cmmnCodeGroup.getCmmnGroupDc())
                .useAt(cmmnCodeGroup.getUseAt())
                .build();
        return cmmnCodeGroupDto;
    }

    /** 공통 그룹 코드 삭제 */
    @Transactional
    public void delete(Long cmmnGroupNo) {
        repository.deleteById(cmmnGroupNo);
    }
}
