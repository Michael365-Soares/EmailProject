package br.com.emailproject.util;

import org.apache.log4j.Logger;

public class LogUtil {
	
   private LogUtil() {}
   
   public static Logger getLogger(Class<?> classe) {
	   return Logger.getLogger(classe);
   }
   
}
