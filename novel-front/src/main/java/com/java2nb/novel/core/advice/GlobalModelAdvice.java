package com.java2nb.novel.core.advice;

import com.java2nb.novel.core.bean.UserDetails;
import com.java2nb.novel.core.utils.JwtTokenUtil;
import com.java2nb.novel.service.AuthorService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * 全ページ共通のモデル属性を補完する。
 * サイドバーの「作品管理」リンクを作家のみ表示するため isAuthor フラグを提供する。
 */
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalModelAdvice {

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthorService authorService;

    @ModelAttribute
    public void addCommonAttributes(HttpServletRequest request, org.springframework.ui.Model model) {
        boolean isAuthor = false;
        try {
            String token = resolveToken(request);
            if (token != null && !token.isEmpty()) {
                UserDetails ud = jwtTokenUtil.getUserDetailsFromToken(token);
                if (ud != null && ud.getId() != null) {
                    isAuthor = authorService.isAuthor(ud.getId());
                }
            }
        } catch (Exception ignored) {
            // 未ログイン・トークン不正時は false のまま
        }
        model.addAttribute("isAuthor", isAuthor);
    }

    private String resolveToken(HttpServletRequest request) {
        // Header
        String h = request.getHeader("Authorization");
        if (h != null && !h.isEmpty()) {
            return h;
        }
        // Cookie
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("Authorization".equals(c.getName())) {
                    return c.getValue();
                }
            }
        }
        // Query
        String q = request.getParameter("token");
        if (q != null && !q.isEmpty()) {
            return q;
        }
        return null;
    }
}
