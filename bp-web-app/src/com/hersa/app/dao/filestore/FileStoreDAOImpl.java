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
package com.hersa.app.dao.filestore;
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
class FileStoreDAOImpl extends AbstractDAImpl implements FileStoreDAO {

  private static final Logger logger = 
      Logger.getLogger(FileStoreDAOImpl.class);

  protected String tableName = FileStorePrefix.PREFIX + "FILE_STORE"; 
  public String getTable(){
      return "\"" + this.tableName + "\""; 
}
  public void setTable(String table){
      this.tableName = table; 
}
  protected String getSelectSQL(){
      String sql = " SELECT CAPTION, CATEGORY, CREATED_BY, DATE_CREATED, DATE_MODIFIED, DESCRIPTION, DISPLAY_NAME, ENABLED, FILE_NAME, FILE_SIZE, FORMAT, ID, MODIFIED_BY, TYPE, URI FROM " + getTable() + " " ;
      return sql;
}
  protected String getSelectCountSQL(){
      String sql = " SELECT COUNT(*) FROM " + getTable();
      return sql;
}
  protected String getInsertSQL(){
      String sql = " INSERT INTO " + getTable() + " ( CAPTION, CATEGORY, CREATED_BY, DATE_CREATED, DATE_MODIFIED, DESCRIPTION, DISPLAY_NAME, ENABLED, FILE_NAME, FILE_SIZE, FORMAT, MODIFIED_BY, TYPE, URI ) VALUES  ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )";
      return sql;
}
  protected String getUpdateSQL(){
      String sql = " UPDATE " + getTable() + " SET  CAPTION = ? , CATEGORY = ? , CREATED_BY = ? , DATE_CREATED = ? , DATE_MODIFIED = ? , DESCRIPTION = ? , DISPLAY_NAME = ? , ENABLED = ? , FILE_NAME = ? , FILE_SIZE = ? , FORMAT = ? , MODIFIED_BY = ? , TYPE = ? , URI = ?  WHERE ";
      return sql;
}
  protected String getUpdateBlobsSQL(){
      String sql = " UPDATE  " + getTable() + "  SET  CAPTION = ? , CATEGORY = ? , CREATED_BY = ? , DATE_CREATED = ? , DATE_MODIFIED = ? , DESCRIPTION = ? , DISPLAY_NAME = ? , ENABLED = ? , FILE_NAME = ? , FILE_SIZE = ? , FORMAT = ? , MODIFIED_BY = ? , TYPE = ? , URI = ?  WHERE ";
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

  public FileStoreDAOImpl() { 
       factory = new DefaultConnectionProvider( FileStoreJNDI.NON_XA_DATASOURCE ); 
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

  public final FileStoreDTO [] listAllFileStore(String orderByColumn) { 
    String order = "";    
     if (orderByColumn != null && orderByColumn.trim().length() > 0) {    
        order = " ORDER BY " + orderByColumn;     
     }    
    return listFileStore(null, null, null, order); 
 } 

public final FileStoreDTO [] listAllFileStore() { 
    return listFileStore(null, null, null, null); 
 } 

public final FileStoreDTO [] customFileStore(String completeSQL ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listFileStore()");  
   }   
   FileStoreDTO[] _FileStore = null;  
   ResultSet rs = null;    
   PreparedStatement stmt = null;  
   Connection con = openConnection();  
   try {   
           stmt = con.prepareStatement(completeSQL);    
           stmt.setMaxRows(getMaxRows());            rs = stmt.executeQuery();       
           if (rs.next()) {        
               List list = new ArrayList();        
               do {        
                   list.add(extractFileStore(rs));        
               } while (rs.next());        
               _FileStore =      
                   (FileStoreDTO[]) list.toArray(new FileStoreDTO[list.size()]);       
           } else {        
               _FileStore = new FileStoreDTO[0];        
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
           logger.trace("<< findFileStore()");     
   }       
   return _FileStore;        
 }  

public final FileStoreDTO [] listFileStore(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listFileStore()");  
   }   
   if (paginationStartIndex > 0) {    
      return searchPageableFileStore(whereClause, whereParams, paramTypes, orderClause, paginationStartIndex, paginationPageSize);  
   }  
   FileStoreDTO[] _FileStore = null;  
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
                   list.add(extractFileStore(rs));        
               } while (rs.next());        
               _FileStore =      
                   (FileStoreDTO[]) list.toArray(new FileStoreDTO[list.size()]);       
           } else {        
               _FileStore = new FileStoreDTO[0];        
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
           logger.trace("<< findFileStore()");     
   }       
   return _FileStore;        
 }  

