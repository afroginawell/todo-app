package todo.controller;

import todo.entity.TodoEntity;
import todo.param.*;
import todo.result.PageResult;
import todo.service.TodoService;
import todo.util.PageParamUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Todo控制器类，用于处理与待办事项相关的API请求。
 * 提供创建、获取和管理待办事项的接口。
 */
@Slf4j
@RestController
@RequestMapping("/api/todo")
@Tag(name = "todo", description = "待办Api")
public class TodoController {

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * 获取未完成待办事项的列表。
     * 接收分页参数并返回未完成的待办事项内容。
     *
     * @param param 分页参数，包含排序方向和其他信息
     * @return 包含未完成待办事项的分页结果
     */
    @PostMapping("/incomplete")
    @Operation(method = "POST", summary = "获取未完成待办", description = "获取未完成待办内容, 返回一个List")
    public ResponseEntity<PageResult<TodoEntity>> incomplete(@RequestBody PageParam param) {
        param.setSortDirection(PageParamUtil.sortDirection(param.getSortDirection()));
        var response = todoService.incomplete(param);
        return ResponseEntity.ok(response);
    }

    /**
     * 获取已完成待办事项的列表。
     * 接收分页参数并返回已完成的待办事项内容。
     *
     * @param param 分页参数，包含排序方向和其他信息
     * @return 包含已完成待办事项的分页结果
     */
    @PostMapping("/complete")
    @Operation(method = "POST", summary = "获取已完成待办", description = "获取已完成待办内容, 返回一个List")
    public ResponseEntity<PageResult<TodoEntity>> complete(@RequestBody PageParam param) {
        param.setSortDirection(PageParamUtil.sortDirection(param.getSortDirection()));
        var response = todoService.complete(param);
        return ResponseEntity.ok(response);
    }

    /**
     * 获取所有待办事项的列表。
     * 接收分页参数并返回所有待办事项内容。
     *
     * @param param 分页参数，包含排序方向和其他信息
     * @return 包含所有待办事项的分页结果
     */
    @PostMapping("/all")
    @Operation(method = "POST", summary = "获取所有待办", description = "获取所有待办内容, 返回一个List")
    public ResponseEntity<PageResult<TodoEntity>> all(@RequestBody PageParam param) {
        param.setSortDirection(PageParamUtil.sortDirection(param.getSortDirection()));
        var response = todoService.all(param);
        return ResponseEntity.ok(response);
    }


    /**
     * 新增待办事项。
     * 接收待办事项的概要和内容，并将其插入数据库。
     *
     * @param param 待办事项插入参数，包括标题和内容
     * @return 返回成功插入的消息
     */
    @PostMapping("/insert")
    @Operation(method = "POST", summary = "新增待办", description = "新增待办, 输入待办的概要和内容")
    public ResponseEntity<String> insert(@RequestBody TodoInsertParam param) {
        log.debug("新增待办: \n" + param.getTitle() + "\n" + param.getBody());
        todoService.insert(param);
        return ResponseEntity.ok("");
    }

    /**
     * 清空待办事项。
     * 根据输入参数删除指定类型的待办信息。
     *
     * @param param 包含待办清空参数，指明要清空的待办类型
     * @return 返回清空操作的结果消息
     */
    @PostMapping("/clear")
    @Operation(method = "POST", summary = "清空待办", description = "清空待办, 删除所有待办信息")
    public ResponseEntity<String> clear(@RequestBody TodoClearParam param) {
        var content = param.getContent();
        log.debug("清空 " + content + " 待办信息");
        if (content.equalsIgnoreCase("incomplete") || content.equalsIgnoreCase("complete") || content.equalsIgnoreCase("all")) {
            todoService.clear(param);
            return ResponseEntity.ok("");
        }
        throw new RuntimeException("清空待办中 " + content + " 不正确, 只支持 incomplete/complete/all");
    }

    /**
     * 进行待办事项的全文搜索。
     * 该方法会首先根据标题搜索待办事项，然后再根据内容进行搜索。
     *
     * @param param 分页参数，包含搜索关键词和排序方向
     * @return 包含匹配待办事项的分页结果
     */
    @PostMapping("/search")
    @Operation(method = "POST", summary = "全文搜索待办", description = "全文搜索待办, 先搜索标题再搜索内容")
    public ResponseEntity<PageResult<TodoEntity>> search(@RequestBody PageParam param) {
        log.debug("查询待办: " + param.getSearch());
        param.setSortDirection(PageParamUtil.sortDirection(param.getSortDirection()));
        var response = todoService.search(param);
        return ResponseEntity.ok(response);
    }

    /**
     * 更新待办事项的信息。
     * 可以更新待办的标题和内容，使用待办的ID进行识别。
     *
     * @param param 待办事项更新参数，包括ID、标题和内容
     * @return 返回更新操作的结果消息
     */
    @PostMapping("/update")
    @Operation(method = "POST", summary = "更新待办", description = "更新待办, 可以更新待办的概要和内容")
    public ResponseEntity<String> update(@RequestBody TodoUpdateParam param) {
        log.debug("更新待办: \n" + param.getId() + "\n" + param.getTitle() + "\n" + param.getBody());
        todoService.update(param);
        return ResponseEntity.ok("");
    }

    /**
     * 根据待办的ID删除指定的待办事项。
     * 该方法会记录删除操作的待办ID。
     *
     * @param param 包含待办ID的删除参数
     * @return 返回删除操作的结果消息
     */
    @PostMapping("/delete")
    @Operation(method = "POST", summary = "删除待办", description = "删除待办,根据待办的id值进行删除")
    public ResponseEntity<String> delete(@RequestBody TodoDeleteParam param) {
        log.debug("删除待办: \n" + param.getId());
        todoService.delete(param);
        return ResponseEntity.ok("");
    }

}
