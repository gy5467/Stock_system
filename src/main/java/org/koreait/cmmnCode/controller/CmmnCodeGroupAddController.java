package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupAddService;
import org.koreait.cmmnCode.Validator.CmmnCodeGroupAddValidator;
import org.koreait.cmmnCode.entities.CmmnCodeGroupAdd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cmmn/groupCodeAdd")
@RequiredArgsConstructor    // 생성자 주입
public class CmmnCodeGroupAddController {

    private final CmmnCodeGroupAddValidator validator;

    private final CmmnCodeGroupAddService service;

    @GetMapping
    public String groupCodeGroup(Model model) {

        CmmnCodeGroupAdd cmmnCodeGroupAdd = new CmmnCodeGroupAdd();
        model.addAttribute("cmmnCodeGroupAdd", cmmnCodeGroupAdd);
        return "cmmn/groupCodeAdd";
    }

    @PostMapping
    public String groupCodeGroupPs(@Valid CmmnCodeGroupAdd cmmnCodeGroupAdd, Errors errors){   // @Valid - 객체의 제약조건을 검증

        validator.validate(cmmnCodeGroupAdd, errors);

        if(errors.hasErrors()){
            return "cmmn/groupCodeAdd";
        }

        service.add(cmmnCodeGroupAdd);

        return "redirect:/cmmn/groupCodeAdd";
    }
}
