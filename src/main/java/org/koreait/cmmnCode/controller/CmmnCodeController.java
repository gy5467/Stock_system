package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupService;
import org.koreait.cmmnCode.Validator.CmmnCodeGroupAddValidator;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cmmn/*")
@RequiredArgsConstructor
public class CmmnCodeController {

    @Autowired
    private CmmnCodeGroupAddValidator validator;
    @Autowired
    private CmmnCodeGroupService service;

    /** 공통 그룹 코드 조회 GET*/
    @GetMapping("groupCodeList")
    public String cmmnCodeGroupList(Model model) {
        List<CmmnCodeGroupDto> list = service.getCmmnCodeGroupList();
        model.addAttribute("list", list);

        return "cmmn/groupCodeList";
    }

    /** 공통 그룹 코드 추가 GET*/
    @GetMapping("/groupCodeAdd")
    public String cmmnCodeGroupAdd(Model model) {
        CmmnCodeGroupDto cmmnCodeGroupAdd = new CmmnCodeGroupDto();
        model.addAttribute("cmmnCodeGroupAdd", cmmnCodeGroupAdd);
        return "cmmn/groupCodeAdd";
    }

    /** 공통 그룹 코드 추가 POST*/
    @PostMapping("/groupCodeAdd")
    public String cmmnCodeGroupAddPs(@Valid CmmnCodeGroupDto cmmnCodeGroupDto, Errors errors){   // @Valid - 객체의 제약조건을 검증

        validator.validate(cmmnCodeGroupDto, errors);

        if(errors.hasErrors()){
            return "cmmn/groupCodeAdd";
        }

        service.add(cmmnCodeGroupDto);

        return "redirect:/cmmn/groupCodeList";
    }

    /** 공통 그룹 코드 수정 GET*/
    @GetMapping("/groupCodeEdit/{cmmnGroupCode}")
    public String edit(@PathVariable("cmmnGroupCode") String cmmnGroupCode, Model model){
        CmmnCodeGroupDto cmmnCodeGroupDto = service.edit(cmmnGroupCode);
        model.addAttribute("list", cmmnCodeGroupDto);
        return "cmmn/groupCodeEdit";
    }

    /** 공통 그룹 코드 수정 POST*/
    @PostMapping("/groupCodeEdit/{cmmnGroupCode}")
    public String update(@PathVariable("cmmnGroupCode") String cmmnGroupCode, CmmnCodeGroup cmmnCodeGroup){
        CmmnCodeGroup codeNo = service.view(cmmnGroupCode);

        codeNo.setCmmnGroupNm(cmmnCodeGroup.getCmmnGroupNm());
        codeNo.setCmmnGroupDc(cmmnCodeGroup.getCmmnGroupDc());
        service.update(codeNo);
        return "redirect:/cmmn/groupCodeList";
    }

    /** 공통 그룹 코드 삭제 */
    @GetMapping("/delete")
    public String delete(Long cmmnGroupNo){
//        CmmnCodeGroup codeNo = service.view(cmmnGroupCode);
//
//        codeNo.setUseAt("N");
        service.delete(cmmnGroupNo);
        return "redirect:/cmmn/groupCodeList";
    }
}
