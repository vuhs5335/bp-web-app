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
import java.sql.SQLException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.hersa.app.DAOException;
import com.hersa.app.DefaultConnectionProvider;  
              class ApiRequestDAOImplXA extends ApiRequestDAOImpl {                                
                  private static final Logger logger =                                             
                      Logger.getLogger(ApiRequestDAOImplXA.class);                           
                  private Connection conXA;                                                          
                  ApiRequestDAOImplXA() {                                                        
                      super();                                                                 
                      factory = new DefaultConnectionProvider(ApiRequestJNDI.XA_DATASOURCE);  
                  }                                                                                
                  public void open() {                                                             
                    if (connectionSetByCaller) conXA = this.con; else                              
                      try {                                                                    
                            this.conXA = factory.openConnection();   
                      } catch (Exception e) {                                                  
                          if (logger.isEnabledFor(Level.ERROR)) {                          
                              logger.error(e);                                         
                          }                                                                
                         try { 	throw new DAOException(e.getMessage(), e);  } catch (DAOException e1) { 	e1.printStackTrace(); }                                       
                      }                                                                        
                  }                                                                                
                  public void close() {                                                            
                    if (!this.persistReadOnly) {                          
                      this.readOnly = false;                        
                    }                        
                    if (connectionSetByCaller) {                                                   
                        return; 
                    } 
                    try {                                                            
                       factory.closeConnection(conXA);                              
                    } catch (SQLException e) {                                       
                      logSQLException(e);                                      
                    }                                                                
                  }                                                                                
                  protected void closeConnection(Connection con) {                                 
                  }                                                                                
                  protected Logger getLogger() {                                                   
                      return ApiRequestDAOImplXA.logger;                                     
                  }                                                                                
                  protected Connection openConnection() {                                          
                      return this.conXA;                                                         
                  }                                                                                
              }               
