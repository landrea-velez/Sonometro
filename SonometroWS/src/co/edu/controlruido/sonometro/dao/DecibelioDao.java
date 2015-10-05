package co.edu.controlruido.sonometro.dao;

  
 
 import java.util.HashMap;
 import java.util.Map;

import co.edu.controlruido.sonometro.model.Decibelio;

 public enum DecibelioDao {
   instance;
   
   private Map<String, Decibelio> contentProvider = new HashMap<>();
   
   private DecibelioDao() {
     
	 Decibelio decibelio = new Decibelio("1", "10");
     decibelio.setDescripcion("Respiración tranquila");
     contentProvider.put("1", decibelio);
     decibelio = new Decibelio("2", "20");
     decibelio.setDescripcion("Biblioteca");
     contentProvider.put("2", decibelio);
     
   }
   public Map<String, Decibelio> getModel(){
     return contentProvider;
   }
   
 } 
