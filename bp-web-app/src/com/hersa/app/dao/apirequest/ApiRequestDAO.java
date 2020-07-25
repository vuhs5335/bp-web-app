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

import com.hersa.app.AbstractDA;      
  public interface ApiRequestDAO extends AbstractDA {

     public void open();
     public void close();
     public void setConnection(Connection con); 
     public ApiRequestDTO createApiRequest( ApiRequestDTO obj )throws ApiRequestCreateException ; 
     public void updateApiRequest( ApiRequestDTO obj ) throws ApiRequestUpdateException, ApiRequestFinderException ;
     public void deleteApiRequest( ApiRequestDTO obj ) throws ApiRequestDeleteException, ApiRequestFinderException; 
     public void deleteApiRequestWhere(String whereClause, Object[] whereParams, int[] paramTypes) throws ApiRequestDeleteException, ApiRequestFinderException; 
     public ApiRequestDTO[] listAllApiRequest();
     public ApiRequestDTO[] listAllApiRequest(String orderByColumn);
     public ApiRequestDTO [] listApiRequest(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause); 
     public ApiRequestDTO [] customApiRequest(String completeSQL); 
     public int countApiRequest(String whereClause, Object[] whereParams, int[] paramTypes); 
     public ApiRequestDTO [] searchPageableApiRequest(String whereClause, Object[] whereParams, int[] paramTypes, String orderClause, int startIndex, int pageSize); 



public ApiRequestDTO [] listApiRequestById(long _Id); 
public ApiRequestDTO [] listApiRequestById(long _Id, String orderByColumn); 
public int countApiRequestById(long _Id); 

  public void deleteApiRequestById(long _Id) throws ApiRequestFinderException, ApiRequestDeleteException; 




public ApiRequestDTO [] listApiRequestByIpAddress(String _IpAddress); 
public ApiRequestDTO [] listApiRequestByIpAddress(String _IpAddress, String orderByColumn); 
public int countApiRequestByIpAddress(String _IpAddress); 

  public void deleteApiRequestByIpAddress(String _IpAddress) throws ApiRequestFinderException, ApiRequestDeleteException; 




public ApiRequestDTO [] listApiRequestByReqDate(java.util.Date _ReqDate); 
public ApiRequestDTO [] listApiRequestByReqDate(java.util.Date _ReqDate, String orderByColumn); 
public int countApiRequestByReqDate(java.util.Date _ReqDate); 

  public void deleteApiRequestByReqDate(java.util.Date _ReqDate) throws ApiRequestFinderException, ApiRequestDeleteException; 




public ApiRequestDTO [] listApiRequestByResource(String _Resource); 
public ApiRequestDTO [] listApiRequestByResource(String _Resource, String orderByColumn); 
public int countApiRequestByResource(String _Resource); 

  public void deleteApiRequestByResource(String _Resource) throws ApiRequestFinderException, ApiRequestDeleteException; 




public ApiRequestDTO [] listApiRequestByResponse(String _Response); 
public ApiRequestDTO [] listApiRequestByResponse(String _Response, String orderByColumn); 
public int countApiRequestByResponse(String _Response); 

  public void deleteApiRequestByResponse(String _Response) throws ApiRequestFinderException, ApiRequestDeleteException; 






 /*************** end *****************/
}
