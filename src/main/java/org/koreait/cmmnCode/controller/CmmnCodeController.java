package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupService;
import org.koreait.cmmnCode.Service.CmmnCodeService;
import org.koreait.cmmnCode.Validator.CmmnCodeAddValidator;
import org.koreait.cmmnCode.Validator.CmmnCodeSortValidator;
import org.koreait.cmmnCode.entities.CmmnCode;
import org.koreait.cmmnCode.entities.CmmnCodeDto;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.koreait.cmmnCode.repositories.CmmnCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/cmmn/*")
@RequiredArgsConstructor
public class CmmnCodeController {

    @Autowired
    private CmmnCodeAddValidator validator;
    @Autowired
    private CmmnCodeSortValidator sortValidator;
    @Autowired
    private CmmnCodeService service;
    @Autowired
    private CmmnCodeGroupService groupService;
    @Autowired
    private CmmnCodeRepository repository;

    /** 공통 코드 조회 GET */
    @GetMapping("/codeList/{cmmnGroupCode}")
    public String cmmnCodeList(@PathVariable("cmmnGroupCode") String cmmnGroupCode, Model model){
        List<CmmnCode> list = repository.search(cmmnGroupCode);

        model.addAttribute("code", list);

        CmmnCodeGroupDto cmmnCodeGroupDto = groupService.search(cmmnGroupCode);
        model.addAttribute("gr", cmmnCodeGroupDto);

        return "cmmn/codeList";
    }

    /** 공통 코드 추가 GET */
    @GetMapping("/codeAdd/{cmmnGroupCode}")
    public String cmmnCodeAdd(@PathVariable("cmmnGroupCode") String cmmnGroupCode, Model model){
        CmmnCodeGroupDto cmmnCodeGroupDto = groupService.search(cmmnGroupCode);
        CmmnCodeDto cmmnCodeDto = new CmmnCodeDto();
        cmmnCodeDto.setCmmnGroupCode(cmmnCodeGroupDto.getCmmnGroupCode());
        model.addAttribute("code", cmmnCodeDto);
        return "cmmn/codeAdd";
    }

    /** 공통 코드 추가 POST */
    @PostMapping("/codeAdd")
    public String cmmnCodeAddPs(@Valid @ModelAttribute("code") CmmnCodeDto cmmnCodeDto, Errors errors, RedirectAttributes redirectAttributes){
        validator.validate(cmmnCodeDto, errors);
        sortValidator.validate(cmmnCodeDto, errors);
        redirectAttributes.addAttribute("cmmnGroupCode", cmmnCodeDto.getCmmnGroupCode());

        if(errors.hasErrors()){
            return "cmmn/codeAdd";
        }

        service.add(cmmnCodeDto);

        return "redirect:/cmmn/codeList/{cmmnGroupCode}";
    }

    /** 공통 코드 수정 GET */
    @GetMapping("/codeEdit")
    public String edit(@RequestParam("cmmnCodeNo") Long cmmnCodeNo, Model model){
        CmmnCodeDto cmmnCodeDto = service.edit(cmmnCodeNo);
        model.addAttribute("code", cmmnCodeDto);
        return "cmmn/codeEdit";
    }

    /** 공통 코드 수정 POST */
    @PostMapping("/codeEdit")
    public String update(@Valid @ModelAttribute("code") CmmnCode cmmnCode, Errors errors, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("cmmnGroupCode", cmmnCode.getCmmnGroupCode());
        sortValidator.validate(cmmnCode, errors);
        repository.save(cmmnCode);

        return "redirect:/cmmn/codeList/{cmmnGroupCode}";
    }

    /** 공통 코드 삭제 GET */
    @GetMapping("/delete/{cmmnCodeNo}")
    public String delete(@PathVariable Long cmmnCodeNo){
        service.delete(cmmnCodeNo);

        return "redirect:/cmmn/groupCodeList";
    }
}
