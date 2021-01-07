package io.github.ihelin.seven.generator.controller;

import io.github.ihelin.seven.generator.service.GeneratorService;
import io.github.ihelin.seven.generator.utils.PageUtils;
import io.github.ihelin.seven.generator.utils.Query;
import io.github.ihelin.seven.generator.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 代码生成器
 *
 * @author iHelin ihelin@outlook.com
 * @since 2021-01-07 12:43
 */
@Controller
@RequestMapping("/sys/generator")
public class SysGeneratorController {

    @Autowired
    private GeneratorService generatorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils pageUtil = generatorService.queryList(new Query(params));
        return R.ok().put("page", pageUtil);
    }

    /**
     * 生成代码
     */
    @GetMapping("/code")
    public void code(String tables, HttpServletResponse response) throws IOException {
        generatorService.generatorCode(tables.split(","), response.getOutputStream());

        response.setHeader("Content-Disposition", "attachment; filename=\"seven.zip\"");
        response.setContentType("application/octet-stream; charset=UTF-8");
    }
}
