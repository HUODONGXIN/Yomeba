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


import com.java2nb.novel.domain.UserDO;
import com.java2nb.novel.service.UserService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * 
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 03:49:08
 */

@Controller
@RequestMapping("/novel/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping()
    @RequiresPermissions("novel:user:user")
    String User() {
        return "novel/user/user";
    }

    @ApiOperation(value = "一覧を取得", notes = "一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:user:user")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<UserDO> userList = userService.list(query);
        int total = userService.count(query);
        PageBean pageBean = new PageBean(userList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "新規追加ページ", notes = "新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:user:add")
    String add() {
        return "novel/user/add";
    }

    @ApiOperation(value = "編集ページ", notes = "編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:user:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            UserDO user = userService.get(id);
        model.addAttribute("user", user);
        return "novel/user/edit";
    }

    @ApiOperation(value = "詳細ページ", notes = "詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:user:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			UserDO user = userService.get(id);
        model.addAttribute("user", user);
        return "novel/user/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "新規追加", notes = "新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:user:add")
    public R save( UserDO user) {
        if (userService.save(user) > 0) {
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
    @RequiresPermissions("novel:user:edit")
    public R update( UserDO user) {
            userService.update(user);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "削除", notes = "削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:user:remove")
    public R remove( Long id) {
        if (userService.remove(id) > 0) {
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
    @RequiresPermissions("novel:user:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            userService.batchRemove(ids);
        return R.ok();
    }

}
