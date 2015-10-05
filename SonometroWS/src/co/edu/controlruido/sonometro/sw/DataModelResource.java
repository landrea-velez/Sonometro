package co.edu.controlruido.sonometro.sw;

  
 
 import javax.ws.rs.Consumes;
 import javax.ws.rs.DELETE;
 import javax.ws.rs.GET;
 import javax.ws.rs.PUT;
 import javax.ws.rs.Produces;
 import javax.ws.rs.core.Context;
 import javax.ws.rs.core.MediaType;
 import javax.ws.rs.core.Request;
 import javax.ws.rs.core.Response;
 import javax.ws.rs.core.UriInfo;
 import javax.xml.bind.JAXBElement;

 import co.edu.controlruido.sonometro.dao.DataModelDao;
 import co.edu.controlruido.sonometro.model.DataModelClass;

 public class DataModelResource {
   @Context
   UriInfo uriInfo;
   @Context
   Request request;
   String id;
   public DataModelResource(UriInfo uriInfo, Request request, String id) {
     this.uriInfo = uriInfo;
     this.request = request;
     this.id = id;
   }
   
   //Application integration     
   @GET
   @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
   public DataModelClass getTodo() {
	   DataModelClass todo = DataModelDao.instance.getModel().get(id);
     if(todo==null)
       throw new RuntimeException("Get: Todo with " + id +  " not found");
     return todo;
   }
   
   // for the browser
   @GET
   @Produces(MediaType.TEXT_XML)
   public DataModelClass getTodoHTML() {
	   DataModelClass todo = DataModelDao.instance.getModel().get(id);
     if(todo==null)
       throw new RuntimeException("Get: Todo with " + id +  " not found");
     return todo;
   }
   
   @PUT
   @Consumes(MediaType.APPLICATION_XML)
   public Response putTodo(JAXBElement<DataModelClass> todo) {
	   DataModelClass c = todo.getValue();
     return putAndGetResponse(c);
   }
   
   @DELETE
   public void deleteTodo() {
	   DataModelClass c = DataModelDao.instance.getModel().remove(id);
     if(c==null)
       throw new RuntimeException("Delete: Todo with " + id +  " not found");
   }
   
   private Response putAndGetResponse(DataModelClass todo) {
     Response res;
     if(DataModelDao.instance.getModel().containsKey(todo.getId())) {
       res = Response.noContent().build();
     } else {
       res = Response.created(uriInfo.getAbsolutePath()).build();
     }
     DataModelDao.instance.getModel().put(todo.getId(), todo);
     return res;
   }
   
   

 }