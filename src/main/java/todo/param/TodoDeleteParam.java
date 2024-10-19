package todo.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 删除待办参数类。
 * 该类用于封装删除待办时所需的参数，包括待办事项的ID。
 */
@Getter
@Setter
public class TodoDeleteParam implements Serializable {
    @Serial
    private static final long serialVersionUID = 406148956853654632L;
    @Schema(description = "ID")
    private String id;
}
