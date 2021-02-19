package com.wx.watersupplierservice.config;

import com.google.common.hash.Funnels;
import com.google.common.hash.Hashing;
import com.wx.watersupplierservice.WatersupplierserviceApplication;
import com.wx.watersupplierservice.po.SysOrgPo;
import com.wx.watersupplierservice.util.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 工具类-提供静态方法
 *
 * @author by yexuerui@xdf.cn
 * @Date 2020-06-18 16:18
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WatersupplierserviceApplication.class)
public class RedisTemplateUtil {


    private static StringRedisTemplate stringRedisTemplate = SpringUtils.getBean(StringRedisTemplate.class);

    /*********************************************************************************
     *
     * 对string的操作
     *
     ********************************************************************************/

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     * @return true成功 false失败
     */
    public static boolean set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception e) {
            log.error("【redis：普通缓存放入-异常】", e);
            return false;
        }
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒) time要大于0 如果time小于等于0 将设置无限期
     * @return true成功 false 失败
     */
    public static boolean set(String key, String value, long time, TimeUnit timeUnit) {
        try {
            if (time > 0) {
                stringRedisTemplate.opsForValue().set(key, value, time, timeUnit);
            } else {
                set(key, value);
            }
            return true;
        } catch (Exception e) {
            log.error("【redis：普通缓存放入并设置时间-异常】", e);
            return false;
        }
    }

    /**
     * 判断key是否存在
     */
    public static boolean exist(String key) {
        try {
            Boolean result = stringRedisTemplate.hasKey(key);
            return result == null ? false : result;
        } catch (Exception e) {
            log.error("【redis：普通缓存放入并设置时间-异常】", e);
            return false;
        }
    }

    /**
     * 递增
     *
     * @param key   键
     * @param delta 要增加几(大于0)
     * @return
     */
    public static long incr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递增因子必须大于0");
        }
        return stringRedisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 递减
     *
     * @param key   键
     * @param delta 要减少几(小于0)
     * @return
     */
    public static long decr(String key, long delta) {
        if (delta < 0) {
            throw new RuntimeException("递减因子必须大于0");
        }
        return stringRedisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 获取值
     *
     * @param key 键
     * @return 值
     */
    public static String get(String key) {
        return key == null ? null : stringRedisTemplate.opsForValue().get(key);
    }

    /*********************************************************************************
     *
     * 对bitmap的操作
     *
     ********************************************************************************/

    /**
     * 将指定param的值设置为1，{@param param}会经过hash计算进行存储。
     *
     * @param key   bitmap结构的key
     * @param param 要设置偏移的key，该key会经过hash运算。
     * @param value true：即该位设置为1，否则设置为0
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, String param, boolean value) {
        return stringRedisTemplate.opsForValue().setBit(key, hash(param), value);
    }

    /**
     * 将指定param的值设置为0，{@param param}会经过hash计算进行存储。
     *
     * @param key   bitmap结构的key
     * @param param 要移除偏移的key，该key会经过hash运算。
     * @return 若偏移位上的值为1，那么返回true。
     */
    public static Boolean getBit(String key, String param) {
        return stringRedisTemplate.opsForValue().getBit(key, hash(param));
    }


    /**
     * 将指定offset偏移量的值设置为1；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @param value  true：即该位设置为1，否则设置为0
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, Long offset, boolean value) {
        return stringRedisTemplate.opsForValue().setBit(key, offset, value);
    }

    /**
     * 将指定offset偏移量的值设置为0；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @return 若偏移位上的值为1，那么返回true。
     */
    public static Boolean getBit(String key, long offset) {
        return stringRedisTemplate.opsForValue().getBit(key, offset);
    }

    /**
     * 统计对应的bitmap上value为1的数量
     *
     * @param key bitmap的key
     * @return value等于1的数量
     */
    public static Long bitCount(String key) {
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    /**
     * 统计指定范围中value为1的数量
     *
     * @param key   bitMap中的key
     * @param start 该参数的单位是byte（1byte=8bit），{@code setBit(key,7,true);}进行存储时，单位是bit。那么只需要统计[0,1]便可以统计到上述set的值。
     * @param end   该参数的单位是byte。
     * @return 在指定范围[start*8,end*8]内所有value=1的数量
     */
    public static Long bitCount(String key, int start, int end) {
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }


    /**
     * 对一个或多个保存二进制的字符串key进行元操作，并将结果保存到saveKey上。
     * <p>
     * bitop and saveKey key [key...]，对一个或多个key逻辑并，结果保存到saveKey。
     * bitop or saveKey key [key...]，对一个或多个key逻辑或，结果保存到saveKey。
     * bitop xor saveKey key [key...]，对一个或多个key逻辑异或，结果保存到saveKey。
     * bitop xor saveKey key，对一个或多个key逻辑非，结果保存到saveKey。
     * <p>
     *
     * @param op      元操作类型；
     * @param saveKey 元操作后将结果保存到saveKey所在的结构中。
     * @param desKey  需要进行元操作的类型。
     * @return 1：返回元操作值。
     */
    public static Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        return stringRedisTemplate.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
    }

    /**
     * 对一个或多个保存二进制的字符串key进行元操作，并将结果保存到saveKey上，并返回统计之后的结果。
     *
     * @param op      元操作类型；
     * @param saveKey 元操作后将结果保存到saveKey所在的结构中。
     * @param desKey  需要进行元操作的类型。
     * @return 返回saveKey结构上value=1的所有数量值。
     */
    public static Long bitOpResult(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        bitOp(op, saveKey, desKey);
        return bitCount(saveKey);
    }


    /*********************************************************************************
     *
     * 对Hash的操作
     *
     ********************************************************************************/


    /**
     * 获取Hash结构中所有的值。
     *
     * @param key hash的key。
     * @return hash中所有的值。
     */
    public static List<Object> hashValues(String key) {
        return stringRedisTemplate.opsForHash().values(key);
    }

    /**
     * 将值存储到Hash结构中
     *
     * @param key     hash的key
     * @param hashKey 属性的key
     * @param value   属性的值
     */
    public static void hashPut(String key, String hashKey, String value) {
        stringRedisTemplate.opsForHash().put(key, hashKey, value);
    }


    /*********************************************************************************
     *
     * 公共操作
     *
     ********************************************************************************/

    /**
     * 判断某个key是否存在
     *
     * @param key Redis中的key值。
     * @return 若调用Redis返回null，那么该方法返回false。
     */
    public static boolean hasKey(String key) {
        Boolean hasKey = stringRedisTemplate.hasKey(key);
        return hasKey == null ? false : hasKey;
    }

    /**
     * 设置失效时间
     *
     * @param key      Redis的key,key不能为空
     * @param unixTime 在某个时间点失效（时间戳）
     */
    public static Boolean expireAt(String key, Long unixTime) {
        return stringRedisTemplate.
                execute((RedisCallback<Boolean>) connection -> connection.expireAt(key.getBytes(), unixTime));
    }

    /**
     * @param key     redis的key
     * @param timeout key存活时间
     * @param unit    key存活时间单位
     * @return
     */
    public static Boolean expire(String key, long timeout, TimeUnit unit) {
        return stringRedisTemplate.expire(key, timeout, unit);
    }

    /**
     * guava依赖获取hash值。
     */
    private static long hash(String key) {
        Charset charset = Charset.forName("UTF-8");
        return Math.abs(Hashing.murmur3_128().hashObject(key, Funnels.stringFunnel(charset)).asInt());
    }

    @Test
    public static void main(String[] args) {
        System.out.println("redis");
        RedisTemplateUtil.set("test","11111");
    }
}
