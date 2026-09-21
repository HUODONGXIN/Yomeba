package com.java2nb.common.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.java2nb.common.domain.BugLogDO;
import com.java2nb.common.domain.PageDO;
import com.java2nb.common.service.BugLogService;
import com.java2nb.common.utils.Query;
import com.java2nb.common.utils.R;

@RequestMapping("/monitor/bugLog")
@Controller
public class BugLogController {
	@Autowired
	BugLogService bugLogService;
	String prefix = "monitor/bugLog";

	@GetMapping()
	String bugLog() { return prefix + "/bugLog"; }

	@ResponseBody
	@GetMapping("/list")
	PageDO<BugLogDO> list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		return bugLogService.queryList(query);
	}

	@ResponseBody
	@PostMapping("/remove")
	R remove(Long id) {
		return bugLogService.remove(id) > 0 ? R.ok() : R.error();
	}

	@ResponseBody
	@PostMapping("/batchRemove")
	R batchRemove(@RequestParam("ids[]") Long[] ids) {
		return bugLogService.batchRemove(ids) > 0 ? R.ok() : R.error();
	}
}
