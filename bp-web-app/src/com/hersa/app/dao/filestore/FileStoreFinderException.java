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
public final class FileStoreFinderException extends Exception {
  public FileStoreFinderException (String message) { 
      super(message); 
  } 
  public FileStoreFinderException(Exception e) { 
      super(e); 
  } 
  public  FileStoreFinderException(String msg, Exception e) {  
      super(msg, e);  
  }  
} 
