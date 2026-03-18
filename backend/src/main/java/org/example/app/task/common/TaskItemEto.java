package org.example.app.task.common;

import java.time.LocalDateTime;

import org.example.app.general.common.AbstractEto;

public class TaskItemEto extends AbstractEto implements TaskItem {

  private String title;
  private boolean completed;
  private boolean starred;
  LocalDateTime deadline;

  @Override
  public String getTitle() {
    return this.title;
  }

  @Override
  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean isCompleted() {
    return this.completed;
  }

  @Override
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  @Override
  public boolean isStarred() {
    return this.starred;
  }

  @Override
  public void setStarred(boolean starred) {
    this.starred = starred;
  }

  @Override
  public LocalDateTime getDeadline() {
    return this.deadline;
  }

  @Override
  public void setDeadline(LocalDateTime deadline) {
    this.deadline = deadline;
  }
}
