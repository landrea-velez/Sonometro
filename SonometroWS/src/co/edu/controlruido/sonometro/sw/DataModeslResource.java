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

import co.edu.controlruido.sonometro.dao.DecibelioDao;
import co.edu.controlruido.sonometro.model.Decibelio;

// Will map the resource to the URL todos
@Path("/todos")
public class DataModeslResource {

  // Allows to insert contextual objects into the class,
  // e.g. ServletContext, Request, Response, UriInfo
  @Context
  UriInfo uriInfo;
  @Context
  Request request;

  // Return the list of decibels to the user in the browser
  @GET
  @Produces(MediaType.TEXT_XML)
  public List<Decibelio> getDecibeliosBrowser() {
    List<Decibelio> decibelios = new ArrayList<Decibelio>();
    decibelios.addAll(DecibelioDao.instance.getModel().values());
    return decibelios;
  }

  // Return the list of decibels for applications
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public List<Decibelio> getDecibelios() {
    List<Decibelio> decibelios = new ArrayList<Decibelio>();
    decibelios.addAll(DecibelioDao.instance.getModel().values());
    return decibelios;
  }

  // retuns the number of decibels
  // Use http://localhost:8080/SonometroWS/rest/todos/count
  // to get the total number of records
  @GET
  @Path("count")
  @Produces(MediaType.TEXT_PLAIN)
  public String getCount() {
    int count = DecibelioDao.instance.getModel().size();
    return String.valueOf(count);
  }

  @POST
  @Produces(MediaType.TEXT_HTML)
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public void newTodo(@FormParam("id") String id,
      @FormParam("nivel") String nivel,
      @FormParam("descripcion") String descripcion,
      @Context HttpServletResponse servletResponse) throws IOException {
	  Decibelio decibelio = new Decibelio(id, nivel);
    if (descripcion != null) {
    	decibelio.setDescripcion(descripcion);
    }
    DecibelioDao.instance.getModel().put(id, decibelio);

    servletResponse.sendRedirect("../create_todo.html");
  }

  // Defines that the next path parameter after decibels is
  // treated as a parameter and passed to the TodoResources
  // Allows to type http://localhost:8080/SonometroWS/rest/todos/1
  // 1 will be treaded as parameter todo and passed to TodoResource
  @Path("{todo}")
  public DataModelResource getTodo(@PathParam("todo") String id) {
    return new DataModelResource(uriInfo, request, id);
  }

} 