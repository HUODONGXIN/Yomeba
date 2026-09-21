package com.java2nb.common.config;

/**
 * @author 11797
 */
public interface CacheKey {

    /**
     * ホーム画面の小説設定
     */
    String INDEX_BOOK_SETTINGS_KEY = "indexBookSettingsKey:v2";

    /**
     * ホーム画面のニュース
     */
    String INDEX_NEWS_KEY = "indexNewsKey";

    /**
     * ホーム画面のアクセス数ランキング
     */
    String INDEX_CLICK_BANK_BOOK_KEY = "indexClickBankBookKey";

    /**
     * ホーム画面のリンク集
     */
    String INDEX_LINK_KEY = "indexLinkKey";

    /**
     * ホーム画面の新着小説ランキング
     */
    String INDEX_NEW_BOOK_KEY = "indexNewBookKey";


    /**
     * ホーム画面の更新ランキング
     */
    String INDEX_UPDATE_BOOK_KEY = "indexUpdateBookKey";

    /**
     * テンプレートディレクトリ保存用キー
     */
    String TEMPLATE_DIR_KEY = "templateDirKey";
    ;

    /**
     * 実行中のクローラースレッドを格納するキーのプレフィックス
     */
    String RUNNING_CRAWL_THREAD_KEY_PREFIX = "runningCrawlTreadDataKeyPrefix";

    /**
     * 前回の検索エンジン更新日時
     */
    String ES_LAST_UPDATE_TIME = "esLastUpdateTime";

    /**
     * 検索エンジン変換ロック
     */
    String ES_TRANS_LOCK = "esTransLock";

    /**
     * 前回の検索エンジンで小説のアクセス数を更新したかどうか
     */
    String ES_IS_UPDATE_VISIT = "esIsUpdateVisit";

    /**
     * 累積された小説のアクセス数
     */
    String BOOK_ADD_VISIT_COUNT = "bookAddVisitCount";
    /**
     * クロールルールテスト用キャッシュ
     */
    String BOOK_TEST_PARSE = "testParse";
}
