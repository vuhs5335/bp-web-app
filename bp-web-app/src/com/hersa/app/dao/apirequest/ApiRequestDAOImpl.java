/*  
 * ===========================================================================  
 *                     Proprietary Rights Notice  
 * ===========================================================================  
 * All rights reserved.  This document contains valuable and proprietary  
 * properties of Hersa Corp.  This document embodies substantial  
 * creative works and confidential information, ideas and expressions, no  
 * part of which may be reproduced or transmitted IN ANY FORM OR BY ANY MEANS,  
 * electronic, mechanical or otherwise, including but not limited to photo-  
 * copying and recording or in connection with any information storage or  
 * retrieval system without the express written permission of Easy Access Inc.  
 * ===========================================================================  
 */  
package com.hersa.app.dao.apirequest;
    
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hersa.app.AbstractDAImpl;
import com.hersa.app.DAOException;
import com.hersa.app.DefaultConnectionProvider;
class ApiRequestDAOImpl extends AbstractDAImpl implements ApiRequestDAO {

  private static final Logger logger = 
      Logger.getLogger(ApiRequestDAOImpl.class);

  protected String tableName = ApiRequestPrefix.PREFIX + "API_REQUEST"; 
  public String getTable(){
      return "\"" + this.tableName + "\""; 
}
  public void setTable(String table){
      this.tableName = table; 
}
  protected String getSelectSQL(){
      String sql = " SELECT ID, IP_ADDRESS, REQ_DATE, RESOURCE, RESPONSE FROM " + getTable() + " " ;
      return sql;
}
  protected String getSelectCountSQL(){
      String sql = " SELECT COUNT(*) FROM " + getTable();
      return sql;
}
  protected String getInsertSQL(){
      String sql = " INSERT INTO " + getTable() + " ( IP_ADDRESS, REQ_DATE, RESOURCE, RESPONSE ) VALUES  ( ?, ?, ?, ? )";
      return sql;
}
  protected String getUpdateSQL(){
      String sql = " UPDATE " + getTable() + " SET  IP_ADDRESS = ? , REQ_DATE = ? , RESOURCE = ? , RESPONSE = ?  WHERE ";
      return sql;
}
  protected String getUpdateBlobsSQL(){
      String sql = " UPDATE  " + getTable() + "  SET  IP_ADDRESS = ? , REQ_DATE = ? , RESOURCE = ? , RESPONSE = ?  WHERE ";
      return sql;
}
  protected String getDeleteSQL(){
      String sql = " DELETE FROM  " + getTable() + "  WHERE  " ;
      return sql;
}
  protected String getDeleteBySQL(){
      String sql = " DELETE FROM " + getTable() ;
      return sql;
}

  public ApiRequestDAOImpl() { 
       factory = new DefaultConnectionProvider( ApiRequestJNDI.NON_XA_DATASOURCE ); 
  } 
    protected Connection openConnection() {  
     if (connectionSetByCaller) return this.con;    
     try {   
         Connection connection = factory.openConnection();   
         if (isReadOnly()) {     
             try {   
                 connection.setReadOnly(isReadOnly());   
             } catch (SQLException e) {      
                 e.printStackTrace();    
             }   
         }   
         return connection;  
       } catch (Exception e) {   
         if (logger.isEnabledFor(Level.ERROR)) {   
             logger.error(e);   
         }   
        try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }   
     }   
         return con;  
   }    
     
     
 protected void closeConnection(Connection con) {   
     if (!this.persistReadOnly) {            
         this.readOnly = false;          
     }          
     if (connectionSetByCaller) { 
        return ;    
     }    
      try {   
         factory.closeConnection(con);   
     } catch (SQLException e) {   
         logSQLException(e);   
     }   
 }   
     @Override   
     public void setReadOnly(boolean readOnly) {   
         this.readOnly = readOnly;   
     }   

    public void open() {;}  
    public void close() {;}  
    protected Logger getLogger() {  
      return logger; 
    } 

  public final ApiRequestDTO [] listAllApiRequest(String orderByColumn) { 
    String order = "";    
     if (orderByColumn != null && orderByColumn.trim().length() > 0) {    
        order = " ORDER BY " + orderByColumn;     
     }    
    return listApiRequest(null, null, null, order); 
 } 

