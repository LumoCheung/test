package cn.paypalm.transaction.servcie;

import cn.paypalm.transaction.bean.ActionBean;
import cn.paypalm.transaction.dao.IActionDao;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * zlc
 */
@Service
public class TransactionService {
    @Resource
    private IActionDao actionDao;
    @Resource
    private SelectService selectService;

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void Update(ActionBean bean){
        ActionBean action = select(bean.getOrderNo());

        if(action==null)
            throw new RuntimeException("操作异常");
        action.setOldIsAgreeMent(action.getIsAgreeMent());
        action.setIsAgreeMent(1);
        actionDao.updateIsAgreeMentByOrderno(action);
        int i=0;
        /**
         * 此处使用其他类的事务，从日志中可以看到，挂起了当前事务，(Transaction synchronization suspending SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3622e177])
         * 但是由于当前事务没有提交，所以获取的依旧是之前的信息，所以REQUIRES_NEW是可行的。
         */
        while ((bean= selectService.select(bean.getOrderNo())).getIsAgreeMent()!=1){
            if(i==1){
                throw new RuntimeException("超时");
            }
            i++;
            System.out.println(i);
            bean.setOldIsAgreeMent(action.getIsAgreeMent());
            bean.setIsAgreeMent(1);
            actionDao.updateIsAgreeMentByOrderno(bean);
        }


    }

    /**
     * 此处事务被Update调起时不生效，原因是由于springaop代理的缺陷导致。Spring的事务传播策略在内部方法调用时将不起作用。
     * @see http://blog.csdn.net/jiesa/article/details/53438342
     * @param orderNo
     * @return
     */
    @Transactional(propagation = Propagation.NESTED,rollbackFor = Exception.class)
    public ActionBean select(Long orderNo){
//        return actionDao.selectActionBean(orderNo);
        return actionDao.selectMysql(orderNo);
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void insert(ActionBean bean){
        //测试事务调用非事务方法是否会回滚
        insertNei(bean);
        throw new RuntimeException("测试回滚");
    }

    private void insertNei(ActionBean bean){

//        actionDao.insertNotifySuccessMessage(bean);
        actionDao.insertMysql(bean);
    }

    /**
     * 进一步还原场景,乐观锁更新
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public void update(ActionBean bean){
        ActionBean action = select(bean.getOrderNo());
        if(action==null)
            throw new RuntimeException("操作异常");
        action.setOldIsAgreeMent(action.getIsAgreeMent());
        action.setIsAgreeMent(1);
        int i=0;
        /**
         * 此处使用其他类的事务，从日志中可以看到，挂起了当前事务，(Transaction synchronization suspending SqlSession [org.apache.ibatis.session.defaults.DefaultSqlSession@3622e177])
         * 但是由于当前事务没有提交，所以获取的依旧是之前的信息，所以REQUIRES_NEW是可行的。
         */
        while (actionDao.updateIsAgreeMentByOrderno(action)==0){
            //oracle 更新0行，锁的也是0行

            //跟隔离级别有关系，当是repeatable read级别时，更新0行，但是使用索引的行数都会被锁（间隙锁）
            //InnoDB除了通过范围条件加锁时使用间隙锁外，如果使用相等条件请求给一个不存在的记录加锁，InnoDB也会使用间隙锁(可重复读隔离级别)
            //当是read committed时，只会锁具体更新的行数
            if(i==1){
                throw new RuntimeException("超时");
            }
            i++;
            System.out.println(i);
            action = selectService.select(bean.getOrderNo());

            if(action==null)
                throw new RuntimeException("操作异常");
            action.setOldIsAgreeMent(action.getIsAgreeMent());
            action.setIsAgreeMent(1);
        }
        System.out.println(action);
    }

    /**
     * 场景还原
     * 1.调用了ServiceA的方法A，而方法A没有声明事务（原因是方法A本身比较耗时而又不需要事务）
     * 2.ServiceA的方法A调用了自己的方法B，而方法B声明了事务，但是方法B的事务声明在这种情况失效了。
     * 3.如果在方法A上也声明事务，则在Action调用方法A时，事务生效，而方法B则自动参与了这个事务。
     */

    public void updateA(ActionBean bean){
//        update(bean);//无事务调用，Closing non transactional SqlSession
        //Exception in thread "main" java.lang.IllegalStateException:
        // Cannot find current proxy: Set 'exposeProxy' property on Advised to 'true' to make it available.
        //需要开启
        ((TransactionService)AopContext.currentProxy()).update(bean);
    }

}
