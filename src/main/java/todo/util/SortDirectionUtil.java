package todo.util;

import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * 排序方向工具类。
 * 该类提供方法用于根据输入字符串获取对应的排序方向（升序或降序）。
 */
public class SortDirectionUtil {

    /**
     * 根据输入的排序方向字符串返回对应的Sort.Direction枚举。
     * 如果输入的方向不支持，抛出异常。
     *
     * @param direction 输入的排序方向（"ASC" 或 "DESC"）
     * @return 对应的Sort.Direction枚举
     * @throws RuntimeException 如果输入的方向不支持
     */
    public static Sort.Direction get(String direction) {
        Map<String, Sort.Direction> directionMap = Map.of(
                "ASC", Sort.Direction.ASC,
                "DESC", Sort.Direction.DESC
        );

        var result = directionMap.get(direction.toUpperCase());
        if (result == null) {
            throw new RuntimeException("排序方向 " + direction + " 不支持, 仅支持 DESC/ASC(忽略大小写)");
        }
        return result;
    }
}
