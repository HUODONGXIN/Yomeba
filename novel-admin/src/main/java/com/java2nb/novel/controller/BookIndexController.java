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


import com.java2nb.novel.domain.BookIndexDO;
import com.java2nb.novel.service.BookIndexService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * 小説目次テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 19:51:54
 */

@Controller
@RequestMapping("/novel/bookIndex")
public class BookIndexController {
    @Autowired
    private BookIndexService bookIndexService;

    @GetMapping()
    @RequiresPermissions("novel:bookIndex:bookIndex")
    String BookIndex() {
        return "novel/bookIndex/bookIndex";
    }

    @ApiOperation(value = "小説目次テーブル一覧を取得", notes = "小説目次テーブルの一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:bookIndex:bookIndex")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<BookIndexDO> bookIndexList = bookIndexService.list(query);
        int total = bookIndexService.count(query);
        PageBean pageBean = new PageBean(bookIndexList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "小説目次新規追加ページ", notes = "小説目次の新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:bookIndex:add")
    String add() {
        return "novel/bookIndex/add";
    }

    @ApiOperation(value = "小説目次編集ページ", notes = "小説目次の編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:bookIndex:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            BookIndexDO bookIndex = bookIndexService.get(id);
        model.addAttribute("bookIndex", bookIndex);
        return "novel/bookIndex/edit";
    }

    @ApiOperation(value = "小説目次詳細ページ", notes = "小説目次の詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:bookIndex:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			BookIndexDO bookIndex = bookIndexService.get(id);
        model.addAttribute("bookIndex", bookIndex);
        return "novel/bookIndex/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "小説目次新規追加", notes = "小説目次を新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:bookIndex:add")
    public R save( BookIndexDO bookIndex) {
        if (bookIndexService.save(bookIndex) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "小説目次更新", notes = "小説目次を更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:bookIndex:edit")
    public R update( BookIndexDO bookIndex) {
            bookIndexService.update(bookIndex);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "小説目次削除", notes = "小説目次を削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:bookIndex:remove")
    public R remove( Long id) {
        if (bookIndexService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "小説目次一括削除", notes = "小説目次を一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:bookIndex:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            bookIndexService.batchRemove(ids);
        return R.ok();
    }

}
