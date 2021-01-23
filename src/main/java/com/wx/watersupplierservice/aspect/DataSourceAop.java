package com.wx.watersupplierservice.aspect;

import com.xdf.pscommon.aop.*;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 主从切换 weili
 */
//@Aspect
//@Configuration
public class DataSourceAop {

    private static final String MASTER = "master";
    private static final String[] SLAVE_DBS = {"slave1"};

    private static final String DB_MASTER_SLAVE_KEY = "DB_MASTER_SLAVE_KEY";

    private Logger logger = LoggerFactory.getLogger(DataSourceAop.class);

    private static Map<String,String> masterSlave = new ConcurrentHashMap<>();

//    @Pointcut("@annotation(com.xdf.pscommon.aop.VPSDataSource)")
    public void changeVPSDataSource(){}

//    @Pointcut("@annotation(com.xdf.pscommon.aop.HitDataSource)")
    public void changeHitDataSource(){}


    @Before("changeVPSDataSource()")
    public void doVPSBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求
        Object target = joinPoint.getTarget();
        String method = joinPoint.getSignature().getName();
        Class<?>[] classz = target.getClass().getInterfaces();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method m = classz[0].getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(VPSDataSource.class)) {
                /**
                 * dao层指定主从
                 */
                String hitDataSource = HitDataSourceHolder.getDataSouce();
                if (StringUtils.isNotEmpty(hitDataSource)) {
                    /**
                     * 优先使用Service层手动指定主从
                     */
                    DynamicDataSourceHolder.putDataSource(hitDataSource);

                    logger.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                            + " use hit datasource: " + hitDataSource);
                } else {
                    /**
                     * 使用dao层指定主从
                     */
                    setVPSDataSource(joinPoint, m);
                }
            } else {
                /**
                 * 没指定走主从 == 默认走主库
                 */
                DynamicDataSourceHolder.putDataSource(MASTER);
                logger.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                        + " use default datasource:" + MASTER);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Before("changeHitDataSource()")
    public void doHitBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求
        Object target = joinPoint.getTarget();
        Class<?> targetClass = target.getClass();
        String method = joinPoint.getSignature().getName();
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getMethod().getParameterTypes();
        try {
            Method objMethod=targetClass.getMethod(method, parameterTypes);
            HitDataSource annotation = objMethod.getAnnotation(HitDataSource.class);
            if (annotation != null) {
                DataSourceType value = annotation.value();
                /**
                 * Service层手动指定主从
                 */
                setHitDataSource(joinPoint, value);
            } else {
                /**
                 * 没指定走主从 == 默认走主库
                 */
                DynamicDataSourceHolder.putDataSource(MASTER);
                logger.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                        + " use default datasource:" + MASTER);
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

    }

    @Around("changeHitDataSource()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object proceed;
        try {
            proceed = joinPoint.proceed();
        } finally {
            //捕捉到进入HitDataSource注解的方法并且执行完成过后-释放threadLocal
            HitDataSourceHolder.removeDataSource();
        }

        return proceed;
    }

    private void setHitDataSource(JoinPoint joinPoint, DataSourceType value) {
        if (value != null && MASTER.equalsIgnoreCase(value.toString())) {
            HitDataSourceHolder.putDataSource(MASTER);
            logger.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                    + " use datasource:" + value);
            return;
        } else {
            String slaveValue = masterSlave.get(DB_MASTER_SLAVE_KEY);
            if (null == slaveValue) {
                HitDataSourceHolder.putDataSource(SLAVE_DBS[0]);
                masterSlave.put(DB_MASTER_SLAVE_KEY, "0");
            } else {
                int length = SLAVE_DBS.length;
                int index = Integer.parseInt(slaveValue.toString());
                if (index == length - 1) {
                    index = 0;
                } else {
                    index++;
                }
                HitDataSourceHolder.putDataSource(SLAVE_DBS[index]);
                slaveValue = String.valueOf(index);
                masterSlave.put(DB_MASTER_SLAVE_KEY, slaveValue);
            }
            logger.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                    + " use datasource: slave" + slaveValue);
        }
    }

    private void setVPSDataSource(JoinPoint joinPoint, Method m) {
        VPSDataSource ds = m.getAnnotation(VPSDataSource.class);
        DataSourceType value = ds.value();
        if (MASTER.equalsIgnoreCase(value.toString())) {
            DynamicDataSourceHolder.putDataSource(MASTER);
            logger.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                    + " use datasource:" + value);
        } else {
            String slaveValue = masterSlave.get(DB_MASTER_SLAVE_KEY);
            if (null == slaveValue) {
                DynamicDataSourceHolder.putDataSource(SLAVE_DBS[0]);
                masterSlave.put(DB_MASTER_SLAVE_KEY, "0");
            } else {
                int length = SLAVE_DBS.length;
                int index = Integer.parseInt(slaveValue.toString());
                if (index == length - 1) {
                    index = 0;
                } else {
                    index++;
                }
                DynamicDataSourceHolder.putDataSource(SLAVE_DBS[index]);
                slaveValue = String.valueOf(index);
                masterSlave.put(DB_MASTER_SLAVE_KEY, slaveValue);
            }
            logger.debug(joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName()
                    + " use datasource: slave" + slaveValue);
        }
    }

}
