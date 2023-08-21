package com.han.project.controller;

import com.han.project.mapper.SoulSootherMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 返回心灵鸡汤
 */
@RestController
@RequestMapping("/soul")
public class SoulController {

    @Resource
    private SoulSootherMapper soulSootherMapper;

    @GetMapping
    public String getSoulSoother() {
        return soulSootherMapper.getRandSoulSoother();
    }
}
