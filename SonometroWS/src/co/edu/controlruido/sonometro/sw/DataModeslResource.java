package co.edu.controlruido.sonometro.sw;

 

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import co.edu.controlruido.sonometro.dao.DataModelDao;
import co.edu.controlruido.sonometro.model.DataModelClass;

// Will map the resource to the URL todos
@Path("/todos")
public class DataModeslResource {

  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of todos to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<DataModelClass> getTodosBrowser() {
    List<DataModelClass> todos = new ArrayList<DataModelClass>();
    todos.addAll(DataModelDao.instance.getModel().values());
    return todos;
  }

  // Return the list of todos for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<DataModelClass> getTodos() {
    List<DataModelClass> todos = new ArrayList<DataModelClass>();
    todos.addAll(DataModelDao.instance.getModel().values());
    return todos;
  }

  // retuns the number of todos
  // Use http://localhost:8080/com.vogella.jersey.todo/rest/todos/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = DataModelDao.instance.getModel().size();
    return String.valueOf(count);
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newTodo(@FormParam("id") String id,
      @FormParam("summary") String summary,
      @FormParam("description") String description,
      @Context HttpServletResponse servletResponse) throws IOException {
	  DataModelClass todo = new DataModelClass(id, summary);
    if (description != null) {
      todo.setDescription(description);
    }
    DataModelDao.instance.getModel().put(id, todo);

    servletResponse.sendRedirect("../create_todo.html");
  }

  // Defines that the next path parameter after todos is
  // treated as a parameter and passed to the TodoResources
  // Allows to type http://localhost:8080/com.vogella.jersey.todo/rest/todos/1
  // 1 will be treaded as parameter todo and passed to TodoResource
  @Path("{todo}")
  public DataModelResource getTodo(@PathParam("todo") String id) {
    return new DataModelResource(uriInfo, request, id);
  }

} 