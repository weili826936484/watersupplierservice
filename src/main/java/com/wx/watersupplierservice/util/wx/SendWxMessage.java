package com.wx.watersupplierservice.util.wx;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

/**
 * 微信推送消息
 * @author wang-ql
 *
 */
public class SendWxMessage {

	/**
	 * 新订单推送
	 */
	public static void sendNewOrderInfo(JSONObject orderInfo, List<JSONObject> productList,
			List<String> userList) {
				
		//新订单通知模板
		String templatId = "MNnysHBph8N-gPiUIUoLgl_Uju9gdnlIjCfN-wh2Xwk";
		String clickUrl = "http://www.yhzav.cn/wx/orderNew.html";

		StringBuffer str = new StringBuffer(); 
		if (productList.size() > 0) {
			for(int n=0;n<productList.size();n++) {
				str.append(productList.get(n).optString("skuName") + " | 数量*" + productList.get(n).optString("skuCount"));
			}
		}
			
		//拼接推送消息模板				
        String topColor = "";
        JSONObject jsc = new JSONObject();
        
        JSONObject first = new JSONObject();
        first.put("value", "您有一个新订单");
        first.put("color", "#173177");
        jsc.put("first", first);
        
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderInfo.optString("orderId"));
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);
        
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", orderInfo.optString("buyerFullName") + orderInfo.optString("buyerTelephone"));
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);
        
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", str.toString());
        keyword3.put("color", "#173177");
        jsc.put("keyword3", keyword3);
        
        JSONObject keyword4 = new JSONObject();
        keyword4.put("value", orderInfo.optString("buyerFullAddress"));
        keyword4.put("color", "#173177");
        jsc.put("keyword4", keyword4);
        
        JSONObject keyword5 = new JSONObject();
        keyword5.put("value", orderInfo.optString("orderPreStartDeliveryTime").substring(0, 16) + "至" + orderInfo.optString("orderPreEndDeliveryTime").substring(11, 16));
        keyword5.put("color", "#173177");
        jsc.put("keyword5", keyword5);
        
        JSONObject remark = new JSONObject();
        remark.put("value", orderInfo.optString("orderBuyerRemark"));
        remark.put("color", "#173177");
        jsc.put("remark", remark);

		if(userList.size() > 0) {
			for(int m=0;m<userList.size();m++) {
				String openId = userList.get(m);
				if (StringUtils.isNotBlank(openId)) {
					//向管理员推送微信
					String result =  WeixinUtil.sendNotify(openId, templatId, clickUrl, topColor, jsc);
				}
				
			}
		}
		
		
		 
	}

	/**
	 * 用户取消申请
	 * @param orderInfo
	 *
	 * @param productList
	 * @param
	 */
	public static void sendPreCancleOrderInfo(JSONObject orderInfo, List<JSONObject> productList,
			List<String> userList) {
			String templatId = "P3Xanj20VkW52jV3jIG4kjmTBr86adhUSa_Q5GApGuU";
		 String clickUrl = "http://39.96.44.58/wx/orderDetail.html?flag=preCancle&orderId=" + orderInfo.optString("id");

			StringBuffer str = new StringBuffer(); 
			if (productList.size() > 0) {
				for(int n=0;n<productList.size();n++) {
					str.append(productList.get(n).optString("skuName") + " | 数量*" + productList.get(n).optString("skuCount"));
				}
			}
			
			//拼接推送消息模板				
	        String topColor = "";
	        JSONObject jsc = new JSONObject();
	        
	        JSONObject first = new JSONObject();
	        first.put("value", "订单取消申请(" + orderInfo.optString("platform_name") + ")");
	        first.put("color", "#173177");
	        jsc.put("first", first);
	        
	        String status = null;
	        if ("L10".equals(orderInfo.optString("opt_code"))) {
	        	status = "分单到水站";
	        } else if("L20".equals(orderInfo.optString("opt_code"))) {
	        	status = "水站接单配送";
	        } else if("L21".equals(orderInfo.optString("opt_code"))) {
	        	status = "水站拒单";
	        } else if("L90".equals(orderInfo.optString("opt_code"))) {
	        	status = "配送完成";
	        } else if("B90".equals(orderInfo.optString("opt_code"))) {
	        	status = "订单取消";
	        } else {
	        	status = "未分单";
	        }
	        JSONObject keyword1 = new JSONObject();
	        keyword1.put("value", orderInfo.optString("orderId") + "(" + status + ")");
	        keyword1.put("color", "#173177");
	        jsc.put("keyword1", keyword1);
	        
	        JSONObject keyword2 = new JSONObject();
	        keyword2.put("value", orderInfo.optString("buyerFullName") + orderInfo.optString("buyerTelephone"));
	        keyword2.put("color", "#173177");
	        jsc.put("keyword2", keyword2);
	        
	        JSONObject keyword3 = new JSONObject();
	        keyword3.put("value", orderInfo.optString("orderPreStartDeliveryTime").substring(0, 16) + "至" + orderInfo.optString("orderPreEndDeliveryTime").substring(11, 16));
	        keyword3.put("color", "#173177");
	        jsc.put("keyword3", keyword3);
	        
	        JSONObject keyword4 = new JSONObject();
	        keyword4.put("value", orderInfo.optString("buyerFullAddress"));
	        keyword4.put("color", "#173177");
	        jsc.put("keyword4", keyword4);
	        
	        JSONObject keyword5 = new JSONObject();
	        keyword5.put("value", str.toString() + orderInfo.optString("orderCancelRemark"));
	        keyword5.put("color", "#173177");
	        jsc.put("keyword5", keyword5);
	        
	        JSONObject remark = new JSONObject();
	        remark.put("value", "请您在15分钟之内审核退单申请！");
	        remark.put("color", "#173177");
	        jsc.put("remark", remark);

			if(userList.size() > 0) {
				for(int m=0;m<userList.size();m++) {
					String openId = userList.get(m);
					if (StringUtils.isNotBlank(openId)) {
						//向管理员推送微信
						String result =  WeixinUtil.sendNotify(openId, templatId, clickUrl, topColor, jsc);
						System.out.println(new Date() + "~~~" + openId + "~~~发送数据:" + result);
					}
					
				}
			}
	}
}
