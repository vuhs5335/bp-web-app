package com.hersa.app.dao.filestore;
public final class FileStoreDB   {
  public static final String ORDERBY_CAPTION = "CAPTION";    
 
  public static final String ORDERBY_CATEGORY = "CATEGORY";    
 
  public static final String ORDERBY_CREATEDBY = "CREATED_BY";    
 
  public static final String ORDERBY_DATECREATED = "DATE_CREATED";    
 
  public static final String ORDERBY_DATEMODIFIED = "DATE_MODIFIED";    
 
  public static final String ORDERBY_DESCRIPTION = "DESCRIPTION";    
 
  public static final String ORDERBY_DISPLAYNAME = "DISPLAY_NAME";    
 
  public static final String ORDERBY_ENABLED = "ENABLED";    
 
  public static final String ORDERBY_FILENAME = "FILE_NAME";    
 
  public static final String ORDERBY_FILESIZE = "FILE_SIZE";    
 
  public static final String ORDERBY_FORMAT = "FORMAT";    
 
  public static final String ORDERBY_ID = "ID";    
 
  public static final String ORDERBY_MODIFIEDBY = "MODIFIED_BY";    
 
  public static final String ORDERBY_TYPE = "TYPE";    
 
  public static final String ORDERBY_URI = "URI";    
 
      
  private static final int DESC = 0;    
  private static final int ASC  = 1;    
  private String orderBys = ""; 
      
  /** Allows the user to easily construct a list of ORDER BY columns   
   *  Example:   
   *      FileStoreDB helper = new FileStoreDB();   
   *      String order = helper.add(ORDERBY_DISPLAYNAME).add(ORDERBY_DISPLAYORDER).toString();   
   * **/   
    public  FileStoreDB add(String column, int type) {   
        if (orderBys.length() > 0) {   
            orderBys += ",";   
        }   
        orderBys += column;   
        if (type == DESC) {   
            orderBys += " DESC";   
        }   
        return this;             
    }   
    public  FileStoreDB add(String column) {   
        return add(column, ASC);             
    }   
    public String toString() {   
        return orderBys;   
    }   
      
} 
