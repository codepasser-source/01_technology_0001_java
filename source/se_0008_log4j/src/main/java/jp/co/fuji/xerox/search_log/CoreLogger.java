package jp.co.fuji.xerox.search_log;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class CoreLogger {

	/**
	 * 日志类型:system log
	 */
	public static final String LOGGER_TYPE_SYSTEM = "SystemLogger";

	/**
	 * 日志类型:access log
	 */
	public static final String LOGGER_TYPE_ACCESS = "AccessLogger";

	/**
	 * 日志级别选项: FATAL
	 */
	public static final String LOGGER_LEVEL_FATAL = "FATAL";

	/**
	 * 日志级别选项: ERROR
	 */
	public static final String LOGGER_LEVEL_ERROR = "ERROR";

	/**
	 * 日志级别选项: WARN
	 */
	public static final String LOGGER_LEVEL_WARN = "WARN";

	/**
	 * 日志级别选项: INFO
	 */
	public static final String LOGGER_LEVEL_INFO = "INFO";

	/**
	 * 日志级别选项: DEBUG
	 */
	public static final String LOGGER_LEVEL_DEBUG = "DEBUG";

	/**
	 * 日志关闭: OFF
	 */
	public static final String LOGGER_LEVEL_OFF = "OFF";

	/**
	 * 日志配置文件
	 */
	private static final String LOG4J_PROPERTIES = "log4j.properties";

	/**
	 * 系统日志配置引用名称
	 */
	private static final String APPENDER_NAME_SYSTEM = "SYSTEM";

	/**
	 * 访问日志配置引用名称
	 */
	private static final String APPENDER_NAME_ACCESS = "ACCESS";

	/**
	 * 系统日志文件路径
	 */
	private static final String LOG_PATH_SYSTEM = "logs/system-log/";

	/**
	 * 访问日志文件路径
	 */
	private static final String LOG_PATH_ACCESS = "logs/access-log/";

	/**
	 * 系统日志文件名前缀
	 */
	private static final String LOG_PREFIX_SYSTEM = "mw_web_system_";

	/**
	 * 访问日志文件名前缀
	 */
	private static final String LOG_PREFIX_ACCESS = "mw_web_access_";

	/**
	 * 日志文件名时间格式
	 */
	private static final String LOG_TIME_FORMAT = "yyyyMMdd";

	/**
	 * 日志文件扩展名
	 */
	private static final String LOG_EXTENSION = ".log";

	/**
	 * 系统日志对象
	 */
	private static Logger systemLogger = null;

	/**
	 * 访问日志对象
	 */
	private static Logger accessLogger = null;

	private CoreLogger() {
		// default constructor disable
	}

	public synchronized void writeLog(String loggerType, String level,
			String message) {
		systemLogger.fatal("systemLogger fatal 系统日志");
		systemLogger.error("systemLogger error 系统日志");
		systemLogger.warn("systemLogger warn 系统日志");
		systemLogger.info("systemLogger info 系统日志");
		systemLogger.debug("systemLogger debug 系统日志");

		accessLogger.fatal("accessLogger fatal ログ");
		accessLogger.error("accessLogger error ログ");
		accessLogger.warn("accessLogger warn ログ");
		accessLogger.info("accessLogger info ログ");
		accessLogger.debug("accessLogger debug ログ");
	}

	public static CoreLogger getInstance() {
		initialization();
		return new CoreLogger();
	}

	private static void initialization() {
		Properties properties = null;
		try {
			properties = loadProperties();
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		PropertyConfigurator.configure(properties);
		systemLogger = Logger.getLogger(LOGGER_TYPE_SYSTEM);
		accessLogger = Logger.getLogger(LOGGER_TYPE_ACCESS);

		Appender systemAppender = systemLogger
				.getAppender(APPENDER_NAME_SYSTEM);
		if (systemAppender instanceof FileAppender) {
			FileAppender fileAppender = (FileAppender) systemAppender;
			fileAppender.setFile(LOG_PATH_SYSTEM + LOG_PREFIX_SYSTEM
					+ currentTimeStr() + LOG_EXTENSION);
			fileAppender.activateOptions();
		}
		Appender accessAppender = accessLogger
				.getAppender(APPENDER_NAME_ACCESS);
		if (accessAppender instanceof FileAppender) {
			FileAppender fileAppender = (FileAppender) accessAppender;
			fileAppender.setFile(LOG_PATH_ACCESS + LOG_PREFIX_ACCESS
					+ currentTimeStr() + LOG_EXTENSION);
			fileAppender.activateOptions();
		}
	}

	private static Properties loadProperties() throws IOException {
		ClassLoader classLoader = CoreLogger.class.getClassLoader();
		URL url = classLoader.getResource(LOG4J_PROPERTIES);
		Properties properties = new Properties();
		properties.load(url.openStream());
		return properties;
	}

	private static String currentTimeStr() {
		Date date = new Date();
		SimpleDateFormat sdFormat = new SimpleDateFormat(LOG_TIME_FORMAT);
		String dateStr = sdFormat.format(date);
		return dateStr;
	}
}
