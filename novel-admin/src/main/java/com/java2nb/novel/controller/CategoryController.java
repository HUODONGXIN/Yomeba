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


import com.java2nb.novel.domain.CategoryDO;
import com.java2nb.novel.service.CategoryService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * ニュースカテゴリテーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 10:03:41
 */

@Controller
@RequestMapping("/novel/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping()
    @RequiresPermissions("novel:category:category")
    String Category() {
        return "novel/category/category";
    }

    @ApiOperation(value = "ニュースカテゴリテーブル一覧を取得", notes = "ニュースカテゴリテーブルの一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:category:category")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<CategoryDO> categoryList = categoryService.list(query);
        int total = categoryService.count(query);
        PageBean pageBean = new PageBean(categoryList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "ニュースカテゴリ新規追加ページ", notes = "ニュースカテゴリの新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:category:add")
    String add() {
        return "novel/category/add";
    }

    @ApiOperation(value = "ニュースカテゴリ編集ページ", notes = "ニュースカテゴリの編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:category:edit")
    String edit(@PathVariable("id") Integer id, Model model) {
            CategoryDO category = categoryService.get(id);
        model.addAttribute("category", category);
        return "novel/category/edit";
    }

    @ApiOperation(value = "ニュースカテゴリ詳細ページ", notes = "ニュースカテゴリの詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:category:detail")
    String detail(@PathVariable("id") Integer id, Model model) {
			CategoryDO category = categoryService.get(id);
        model.addAttribute("category", category);
        return "novel/category/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "ニュースカテゴリ新規追加", notes = "ニュースカテゴリを新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:category:add")
    public R save( CategoryDO category) {
        if (categoryService.save(category) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "ニュースカテゴリ更新", notes = "ニュースカテゴリを更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:category:edit")
    public R update( CategoryDO category) {
            categoryService.update(category);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "ニュースカテゴリ削除", notes = "ニュースカテゴリを削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:category:remove")
    public R remove( Integer id) {
        if (categoryService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "ニュースカテゴリ一括削除", notes = "ニュースカテゴリを一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:category:batchRemove")
    public R remove(@RequestParam("ids[]") Integer[] ids) {
            categoryService.batchRemove(ids);
        return R.ok();
    }

}
