package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupService;
import org.koreait.cmmnCode.Service.CmmnCodeService;
import org.koreait.cmmnCode.Validator.CmmnCodeAddValidator;
import org.koreait.cmmnCode.entities.CmmnCode;
import org.koreait.cmmnCode.entities.CmmnCodeDto;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupDto;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.koreait.cmmnCode.repositories.CmmnCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @Autowired
    private CmmnCodeRepository repository;

    /** 공통 코드 조회 GET */
    @GetMapping("/codeList/{cmmnGroupCode}")
    public String cmmnCodeList(@PathVariable("cmmnGroupCode") String cmmnGroupCode, Model model){
        List<CmmnCode> list = repository.search(cmmnGroupCode);

        model.addAttribute("list", list);

        CmmnCodeGroupDto cmmnCodeGroupDto = groupService.edit(cmmnGroupCode);
        model.addAttribute("gr", cmmnCodeGroupDto);

        return "cmmn/codeList";
    }

    /** 공통 코드 추가 GET */
    @GetMapping("/codeAdd/{cmmnGroupCode}")
    public String cmmnCodeAdd(@PathVariable("cmmnGroupCode") String cmmnGroupCode, Model model){
        CmmnCodeGroupDto cmmnCodeGroupDto = groupService.edit(cmmnGroupCode);
        CmmnCodeDto cmmnCodeDto = new CmmnCodeDto();
        cmmnCodeDto.setCmmnGroupCode(cmmnCodeGroupDto.getCmmnGroupCode());
        model.addAttribute("list", cmmnCodeDto);
        return "cmmn/codeAdd";
    }

    /** 공통 코드 추가 POST */
    @PostMapping("/codeAdd")
    public String cmmnCodeAddPs(@Valid CmmnCodeDto cmmnCodeDto, Errors errors){
        validator.validate(cmmnCodeDto, errors);
//        CmmnCodeDto cmmnCodeDtoPost = new CmmnCodeDto();
//        model.addAttribute("cmmnGroupCode", cmmnCodeDtoPost.getCmmnGroupCode());

        if(errors.hasErrors()){
            return "cmmn/codeAdd";
        }

        service.add(cmmnCodeDto);

        return "redirect:/cmmn/groupCodeList";
    }

    /** 공통 코드 수정 GET */
    @GetMapping("/codeEdit")
    public String edit(@RequestParam("cmmnCodeNo") Long cmmnCodeNo, Model model){
        CmmnCodeDto cmmnCodeDto = service.edit(cmmnCodeNo);
        model.addAttribute("list", cmmnCodeDto);
        return "cmmn/codeEdit";
    }

    /** 공통 코드 수정 POST */
    @PostMapping("/codeEdit")
    public String update(@ModelAttribute("list") CmmnCode cmmnCode) {
        repository.save(cmmnCode);

        return "redirect:/cmmn/groupCodeList";
    }

    /** 공통 코드 삭제 GET */
    @GetMapping("/delete/{cmmnCodeNo}")
    public String delete(@PathVariable Long cmmnCodeNo){
        service.delete(cmmnCodeNo);

        return "redirect:/cmmn/groupCodeList";
    }
}
