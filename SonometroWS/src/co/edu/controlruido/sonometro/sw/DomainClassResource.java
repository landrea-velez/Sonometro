package co.edu.controlruido.sonometro.sw;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.edu.controlruido.sonometro.model.DomainClass;

@Path("/domainclass")
public class DomainClassResource {
  // This method is called if XMLis request
  @GET
  @Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
  public DomainClass getXML() {
	DomainClass domailClass = new DomainClass();
    domailClass.setSummary("This is my first todo");
    domailClass.setDescription("This is my first todo");
    return domailClass;
  }
  
  // This can be used to test the integration with the browser
  @GET
  @Produces({ MediaType.TEXT_XML })
  public DomainClass getHTML() {
	DomainClass domainClass = new DomainClass();
    domainClass.setSummary("This is my first todo");
    domainClass.setDescription("This is my first todo");
    return domainClass;
  }

} 