package todo.repository;

import todo.entity.TodoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 待办事项仓库接口。
 * 该接口用于定义对待办事项实体的数据库操作，包括查询和删除功能。
 */
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, String> {
    /**
     * 根据状态获取待办事项的分页结果。
     *
     * @param status   待办事项的状态
     * @param pageable 分页信息
     * @return 符合条件的待办事项分页结果
     */
    Page<TodoEntity> findAllByStatusEquals(int status, Pageable pageable);

    /**
     * 根据状态删除待办事项。
     *
     * @param status 待办事项的状态
     * @return 删除的待办事项数量
     */
    int deleteByStatus(int status);

    /**
     * 根据标题或内容模糊查询待办事项的分页结果。
     *
     * @param title    待办事项的标题
     * @param body     待办事项的内容
     * @param pageable 分页信息
     * @return 符合条件的待办事项分页结果
     */
    Page<TodoEntity> findAllByTitleLikeOrBodyLike(String title, String body, Pageable pageable);
}
