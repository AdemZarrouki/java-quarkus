package org.example.app.task.service;

import java.net.URI;
import java.util.List;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.example.app.task.common.TaskItemEto;
import org.example.app.task.common.TaskListCto;
import org.example.app.task.common.TaskListEto;
import org.example.app.task.logic.UcAddRandomActivityTaskItem;
import org.example.app.task.logic.UcDeleteTaskItem;
import org.example.app.task.logic.UcDeleteTaskList;
import org.example.app.task.logic.UcFindTaskItem;
import org.example.app.task.logic.UcFindTaskList;
import org.example.app.task.logic.UcSaveTaskItem;
import org.example.app.task.logic.UcSaveTaskList;

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
  @Inject
  UcAddRandomActivityTaskItem ucAddRandomActivityTaskItem;


  @GET
  @Path("/lists")
  @Operation(
      summary = "Find all TaskLists",
      description = "Returns a list of all TaskLists in the system."
  )
  @APIResponse(
      responseCode = "200",
      description = "A list of TaskLists was successfully retrieved.",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = TaskListEto.class))
  )
  @APIResponse(
      responseCode = "404",
      description = "No TaskLists were found in the system."
  )
  public List<TaskListEto> findAllTaskLists() {
    List<TaskListEto> tasklist = this.ucFindTaskList.findAll();
    if (tasklist.isEmpty()) {
      throw new NotFoundException("No TaskLists found.");
    }
    return tasklist;
  }

  @GET
  @Path("/list-with-items/{id}")
  @Operation(
      summary = "Find a TaskList with its items",
      description = "Returns a TaskList along with its associated TaskItems based on the provided ID."
  )
  @APIResponse(
      responseCode = "200",
      description = "The TaskList with its items was successfully retrieved.",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = TaskListCto.class))
  )
  @APIResponse(
      responseCode = "404",
      description = "A TaskList with the specified ID was not found in the system."
  )
  public TaskListCto findTaskListWithItems(@Parameter(description = "Task List Id") @PathParam("id") Long id) {
    TaskListCto taskListCto = ucFindTaskList.findTaskListWithItems(id);
    if (taskListCto == null) {
      throw new NotFoundException("TaskList with id " + id + " does not exist.");
    }
    return taskListCto;
  }

  @GET
  @Path("/list/{id}")
  @Operation(
      summary = "Find a TaskList by ID",
      description = "Returns a TaskList based on the provided ID."
  )
  @APIResponse(
      description = "The TaskList was successfully retrieved.",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = TaskListEto.class))
  )
  @APIResponse(
      responseCode = "404",
      description = "A TaskList with the specified ID was not found in the system."
  )
  public TaskListEto findTaskList(@Parameter(description = "Task List id") @PathParam("id") Long id) {
    TaskListEto task = this.ucFindTaskList.findById(id);
    if (task == null) {
      throw new NotFoundException("TaskList with id " + id + " does not exist.");
    }
    return task;
  }

  @DELETE
  @Path("/list/{id}")
  @Operation(
      summary = "Delete a TaskList by ID",
      description = "Deletes a TaskList based on the provided ID."
  )
  @APIResponse(
      description = "The TaskList was successfully deleted.",
      content = @Content(mediaType = "application/json")
  )
  @APIResponse(
      responseCode = "404",
      description = "A TaskList with the specified ID was not found in the system."
  )
  public void deleteListItem(@PathParam("id") Long id) {
    this.ucDeleteTaskList.deleteById(id);
  }

  @POST
  @Path("/list")
  @Operation(
      summary = "Save a TaskList",
      description = "Saves a TaskList. If the TaskList has an ID, it will be updated; otherwise, a new TaskList will be created."
  )
  @APIResponse(
      description = "The TaskList was successfully saved.",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = TaskListEto.class))
  )
  @APIResponse(
      responseCode = "400",
      description = "Invalid TaskList data provided in the request."
  )
  public TaskListEto saveListItem(@Parameter(description = "New task List") TaskListEto listEto) {
    return this.ucSaveTaskList.saveList(listEto);
  }


  @GET
  @Path("/item/{id}")
  @Operation(
      summary = "Find a TaskItem by ID",
      description = "Returns a TaskItem based on the provided ID."
  )
  @APIResponse(
      description = "The TaskItem was successfully retrieved.",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = TaskItemEto.class))
  )
  @APIResponse(
      responseCode = "404",
      description = "A TaskItem with the specified ID was not found in the system."
  )
  public TaskItemEto findTaskItem(@Parameter(description = "Task Item id") @PathParam("id") Long id) {
    TaskItemEto task = this.ucFindTaskItem.findById(id);
    if (task == null) {
      throw new NotFoundException("TaskItem with id " + id + " does not exist.");
    }
    return task;
  }


  @DELETE
  @Path("/item/{id}")
  @Operation(
      summary = "Delete a TaskItem by ID",
      description = "Deletes a TaskItem based on the provided ID."
  )
  @APIResponse(
      description = "The TaskItem was successfully deleted.",
      content = @Content(mediaType = "application/json")
  )
  @APIResponse(
      responseCode = "404",
      description = "A TaskItem with the specified ID was not found in the system."
  )
  public void deleteTaskItem(@Parameter(description = "Task Item id") @PathParam("id") Long id) {
    this.ucDeleteTaskItem.deleteById(id);
  }

  @POST
  @Path("/item")
  @Operation(
      summary = "Save a TaskItem",
      description = "Saves a TaskItem. If the TaskItem has an ID, it will be updated; otherwise, a new TaskItem will be created."
  )
  @APIResponse(
      description = "The TaskItem was successfully saved.",
      content = @Content(mediaType = "application/json",
          schema = @Schema(implementation = TaskItemEto.class))
  )
  @APIResponse(
      responseCode = "400",
      description = "Invalid TaskItem data provided in the request."
  )
  public TaskItemEto saveTaskItem(TaskItemEto item) {
    return this.ucSaveTaskItem.saveItem(item);
  }

  // snippet
  @POST
  @Path("/list/{id}/random-activity")
  @Operation(summary = "Add random activity", description = "Add a random activity to this task list")
  @APIResponse(responseCode = "201", description = "Task item successfully added")
  @APIResponse(responseCode = "500", description = "Server unavailable or a server-side error occurred")
  public Response addRandomActivity(
      @Parameter(description = "The id of the task list for which to add the task", required = true, example = "1", schema = @Schema(type = SchemaType.INTEGER)) @PathParam("id") Long id) {

    Long taskItemId = this.ucAddRandomActivityTaskItem.addRandom(id);
    return Response.created(URI.create("/task/item/" + taskItemId)).build();
  }
}
