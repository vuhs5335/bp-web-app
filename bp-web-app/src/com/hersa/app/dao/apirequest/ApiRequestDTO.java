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
import java.io.Serializable;

import com.hersa.app.AbstractD;
public class ApiRequestDTO extends AbstractD  implements Serializable {

private static final long serialVersionUID = 1L;
private long _Id;

private String _IpAddress;

private java.util.Date _ReqDate;

private String _Resource;

private String _Response;



public long getId() { 
  return _Id; 
}
public void setId(long value) { 
  _Id = value; 
}

public String getIpAddress() { 
  return _IpAddress; 
}
public void setIpAddress(String value) { 
  _IpAddress = value; 
}

public java.util.Date getReqDate() { 
  return _ReqDate; 
}
public void setReqDate(java.util.Date value) { 
  _ReqDate = value; 
}

public String getResource() { 
  return _Resource; 
}
public void setResource(String value) { 
  _Resource = value; 
}

public String getResponse() { 
  return _Response; 
}
public void setResponse(String value) { 
  _Response = value; 
}

public String getInfo() {
 StringBuffer buf = new StringBuffer();
  buf.append("Id=" + _Id + "| "); 
  buf.append("IpAddress=" + _IpAddress + "| "); 
  buf.append("ReqDate=" + _ReqDate + "| "); 
  buf.append("Resource=" + _Resource + "| "); 
  buf.append("Response=" + _Response + "| "); 
  return buf.toString();  }}
/***************** end *********************/ 
