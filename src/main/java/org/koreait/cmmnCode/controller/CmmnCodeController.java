package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupService;
import org.koreait.cmmnCode.Validator.CmmnCodeGroupAddValidator;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cmmn/*")
@RequiredArgsConstructor
public class CmmnCodeController {

    @Autowired
    private CmmnCodeGroupAddValidator validator;
    @Autowired
    private CmmnCodeGroupService service;

    /** 공통 그룹 코드 조회 */
    @GetMapping("groupCodeList")
    public String cmmnCodeGroupList(Model model) {
        List<CmmnCodeGroupDto> list = service.getCmmnCodeGroupList();
        model.addAttribute("list", list);

        return "cmmn/groupCodeList";
    }

    @PostMapping("groupCodeList")
    public String cmmnCodeGroupListPs(){
        return "cmmn/groupCodeAdd";
    }

    /** 공통 그룹 코드 추가 */
    @GetMapping("/groupCodeAdd")
    public String cmmnCodeGroupAdd(Model model) {

        CmmnCodeGroupDto cmmnCodeGroupAdd = new CmmnCodeGroupDto();
        model.addAttribute("cmmnCodeGroupAdd", cmmnCodeGroupAdd);
        return "cmmn/groupCodeAdd";
    }

    /** 공통 그룹 코드 추가 */
    @PostMapping("/groupCodeAdd")
    public String cmmnCodeGroupAddPs(@Valid CmmnCodeGroupDto cmmnCodeGroupAdd, Errors errors){   // @Valid - 객체의 제약조건을 검증

        validator.validate(cmmnCodeGroupAdd, errors);

        if(errors.hasErrors()){
            return "cmmn/groupCodeAdd";
        }

        service.add(cmmnCodeGroupAdd);

        return "redirect:/cmmn/groupCodeList";
    }
}
