package com.java2nb.system.controller;

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


import com.java2nb.system.domain.RoleDataPermDO;
import com.java2nb.system.service.RoleDataPermService;
import com.java2nb.common.utils.PageBean;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

/**
 * ロールとデータ権限の対応関係
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2019-11-25 11:32:49
 */

@Controller
@RequestMapping("/system/roleDataPerm")
public class RoleDataPermController {
    @Autowired
    private RoleDataPermService roleDataPermService;

    @GetMapping()
    @RequiresPermissions("system:roleDataPerm:roleDataPerm")
    String RoleDataPerm() {
        return "system/roleDataPerm/roleDataPerm";
    }

    @ApiOperation(value = "ロールとデータ権限の対応関係一覧を取得", notes = "ロールとデータ権限の対応関係の一覧を取得します")
    @ResponseBody
    @GetMapping("/list")
    @RequiresPermissions("system:roleDataPerm:roleDataPerm")
    public R list(@RequestParam Map<String, Object> params) {
        //一覧データを検索
        Query query = new Query(params);
        List<RoleDataPermDO> roleDataPermList = roleDataPermService.list(query);
        int total = roleDataPermService.count(query);
        PageBean pageBean = new PageBean(roleDataPermList, total);
        return R.ok().put("data", pageBean);
    }

    @ApiOperation(value = "ロールとデータ権限の対応関係新規追加ページ", notes = "ロールとデータ権限の対応関係の新規追加ページを表示します")
    @GetMapping("/add")
    @RequiresPermissions("system:roleDataPerm:add")
    String add() {
        return "system/roleDataPerm/add";
    }

    @ApiOperation(value = "ロールとデータ権限の対応関係編集ページ", notes = "ロールとデータ権限の対応関係の編集ページを表示します")
    @GetMapping("/edit/{id}")
    @RequiresPermissions("system:roleDataPerm:edit")
    String edit(@PathVariable("id") Long id, Model model) {
            RoleDataPermDO roleDataPerm = roleDataPermService.get(id);
        model.addAttribute("roleDataPerm", roleDataPerm);
        return "system/roleDataPerm/edit";
    }

    @ApiOperation(value = "ロールとデータ権限の対応関係詳細ページ", notes = "ロールとデータ権限の対応関係の詳細ページを表示します")
    @GetMapping("/detail/{id}")
    @RequiresPermissions("system:roleDataPerm:detail")
    String detail(@PathVariable("id") Long id, Model model) {
			RoleDataPermDO roleDataPerm = roleDataPermService.get(id);
        model.addAttribute("roleDataPerm", roleDataPerm);
        return "system/roleDataPerm/detail";
    }

    /**
     * 保存
     */
    @ApiOperation(value = "ロールとデータ権限の対応関係新規追加", notes = "ロールとデータ権限の対応関係を新規追加します")
    @ResponseBody
    @PostMapping("/save")
    @RequiresPermissions("system:roleDataPerm:add")
    public R save( RoleDataPermDO roleDataPerm) {
        if (roleDataPermService.save(roleDataPerm) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 更新
     */
    @ApiOperation(value = "ロールとデータ権限の対応関係更新", notes = "ロールとデータ権限の対応関係を更新します")
    @ResponseBody
    @RequestMapping("/update")
    @RequiresPermissions("system:roleDataPerm:edit")
    public R update( RoleDataPermDO roleDataPerm) {
            roleDataPermService.update(roleDataPerm);
        return R.ok();
    }

    /**
     * 削除
     */
    @ApiOperation(value = "ロールとデータ権限の対応関係削除", notes = "ロールとデータ権限の対応関係を削除します")
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("system:roleDataPerm:remove")
    public R remove( Long id) {
        if (roleDataPermService.remove(id) > 0) {
            return R.ok();
        }
        return R.error();
    }

    /**
     * 一括削除
     */
    @ApiOperation(value = "ロールとデータ権限の対応関係一括削除", notes = "ロールとデータ権限の対応関係を一括削除します")
    @PostMapping("/batchRemove")
    @ResponseBody
    @RequiresPermissions("system:roleDataPerm:batchRemove")
    public R remove(@RequestParam("ids[]") Long[] ids) {
            roleDataPermService.batchRemove(ids);
        return R.ok();
    }

}