public final ApiRequestDTO [] listAllApiRequest() { 
    return listApiRequest(null, null, null, null); 
 } 

public final ApiRequestDTO [] customApiRequest(String completeSQL ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listApiRequest()");  
   }   
   ApiRequestDTO[] _ApiRequest = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   try {   
           stmt = con.prepareStatement(completeSQL);    
           stmt.setMaxRows(getMaxRows());            rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractApiRequest(rs));        
               } while (rs.next());        
               _ApiRequest =      
                   (ApiRequestDTO[]) list.toArray(new ApiRequestDTO[list.size()]);       
           } else {        
               _ApiRequest = new ApiRequestDTO[0];        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< findApiRequest()");     
   }       
   return _ApiRequest;        
 }  

public final ApiRequestDTO [] listApiRequest(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listApiRequest()");  
   }   
   if (paginationStartIndex > 0) {    
      return searchPageableApiRequest(whereClause, whereParams, paramTypes, orderClause, paginationStartIndex, paginationPageSize);  
   }  
   ApiRequestDTO[] _ApiRequest = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   if (orderClause == null) {      
       orderClause = "";     
   }                   
   try {   
           stmt = con.prepareStatement(getSelectSQL() + whereClause + orderClause);    
           stmt.setMaxRows(getMaxRows());            if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else { 
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractApiRequest(rs));        
               } while (rs.next());        
               _ApiRequest =      
                   (ApiRequestDTO[]) list.toArray(new ApiRequestDTO[list.size()]);       
           } else {        
               _ApiRequest = new ApiRequestDTO[0];        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< findApiRequest()");     
   }       
   return _ApiRequest;        
 }  

public final ApiRequestDTO [] searchPageableApiRequest(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause,int startIndex, int pageSize ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> searchPageableApiRequest()");  
   }   
   ApiRequestDTO[] _ApiRequest = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   if (orderClause == null) {      
       orderClause = "";     
   }                   
   try {   
           int maxRowsToFetch = startIndex + pageSize-1;     
           int len = pageSize;		     
           stmt = con.prepareStatement(     
           		getSelectSQL() + whereClause + orderClause,     
           		ResultSet.TYPE_SCROLL_INSENSITIVE,     
           		ResultSet.CONCUR_READ_ONLY);		     
           stmt.setMaxRows(maxRowsToFetch);     
           stmt.setFetchSize(pageSize);     
           if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else { 
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           rs = stmt.executeQuery();       
           if (rs.absolute(startIndex)) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractApiRequest(rs));        
               } while ((rs.next()) && (--len > 0));      
               _ApiRequest =      
                   (ApiRequestDTO[]) list.toArray(new ApiRequestDTO[list.size()]);       
           } else {        
               _ApiRequest = new ApiRequestDTO[0];        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< searchPageableApiRequest()");     
   }       
   return _ApiRequest;        
 }  

