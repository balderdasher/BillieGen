import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

/**
 * 测试log4j2日志使用
 *
 * @author CodePorter
 * @date 2017-08-31
 */
public class Log4j2Test {
    /* DEBUG信息打印到控制台 */
    private static final Logger logger = LogManager.getLogger();
    /* 需要输出到文件的INFO */
    private static final Logger fileLogger = LogManager.getLogger("billiegen");

    @Test
    public void testLog4j2() {
        logRecord(20);
    }

    private static void logRecord(int times) {
        logger.debug("test log print.{},{}", "fuck", "you");
        for (int i = 0; i < times; i++) {
            fileLogger.info("this is a test log. " + (i + 1));
        }
    }
}
