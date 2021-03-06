package io.github.ihelin.seven.open.controller;

import io.github.ihelin.seven.open.service.MallService;
import io.github.ihelin.seven.open.vo.SearchParam;
import io.github.ihelin.seven.open.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author iHelin
 * @since 2021/1/18 11:25
 */
@Controller
public class SearchController {

    @Autowired
    private MallService mallService;

    @GetMapping("/list.html")
    public String listPage(SearchParam searchParam, Model model, HttpServletRequest request) {

        // 获取路径原生的查询属性
        searchParam.setQueryString(request.getQueryString());
//        // ES中检索到的结果 传递给页面
        SearchResult result = mallService.search(searchParam);
        model.addAttribute("result", result);
        return "list";
    }
}
