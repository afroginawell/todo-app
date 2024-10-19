package todo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 待办事项实体类，表示一个待办事项的基本信息。
 * 包括待办的ID、标题、内容、状态、创建时间和完成时间。
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Schema(description = "待办实体")
public class TodoEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -6902417867422354631L;
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Schema(description = "ID")
    private String id;
    @Schema(description = "概要")
    @Column
    private String title;
    @Schema(description = "内容")
    @Column
    private String body;
    @Schema(description = "状态(0=未完成,1=完成)")
    @Column
    private Integer status = 0;
    @Schema(description = "创建时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime createAt;
    @Schema(description = "完成时间")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column
    private LocalDateTime completeAt;
}
