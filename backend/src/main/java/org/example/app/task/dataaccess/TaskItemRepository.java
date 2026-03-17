package org.example.app.task.dataaccess;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskItemRepository extends JpaRepository<TaskItemEntity, Long> {

   List<TaskItemEntity> findByCompleted(Boolean completed);

   void deleteAllByCompleted(Boolean completed);

}
