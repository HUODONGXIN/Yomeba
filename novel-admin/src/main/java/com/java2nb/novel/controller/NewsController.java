package com.java2nb.novel.controller;

import com.java2nb.common.config.CacheKey;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;
import com.java2nb.novel.domain.NewsDO;
import com.java2nb.novel.service.NewsService;
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
 * ニューステーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 10:05:51
 */

@Controller
@RequestMapping("/novel/news")
public class NewsController {

    @Autowired
    private NewsService newsService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping()
    @RequiresPermissions("novel:news:news")
    String News() {
        return "novel/news/news";
    }

    @ApiOperation(value = "ニューステーブル一覧を取得", notes = "ニューステーブルの一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:news:news")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<NewsDO> newsList = newsService.list(query);
        int total = newsService.count(query);
        PageBean pageBean = new PageBean(newsList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "ニュース新規追加ページ", notes = "ニュースの新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:news:add")
    String add() {
        return "novel/news/add";
    }

    @ApiOperation(value = "ニュース編集ページ", notes = "ニュースの編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:news:edit")
    String edit(@PathVariable("id") Long id, Model model) {
        NewsDO news = newsService.get(id);
        model.addAttribute("news", news);
        return "novel/news/edit";
    }

    @ApiOperation(value = "ニュース詳細ページ", notes = "ニュースの詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:news:detail")
    String detail(@PathVariable("id") Long id, Model model) {
        NewsDO news = newsService.get(id);
        model.addAttribute("news", news);
        return "novel/news/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "ニュース新規追加", notes = "ニュースを新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:news:add")
    public R save(NewsDO news) {
        if (newsService.save(news) > 0) {
            redisTemplate.delete(CacheKey.INDEX_NEWS_KEY);
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "ニュース更新", notes = "ニュースを更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:news:edit")
    public R update(NewsDO news) {
        newsService.update(news);
        redisTemplate.delete(CacheKey.INDEX_NEWS_KEY);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "ニュース削除", notes = "ニュースを削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:news:remove")
    public R remove(Long id) {
        if (newsService.remove(id) > 0) {
            redisTemplate.delete(CacheKey.INDEX_NEWS_KEY);
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "ニュース一括削除", notes = "ニュースを一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:news:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
        newsService.batchRemove(ids);
        redisTemplate.delete(CacheKey.INDEX_NEWS_KEY);
        return R.ok();
    }

}
