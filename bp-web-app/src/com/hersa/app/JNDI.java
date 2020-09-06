  package com.hersa.app;
  public final class JNDI {   
      public static final String XA_DATASOURCE = "java:comp/env/jdbc/XADataSource";
      public static final String NON_XA_DATASOURCE = "java:comp/env/jdbc/NonXADataSource";
      public static final String BPMONITOR = "java:comp/env/jdbc/BPMONITOR";
      private JNDI() {
      }
  } 
