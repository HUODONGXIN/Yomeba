package com.java2nb.novel.core.enums;

import io.github.xxyopen.model.resp.IResultCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author 11797
 */

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseStatus implements IResultCode {


    /**
     * 用户相关错误
     * */
   NO_LOGIN(1001, "ログインしていません"),
    VEL_CODE_ERROR(1002, "認証コードが不正です"),
    USERNAME_EXIST(1003,"この携帯番号は既に登録されています"),
    USERNAME_PASS_ERROR(1004,"携帯番号またはパスワードが違います"),
    TWO_PASSWORD_DIFF(1005, "新しいパスワードが一致しません"),
    OLD_PASSWORD_ERROR(1006, "現在のパスワードが違います"),
    USER_NO_BALANCE(1007, "残高が不足しています"),

    /**
     * 评论相关错误
     * */
    HAS_COMMENTS(3001, "この本は既に評価済みです"),

    /**
     * 作者相关错误
     * */
    INVITE_CODE_INVALID(4001, "招待コードが無効です"),
    AUTHOR_STATUS_FORBIDDEN(4002, "作者アカウントの状態が正常でないため、作品を管理できません")
    , BOOKNAME_EXISTS(4003,"同名の作品は既に公開されています"),

    /**
     * 小说相关错误
     */
    BOOK_EXISTS(5001,"この作品は既に存在します")

            ,
    /**
     * 搜索引擎相关错误
     * */
    ES_SEARCH_FAIL(9001,"検索エンジンでエラーが発生しました"),

    /**
     * 文件相关错误
     * */
    FILE_DIR_MAKE_FAIL(10001,"ディレクトリの作成に失敗しました"),
    FILE_NOT_IMAGE(10002,"画像ファイルをアップロードしてください"),
    FILE_SIZE_LIMIT(10003,"ファイルサイズが制限を超えています"),

    /**
     * 其他通用错误
     * */
    PASSWORD_ERROR(88001,"パスワードが違います");

    private int code;
    private String msg;


}
