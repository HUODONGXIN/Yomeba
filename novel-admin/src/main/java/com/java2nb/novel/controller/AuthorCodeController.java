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


import com.java2nb.novel.domain.AuthorCodeDO;
import com.java2nb.novel.service.AuthorCodeService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * 作家招待コードテーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-05-13 11:29:15
 */

@Controller
@RequestMapping("/novel/authorCode")
public class AuthorCodeController {
    @Autowired
    private AuthorCodeService authorCodeService;

    @GetMapping()
    @RequiresPermissions("novel:authorCode:authorCode")
    String AuthorCode() {
        return "novel/authorCode/authorCode";
    }

    @ApiOperation(value = "作家招待コードテーブル一覧を取得", notes = "作家招待コードテーブルの一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:authorCode:authorCode")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<AuthorCodeDO> authorCodeList = authorCodeService.list(query);
        int total = authorCodeService.count(query);
        PageBean pageBean = new PageBean(authorCodeList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "作家招待コード新規追加ページ", notes = "作家招待コードの新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:authorCode:add")
    String add() {
        return "novel/authorCode/add";
    }

    @ApiOperation(value = "作家招待コード編集ページ", notes = "作家招待コードの編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:authorCode:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            AuthorCodeDO authorCode = authorCodeService.get(id);
        model.addAttribute("authorCode", authorCode);
        return "novel/authorCode/edit";
    }

    @ApiOperation(value = "作家招待コード詳細ページ", notes = "作家招待コードの詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:authorCode:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			AuthorCodeDO authorCode = authorCodeService.get(id);
        model.addAttribute("authorCode", authorCode);
        return "novel/authorCode/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "作家招待コード新規追加", notes = "作家招待コードを新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:authorCode:add")
    public R save( AuthorCodeDO authorCode) {
        if (authorCodeService.save(authorCode) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "作家招待コード更新", notes = "作家招待コードを更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:authorCode:edit")
    public R update( AuthorCodeDO authorCode) {
            authorCodeService.update(authorCode);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "作家招待コード削除", notes = "作家招待コードを削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:authorCode:remove")
    public R remove( Long id) {
        if (authorCodeService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "作家招待コード一括削除", notes = "作家招待コードを一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:authorCode:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            authorCodeService.batchRemove(ids);
        return R.ok();
    }

}
