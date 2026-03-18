package org.example.app.task.logic;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.transaction.Transactional;

import org.example.app.task.dataaccess.TaskItemRepository;

@ApplicationScoped
@Named
@Transactional
public class UcFindTaskItem {

  @Inject
  TaskItemRepository taskItemRepository;


}
