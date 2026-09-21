package com.java2nb.novel.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import io.swagger.annotations.ApiOperation;


import com.java2nb.novel.domain.UserFeedbackDO;
import com.java2nb.novel.service.UserFeedbackService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * 
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-18 11:08:54
 */

@Controller
@RequestMapping("/novel/userFeedback")
public class UserFeedbackController {
    @Autowired
    private UserFeedbackService userFeedbackService;

    @GetMapping()
    @RequiresPermissions("novel:userFeedback:userFeedback")
    String UserFeedback() {
        return "novel/userFeedback/userFeedback";
    }

    @ApiOperation(value = "一覧を取得", notes = "一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:userFeedback:userFeedback")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<UserFeedbackDO> userFeedbackList = userFeedbackService.list(query);
        int total = userFeedbackService.count(query);
        PageBean pageBean = new PageBean(userFeedbackList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "新規追加ページ", notes = "新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:userFeedback:add")
    String add() {
        return "novel/userFeedback/add";
    }

    @ApiOperation(value = "編集ページ", notes = "編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:userFeedback:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            UserFeedbackDO userFeedback = userFeedbackService.get(id);
        model.addAttribute("userFeedback", userFeedback);
        return "novel/userFeedback/edit";
    }

    @ApiOperation(value = "詳細ページ", notes = "詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:userFeedback:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			UserFeedbackDO userFeedback = userFeedbackService.get(id);
        model.addAttribute("userFeedback", userFeedback);
        return "novel/userFeedback/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新規追加", notes = "新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:userFeedback:add")
    public R save( UserFeedbackDO userFeedback) {
        if (userFeedbackService.save(userFeedback) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "更新", notes = "更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:userFeedback:edit")
    public R update( UserFeedbackDO userFeedback) {
            userFeedbackService.update(userFeedback);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "削除", notes = "削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:userFeedback:remove")
    public R remove( Long id) {
        if (userFeedbackService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "一括削除", notes = "一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:userFeedback:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            userFeedbackService.batchRemove(ids);
        return R.ok();
    }

}
