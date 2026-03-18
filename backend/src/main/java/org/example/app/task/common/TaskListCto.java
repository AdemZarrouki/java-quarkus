package org.example.app.task.common;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Composite transfer object for a {@link TaskListEto} with its containing {@link TaskItemEto}s.
 */
@Schema(name = "TaskListWithItems", description = "Object that represents a task list and its task items")
public class TaskListCto {

  @Schema(required = true)
  private TaskListEto list;

  @Schema(required = true)
  private List<TaskItemEto> items;

  public TaskListCto() {
    super();
  }

  public TaskListEto getList() {
    return this.list;
  }

  public void setList(TaskListEto taskListEto) {
    this.list = taskListEto;
  }

  public List<TaskItemEto> getItems() {
    return this.items;
  }

  public void setItems(List<TaskItemEto> items) {
    this.items = items;
  }

}
