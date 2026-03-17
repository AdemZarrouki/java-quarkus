package org.example.app.task.dataaccess;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class TaskItemRepositoryTest extends Assertions {

  @Inject
  private TaskItemRepository taskItemRepository;

  @Test
  public void testFindById() {
    Long itemId = 11L;
    TaskItemEntity item = this.taskItemRepository.findById(itemId).get();
    assertThat(item.getTitle()).isEqualTo("Milk");
  }

  @Test
  public void testFindByIdNotFound() {
    Long itemId = 10L;
    TaskItemEntity item = this.taskItemRepository.findById(itemId).orElse(null);
    assertThat(item).isNull();
  }

  @Test
  public void testFindCompletedTask() {
    boolean completed = true;
    List<TaskItemEntity> items = this.taskItemRepository.findByCompleted(completed);
    assertThat(items).extracting(TaskItemEntity::getTitle).containsExactlyInAnyOrder("Super-Glue", "Lingerie");
    assertThat(items).allMatch(TaskItemEntity::isCompleted);
  }

  @Test
  public void testFindNotCompletedTask() {
    boolean completed = false;
    List<TaskItemEntity> items = this.taskItemRepository.findByCompleted(completed);
    assertThat(items).extracting(TaskItemEntity::getTitle).containsExactlyInAnyOrder("Milk","Butter","Bread", "Honey", "Jigsaw","Paste","Sunscreen","Swimsuit","Wetsuit","Flip-flops","Surfboard","Diamond ring");
    assertThat(items).noneMatch(TaskItemEntity::isCompleted);
  }

  @Test
  public void testDeleteCompletedItem() {
    boolean completed = true;
    this.taskItemRepository.deleteAllByCompleted(completed);
    List<TaskItemEntity> items = this.taskItemRepository.findByCompleted(completed);
    assertThat(items).isEmpty();
  }

}
