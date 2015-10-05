package co.edu.controlruido.sonometro.dao;

  
 
 import java.util.HashMap;
 import java.util.Map;

import co.edu.controlruido.sonometro.model.DataModelClass;

 public enum DataModelDao {
   instance;
   
   private Map<String, DataModelClass> contentProvider = new HashMap<>();
   
   private DataModelDao() {
     
	 DataModelClass todo = new DataModelClass("1", "Learn REST");
     todo.setDescription("Read http://www.vogella.com/tutorials/REST/article.html");
     contentProvider.put("1", todo);
     todo = new DataModelClass("2", "Do something");
     todo.setDescription("Read complete http://www.vogella.com");
     contentProvider.put("2", todo);
     
   }
   public Map<String, DataModelClass> getModel(){
     return contentProvider;
   }
   
 } 
