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
  import java.math.BigDecimal;     
    public final class ApiRequestDAOFactory {         
     //private static final ApiRequestDAO dao = new ApiRequestDAOImpl();      
     private ApiRequestDAOFactory() {      
     }      
     public final static ApiRequestDAO getDAO() {      
             return new ApiRequestDAOImpl();       
         }       
     public final static ApiRequestDAO getDAO(boolean external) {      
         return (external ? new ApiRequestDAOImplXA(): new ApiRequestDAOImpl());      
     }                 
      public final static ApiRequestDAO getDAO(boolean external, Connection nestedConnection) {      
          ApiRequestDAO dao = (external ? new ApiRequestDAOImplXA() : new ApiRequestDAOImpl());      
          dao.setConnection(nestedConnection);      
          return dao;      
      }      
      public final static ApiRequestDAO getDAO(Connection nestedConnection) {      
          return getDAO(false, nestedConnection);      
      }               
    }         
