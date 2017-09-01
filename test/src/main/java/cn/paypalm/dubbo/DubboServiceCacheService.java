package cn.paypalm.dubbo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;

import cn.paypalm.dubbo.api.TestService;

/**
 * Created by zlc on 2017/5/27.
 */
public class DubboServiceCacheService {
//    private static final Logger log = LoggerFactory.getLogger(DubboServiceCacheService.class);
    Map<String, ReferenceConfig> referenceMap = new HashMap<String, ReferenceConfig>();
//    Map<GatewayServiceEnum, Class> classMap = new HashMap<GatewayServiceEnum, Class>();
	static ReferenceConfigCache cache=ReferenceConfigCache.getCache();

    @PostConstruct
    public void init() {
        /*classMap.put(GatewayServiceEnum.COLLECTION, BankCollectService.class);
        classMap.put(GatewayServiceEnum.APPLY, BankApplyService.class);
        classMap.put(GatewayServiceEnum.SIGN, BankSignService.class);
        classMap.put(GatewayServiceEnum.QUERY_COLLECT, BankQueryService.class);
        classMap.put(GatewayServiceEnum.QUERY_PAY, BankQueryService.class);
        classMap.put(GatewayServiceEnum.REFUND, BankRefundService.class);
        classMap.put(GatewayServiceEnum.REVOKE, BankRevokeService.class);
        classMap.put(GatewayServiceEnum.REVERSE, BankReverseService.class);
        classMap.put(GatewayServiceEnum.PAY, BankPayService.class);
        classMap.put(GatewayServiceEnum.VERIFY, BankVerifyService.class);
        classMap.put(GatewayServiceEnum.CLOSE,BankCloseService.class);*/
    }

    /**
     * 获取服务
     * @param type
     * @param bankchannel
     * @return 
     * @return
     * @throws SystemException
     */
    /*public <T> T getService(GatewayServiceEnum type, String bankchannel) throws SystemException {

        String key = bankchannel+type;
        ReferenceConfig reference = referenceMap.get(key);
        Class clazz=classMap.get(type);
        if(reference==null){
            log.debugf("引用远程服务初始化，interface[%s]，group[%s]", clazz.getName(), bankchannel);
            // 引用远程服务
            reference = new ReferenceConfig(); // 此实例很重，封装了与注册中心的连接以及与提供者的连接，请自行缓存，否则可能造成内存和连接泄漏
            //reference.setApplication(application);
            //reference.setRegistry(registry); // 多个注册中心可以用setRegistries()
            reference.setInterface(clazz);
            //reference.setVersion("1.0.0");
            reference.setGroup(bankchannel);
            log.debugf("缓存远程服务，key[%s]，reference对象[%s]", key, reference);
            referenceMap.put(key, reference);
        }
        log.debugf("获取远程服务对象，reference对象[%s]", reference);
        Object service = reference.get(); // 注意：此代理对象内部封装了所有通讯细节，对象较重，请缓存复用
        if(service == null) {
            log.warn("通道号:", bankchannel, "，不支持（或未配置）该交易：", type);
            throw new SystemException(RetCodeConstant.RSPCODE_SYS_ERROR);
        }
        return (T)service;
    	
    }*/
    
    /**
     * ReferenceConfig实例很重，封装了与注册中心的连接以及与提供者的连接，需要缓存，
     * 否则重复生成ReferenceConfig可能造成性能问题并且会有内存和连接泄漏。API方式编程时，容易忽略此问题。
     *
     * Dubbo 2.4.0+版本，提供了简单的工具类ReferenceConfigCache用于缓存ReferenceConfig实例。
     * 
     * getService
     * 方法描述: 
     * 逻辑描述: 
     * @return
     * @since Ver 1.00
     */
    public static <T> T getService(){
    	ReferenceConfig reference=new ReferenceConfig();
    	
    	Object service=null;
    	
    	reference.setInterface(TestService.class);
    	reference.setGroup("two");
    	
    	// cache.get方法中会Cache Reference对象，并且调用ReferenceConfig.get方法启动ReferenceConfig
    	// 注意！ Cache会持有ReferenceConfig，不要在外部再调用ReferenceConfig的destroy方法，导致Cache内的ReferenceConfig失效！
    	
    	//ReferenceConfig.get 获取时的实例或比较重
    	service=cache.get(reference);
    	
    	return (T)service;
    }

}
