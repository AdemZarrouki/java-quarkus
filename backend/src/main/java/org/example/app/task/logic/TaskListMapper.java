package org.example.app.task.logic;

import java.util.List;

import org.example.app.task.common.TaskListEto;
import org.example.app.task.dataaccess.TaskListEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TaskListMapper {

  default List<TaskListEto> toEtos(List<TaskListEntity> items) {
    if (items == null) {
      return List.of();
    }

    return items.stream()
        .map(this::toEto)
        .toList();
  }

  TaskListEto toEto(TaskListEntity item);

  TaskListEntity toEntity(TaskListEto item);

}
