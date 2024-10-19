package todo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 新增待办参数类。
 * 该类用于封装新增待办事项时所需的参数，包括待办的标题和内容。
 */
@Getter
@Setter
@Schema(description = "新增待办参数")
public class TodoInsertParam implements Serializable {
    @Serial
    private static final long serialVersionUID = -2500628760069635249L;

    @Schema(description = "概要")
    private String title;
    @Schema(description = "内容")
    private String body;
}
