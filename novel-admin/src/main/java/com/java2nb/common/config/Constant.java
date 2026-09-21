package com.java2nb.common.config;

public class Constant {

    //デモシステム用アカウント
    public static String DEMO_ACCOUNT = "test";
    //テーブルプレフィックスを自動除去
    public static String AUTO_REOMVE_PRE = "true";
    //定期タスクを停止
    public static String STATUS_RUNNING_STOP = "stop";
    //定期タスクを開始
    public static String STATUS_RUNNING_START = "start";
    //通知お知らせの既読ステータス - 未読
    public static String OA_NOTIFY_READ_NO = "0";
    //通知お知らせの既読ステータス - 既読
    public static int OA_NOTIFY_READ_YES = 1;
    //部門ルートノードのID
    public static Long DEPT_ROOT_ID = 0L;
    //キャッシュ方式
    public static String CACHE_TYPE_REDIS = "redis";

    public static String LOG_ERROR = "error";

    public static final String UPLOAD_FILES_PREFIX = "/files/";

    public static final String BOOK_IS_DOWNLOADING_KEY = "bookIsDownloading:";

}
