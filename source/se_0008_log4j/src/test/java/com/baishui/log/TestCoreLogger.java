package com.baishui.log;

import jp.co.fuji.xerox.public_log.CoreLogger;
import junit.framework.TestCase;

public class TestCoreLogger extends TestCase {

	public void testPublicCoreLogger() {
		CoreLogger logger1 = CoreLogger.getInstance();
		logger1.writeLog(CoreLogger.LOGGER_TYPE_SYSTEM, "debug", "系统日志");
	}
}
