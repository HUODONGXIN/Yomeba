package com.java2nb.common.annotation;

import java.lang.annotation.*;

/**
 * あるメソッドの引数に対して、Map フィールドのサニタイズと正規化処理を行うことを示すマーカー。
 *
 * <p>通常は DAO インターフェースの list メソッドの Map 引数に使用し、不正なソートフィールドやソート順を防ぐために用いる。</p>
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SanitizeMap {
}
