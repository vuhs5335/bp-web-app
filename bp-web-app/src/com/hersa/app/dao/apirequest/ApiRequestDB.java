package com.hersa.app.dao.apirequest;
public final class ApiRequestDB   {
  public static final String ORDERBY_ID = "ID";    
 
  public static final String ORDERBY_IPADDRESS = "IP_ADDRESS";    
 
  public static final String ORDERBY_REQDATE = "REQ_DATE";    
 
  public static final String ORDERBY_RESOURCE = "RESOURCE";    
 
  public static final String ORDERBY_RESPONSE = "RESPONSE";    
 
      
  private static final int DESC = 0;    
  private static final int ASC  = 1;    
  private String orderBys = ""; 
      
  /** Allows the user to easily construct a list of ORDER BY columns   
   *  Example:   
   *      ApiRequestDB helper = new ApiRequestDB();   
   *      String order = helper.add(ORDERBY_DISPLAYNAME).add(ORDERBY_DISPLAYORDER).toString();   
   * **/   
    public  ApiRequestDB add(String column, int type) {   
        if (orderBys.length() > 0) {   
            orderBys += ",";   
        }   
        orderBys += column;   
        if (type == DESC) {   
            orderBys += " DESC";   
        }   
        return this;             
    }   
    public  ApiRequestDB add(String column) {   
        return add(column, ASC);             
    }   
    public String toString() {   
        return orderBys;   
    }   
      
} 
