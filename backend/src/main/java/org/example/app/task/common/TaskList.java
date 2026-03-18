package org.example.app.task.common;

import org.example.app.general.common.ApplicationEntity;

public interface TaskList extends ApplicationEntity {

  String getTitle();

  void setTitle(String title);
}
