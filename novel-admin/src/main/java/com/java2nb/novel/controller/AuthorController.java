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


import com.java2nb.novel.domain.AuthorDO;
import com.java2nb.novel.service.AuthorService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * 作家テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-05-13 11:16:51
 */

@Controller
@RequestMapping("/novel/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping()
    @RequiresPermissions("novel:author:author")
    String Author() {
        return "novel/author/author";
    }

    @ApiOperation(value = "作家テーブル一覧を取得", notes = "作家テーブルの一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:author:author")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<AuthorDO> authorList = authorService.list(query);
        int total = authorService.count(query);
        PageBean pageBean = new PageBean(authorList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "作家新規追加ページ", notes = "作家の新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:author:add")
    String add() {
        return "novel/author/add";
    }

    @ApiOperation(value = "作家編集ページ", notes = "作家の編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:author:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            AuthorDO author = authorService.get(id);
        model.addAttribute("author", author);
        return "novel/author/edit";
    }

    @ApiOperation(value = "作家詳細ページ", notes = "作家の詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:author:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			AuthorDO author = authorService.get(id);
        model.addAttribute("author", author);
        return "novel/author/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "作家新規追加", notes = "作家を新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:author:add")
    public R save( AuthorDO author) {
        if (authorService.save(author) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "作家更新", notes = "作家を更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:author:edit")
    public R update( AuthorDO author) {
            authorService.update(author);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "作家削除", notes = "作家を削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:author:remove")
    public R remove( Long id) {
        if (authorService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "作家一括削除", notes = "作家を一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:author:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            authorService.batchRemove(ids);
        return R.ok();
    }

}
