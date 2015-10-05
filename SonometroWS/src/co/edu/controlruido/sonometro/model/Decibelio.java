package co.edu.controlruido.sonometro.model;


 

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Decibelio {
  private String id;
  private String nivelIntensidad;
  private String descriptionIntensidad;
  
  public Decibelio(){
    
  }
  public Decibelio (String id, String nivel){
    this.id = id;
    this.nivelIntensidad = nivel;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public String getNivelIntensidad() {
    return nivelIntensidad;
  }
  public void setNivelIntensidad(String nivel) {
    this.nivelIntensidad = nivel;
  }
  public String getDescripcion() {
    return descriptionIntensidad;
  }
  public void setDescripcion(String descriptionIntensidad) {
    this.descriptionIntensidad = descriptionIntensidad;
  }
  
  
} 
