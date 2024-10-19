package todo.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页查询结果类。
 * 该类用于封装分页查询的结果，包括页数信息和查询的数据列表。
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "分页查询结果")
public class PageResult<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 3611338075168193420L;

    @Schema(description = "页数信息")
    private Pages pages;

    @Schema(description = "查询数据")
    private List<T> body;

    /**
     * 页数信息类。
     * 该类用于封装分页信息，包括第一页、页大小、最后一页和页码列表。
     */
    @Setter
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Schema(description = "页数信息")
    public static class Pages implements Serializable {

        @Serial
        private static final long serialVersionUID = -7026954721092843845L;

        @Schema(description = "第一页")
        private Integer first = -1;

        @Schema(description = "页大小")
        private Integer size;

        @Schema(description = "最后一页")
        private Integer last = -1;

        @Schema(description = "前几页和后几页的序号")
        private List<Integer> nums = new ArrayList<>();
    }
}
