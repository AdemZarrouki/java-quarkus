package org.example.app.task.logic;

import java.util.List;

import org.example.app.task.common.TaskItemEto;
import org.example.app.task.dataaccess.TaskItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface TaskItemMapper {


  default List<TaskItemEto> toEtos(List<TaskItemEntity> items) {
    return null;
  }

  TaskItemEto toEto(TaskItemEntity item);

  TaskItemEntity toEntity(TaskItemEto item);

}
