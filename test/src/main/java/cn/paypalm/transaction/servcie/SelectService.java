package cn.paypalm.transaction.servcie;

import cn.paypalm.transaction.bean.ActionBean;
import cn.paypalm.transaction.dao.IActionDao;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class SelectService {

    @Resource
    private IActionDao actionDao;

    @Transactional(propagation = Propagation.REQUIRES_NEW,rollbackFor = Exception.class)
    //1.当使用PROPAGATION_NESTED时，底层的数据源必须基于JDBC 3.0，并且实现者需要支持保存点事务机制。
    //如果不支持该特性，会默认无事务运行
    // 一般都不支持该特性，所以不要使用。
    public ActionBean select(Long orderNo){
//        return actionDao.selectActionBean(orderNo);
        return actionDao.selectMysql(orderNo);
    }

}
