package com.java2nb.novel.controller;

import com.java2nb.common.config.CacheKey;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;
import com.java2nb.novel.domain.BookSettingDO;
import com.java2nb.novel.service.BookSettingService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ホーム小説レコメンド
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-18 10:01:13
 */

@Controller
@RequestMapping("/novel/bookSetting")
public class BookSettingController {

    @Autowired
    private BookSettingService bookSettingService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping()
    @RequiresPermissions("novel:bookSetting:bookSetting")
    String BookSetting() {
        return "novel/bookSetting/bookSetting";
    }

    @ApiOperation(value = "ホーム小説設定テーブル一覧を取得", notes = "ホーム小説設定テーブルの一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:bookSetting:bookSetting")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<BookSettingDO> bookSettingList = bookSettingService.list(query);
        int total = bookSettingService.count(query);
        PageBean pageBean = new PageBean(bookSettingList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "ホーム小説設定新規追加ページ", notes = "ホーム小説設定の新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:bookSetting:add")
    String add() {
        return "novel/bookSetting/add";
    }

    @ApiOperation(value = "ホーム小説設定編集ページ", notes = "ホーム小説設定の編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:bookSetting:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        BookSettingDO bookSetting = bookSettingService.get(id);
        model.addAttribute("bookSetting", bookSetting);
        return "novel/bookSetting/edit";
    }

    @ApiOperation(value = "ホーム小説設定詳細ページ", notes = "ホーム小説設定の詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:bookSetting:detail")
    String detail(@PathVariable("id") Long id, Model model) {
        BookSettingDO bookSetting = bookSettingService.get(id);
        model.addAttribute("bookSetting", bookSetting);
        return "novel/bookSetting/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "ホーム小説設定新規追加", notes = "ホーム小説設定を新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:bookSetting:add")
    public R save(BookSettingDO bookSetting) {
        if (bookSettingService.save(bookSetting) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "ホーム小説設定更新", notes = "ホーム小説設定を更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:bookSetting:edit")
    public R update(BookSettingDO bookSetting) {
        bookSettingService.update(bookSetting);
        redisTemplate.delete(CacheKey.INDEX_BOOK_SETTINGS_KEY);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "ホーム小説設定削除", notes = "ホーム小説設定を削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:bookSetting:remove")
    public R remove(Long id) {
        if (bookSettingService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "ホーム小説設定一括削除", notes = "ホーム小説設定を一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:bookSetting:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        bookSettingService.batchRemove(ids);
        return R.ok();
    }

}
