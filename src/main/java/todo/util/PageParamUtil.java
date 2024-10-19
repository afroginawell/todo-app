package todo.util;

import lombok.extern.slf4j.Slf4j;

import java.util.Set;

/**
 * 页面参数工具类。
 * 该类提供用于处理分页查询参数的工具方法，尤其是排序方向的验证与默认值设置。
 */
@Slf4j
public class PageParamUtil {
    public static final String DEFAULT_SORT_DIRECTION = "ASC";

    /**
     * 获取排序方向。
     * 如果提供的方向有效（"ASC" 或 "DESC"），则返回该方向；否则，返回默认的排序方向（"ASC"）并记录警告。
     *
     * @param direction 传入的排序方向
     * @return 有效的排序方向
     */
    public static String sortDirection(String direction) {
        Set<String> validDirections = Set.of("ASC", "DESC");

        if (validDirections.contains(direction.toUpperCase())) {
            return direction.toUpperCase();
        }

        log.warn("排序方向 " + direction + " 不支持, 仅支持 DESC/ASC(忽略大小写), 默认使用 ASC。");
        return DEFAULT_SORT_DIRECTION;
    }
}
