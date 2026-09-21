package com.java2nb.common.exception;

import lombok.Data;

/**
 * カスタム業務例外
 */
@Data
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
    private String msg;
    private int code;
    
    public BusinessException(int code,String msg) {
		//親クラスThrowableのfillInStackTrace()メソッドを呼び出さずスタックトレース情報を生成しないことで、アプリのパフォーマンスを向上させます
		//コンストラクタ間の呼び出しは1行目に記述する必要があります
		super(msg, null, false, false);
		this.code = code;
		this.msg = msg;
	}
	

	
}
