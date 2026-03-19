package org.example.app.task.logic;

import java.util.Optional;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import org.example.app.task.dataaccess.TaskItemEntity;
import org.example.app.task.dataaccess.TaskItemRepository;
import org.example.app.task.dataaccess.TaskListEntity;
import org.example.app.task.dataaccess.TaskListRepository;


@ApplicationScoped
@Named
@Transactional
public class UcAddRandomActivityTaskItem {

  @Inject
  TaskListRepository taskListRepository;

  @Inject
  TaskItemRepository taskItemRepository;

  @Inject
  ActivityService activityService;


  public Long addRandom(Long taskListId) {
    Optional<TaskListEntity> list = taskListRepository.findById(taskListId);

    TaskItemEntity item = new TaskItemEntity();
    item.setTaskListEntity(list.get());
    item.setTitle(activityService.getRandomActivity());
    item.setCompleted(false);

    taskItemRepository.save(item);

    return item.getId();
  }

}
