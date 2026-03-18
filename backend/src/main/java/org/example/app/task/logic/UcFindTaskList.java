package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import org.example.app.task.common.TaskListEto;
import org.example.app.task.dataaccess.TaskListRepository;

@ApplicationScoped
@Named
@Transactional
public class UcFindTaskList {

  @Inject
  TaskListRepository taskListRepository;

  @Inject
  TaskListMapper taskListMapper;

  public TaskListEto findById(Long id) {
    return this.taskListRepository.findById(id).map(entity -> this.taskListMapper.toEto(entity)).orElse(null);
  }

}
