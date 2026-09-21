package com.java2nb.novel.controller;

import com.java2nb.common.utils.R;
import com.java2nb.novel.domain.WebsiteInfoDO;
import com.java2nb.novel.service.WebsiteInfoService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * サイト情報テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 11:05:43
 */

@Controller
@RequestMapping("/novel/websiteInfo")
public class WebsiteInfoController {

    @Autowired
    private WebsiteInfoService websiteInfoService;

    @GetMapping()
    @RequiresPermissions("novel:websiteInfo:websiteInfo")
    String detail(Model model) {
        WebsiteInfoDO websiteInfo = websiteInfoService.get(1L);
        model.addAttribute("websiteInfo", websiteInfo);
        return "novel/websiteInfo/detail";
    }

    /**
     * 更新
     */
    @ApiOperation(value = "サイト情報更新", notes = "サイト情報を更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:websiteInfo:edit")
    public R update(WebsiteInfoDO websiteInfo) {
        websiteInfoService.update(websiteInfo);
        return R.ok();
    }

}
