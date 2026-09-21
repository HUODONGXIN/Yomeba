package com.java2nb.common.aspect;

import com.java2nb.common.annotation.SanitizeMap;
import com.java2nb.common.utils.SortWhitelistUtil;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

/**
 * すべての Mapper インターフェースの list* メソッドをインターセプトし、@SanitizeMap アノテーションが付与された Map 引数のソート項目と順序を正規化します。
 *
 * <p>主に SQL インジェクションや不正なソート項目・不正なソート順序を防ぎます。
 * 例えば sort と order フィールドに対してホワイトリストフィルタリングと標準化を行います。</p>
 */
@Aspect
@Component
@RequiredArgsConstructor
public class MapSortValidationAspect {

    /**
     * すべての Mapper インターフェースの list* メソッド（list(), listByPage など）をインターセプトします。
     * @SanitizeMap アノテーションが付与された Map 引数を処理します。
     *
     * <p>実行ロジック：</p>
     * <ol>
     *   <li>メソッドの引数とアノテーション情報を取得</li>
     *   <li>すべての引数を走査し、@SanitizeMap アノテーションが付与されているか確認</li>
     *   <li>引数が Map 型でアノテーションがある場合、項目のクリーニングを実行</li>
     * </ol>
     *
     * @param joinPoint ポイントカット情報
     * @return メソッド実行結果
     */
    @SneakyThrows
    @Around("execution(* com.java2nb.*.dao.*Dao.list*(..))")
    public Object sanitizeMapParameters(ProceedingJoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Annotation[][] parameterAnnotations = method.getParameterAnnotations();

        for (int i = 0; i < parameterAnnotations.length; i++) {
            boolean hasAnnotation = Arrays.stream(parameterAnnotations[i])
                .anyMatch(a -> a.annotationType().equals(SanitizeMap.class));

            if (hasAnnotation && args[i] instanceof Map map) {
                if (map.get("sort") instanceof String sortStr) {
                    map.put("sort", SortWhitelistUtil.sanitizeColumn(sortStr));
                }
                if (map.get("order") instanceof String orderStr) {
                    map.put("order", SortWhitelistUtil.sanitizeOrder(orderStr));
                }
            }
        }

        return joinPoint.proceed(args);
    }

}
