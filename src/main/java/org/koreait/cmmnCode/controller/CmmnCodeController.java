package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupService;
import org.koreait.cmmnCode.Service.CmmnCodeService;
import org.koreait.cmmnCode.Validator.CmmnCodeAddValidator;
import org.koreait.cmmnCode.entities.CmmnCodeDto;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.koreait.cmmnCode.repositories.CmmnCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cmmn/*")
@RequiredArgsConstructor
public class CmmnCodeController {

    @Autowired
    private CmmnCodeAddValidator validator;

    @Autowired
    private CmmnCodeService service;

    @Autowired
    private CmmnCodeGroupService groupService;

    /** 공통 코드 조회 GET */
    @GetMapping("/codeList/{cmmnGroupCode}")
    public String cmmnCodeList(@PathVariable("cmmnGroupCode") String cmmnGroupCode, Model model){
        List<CmmnCodeDto> list = service.getCmmnCodeList();
        model.addAttribute("list", list);

        CmmnCodeGroupDto cmmnCodeGroupDto = groupService.edit(cmmnGroupCode);
        model.addAttribute("gr", cmmnCodeGroupDto);

        return "cmmn/codeList";
    }

    /** 공통 코드 추가 GET */
    @GetMapping("/codeAdd/{cmmnGroupCode}")
    public String cmmnCodeAdd(@PathVariable("cmmnGroupCode") String cmmnGroupCode, Model model){
        CmmnCodeGroupDto cmmnCodeGroupDto = groupService.edit(cmmnGroupCode);
        model.addAttribute("list", cmmnCodeGroupDto);

        CmmnCodeDto cmmnCodeDto = new CmmnCodeDto();
        model.addAttribute("code", cmmnCodeDto);
        return "cmmn/codeAdd";
    }

    /** 공통 코드 추가 POST */
    @PostMapping("/codeAdd")
    public String cmmnCodeAddPs(@Valid CmmnCodeDto cmmnCodeDto, Errors errors){
        validator.validate(cmmnCodeDto, errors);

        if(errors.hasErrors()){
            return "cmmn/codeAdd";
        }

        service.add(cmmnCodeDto);

        return "redirect:/cmmn/codeList";
    }
}
