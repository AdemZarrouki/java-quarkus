package org.example.app.task.common;

import java.time.LocalDateTime;

import org.example.app.general.common.ApplicationEntity;

public interface TaskItem extends ApplicationEntity {

  String getTitle();

  void setTitle(String title);

  boolean isCompleted();

  void setCompleted(boolean completed);

  boolean isStarred();

  void setStarred(boolean starred);

  LocalDateTime getDeadline();

  void setDeadline(LocalDateTime deadline);
}
