package com.wx.watersupplierservice.aspect;

import com.xdf.pscommon.aop.DynamicDataSourceHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/***
 * 事务控制 @Transactional 注解后默认走主库 weili
 */
@Order(1)
@Aspect
@Component
public class TransactionalAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final String MASTER = "master";

    @Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void annotationPointCut() {
    }

    @Before("annotationPointCut()")
    public void before(JoinPoint point) {
        DynamicDataSourceHolder.putDataSource(MASTER);
        logger.info(point.getSignature().getDeclaringTypeName()
                + "." + point.getSignature().getName()
                +" use Transactional datasource: {}", DynamicDataSourceHolder.getDataSouce());
    }

}
