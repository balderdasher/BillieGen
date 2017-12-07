package com.billiegen.gen;

import com.Application;
import com.billiegen.system.dao.IconDao;
import com.billiegen.system.entity.Icon;
import com.billiegen.utils.file.RegexUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.StopWatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author CodePorter
 * @date 2017-12-06
 */
@Transactional
@Rollback(value = false)
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class InitBusinessData {
    private static final Logger logger = LoggerFactory.getLogger(InitBusinessData.class);

    @Autowired
    private IconDao iconDao;

    /**
     * 初始化bootstrap图标数据
     */
    @Test
    public void initIconDatas() {
        StopWatch timer = new StopWatch();
        timer.start();
        String regex = "^\\.(?<name>.*?):before\\s*\\{";
        List<String> filePaths = new ArrayList<>();
        filePaths.add("C:/xinhuanet/projects/billiegen/metronic/assets/global/plugins/font-awesome/css/font-awesome.css");
        filePaths.add("C:/xinhuanet/projects/billiegen/metronic/assets/global/plugins/simple-line-icons/simple-line-icons.css");
        filePaths.add("C:/xinhuanet/projects/billiegen/metronic/assets/global/plugins/bootstrap/css/bootstrap.css");

        filePaths.forEach(file -> {
            List<String> icons = RegexUtil.getListFromFile(file, regex, 1);
            String first = icons.get(0);
            String classPrefix = first.substring(0, first.indexOf("-"));
            classPrefix = classPrefix.equals("icon") ? "" : classPrefix;
            String sourceType = classPrefix.equals("") ? "SimpleLine" : classPrefix.equals("glyphicon") ? "Glyphicons" : "FontAwesome";
            logger.info("{} 共有{}个图标,class前缀为{}", sourceType, icons.size(), classPrefix);

            String finalClassPrefix = classPrefix;
            icons.forEach(icon -> {
                Icon old = iconDao.findByDisplayName(icon);
                if (old != null) {
                    logger.info("Icon-{}[{}] is exist...", sourceType, icon);
                } else {
                    Icon bootstrapIcon = new Icon();
                    bootstrapIcon.setDisplayName(icon);
                    bootstrapIcon.setClassName(StringUtils.isEmpty(finalClassPrefix) ? icon : finalClassPrefix + " " + icon);
                    bootstrapIcon.setSourceType(sourceType);
                    logger.info("Icon-{} is not exist,create new one...", bootstrapIcon.toString());
                    iconDao.save(bootstrapIcon);
                }
            });
        });
        timer.stop();
        logger.info("Bootstrap icon data init OK, It takes {} seconds...", timer.getTime(TimeUnit.SECONDS) + "");
    }
}
