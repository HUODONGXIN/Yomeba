package com.java2nb.system.controller;

import com.java2nb.common.annotation.Log;
import com.java2nb.common.config.JnConfig;
import com.java2nb.common.controller.BaseController;
import com.java2nb.common.domain.FileDO;
import com.java2nb.common.domain.Tree;
import com.java2nb.common.service.FileService;
import com.java2nb.common.utils.*;
import com.java2nb.system.domain.MenuDO;
import com.java2nb.system.service.MenuService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class LoginController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MenuService menuService;
    @Autowired
    FileService fileService;
    @Autowired
    JnConfig jnConfig;


    @Log("ホームアクセスをリクエスト")
    @GetMapping({"","/","/index"})
    String index(Model model) {
        List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());
        model.addAttribute("menus", menus);
        model.addAttribute("name", getUser().getName());
        FileDO fileDO = fileService.get(getUser().getPicId());
        if (fileDO != null && fileDO.getUrl() != null) {
            if (fileService.isExist(fileDO.getUrl())) {
                model.addAttribute("picUrl", fileDO.getUrl());
            } else {
                model.addAttribute("picUrl", "/img/photo_s.jpg");
            }
        } else {
            model.addAttribute("picUrl", "/img/photo_s.jpg");
        }
        model.addAttribute("username", getUser().getUsername());
        return "index";
    }

    @GetMapping("/login")
    String login(Model model) {
        model.addAttribute("username", jnConfig.getUsername());
        model.addAttribute("password", jnConfig.getPassword());
        return "login";
    }

    @Log("ログイン")
    @PostMapping("/login")
    @ResponseBody
    R ajaxLogin(String username, String password,String verify,HttpServletRequest request) {

        try {
            //セッションから乱数を取得
            String random = (String) request.getSession().getAttribute(RandomValidateCodeUtil.RANDOMCODEKEY);
            if (StringUtils.isBlank(verify)) {
                return R.error("認証コードを入力してください");
            }
            if (random.equals(verify)) {
            } else {
                return R.error("正しい認証コードを入力してください");
            }
        } catch (Exception e) {
            logger.error("認証コードの検証に失敗しました", e);
            return R.error("認証コードの検証に失敗しました");
        }
        password = MD5Utils.encrypt(username, password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return R.ok();
        } catch (AuthenticationException e) {
            return R.error("アカウントまたはパスワードが正しくありません");
        }
    }

    @GetMapping("/logout")
    String logout() {
        ShiroUtils.logout();
        return "redirect:/login";
    }

    @GetMapping("/main")
    String main() {
        return "main";
    }

    /**
     * 認証コードを生成
     */
    @GetMapping(value = "/getVerify")
    public void getVerify(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.setContentType("image/jpeg");//レスポンスタイプを設定し、ブラウザに出力内容が画像であることを通知
            response.setHeader("Pragma", "No-cache");//レスポンスヘッダーを設定し、ブラウザにこの内容をキャッシュしないよう通知
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expire", 0);
            RandomValidateCodeUtil randomValidateCode = new RandomValidateCodeUtil();
            randomValidateCode.getRandcode(request, response);//認証コード画像を出力するメソッド
        } catch (Exception e) {
            logger.error("認証コードの取得に失敗しました>>>> ", e);
        }
    }

}
