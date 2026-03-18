package org.example.app.task.dataaccess;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@QuarkusTest
public class TaskListRepositoryTest extends Assertions {

  @Inject
  private TaskListRepository taskListRepository;

  @Test
  public void testFindById() {
    Long itemId = 1L;
    TaskListEntity task = this.taskListRepository.findById(itemId).get();
    assertThat(task.getTitle()).isEqualTo("Shopping List");
  }


}
