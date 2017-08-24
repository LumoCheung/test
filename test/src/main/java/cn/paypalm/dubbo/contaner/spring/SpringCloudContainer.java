package cn.paypalm.dubbo.contaner.spring;

import com.alibaba.dubbo.common.logger.Logger;
import com.alibaba.dubbo.common.logger.LoggerFactory;
import com.alibaba.dubbo.common.utils.ConfigUtils;
import com.alibaba.dubbo.container.Container;

import cn.paypalm.util.SystemConfigProperties;
import cn.paypalm.util.UContext;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * dubbo 扩展容器的方式解决beans profile 根据环境配置不同的bean的做法
 * 可以通过main传值或是dubbo.properties中的dubbo.container项配置
 * @see com.alibaba.dubbo.container.spring.SpringContainer
 * @author zlc
 */
public class SpringCloudContainer implements Container {
    private static final Logger logger = LoggerFactory.getLogger(SpringCloudContainer.class);

    public static final String SPRING_CONFIG = "dubbo.spring.config";

    public static final String DEFAULT_SPRING_CONFIG = "classpath*:META-INF/spring/*.xml";

    static ClassPathXmlApplicationContext context;

    public static ClassPathXmlApplicationContext getContext() {
        return context;
    }

    public void start() {
        String configPath = ConfigUtils.getProperty(SPRING_CONFIG);
        if (configPath == null || configPath.length() == 0) {
            configPath = DEFAULT_SPRING_CONFIG;
        }
        context = new ClassPathXmlApplicationContext(new String[]{"classpath*:META-INF/spring/mybatis.xml"});
        logger.info("redis配置为："+SystemConfigProperties.getValueAt("redis.connection","single"));
        context.getEnvironment().setActiveProfiles(SystemConfigProperties.getValueAt("redis.connection","single"));
        context = new ClassPathXmlApplicationContext(configPath.split("[,\\s]+"),true,context);
        context.refresh();

        context.start();
        UContext.setContext(context);
    }

    public void stop() {
        try {
            if (context != null) {
                context.stop();
                context.close();
                context = null;
            }
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
        }
    }


}
