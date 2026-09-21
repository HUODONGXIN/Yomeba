package com.java2nb.novel.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.java2nb.common.jsonserializer.LongToStringSerializer;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;


/**
 * 小説テーブル
 *
 * @author xiongxy
 * @email 1179705413@qq.com
 * @date 2023-04-14 17:42:55
 */
public class BookDO implements Serializable {

    private static final long serialVersionUID = 1L;


    //主キー
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long id;
    //作品の方向性。0：男性向け、1：女性向け
    private Integer workDirection;
    //ジャンルID
    private Integer catId;
    //ジャンル名
    private String catName;
    //小説の表紙
    private String picUrl;
    //小説名
    private String bookName;
    //作家ID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long authorId;
    //作家名
    private String authorName;
    //作品の説明
    private String bookDesc;
    //評価点。予約フィールド
    private Float score;
    //作品の状態。0：連載中、1：完結
    private Integer bookStatus;
    //アクセス数
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long visitCount;
    //総文字数
    private Integer wordCount;
    //コメント数
    private Integer commentCount;
    //前日の購読数
    private Integer yesterdayBuy;
    //最新の目次ID
    //Java の long は JS の number より表現範囲が広く、一部の数値は JS で正確に保持できない（不正確な値になる）
    //そのため文字列にシリアライズして解決する
    @JsonSerialize(using = LongToStringSerializer.class)
    private Long lastIndexId;
    //最新の目次名
    private String lastIndexName;
    //最新の目次の更新日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastIndexUpdateTime;
    //有料かどうか。1：有料、0：無料
    private Integer isVip;
    //状態。0：未公開、1：公開
    private Integer status;
    //更新日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    //作成日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    //クロール元サイトID
    private Integer crawlSourceId;
    //クロールした元サイトの小説ID
    private String crawlBookId;
    //最終のクロール日時
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date crawlLastTime;
    //更新を停止したかどうか。0：未停止、1：停止済み
    private Integer crawlIsStop;

    /**
     * 設定：主キー
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 取得：主キー
     */
    public Long getId() {
        return id;
    }

    /**
     * 設定：作品の方向性。0：男性向け、1：女性向け
     */
    public void setWorkDirection(Integer workDirection) {
        this.workDirection = workDirection;
    }

    /**
     * 取得：作品の方向性。0：男性向け、1：女性向け
     */
    public Integer getWorkDirection() {
        return workDirection;
    }

    /**
     * 設定：ジャンルID
     */
    public void setCatId(Integer catId) {
        this.catId = catId;
    }

    /**
     * 取得：ジャンルID
     */
    public Integer getCatId() {
        return catId;
    }

    /**
     * 設定：ジャンル名
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }

    /**
     * 取得：ジャンル名
     */
    public String getCatName() {
        return catName;
    }

    /**
     * 設定：小説の表紙
     */
    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    /**
     * 取得：小説の表紙
     */
    public String getPicUrl() {
        return picUrl;
    }

    /**
     * 設定：小説名
     */
    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    /**
     * 取得：小説名
     */
    public String getBookName() {
        return bookName;
    }

    /**
     * 設定：作家ID
     */
    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    /**
     * 取得：作家ID
     */
    public Long getAuthorId() {
        return authorId;
    }

    /**
     * 設定：作家名
     */
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    /**
     * 取得：作家名
     */
    public String getAuthorName() {
        return authorName;
    }

    /**
     * 設定：作品の説明
     */
    public void setBookDesc(String bookDesc) {
        this.bookDesc = bookDesc;
    }

    /**
     * 取得：作品の説明
     */
    public String getBookDesc() {
        return bookDesc;
    }

    /**
     * 設定：評価点。予約フィールド
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * 取得：評価点。予約フィールド
     */
    public Float getScore() {
        return score;
    }

    /**
     * 設定：作品の状態。0：連載中、1：完結
     */
    public void setBookStatus(Integer bookStatus) {
        this.bookStatus = bookStatus;
    }

    /**
     * 取得：作品の状態。0：連載中、1：完結
     */
    public Integer getBookStatus() {
        return bookStatus;
    }

    /**
     * 設定：アクセス数
     */
    public void setVisitCount(Long visitCount) {
        this.visitCount = visitCount;
    }

    /**
     * 取得：アクセス数
     */
    public Long getVisitCount() {
        return visitCount;
    }

    /**
     * 設定：総文字数
     */
    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }

    /**
     * 取得：総文字数
     */
    public Integer getWordCount() {
        return wordCount;
    }

    /**
     * 設定：コメント数
     */
    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    /**
     * 取得：コメント数
     */
    public Integer getCommentCount() {
        return commentCount;
    }

    /**
     * 設定：前日の購読数
     */
    public void setYesterdayBuy(Integer yesterdayBuy) {
        this.yesterdayBuy = yesterdayBuy;
    }

    /**
     * 取得：前日の購読数
     */
    public Integer getYesterdayBuy() {
        return yesterdayBuy;
    }

    /**
     * 設定：最新の目次ID
     */
    public void setLastIndexId(Long lastIndexId) {
        this.lastIndexId = lastIndexId;
    }

    /**
     * 取得：最新の目次ID
     */
    public Long getLastIndexId() {
        return lastIndexId;
    }

    /**
     * 設定：最新の目次名
     */
    public void setLastIndexName(String lastIndexName) {
        this.lastIndexName = lastIndexName;
    }

    /**
     * 取得：最新の目次名
     */
    public String getLastIndexName() {
        return lastIndexName;
    }

    /**
     * 設定：最新の目次の更新日時
     */
    public void setLastIndexUpdateTime(Date lastIndexUpdateTime) {
        this.lastIndexUpdateTime = lastIndexUpdateTime;
    }

    /**
     * 取得：最新の目次の更新日時
     */
    public Date getLastIndexUpdateTime() {
        return lastIndexUpdateTime;
    }

    /**
     * 設定：有料かどうか。1：有料、0：無料
     */
    public void setIsVip(Integer isVip) {
        this.isVip = isVip;
    }

    /**
     * 取得：有料かどうか。1：有料、0：無料
     */
    public Integer getIsVip() {
        return isVip;
    }

    /**
     * 設定：状態。0：未公開、1：公開
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 取得：状態。0：未公開、1：公開
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 設定：更新日時
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 取得：更新日時
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 設定：作成日時
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 取得：作成日時
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 設定：クロール元サイトID
     */
    public void setCrawlSourceId(Integer crawlSourceId) {
        this.crawlSourceId = crawlSourceId;
    }

    /**
     * 取得：クロール元サイトID
     */
    public Integer getCrawlSourceId() {
        return crawlSourceId;
    }

    /**
     * 設定：クロールした元サイトの小説ID
     */
    public void setCrawlBookId(String crawlBookId) {
        this.crawlBookId = crawlBookId;
    }

    /**
     * 取得：クロールした元サイトの小説ID
     */
    public String getCrawlBookId() {
        return crawlBookId;
    }

    /**
     * 設定：最終のクロール日時
     */
    public void setCrawlLastTime(Date crawlLastTime) {
        this.crawlLastTime = crawlLastTime;
    }

    /**
     * 取得：最終のクロール日時
     */
    public Date getCrawlLastTime() {
        return crawlLastTime;
    }

    /**
     * 設定：更新を停止したかどうか。0：未停止、1：停止済み
     */
    public void setCrawlIsStop(Integer crawlIsStop) {
        this.crawlIsStop = crawlIsStop;
    }

    /**
     * 取得：更新を停止したかどうか。0：未停止、1：停止済み
     */
    public Integer getCrawlIsStop() {
        return crawlIsStop;
    }
}
