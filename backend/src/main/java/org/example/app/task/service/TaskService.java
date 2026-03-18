package org.example.app.task.service;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;

import org.example.app.task.common.TaskItemEto;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.logic.*;

@Path("/task")
public class TaskService {

  @Inject
  private UcFindTaskList ucFindTaskList;
  @Inject
  private UcFindTaskItem ucFindTaskItem;
  @Inject
  private UcDeleteTaskItem ucDeleteTaskItem;
  @Inject
  private UcDeleteTaskList ucDeleteTaskList;
  @Inject
  private UcSaveTaskItem ucSaveTaskItem;
  @Inject
  private UcSaveTaskList ucSaveTaskList;


  @GET
  @Path("/list/{id}")
  public TaskListEto findTaskList(@PathParam("id") Long id) {
    TaskListEto task = this.ucFindTaskList.findById(id);
    if (task == null) {
      throw new NotFoundException("TaskList with id " + id + " does not exist.");
    }
    return task;
  }

  @DELETE
  @Path("/list/{id}")
  public void deleteListItem(@PathParam("id") Long id){
    this.ucDeleteTaskList.deleteById(id);
  }

  @POST
  @Path("/list")
  public TaskListEto saveListItem(TaskListEto listEto){
    return this.ucSaveTaskList.saveList(listEto);
  }


  @GET
  @Path("/item/{id}")
  public TaskItemEto findTaskItem(@PathParam("id") Long id) {
    TaskItemEto task = this.ucFindTaskItem.findById(id);
    if (task == null) {
      throw new NotFoundException("TaskItem with id " + id + " does not exist.");
    }
    return task;
  }


  @DELETE
  @Path("/item/{id}")
  public void deleteTaskItem(@PathParam("id") Long id){
    this.ucDeleteTaskItem.deleteById(id);
  }

  @POST
  @Path("/item")
  public TaskItemEto saveTaskItem(TaskItemEto item){
    return this.ucSaveTaskItem.saveItem(item);
  }

}
