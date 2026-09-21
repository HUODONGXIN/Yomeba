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


import com.java2nb.novel.domain.PayDO;
import com.java2nb.novel.service.PayService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * チャージ注文
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2020-12-01 03:49:57
 */

@Controller
@RequestMapping("/novel/pay")
public class PayController {
    @Autowired
    private PayService payService;

    @GetMapping()
    @RequiresPermissions("novel:pay:pay")
    String Pay() {
        return "novel/pay/pay";
    }

    @ApiOperation(value = "チャージ注文一覧を取得", notes = "チャージ注文の一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("novel:pay:pay")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<PayDO> payList = payService.list(query);
        int total = payService.count(query);
        PageBean pageBean = new PageBean(payList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "チャージ注文新規追加ページ", notes = "チャージ注文の新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("novel:pay:add")
    String add() {
        return "novel/pay/add";
    }

    @ApiOperation(value = "チャージ注文編集ページ", notes = "チャージ注文の編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("novel:pay:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            PayDO pay = payService.get(id);
        model.addAttribute("pay", pay);
        return "novel/pay/edit";
    }

    @ApiOperation(value = "チャージ注文詳細ページ", notes = "チャージ注文の詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("novel:pay:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			PayDO pay = payService.get(id);
        model.addAttribute("pay", pay);
        return "novel/pay/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "チャージ注文新規追加", notes = "チャージ注文を新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("novel:pay:add")
    public R save( PayDO pay) {
        if (payService.save(pay) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "チャージ注文更新", notes = "チャージ注文を更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("novel:pay:edit")
    public R update( PayDO pay) {
            payService.update(pay);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "チャージ注文削除", notes = "チャージ注文を削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("novel:pay:remove")
    public R remove( Long id) {
        if (payService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "チャージ注文一括削除", notes = "チャージ注文を一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("novel:pay:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            payService.batchRemove(ids);
        return R.ok();
    }

}
