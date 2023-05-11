package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupService;
import org.koreait.cmmnCode.Validator.CmmnCodeGroupAddValidator;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cmmn/*")
@RequiredArgsConstructor
public class CmmnCodeGroupController {

    @Autowired
    private CmmnCodeGroupAddValidator validator;
    @Autowired
    private CmmnCodeGroupService service;
    @Autowired
    private CmmnCodeGroupRepository repository;

    /** 공통 그룹 코드 조회 GET*/
    @GetMapping("groupCodeList")
    public String cmmnCodeGroupList(Model model) {
        List<CmmnCodeGroupDto> list = service.getCmmnCodeGroupList();
        model.addAttribute("code", list);

        return "cmmn/groupCodeList";
    }

    /** 공통 그룹 코드 추가 GET*/
    @GetMapping("/groupCodeAdd")
    public String cmmnCodeGroupAdd(Model model) {
        CmmnCodeGroupDto cmmnCodeGroupAdd = new CmmnCodeGroupDto();
        model.addAttribute("code", cmmnCodeGroupAdd);
        return "cmmn/groupCodeAdd";
    }

    /** 공통 그룹 코드 추가 POST*/
    @PostMapping("/groupCodeAdd")
    public String cmmnCodeGroupAddPs(@Valid @ModelAttribute("code") CmmnCodeGroupDto cmmnCodeGroupDto, Errors errors){   // @Valid - 객체의 제약조건을 검증
        validator.validate(cmmnCodeGroupDto, errors);

        if(errors.hasErrors()){
            return "cmmn/groupCodeAdd";
        }

        service.add(cmmnCodeGroupDto);

        return "redirect:/cmmn/groupCodeList";
    }

    /** 공통 그룹 코드 수정 GET*/
    @GetMapping("/groupCodeEdit")
    public String edit(@RequestParam("cmmnGroupNo") Long cmmnGroupNo, Model model){
        CmmnCodeGroupDto cmmnCodeGroupDto = service.edit(cmmnGroupNo);
        model.addAttribute("code", cmmnCodeGroupDto);
        return "cmmn/groupCodeEdit";
    }

    /** 공통 그룹 코드 수정 POST*/
    @PostMapping("/groupCodeEdit")
    public String update(@ModelAttribute("code") CmmnCodeGroup cmmnGroupCode){
        repository.save(cmmnGroupCode);

        return "redirect:/cmmn/groupCodeList";
    }

    /** 공통 그룹 코드 삭제 */
//    @GetMapping("/delete")
//    public String delete(Long cmmnGroupNo){
////        CmmnCodeGroup codeNo = service.view(cmmnGroupCode);
////
////        codeNo.setUseAt("N");
//        service.delete(cmmnGroupNo);
//        return "redirect:/cmmn/groupCodeList";
//    }
}
