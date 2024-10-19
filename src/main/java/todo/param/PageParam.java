package todo.param;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 分页查询参数类。
 * 该类用于封装分页查询时所需的参数，包括页数、页大小、排序信息和搜索内容。
 */
@Getter
@Setter
@Schema(description = "分页查询参数")
public class PageParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -8453550038848069329L;
    @Schema(description = "页数")
    private Integer page;
    @Schema(description = "页大小", defaultValue = "20")
    private Integer size;
    @Schema(description = "排序列")
    private String[] sortColumns;
    @Schema(description = "排序方向, desc/asc")
    private String sortDirection;
    @Schema(description = "搜索内容")
    private String search;
}
