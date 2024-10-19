package todo.service;

import todo.entity.TodoEntity;
import todo.enums.TodoEntityPageResultType;
import todo.param.*;
import todo.repository.TodoRepository;
import todo.result.PageResult;
import todo.util.SortDirectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * 待办事项服务类。
 * 该类用于处理待办事项的业务逻辑，包括插入、更新、查询和删除等操作。
 */
@Service
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;

    @Autowired
    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    public PageResult<TodoEntity> complete(PageParam param) {
        return getTodoEntityPageResult(param, TodoEntityPageResultType.COMPLETE);
    }

    /**
     * 新增待办事项。
     *
     * @param param 待办事项的插入参数
     */
    @Transactional
    public void insert(TodoInsertParam param) {
        var todoEntity = new TodoEntity();
        todoEntity.setTitle(param.getTitle());
        todoEntity.setStatus(0); // 默认状态为未完成
        todoEntity.setCreateAt(LocalDateTime.now());
        todoEntity.setBody(param.getBody());
        todoRepository.save(todoEntity);
    }

    /**
     * 获取未完成的待办事项分页结果。
     *
     * @param param 分页查询参数
     * @return 未完成待办事项的分页结果
     */
    public PageResult<TodoEntity> incomplete(PageParam param) {
        return getTodoEntityPageResult(param, TodoEntityPageResultType.INCOMPLETE);
    }

    /**
     * 获取所有待办事项的分页结果。
     *
     * @param param 分页查询参数
     * @return 所有待办事项的分页结果
     */
    public PageResult<TodoEntity> all(PageParam param) {
        return getTodoEntityPageResult(param, TodoEntityPageResultType.ALL);
    }

    /**
     * 获取待办事项的分页结果。
     *
     * @param param 分页查询参数
     * @param type  待办事项类型
     * @return 待办事项的分页结果
     */
    private PageResult<TodoEntity> getTodoEntityPageResult(PageParam param, TodoEntityPageResultType type) {
        Sort.Direction direction = SortDirectionUtil.get(param.getSortDirection());
        int currentPage = param.getPage();
        var pageable = PageRequest.of(currentPage, param.getSize(), direction, param.getSortColumns());
        Page<TodoEntity> todoEntityPage = switch (type) {
            case INCOMPLETE -> todoRepository.findAllByStatusEquals(0, pageable);
            case COMPLETE -> todoRepository.findAllByStatusEquals(1, pageable);
            case ALL -> todoRepository.findAll(pageable);
            case SEARCH ->
                    todoRepository.findAllByTitleLikeOrBodyLike("%" + param.getSearch() + "%", "%" + param.getSearch() + "%", pageable);
        };

        List<TodoEntity> todoEntityList = todoEntityPage.getContent();
        var pages = new PageResult.Pages();
        pages.setSize(todoEntityPage.getSize());

        if (todoEntityPage.getTotalPages() == 0) {
            return new PageResult<>();
        }
        int totalPage = todoEntityPage.getTotalPages() - 1;
        int preNum = (totalPage - currentPage < 2) ? Math.min(4, currentPage) : Math.min(2, currentPage);
        int postNum = Math.min(4 - preNum, totalPage - currentPage);

        if (preNum > 0) pages.setFirst(0);
        if (postNum > 0) pages.setLast(totalPage);

        for (int i = 1; i <= preNum; i++) {
            pages.getNums().add(currentPage - i);
        }
        pages.getNums().add(currentPage);
        for (int i = 1; i <= postNum; i++) {
            pages.getNums().add(currentPage + i);
        }
        return new PageResult<>(pages, todoEntityList);
    }

    /**
     * 清空待办事项。
     *
     * @param param 清空参数，包括待办类型
     */
    @Transactional
    public void clear(TodoClearParam param) {
        var content = param.getContent().toLowerCase();
        switch (content) {
            case "incomplete" -> todoRepository.deleteByStatus(0);
            case "complete" -> todoRepository.deleteByStatus(1);
            case "all" -> todoRepository.deleteAll();
            default -> throw new RuntimeException("清空待办中 " + content + " 不正确, 只支持 incomplete/complete/all");
        }
    }

    /**
     * 搜索待办事项。
     *
     * @param param 分页查询参数
     * @return 符合搜索条件的待办事项分页结果
     */
    public PageResult<TodoEntity> search(PageParam param) {
        return getTodoEntityPageResult(param, TodoEntityPageResultType.SEARCH);
    }

    /**
     * 更新待办事项。
     *
     * @param param 更新参数，包括待办的ID和新的信息
     */
    @Transactional
    public void update(TodoUpdateParam param) {
        var todoEntity = todoRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException("待办 " + param.getId() + " 不存在"));
        todoEntity.setTitle(param.getTitle());
        todoEntity.setBody(param.getBody());

        if (!Objects.equals(todoEntity.getStatus(), param.getStatus())) {
            todoEntity.setCompleteAt(param.getStatus() == 1 ? LocalDateTime.now() : null);
            todoEntity.setStatus(param.getStatus());
        }
        todoRepository.save(todoEntity);
    }

    /**
     * 删除待办事项。
     *
     * @param param 删除参数，包括待办的ID
     */
    @Transactional
    public void delete(TodoDeleteParam param) {
        var todoEntity = todoRepository.findById(param.getId())
                .orElseThrow(() -> new RuntimeException("待办 " + param.getId() + " 不存在"));

        todoRepository.delete(todoEntity);
    }
}
