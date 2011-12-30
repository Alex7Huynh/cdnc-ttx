package bingle.util;

import org.apache.log4j.Logger;

public aspect LogAspect {
	pointcut Search(String aKeyword) : call( * SearchEngine.Search(String, int, int)) && args(aKeyword, int, int);
	before(String aKeyword) : Search(aKeyword) {
		Logger logger = Logger.getLogger("Logging.Search");
		logger.fatal(aKeyword);
	}
	pointcut AllMethod() : call(* bingle..*(..)) && !within(bingle..LogAspect);
	after() throwing(Exception ex) : AllMethod() 
	{
		Logger logger =  Logger.getLogger("Logging.Exception");
		logger.fatal("Exception", ex);
		
	}
	

}
