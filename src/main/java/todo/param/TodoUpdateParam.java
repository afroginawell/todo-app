package todo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 更新待办参数类。
 * 该类用于封装更新待办事项时所需的参数，包括待办的ID、标题、内容和状态。
 */
@Getter
@Setter
@Schema(description = "更新待办参数")
public class TodoUpdateParam implements Serializable {

    @Serial
    private static final long serialVersionUID = 4813040271568712627L;

    @Schema(description = "ID")
    private String id;
    @Schema(description = "概要")
    private String title;
    @Schema(description = "内容")
    private String body;
    @Schema(description = "状态(0=未完成,1=完成)")
    private Integer status;

}
