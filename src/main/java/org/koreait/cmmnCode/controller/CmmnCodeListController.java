package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupAdd;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cmmn/groupCodeList")
@RequiredArgsConstructor
public class CmmnCodeListController {

    @GetMapping
    public String cmmnCodeGroup(Model model) {
        List<CmmnCodeGroup> list = new ArrayList<>();
        model.addAttribute("list", list);

        return "cmmn/groupCodeList";
    }

    @PostMapping
    public String cmmnCodeGroupPs(@Valid CmmnCodeGroupAdd cmmnCodeGroupAdd, Errors errors){   // @Valid - 객체의 제약조건을 검증

        return "redirect:/cmmn/groupCodeList";
    }
}
