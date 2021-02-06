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
  import java.math.BigDecimal;     
    public final class FileStoreDAOFactory {         
     //private static final FileStoreDAO dao = new FileStoreDAOImpl();      
     private FileStoreDAOFactory() {      
     }      
     public final static FileStoreDAO getDAO() {      
             return new FileStoreDAOImpl();       
         }       
     public final static FileStoreDAO getDAO(boolean external) {      
         return (external ? new FileStoreDAOImplXA(): new FileStoreDAOImpl());      
     }                 
      public final static FileStoreDAO getDAO(boolean external, Connection nestedConnection) {      
          FileStoreDAO dao = (external ? new FileStoreDAOImplXA() : new FileStoreDAOImpl());      
          dao.setConnection(nestedConnection);      
          return dao;      
      }      
      public final static FileStoreDAO getDAO(Connection nestedConnection) {      
          return getDAO(false, nestedConnection);      
      }               
    }         
