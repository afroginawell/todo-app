package todo.param;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * 清空待办参数类。
 * 该类用于封装清空待办时所需的参数，包括待办类型的内容。
 */
@Getter
@Setter
public class TodoClearParam implements Serializable {

    @Serial
    private static final long serialVersionUID = -5566060395344050267L;

    private String content;
}
