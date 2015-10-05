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

 import co.edu.controlruido.sonometro.dao.DecibelioDao;
 import co.edu.controlruido.sonometro.model.Decibelio;

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
   public Decibelio getDecibelio() {
	   Decibelio decibelio = DecibelioDao.instance.getModel().get(id);
     if(decibelio==null)
       throw new RuntimeException("Get: decibel with " + id +  " not found");
     return decibelio;
   }
   
   // for the browser
   @GET
   @Produces(MediaType.TEXT_XML)
   public Decibelio getDecibelioHTML() {
	   Decibelio decibelio = DecibelioDao.instance.getModel().get(id);
     if(decibelio==null)
       throw new RuntimeException("Get: decibel with " + id +  " not found");
     return decibelio;
   }
   
   @PUT
   @Consumes(MediaType.APPLICATION_XML)
   public Response putDecibelio(JAXBElement<Decibelio> decibelio) {
	   Decibelio c = decibelio.getValue();
     return putAndGetResponse(c);
   }
   
   @DELETE
   public void deleteDecibelio() {
	   Decibelio decibelio = DecibelioDao.instance.getModel().remove(id);
     if(decibelio==null)
       throw new RuntimeException("Delete: decibelio with " + id +  " not found");
   }
   
   private Response putAndGetResponse(Decibelio decibelio) {
     Response res;
     if(DecibelioDao.instance.getModel().containsKey(decibelio.getId())) {
       res = Response.noContent().build();
     } else {
       res = Response.created(uriInfo.getAbsolutePath()).build();
     }
     DecibelioDao.instance.getModel().put(decibelio.getId(), decibelio);
     return res;
   }
   
   

 }