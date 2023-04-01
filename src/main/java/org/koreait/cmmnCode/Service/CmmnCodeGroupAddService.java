package org.koreait.cmmnCode.Service;

import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupAdd;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CmmnCodeGroupAddService {
    private final CmmnCodeGroupRepository repository;

    public void add(CmmnCodeGroupAdd cmmnCodeGroupAdd){
        CmmnCodeGroup cmmnCodeGroup = CmmnCodeGroupAdd.of(cmmnCodeGroupAdd);
        repository.save(cmmnCodeGroup);
    }
}
