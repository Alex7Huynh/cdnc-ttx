package util;

import org.apache.log4j.Logger;

public aspect LogAspect {
	pointcut Search(String aKeyword) : call( * SearchEngine.SearchGoogle(String, int, int)) && args(aKeyword, int, int);
	before(String aKeyword) : Search(aKeyword) {
		Logger logger = Logger.getLogger("Logging.Search");
		logger.fatal(aKeyword);
	}

}
