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


import com.java2nb.novel.domain.BookContentDO;
import com.java2nb.novel.service.BookContentService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * 小説コンテンツテーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 19:52:06
 */

@Controller
@RequestMapping("/novel/bookContent")
public class BookContentController {
    @Autowired
    private BookContentService bookContentService;

    @GetMapping()
    @RequiresPermissions("novel:bookContent:bookContent")
    String BookContent() {
        return "novel/bookContent/bookContent";
    }

    @ApiOperation(value = "小説コンテンツテーブル一覧を取得", notes = "小説コンテンツテーブルの一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:bookContent:bookContent")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<BookContentDO> bookContentList = bookContentService.list(query);
        int total = bookContentService.count(query);
        PageBean pageBean = new PageBean(bookContentList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "小説コンテンツ新規追加ページ", notes = "小説コンテンツの新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:bookContent:add")
    String add() {
        return "novel/bookContent/add";
    }

    @ApiOperation(value = "小説コンテンツ編集ページ", notes = "小説コンテンツの編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:bookContent:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            BookContentDO bookContent = bookContentService.get(id);
        model.addAttribute("bookContent", bookContent);
        return "novel/bookContent/edit";
    }

    @ApiOperation(value = "小説コンテンツ詳細ページ", notes = "小説コンテンツの詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:bookContent:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			BookContentDO bookContent = bookContentService.get(id);
        model.addAttribute("bookContent", bookContent);
        return "novel/bookContent/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "小説コンテンツ新規追加", notes = "小説コンテンツを新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:bookContent:add")
    public R save( BookContentDO bookContent) {
        if (bookContentService.save(bookContent) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "小説コンテンツ更新", notes = "小説コンテンツを更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:bookContent:edit")
    public R update( BookContentDO bookContent) {
            bookContentService.update(bookContent);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "小説コンテンツ削除", notes = "小説コンテンツを削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:bookContent:remove")
    public R remove( Long id) {
        if (bookContentService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "小説コンテンツ一括削除", notes = "小説コンテンツを一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:bookContent:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            bookContentService.batchRemove(ids);
        return R.ok();
    }

}
