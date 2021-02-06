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

import com.hersa.app.AbstractDA;      
  public interface FileStoreDAO extends AbstractDA {

     public void open();
     public void close();
     public void setConnection(Connection con); 
     public FileStoreDTO createFileStore( FileStoreDTO obj )throws FileStoreCreateException ; 
     public void updateFileStore( FileStoreDTO obj ) throws FileStoreUpdateException, FileStoreFinderException ;
     public void deleteFileStore( FileStoreDTO obj ) throws FileStoreDeleteException, FileStoreFinderException; 
     public void deleteFileStoreWhere(String whereClause, Object[] whereParams, int[] paramTypes) throws FileStoreDeleteException, FileStoreFinderException; 
     public FileStoreDTO[] listAllFileStore();
     public FileStoreDTO[] listAllFileStore(String orderByColumn);
     public FileStoreDTO [] listFileStore(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause); 
     public FileStoreDTO [] customFileStore(String completeSQL); 
     public int countFileStore(String whereClause, Object[] whereParams, int[] paramTypes); 
     public FileStoreDTO [] searchPageableFileStore(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause, int startIndex, int pageSize); 



public FileStoreDTO [] listFileStoreByCaption(String _Caption); 
public FileStoreDTO [] listFileStoreByCaption(String _Caption, String orderByColumn); 
public int countFileStoreByCaption(String _Caption); 

  public void deleteFileStoreByCaption(String _Caption) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByCategory(String _Category); 
public FileStoreDTO [] listFileStoreByCategory(String _Category, String orderByColumn); 
public int countFileStoreByCategory(String _Category); 

  public void deleteFileStoreByCategory(String _Category) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByCreatedBy(String _CreatedBy); 
public FileStoreDTO [] listFileStoreByCreatedBy(String _CreatedBy, String orderByColumn); 
public int countFileStoreByCreatedBy(String _CreatedBy); 

  public void deleteFileStoreByCreatedBy(String _CreatedBy) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByDateCreated(java.util.Date _DateCreated); 
public FileStoreDTO [] listFileStoreByDateCreated(java.util.Date _DateCreated, String orderByColumn); 
public int countFileStoreByDateCreated(java.util.Date _DateCreated); 

  public void deleteFileStoreByDateCreated(java.util.Date _DateCreated) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByDateModified(java.util.Date _DateModified); 
public FileStoreDTO [] listFileStoreByDateModified(java.util.Date _DateModified, String orderByColumn); 
public int countFileStoreByDateModified(java.util.Date _DateModified); 

  public void deleteFileStoreByDateModified(java.util.Date _DateModified) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByDescription(String _Description); 
public FileStoreDTO [] listFileStoreByDescription(String _Description, String orderByColumn); 
public int countFileStoreByDescription(String _Description); 

  public void deleteFileStoreByDescription(String _Description) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByDisplayName(String _DisplayName); 
public FileStoreDTO [] listFileStoreByDisplayName(String _DisplayName, String orderByColumn); 
public int countFileStoreByDisplayName(String _DisplayName); 

  public void deleteFileStoreByDisplayName(String _DisplayName) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByEnabled(int _Enabled); 
public FileStoreDTO [] listFileStoreByEnabled(int _Enabled, String orderByColumn); 
public int countFileStoreByEnabled(int _Enabled); 

  public void deleteFileStoreByEnabled(int _Enabled) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByFileName(String _FileName); 
public FileStoreDTO [] listFileStoreByFileName(String _FileName, String orderByColumn); 
public int countFileStoreByFileName(String _FileName); 

  public void deleteFileStoreByFileName(String _FileName) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByFileSize(int _FileSize); 
public FileStoreDTO [] listFileStoreByFileSize(int _FileSize, String orderByColumn); 
public int countFileStoreByFileSize(int _FileSize); 

  public void deleteFileStoreByFileSize(int _FileSize) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByFormat(String _Format); 
public FileStoreDTO [] listFileStoreByFormat(String _Format, String orderByColumn); 
public int countFileStoreByFormat(String _Format); 

  public void deleteFileStoreByFormat(String _Format) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreById(long _Id); 
public FileStoreDTO [] listFileStoreById(long _Id, String orderByColumn); 
public int countFileStoreById(long _Id); 

  public void deleteFileStoreById(long _Id) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByModifiedBy(String _ModifiedBy); 
public FileStoreDTO [] listFileStoreByModifiedBy(String _ModifiedBy, String orderByColumn); 
public int countFileStoreByModifiedBy(String _ModifiedBy); 

  public void deleteFileStoreByModifiedBy(String _ModifiedBy) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByType(String _Type); 
public FileStoreDTO [] listFileStoreByType(String _Type, String orderByColumn); 
public int countFileStoreByType(String _Type); 

  public void deleteFileStoreByType(String _Type) throws FileStoreFinderException, FileStoreDeleteException; 




public FileStoreDTO [] listFileStoreByUri(String _Uri); 
public FileStoreDTO [] listFileStoreByUri(String _Uri, String orderByColumn); 
public int countFileStoreByUri(String _Uri); 

  public void deleteFileStoreByUri(String _Uri) throws FileStoreFinderException, FileStoreDeleteException; 






 /*************** end *****************/
}
