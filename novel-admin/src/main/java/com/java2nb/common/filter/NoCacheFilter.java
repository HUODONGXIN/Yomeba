package com.java2nb.common.filter;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * ページと静的リソースのキャッシュをグローバルに無効化する（no-cache）。
 * ブラウザが古い HTML/JS/CSS をキャッシュし、修正後に最新の内容が表示されない事象を防ぐ。
 */
@Component
@Order(1)
public class NoCacheFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();

        // アップロード画像などのファイルはキャッシュを残し、それ以外のページ・スクリプト・スタイルはキャッシュしない
        if (!uri.startsWith("/files/")) {
            resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            resp.setHeader("Pragma", "no-cache");
            resp.setHeader("Expires", "0");
        }
        chain.doFilter(request, response);
    }
}
