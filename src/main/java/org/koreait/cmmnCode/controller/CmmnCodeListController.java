package org.koreait.cmmnCode.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.koreait.cmmnCode.Service.CmmnCodeGroupService;
import org.koreait.cmmnCode.entities.CmmnCodeGroup;
import org.koreait.cmmnCode.entities.CmmnCodeGroupAdd;
import org.koreait.cmmnCode.repositories.CmmnCodeGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CmmnCodeGroupService service;

    @GetMapping
    public String cmmnCodeGroup(Model model) {
        List<CmmnCodeGroupAdd> list = service.getCmmnCodeGroupList();
        model.addAttribute("list", list);

        return "cmmn/groupCodeList";
    }
}