public final int countApiRequest(String whereClause, Object[] whereParams, int[] paramTypes ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> countApiRequest()");  
   }   
   int count = 0;    
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   try {   
           stmt = con.prepareStatement(getSelectCountSQL() + whereClause );    
           if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else { 
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   count = rs.getInt(1);   
               } while (rs.next());        
           }       
   } catch (SQLException e) {      
           logSQLException(e);     
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }      
   } finally {     
           if (rs != null) {       
               try {       
                   rs.close();     
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           if (stmt != null) {     
               try {       
                   stmt.close();       
               } catch (SQLException e) {      
                   logSQLException(e);     
               }       
           }       
           closeConnection(con);       
   }       
   if (logger.isEnabledFor(Level.TRACE)) {     
           logger.trace("<< findApiRequest()");     
   }       
   return count;     
 }  

  public final ApiRequestDTO [] listApiRequestById(long _Id) { 
     return listApiRequestById( _Id, null);     
 } 

  public final ApiRequestDTO [] listApiRequestById(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listApiRequest(whereClause, objs, types, order); 
 } 



  public final int countApiRequestById(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countApiRequest(whereClause, objs, types); 
 } 




  public final ApiRequestDTO [] listApiRequestByIpAddress(String _IpAddress) { 
     return listApiRequestByIpAddress( _IpAddress, null);     
 } 

  public final ApiRequestDTO [] listApiRequestByIpAddress(String _IpAddress, String orderByColumn) { 
    String whereClause = " WHERE IP_ADDRESS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _IpAddress;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listApiRequest(whereClause, objs, types, order); 
 } 



  public final int countApiRequestByIpAddress(String _IpAddress) { 
    String whereClause = " WHERE IP_ADDRESS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _IpAddress;   
    types[0] = java.sql.Types.VARCHAR;   
    return countApiRequest(whereClause, objs, types); 
 } 




  public final ApiRequestDTO [] listApiRequestByReqDate(java.util.Date _ReqDate) { 
     return listApiRequestByReqDate( _ReqDate, null);     
 } 

  public final ApiRequestDTO [] listApiRequestByReqDate(java.util.Date _ReqDate, String orderByColumn) { 
    String whereClause = " WHERE REQ_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ReqDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listApiRequest(whereClause, objs, types, order); 
 } 



  public final int countApiRequestByReqDate(java.util.Date _ReqDate) { 
    String whereClause = " WHERE REQ_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ReqDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countApiRequest(whereClause, objs, types); 
 } 




  public final ApiRequestDTO [] listApiRequestByResource(String _Resource) { 
     return listApiRequestByResource( _Resource, null);     
 } 

  public final ApiRequestDTO [] listApiRequestByResource(String _Resource, String orderByColumn) { 
    String whereClause = " WHERE RESOURCE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Resource;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listApiRequest(whereClause, objs, types, order); 
 } 



  public final int countApiRequestByResource(String _Resource) { 
    String whereClause = " WHERE RESOURCE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Resource;   
    types[0] = java.sql.Types.VARCHAR;   
    return countApiRequest(whereClause, objs, types); 
 } 




  public final ApiRequestDTO [] listApiRequestByResponse(String _Response) { 
     return listApiRequestByResponse( _Response, null);     
 } 

  public final ApiRequestDTO [] listApiRequestByResponse(String _Response, String orderByColumn) { 
    String whereClause = " WHERE RESPONSE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Response;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listApiRequest(whereClause, objs, types, order); 
 } 



  public final int countApiRequestByResponse(String _Response) { 
    String whereClause = " WHERE RESPONSE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Response;   
    types[0] = java.sql.Types.VARCHAR;   
    return countApiRequest(whereClause, objs, types); 
 } 









  public final ApiRequestDTO createApiRequest(ApiRequestDTO _ApiRequest)
    throws ApiRequestCreateException {  
         if (logger.isEnabledFor(Level.TRACE)) {
             logger.trace(">> createApiRequest()");
         }  
             PreparedStatement stmt = null;   
             Connection con = openConnection();
         try { 
                 int i = 1;
                 stmt = con.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                  stmt.setString(i++, _ApiRequest.getIpAddress()); 
                  if (_ApiRequest.getReqDate() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_ApiRequest.getReqDate().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _ApiRequest.getResource()); 
                  stmt.setString(i++, _ApiRequest.getResponse()); 
                 stmt.executeUpdate();     
                  int generatedId = -1;      
                  ResultSet rs = stmt.getGeneratedKeys();      
                  while (rs.next()) {      
                     generatedId = (int) rs.getLong(1);      
                  }       
                  rs.close();      
                  _ApiRequest.setId(generatedId);     
         } catch (SQLException e) {     
             logSQLException(e);     
             if (isSQLConstraintViolated(e)) {     
                 throw new ApiRequestCreateException(e.getMessage(), e);     
             }     
            try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }     
         } finally {     
             if (stmt != null) {     
                 try {     
                     stmt.close();     
                 } catch (SQLException e) {     
                     logSQLException(e);     
                 }     
             }     
             closeConnection(con);     
         }     
         if (logger.isEnabledFor(Level.TRACE)) {     
             logger.trace("<< createApiRequest()");     
         }     
         return _ApiRequest;     
     }       

  public final void updateApiRequest(ApiRequestDTO _ApiRequest) 
    throws ApiRequestUpdateException, ApiRequestFinderException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> updateApiRequest()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getUpdateSQL());         
                  stmt.setString(i++,  _ApiRequest.getIpAddress() ); 
             if (_ApiRequest.getReqDate() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_ApiRequest.getReqDate().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _ApiRequest.getResource() ); 
                  stmt.setString(i++,  _ApiRequest.getResponse() ); 
  /*** now set the criteria, PK values ***/ 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new ApiRequestFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new ApiRequestUpdateException("");       
           }        
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }       
       } finally {      
           if (stmt != null) {      
               try {        
                   stmt.close();        
               } catch (SQLException e) {       
                   logSQLException(e);      
               }        
           }        
           closeConnection(con);        
       }        
       if (logger.isEnabledFor(Level.TRACE)) {      
           logger.trace("<< updateApiRequest()");         
       }        
   }        

  public final void deleteApiRequest(ApiRequestDTO _ApiRequest) 
    throws ApiRequestFinderException, ApiRequestDeleteException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> deleteApiRequest()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getDeleteSQL());         
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new ApiRequestFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new ApiRequestDeleteException("");       
           }        
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }       
       } finally {      
           if (stmt != null) {      
               try {        
                   stmt.close();        
               } catch (SQLException e) {       
                   logSQLException(e);      
               }        
           }        
           closeConnection(con);        
       }        
       if (logger.isEnabledFor(Level.TRACE)) {      
           logger.trace("<< deleteApiRequest()");         
       }        
   }        

  public final void deleteApiRequestWhere(String whereClause, Object[] whereParams, int[] paramTypes)   
    throws ApiRequestFinderException, ApiRequestDeleteException  {      
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listApiRequest()");  
   }   
   ApiRequestDTO[] _ApiRequest = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   if (whereClause == null) {      
       whereClause = "";     
   }           
   try {   
           stmt = con.prepareStatement(getDeleteBySQL() + whereClause);   
           if (whereParams != null) {      
               for (int i = 0; i < whereParams.length; i++) {      
                   if (whereParams[i] == null) { 
                       stmt.setNull(i+1, paramTypes[i]);   
                   } else {
                       stmt.setObject(i+1, whereParams[i], paramTypes[i]);     
                   }  
               }   
           }               
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new ApiRequestFinderException("");       
           }        
   } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new ApiRequestDeleteException(e.getMessage(), e);      
           }        
          try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }       
   } finally {      
           if (stmt != null) {      
               try {        
                   stmt.close();        
               } catch (SQLException e) {       
                   logSQLException(e);      
               }        
           }        
           closeConnection(con);        
   }        
   if (logger.isEnabledFor(Level.TRACE)) {      
       logger.trace("<< deleteApiRequest()");         
   }        
   }        

  public final void deleteApiRequestById(long _Id) throws ApiRequestFinderException, ApiRequestDeleteException{ 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    deleteApiRequestWhere(whereClause, objs, types); 
 } 




  public final void deleteApiRequestByIpAddress(String _IpAddress) throws ApiRequestFinderException, ApiRequestDeleteException{ 
    String whereClause = " WHERE IP_ADDRESS = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _IpAddress;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteApiRequestWhere(whereClause, objs, types); 
 } 




  public final void deleteApiRequestByReqDate(java.util.Date _ReqDate) throws ApiRequestFinderException, ApiRequestDeleteException{ 
    String whereClause = " WHERE REQ_DATE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_ReqDate.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteApiRequestWhere(whereClause, objs, types); 
 } 




  public final void deleteApiRequestByResource(String _Resource) throws ApiRequestFinderException, ApiRequestDeleteException{ 
    String whereClause = " WHERE RESOURCE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Resource;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteApiRequestWhere(whereClause, objs, types); 
 } 




  public final void deleteApiRequestByResponse(String _Response) throws ApiRequestFinderException, ApiRequestDeleteException{ 
    String whereClause = " WHERE RESPONSE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Response;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteApiRequestWhere(whereClause, objs, types); 
 } 




public final ApiRequestDTO extractApiRequest(ResultSet rs)       
    throws SQLException {  
         ApiRequestDTO obj = new ApiRequestDTO();          
         int i = 1;      
         obj.setId( rs.getLong(i++) );       
         obj.setIpAddress( rs.getString(i++) );       
         obj.setReqDate( rs.getTimestamp(i++) );       
         obj.setResource( rs.getString(i++) );       
         obj.setResponse( rs.getString(i++) );       
 return obj;  } 


 /*************** end *****************/
}
