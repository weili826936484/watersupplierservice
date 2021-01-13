package com.wx.watersupplierservice.util.jddj;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

/**
 * 
 * 与京东到家平台信息交互接口
 * @author wql
 *
 */
public class JddjOrderUtil {
	
	/** 时间格式化*/ 
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
	/**
     * *根据单号获取订单详细信息
     * @param request
     * @param response
     * @throws Exception
     */
    public static String findOrderFromJddj(JSONObject orgJson, String orderId) throws Exception {
	    
    	//请求地址
    	String url = "https://openo2o.jd.com/djapi/order/es/query";
    	
    	//应用级别输入参数
	    JSONObject reqparams = new JSONObject();
        reqparams.put("pageNo", "1");
        reqparams.put("pageSize", "1");
        reqparams.put("orderId", orderId);
        String jd_param_json = reqparams.toString();
        
	    //根据单号获取订单信息  
	    String result = HttpUtil.sendJdPostRequest(url, addJddjParam(orgJson, jd_param_json));
	    
		return result;
	}
    
    /** 
     **拣货完成且商家自送
     * @throws Exception 
     */
    public static String sendOrderSerllerDelivery(JSONObject orgJson, String orderId, String operator) throws Exception {
    	
    	//请求地址
    	String url = "https://openapi.jddj.com/djapi/bm/open/api/order/OrderSerllerDelivery";
    	
    	//应用级别输入参数
    	JSONObject reqparams = new JSONObject();
        reqparams.put("orderId", orderId);
        reqparams.put("operator", operator);
        String jd_param_json = reqparams.toString();
        
	    //拣货完成且商家自送接口  商家将拣货完成状态推送给京东
	    String result = HttpUtil.sendJdPostRequest(url, addJddjParam(orgJson, jd_param_json));
	    
		return result;
    }
    
    /** 
     * *商家配送完成
     * @throws Exception 
     */
    public static String sendDeliveryEndOrder(JSONObject orgJson, String orderId, String operPin, String operTime) throws Exception {
    	
    	//请求地址
    	String url = "https://openapi.jddj.com/djapi/ocs/deliveryEndOrder";
    	
    	//应用级别输入参数
    	JSONObject reqparams = new JSONObject();
        reqparams.put("orderId", orderId);
        reqparams.put("operPin", operPin);
        reqparams.put("operTime", operTime);
        String jd_param_json = reqparams.toString();
        
	    //拣货完成且商家自送接口  商家将配送完成状态推送给京东
	    String result = HttpUtil.sendJdPostRequest(url, addJddjParam(orgJson, jd_param_json));
	    
		return result;
    }
    
    /** 
     * *商家审核用户取消申请
     * @throws Exception 
     */
    public static String sendOrderCancelOperate(JSONObject orgJson, String orderId, Boolean isAgreed, String operator, String remark) throws Exception {
    	
    	//请求地址
    	String url = "https://openapi.jddj.com/djapi/ocs/orderCancelOperate";
    	
    	//应用级别输入参数
    	JSONObject reqparams = new JSONObject();
        reqparams.put("orderId", orderId);
        reqparams.put("isAgreed", isAgreed);
        reqparams.put("operator", operator);
        reqparams.put("remark", remark);
        String jd_param_json = reqparams.toString();
        
	    //拣货完成且商家自送接口  商家将拣货完成状态推送给京东
	    String result = HttpUtil.sendJdPostRequest(url, addJddjParam(orgJson, jd_param_json));
	    
		return result;
    }
    
    /**
     * *拼接京东调用参数
     * @param orgJson
     * @param jd_param_json
     * @return
     * @throws Exception
     */
    public static Map<String, Object> addJddjParam(JSONObject orgJson, String jd_param_json ) throws Exception{
    	
    	Date now = new Date();
		String appKey = orgJson.optString("app_key");
		String appSecret = orgJson.optString("app_secret");
		String token = orgJson.optString("token");
	    String format = "json";
	    String v = "1.0";	    
	    String timestamp = sdf.format(now);
	    
    	// 计算签名实体
	    WebRequestDTO webReqeustDTO = new WebRequestDTO();
	    webReqeustDTO.setApp_key(appKey);
	    webReqeustDTO.setFormat(format);
	    webReqeustDTO.setJd_param_json(jd_param_json);
	    webReqeustDTO.setTimestamp(timestamp);
	    webReqeustDTO.setToken(token);
	    webReqeustDTO.setV(v);
	    
	    //计算签名
	    String sign = SignUtils.getSignByMD5(webReqeustDTO, appSecret);
	    System.out.println("md5 sign:" + sign);
	    
	    //拼接请求体
	    Map<String, Object> param = new HashMap<String, Object>();
	    param.put("token", token);
	    param.put("app_key", appKey);
	    param.put("timestamp", timestamp);
	    param.put("sign", sign);
	    param.put("format", format);
	    param.put("v", v);
	    param.put("jd_param_json", jd_param_json);
	    
		return param;
    }
}
