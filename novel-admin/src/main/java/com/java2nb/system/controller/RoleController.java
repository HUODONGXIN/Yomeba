package com.java2nb.system.controller;

import com.java2nb.common.annotation.Log;
import com.java2nb.common.config.Constant;
import com.java2nb.common.controller.BaseController;
import com.java2nb.common.utils.R;
import com.java2nb.system.domain.RoleDO;
import com.java2nb.system.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/sys/role")
@Controller
public class RoleController extends BaseController {
	String prefix = "system/role";
	@Autowired
	RoleService roleService;

	@RequiresPermissions("sys:role:role")
	@GetMapping()
	String role() {
		return prefix + "/role";
	}

	@RequiresPermissions("sys:role:role")
	@GetMapping("/list")
	@ResponseBody()
	List<RoleDO> list() {
		List<RoleDO> roles = roleService.list();
		return roles;
	}

	@Log("ロール新規追加")
	@RequiresPermissions("sys:role:add")
	@GetMapping("/add")
	String add() {
		return prefix + "/add";
	}

	@Log("ロール編集")
	@RequiresPermissions("sys:role:edit")
	@GetMapping("/edit/{id}")
	String edit(@PathVariable("id") Long id, Model model) {
		RoleDO roleDO = roleService.get(id);
		model.addAttribute("role", roleDO);
		return prefix + "/edit";
	}

	@Log("ロール保存")
	@RequiresPermissions("sys:role:add")
	@PostMapping("/save")
	@ResponseBody()
	R save(RoleDO role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "デモシステムでは変更できません。完全な機能をご利用になるにはプログラムをデプロイしてください。");
		}
		if (roleService.save(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存に失敗しました");
		}
	}

	@Log("ロール更新")
	@RequiresPermissions("sys:role:edit")
	@PostMapping("/update")
	@ResponseBody()
	R update(RoleDO role) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "デモシステムでは変更できません。完全な機能をご利用になるにはプログラムをデプロイしてください。");
		}
		if (roleService.update(role) > 0) {
			return R.ok();
		} else {
			return R.error(1, "保存に失敗しました");
		}
	}

	@Log("ロール削除")
	@RequiresPermissions("sys:role:remove")
	@PostMapping("/remove")
	@ResponseBody()
	R save(Long id) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "デモシステムでは変更できません。完全な機能をご利用になるにはプログラムをデプロイしてください。");
		}
		if (roleService.remove(id) > 0) {
			return R.ok();
		} else {
			return R.error(1, "削除に失敗しました");
		}
	}
	
	@RequiresPermissions("sys:role:batchRemove")
	@Log("ロール一括削除")
	@PostMapping("/batchRemove")
	@ResponseBody
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		if (Constant.DEMO_ACCOUNT.equals(getUsername())) {
			return R.error(1, "デモシステムでは変更できません。完全な機能をご利用になるにはプログラムをデプロイしてください。");
		}
		int r = roleService.batchremove(ids);
		if (r > 0) {
			return R.ok();
		}
		return R.error();
	}
}
