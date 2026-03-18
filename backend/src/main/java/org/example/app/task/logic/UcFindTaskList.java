package org.example.app.task.logic;

import java.util.List;
import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import org.example.app.task.common.TaskListCto;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.dataaccess.TaskListEntity;
import org.example.app.task.dataaccess.TaskListRepository;

@ApplicationScoped
@Named
@Transactional
public class UcFindTaskList {

  @Inject
  TaskListRepository taskListRepository;

  @Inject
  TaskListMapper taskListMapper;

  @Inject
  TaskItemMapper taskItemMapper;

  public List<TaskListEto> findAll() {
    return this.taskListMapper.toEtos(this.taskListRepository.findAll());
  }

  public TaskListEto findById(Long id) {
    return this.taskListRepository.findById(id).map(entity -> this.taskListMapper.toEto(entity)).orElse(null);
  }

  public TaskListCto findTaskListWithItems(long id) {
    Optional<TaskListEntity> taskList = taskListRepository.findById(id);
    if (taskList.isEmpty()) {
      return null;
    }
    TaskListCto cto = new TaskListCto();
    TaskListEntity taskListEntity = taskList.get();
    cto.setList(this.taskListMapper.toEto(taskListEntity));
    cto.setItems(this.taskItemMapper.toEtos(taskListEntity.getTaskItemEntities()));
    return cto;
  }

}
