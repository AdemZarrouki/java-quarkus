package org.example.app.task.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import org.example.app.task.common.TaskListEto;
import org.example.app.task.logic.UcFindTaskList;

@Path("/task")
public class TaskService {

  @Inject
  private UcFindTaskList ucFindTaskList;

  @GET
  @Path("/list/{id}")
  public TaskListEto findTaskList(@PathParam("id") Long id) {
    return ucFindTaskList.findById(id);
  }

}