public final FileStoreDTO [] searchPageableFileStore(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause,int startIndex, int pageSize ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> searchPageableFileStore()");  
   }   
   FileStoreDTO[] _FileStore = null;  
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
                   list.add(extractFileStore(rs));        
               } while ((rs.next()) && (--len > 0));      
               _FileStore =      
                   (FileStoreDTO[]) list.toArray(new FileStoreDTO[list.size()]);       
           } else {        
               _FileStore = new FileStoreDTO[0];        
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
           logger.trace("<< searchPageableFileStore()");     
   }       
   return _FileStore;        
 }  

public final int countFileStore(String whereClause, Object[] whereParams, int[] paramTypes ) { 
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> countFileStore()");  
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
           logger.trace("<< findFileStore()");     
   }       
   return count;     
 }  

  public final FileStoreDTO [] listFileStoreByCaption(String _Caption) { 
     return listFileStoreByCaption( _Caption, null);     
 } 

  public final FileStoreDTO [] listFileStoreByCaption(String _Caption, String orderByColumn) { 
    String whereClause = " WHERE CAPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Caption;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByCaption(String _Caption) { 
    String whereClause = " WHERE CAPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Caption;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByCategory(String _Category) { 
     return listFileStoreByCategory( _Category, null);     
 } 

  public final FileStoreDTO [] listFileStoreByCategory(String _Category, String orderByColumn) { 
    String whereClause = " WHERE CATEGORY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Category;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByCategory(String _Category) { 
    String whereClause = " WHERE CATEGORY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Category;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByCreatedBy(String _CreatedBy) { 
     return listFileStoreByCreatedBy( _CreatedBy, null);     
 } 

  public final FileStoreDTO [] listFileStoreByCreatedBy(String _CreatedBy, String orderByColumn) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByCreatedBy(String _CreatedBy) { 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByDateCreated(java.util.Date _DateCreated) { 
     return listFileStoreByDateCreated( _DateCreated, null);     
 } 

  public final FileStoreDTO [] listFileStoreByDateCreated(java.util.Date _DateCreated, String orderByColumn) { 
    String whereClause = " WHERE DATE_CREATED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateCreated.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByDateCreated(java.util.Date _DateCreated) { 
    String whereClause = " WHERE DATE_CREATED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateCreated.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByDateModified(java.util.Date _DateModified) { 
     return listFileStoreByDateModified( _DateModified, null);     
 } 

  public final FileStoreDTO [] listFileStoreByDateModified(java.util.Date _DateModified, String orderByColumn) { 
    String whereClause = " WHERE DATE_MODIFIED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateModified.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByDateModified(java.util.Date _DateModified) { 
    String whereClause = " WHERE DATE_MODIFIED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateModified.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByDescription(String _Description) { 
     return listFileStoreByDescription( _Description, null);     
 } 

  public final FileStoreDTO [] listFileStoreByDescription(String _Description, String orderByColumn) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByDescription(String _Description) { 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByDisplayName(String _DisplayName) { 
     return listFileStoreByDisplayName( _DisplayName, null);     
 } 

  public final FileStoreDTO [] listFileStoreByDisplayName(String _DisplayName, String orderByColumn) { 
    String whereClause = " WHERE DISPLAY_NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _DisplayName;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByDisplayName(String _DisplayName) { 
    String whereClause = " WHERE DISPLAY_NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _DisplayName;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByEnabled(int _Enabled) { 
     return listFileStoreByEnabled( _Enabled, null);     
 } 

  public final FileStoreDTO [] listFileStoreByEnabled(int _Enabled, String orderByColumn) { 
    String whereClause = " WHERE ENABLED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Enabled);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByEnabled(int _Enabled) { 
    String whereClause = " WHERE ENABLED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Enabled);   
    types[0] = java.sql.Types.INTEGER;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByFileName(String _FileName) { 
     return listFileStoreByFileName( _FileName, null);     
 } 

  public final FileStoreDTO [] listFileStoreByFileName(String _FileName, String orderByColumn) { 
    String whereClause = " WHERE FILE_NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _FileName;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByFileName(String _FileName) { 
    String whereClause = " WHERE FILE_NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _FileName;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByFileSize(int _FileSize) { 
     return listFileStoreByFileSize( _FileSize, null);     
 } 

  public final FileStoreDTO [] listFileStoreByFileSize(int _FileSize, String orderByColumn) { 
    String whereClause = " WHERE FILE_SIZE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_FileSize);   
    types[0] = java.sql.Types.INTEGER;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByFileSize(int _FileSize) { 
    String whereClause = " WHERE FILE_SIZE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_FileSize);   
    types[0] = java.sql.Types.INTEGER;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByFormat(String _Format) { 
     return listFileStoreByFormat( _Format, null);     
 } 

  public final FileStoreDTO [] listFileStoreByFormat(String _Format, String orderByColumn) { 
    String whereClause = " WHERE FORMAT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Format;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByFormat(String _Format) { 
    String whereClause = " WHERE FORMAT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Format;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreById(long _Id) { 
     return listFileStoreById( _Id, null);     
 } 

  public final FileStoreDTO [] listFileStoreById(long _Id, String orderByColumn) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreById(long _Id) { 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByModifiedBy(String _ModifiedBy) { 
     return listFileStoreByModifiedBy( _ModifiedBy, null);     
 } 

  public final FileStoreDTO [] listFileStoreByModifiedBy(String _ModifiedBy, String orderByColumn) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByModifiedBy(String _ModifiedBy) { 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByType(String _Type) { 
     return listFileStoreByType( _Type, null);     
 } 

  public final FileStoreDTO [] listFileStoreByType(String _Type, String orderByColumn) { 
    String whereClause = " WHERE TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Type;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByType(String _Type) { 
    String whereClause = " WHERE TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Type;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 




  public final FileStoreDTO [] listFileStoreByUri(String _Uri) { 
     return listFileStoreByUri( _Uri, null);     
 } 

  public final FileStoreDTO [] listFileStoreByUri(String _Uri, String orderByColumn) { 
    String whereClause = " WHERE URI = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Uri;   
    types[0] = java.sql.Types.VARCHAR;   
    String order = "";   
    if (orderByColumn != null && orderByColumn.trim().length() > 0) {  
        order = " ORDER BY " + orderByColumn; 
    } 
     return listFileStore(whereClause, objs, types, order); 
 } 



  public final int countFileStoreByUri(String _Uri) { 
    String whereClause = " WHERE URI = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Uri;   
    types[0] = java.sql.Types.VARCHAR;   
    return countFileStore(whereClause, objs, types); 
 } 









  public final FileStoreDTO createFileStore(FileStoreDTO _FileStore)
    throws FileStoreCreateException {  
         if (logger.isEnabledFor(Level.TRACE)) {
             logger.trace(">> createFileStore()");
         }  
             PreparedStatement stmt = null;   
             Connection con = openConnection();
         try { 
                 int i = 1;
                 stmt = con.prepareStatement(getInsertSQL(), Statement.RETURN_GENERATED_KEYS);
                  stmt.setString(i++, _FileStore.getCaption()); 
                  stmt.setString(i++, _FileStore.getCategory()); 
                  stmt.setString(i++, _FileStore.getCreatedBy()); 
                  if (_FileStore.getDateCreated() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_FileStore.getDateCreated().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  if (_FileStore.getDateModified() != null) { 
                     stmt.setTimestamp(i++, new java.sql.Timestamp(_FileStore.getDateModified().getTime()) ); 
                  } else {
                     stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
                  } 
                  stmt.setString(i++, _FileStore.getDescription()); 
                  stmt.setString(i++, _FileStore.getDisplayName()); 
                  stmt.setInt(i++, _FileStore.getEnabled()); 
                  stmt.setString(i++, _FileStore.getFileName()); 
                  stmt.setInt(i++, _FileStore.getFileSize()); 
                  stmt.setString(i++, _FileStore.getFormat()); 
                  stmt.setString(i++, _FileStore.getModifiedBy()); 
                  stmt.setString(i++, _FileStore.getType()); 
                  stmt.setString(i++, _FileStore.getUri()); 
                 stmt.executeUpdate();     
                  int generatedId = -1;      
                  ResultSet rs = stmt.getGeneratedKeys();      
                  while (rs.next()) {      
                     generatedId = (int) rs.getLong(1);      
                  }       
                  rs.close();      
                  _FileStore.setId(generatedId);     
         } catch (SQLException e) {     
             logSQLException(e);     
             if (isSQLConstraintViolated(e)) {     
                 throw new FileStoreCreateException(e.getMessage(), e);     
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
             logger.trace("<< createFileStore()");     
         }     
         return _FileStore;     
     }       

  public final void updateFileStore(FileStoreDTO _FileStore) 
    throws FileStoreUpdateException, FileStoreFinderException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> updateFileStore()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getUpdateSQL());         
                  stmt.setString(i++,  _FileStore.getCaption() ); 
                  stmt.setString(i++,  _FileStore.getCategory() ); 
                  stmt.setString(i++,  _FileStore.getCreatedBy() ); 
             if (_FileStore.getDateCreated() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_FileStore.getDateCreated().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
             if (_FileStore.getDateModified() != null) { 
                  stmt.setTimestamp(i++,  new java.sql.Timestamp(_FileStore.getDateModified().getTime()) ); 
             } else { 
                  stmt.setNull(i++, java.sql.Types.TIMESTAMP); 
             }
                  stmt.setString(i++,  _FileStore.getDescription() ); 
                  stmt.setString(i++,  _FileStore.getDisplayName() ); 
                  stmt.setInt(i++,  _FileStore.getEnabled() ); 
                  stmt.setString(i++,  _FileStore.getFileName() ); 
                  stmt.setInt(i++,  _FileStore.getFileSize() ); 
                  stmt.setString(i++,  _FileStore.getFormat() ); 
                  stmt.setString(i++,  _FileStore.getModifiedBy() ); 
                  stmt.setString(i++,  _FileStore.getType() ); 
                  stmt.setString(i++,  _FileStore.getUri() ); 
  /*** now set the criteria, PK values ***/ 
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new FileStoreFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new FileStoreUpdateException("");       
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
           logger.trace("<< updateFileStore()");         
       }        
   }        

  public final void deleteFileStore(FileStoreDTO _FileStore) 
    throws FileStoreFinderException, FileStoreDeleteException  {      
    if (logger.isEnabledFor(Level.TRACE)) {         
       logger.trace(">> deleteFileStore()");         
    }       
    PreparedStatement stmt = null;      
    Connection con = openConnection();      
       try {        
           int i = 1;       
           stmt = con.prepareStatement(getDeleteSQL());         
           int count = stmt.executeUpdate();        
           if (count == 0) {        
               throw new FileStoreFinderException("");       
           }        
       } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new FileStoreDeleteException("");       
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
           logger.trace("<< deleteFileStore()");         
       }        
   }        

  public final void deleteFileStoreWhere(String whereClause, Object[] whereParams, int[] paramTypes)   
    throws FileStoreFinderException, FileStoreDeleteException  {      
   if (logger.isEnabledFor(Level.TRACE)) {     
       logger.trace(">> listFileStore()");  
   }   
   FileStoreDTO[] _FileStore = null;  
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
               throw new FileStoreFinderException("");       
           }        
   } catch (SQLException e) {       
           logSQLException(e);      
           if (isSQLConstraintViolated(e)) {        
               throw new FileStoreDeleteException(e.getMessage(), e);      
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
       logger.trace("<< deleteFileStore()");         
   }        
   }        

  public final void deleteFileStoreByCaption(String _Caption) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE CAPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Caption;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByCategory(String _Category) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE CATEGORY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Category;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByCreatedBy(String _CreatedBy) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE CREATED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _CreatedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByDateCreated(java.util.Date _DateCreated) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE DATE_CREATED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateCreated.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByDateModified(java.util.Date _DateModified) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE DATE_MODIFIED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.sql.Timestamp(_DateModified.getTime());   
    types[0] = java.sql.Types.TIMESTAMP;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByDescription(String _Description) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE DESCRIPTION = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Description;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByDisplayName(String _DisplayName) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE DISPLAY_NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _DisplayName;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByEnabled(int _Enabled) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE ENABLED = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_Enabled);   
    types[0] = java.sql.Types.INTEGER;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByFileName(String _FileName) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE FILE_NAME = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _FileName;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByFileSize(int _FileSize) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE FILE_SIZE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Integer(_FileSize);   
    types[0] = java.sql.Types.INTEGER;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByFormat(String _Format) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE FORMAT = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Format;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreById(long _Id) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE ID = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = new java.lang.Long(_Id);   
    types[0] = java.sql.Types.BIGINT;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByModifiedBy(String _ModifiedBy) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE MODIFIED_BY = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _ModifiedBy;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByType(String _Type) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE TYPE = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Type;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




  public final void deleteFileStoreByUri(String _Uri) throws FileStoreFinderException, FileStoreDeleteException{ 
    String whereClause = " WHERE URI = ? ";       
    Object[] objs = new Object[1];   
    int[] types = new int[1];   
    objs[0] = _Uri;   
    types[0] = java.sql.Types.VARCHAR;   
    deleteFileStoreWhere(whereClause, objs, types); 
 } 




public final FileStoreDTO extractFileStore(ResultSet rs)       
    throws SQLException {  
         FileStoreDTO obj = new FileStoreDTO();          
         int i = 1;      
         obj.setCaption( rs.getString(i++) );       
         obj.setCategory( rs.getString(i++) );       
         obj.setCreatedBy( rs.getString(i++) );       
         obj.setDateCreated( rs.getTimestamp(i++) );       
         obj.setDateModified( rs.getTimestamp(i++) );       
         obj.setDescription( rs.getString(i++) );       
         obj.setDisplayName( rs.getString(i++) );       
         obj.setEnabled( rs.getInt(i++) );       
         obj.setFileName( rs.getString(i++) );       
         obj.setFileSize( rs.getInt(i++) );       
         obj.setFormat( rs.getString(i++) );       
         obj.setId( rs.getLong(i++) );       
         obj.setModifiedBy( rs.getString(i++) );       
         obj.setType( rs.getString(i++) );       
         obj.setUri( rs.getString(i++) );       
 return obj;  } 


 /*************** end *****************/
}
