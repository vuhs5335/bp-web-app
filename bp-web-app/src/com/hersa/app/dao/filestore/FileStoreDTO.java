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
import java.io.Serializable;

import com.hersa.app.AbstractD;
public class FileStoreDTO extends AbstractD  implements Serializable {

private static final long serialVersionUID = 1L;
private String _Caption;

private String _Category;

private String _CreatedBy;

private java.util.Date _DateCreated;

private java.util.Date _DateModified;

private String _Description;

private String _DisplayName;

private int _Enabled;

private String _FileName;

private int _FileSize;

private String _Format;

private long _Id;

private String _ModifiedBy;

private String _Type;

private String _Uri;



public String getCaption() { 
  return _Caption; 
}
public void setCaption(String value) { 
  _Caption = value; 
}

public String getCategory() { 
  return _Category; 
}
public void setCategory(String value) { 
  _Category = value; 
}

public String getCreatedBy() { 
  return _CreatedBy; 
}
public void setCreatedBy(String value) { 
  _CreatedBy = value; 
}

public java.util.Date getDateCreated() { 
  return _DateCreated; 
}
public void setDateCreated(java.util.Date value) { 
  _DateCreated = value; 
}

public java.util.Date getDateModified() { 
  return _DateModified; 
}
public void setDateModified(java.util.Date value) { 
  _DateModified = value; 
}

public String getDescription() { 
  return _Description; 
}
public void setDescription(String value) { 
  _Description = value; 
}

public String getDisplayName() { 
  return _DisplayName; 
}
public void setDisplayName(String value) { 
  _DisplayName = value; 
}

public int getEnabled() { 
  return _Enabled; 
}
public void setEnabled(int value) { 
  _Enabled = value; 
}

public String getFileName() { 
  return _FileName; 
}
public void setFileName(String value) { 
  _FileName = value; 
}

public int getFileSize() { 
  return _FileSize; 
}
public void setFileSize(int value) { 
  _FileSize = value; 
}

public String getFormat() { 
  return _Format; 
}
public void setFormat(String value) { 
  _Format = value; 
}

public long getId() { 
  return _Id; 
}
public void setId(long value) { 
  _Id = value; 
}

public String getModifiedBy() { 
  return _ModifiedBy; 
}
public void setModifiedBy(String value) { 
  _ModifiedBy = value; 
}

public String getType() { 
  return _Type; 
}
public void setType(String value) { 
  _Type = value; 
}

public String getUri() { 
  return _Uri; 
}
public void setUri(String value) { 
  _Uri = value; 
}

public String getInfo() {
 StringBuffer buf = new StringBuffer();
  buf.append("Caption=" + _Caption + "| "); 
  buf.append("Category=" + _Category + "| "); 
  buf.append("CreatedBy=" + _CreatedBy + "| "); 
  buf.append("DateCreated=" + _DateCreated + "| "); 
  buf.append("DateModified=" + _DateModified + "| "); 
  buf.append("Description=" + _Description + "| "); 
  buf.append("DisplayName=" + _DisplayName + "| "); 
  buf.append("Enabled=" + _Enabled + "| "); 
  buf.append("FileName=" + _FileName + "| "); 
  buf.append("FileSize=" + _FileSize + "| "); 
  buf.append("Format=" + _Format + "| "); 
  buf.append("Id=" + _Id + "| "); 
  buf.append("ModifiedBy=" + _ModifiedBy + "| "); 
  buf.append("Type=" + _Type + "| "); 
  buf.append("Uri=" + _Uri + "| "); 
  return buf.toString();  }}
/***************** end *********************/ 
