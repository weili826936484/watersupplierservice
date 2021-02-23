package com.wx.watersupplierservice.util.wx;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.wx.watersupplierservice.dto.OrderDto;
import com.wx.watersupplierservice.util.Cfg;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class SendWxMessage2 {
	
	/**
	 * 水站新订单
     * @param orderDto  订单信息
     * @param productList  商品信息
     * @param userList  orderId 对应水站人员openId列表
     */
	public static void sendNewOrder(OrderDto orderDto, List<OrderDto.ProductDto> productList,
                                    List<String> userList) {
		
		//新订单通知模板
        String templatId = Cfg.getConfig("templatId_newOrder");
        String clickUrl = Cfg.getConfig("web.url") + "/wx/siteNewOrder.html";
		
		//拼接商品信息
		StringBuffer str = new StringBuffer(); 
		if (productList.size() > 0) {
			for(int n=0;n<productList.size();n++) {
				str.append(productList.get(n).getSkuName() + " | 数量*" + productList.get(n).getSkuCount());
			}
		}
			
		//拼接推送消息模板				
        String topColor = "";
        JSONObject jsc = new JSONObject();
        
        JSONObject first = new JSONObject();
        first.put("value", "收到一个新的订单，请及时处理");
        first.put("color", "#173177");
        jsc.put("first", first);
        
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderDto.getOrderid());
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);
        
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", orderDto.getBuyerfullname() + orderDto.getBuyertelephone());
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);
        
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", str.toString());
        keyword3.put("color", "#173177");
        jsc.put("keyword3", keyword3);
        
        JSONObject keyword4 = new JSONObject();
        keyword4.put("value", orderDto.getBuyerfulladdress());
        keyword4.put("color", "#173177");
        jsc.put("keyword4", keyword4);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject keyword5 = new JSONObject();
        keyword5.put("value", format.format(orderDto.getOrderprestartdeliverytime()).substring(0, 16) + "至" + format.format(orderDto.getOrderpreenddeliverytime()).substring(11, 16));
        keyword5.put("color", "#173177");
        jsc.put("keyword5", keyword5);
        
        JSONObject remark = new JSONObject();
        remark.put("value", orderDto.getOrderbuyerremark());
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
	 * 水站拒单
	 * @param orderDto  订单信息
	 * @param productList  商品信息
	 * @param userList   orderId 对应管理员openId列表
	 */
	public static void sendRefuseOrder(OrderDto orderDto, List<JSONObject> productList,
                                       List<String> userList, Date refuseTime,String refuseReason) {
		

        //订单拒绝通知模板
        String templatId = Cfg.getConfig("templatId_refuseOrder");
        String clickUrl = "";
        if ("JDDJ".equals(orderDto.getPlatform())) {
            clickUrl = Cfg.getConfig("web.url") + "/wx/orderNew.html";
        } else if("MT".equals(orderDto.getPlatform())) {
            clickUrl = Cfg.getConfig("web.url") + "/wx/orderNewMT.html";
        } else if("ELEME".equals(orderDto.getPlatform())) {
            clickUrl = Cfg.getConfig("web.url") + "/wx/orderNewELEME.html";
        }

		//拼接推送消息模板				
        String topColor = "";
        JSONObject jsc = new JSONObject();
        
        JSONObject first = new JSONObject();
        first.put("value", "水站拒单，请及时处理");
        first.put("color", "#173177");
        jsc.put("first", first);
        
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderDto.getOrderid());
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", format.format(orderDto.getOrderstarttime()).substring(0, 19));  //下单时间
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);
        
        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", format.format(refuseTime));  //拒绝时间   业务流程明细表拒单记录创建时间
        keyword3.put("color", "#173177");
        jsc.put("keyword3", keyword3);
        
        JSONObject keyword4 = new JSONObject();
        keyword4.put("value", refuseReason); //拒绝理由   业务流程明细表拒单记录result_info
        keyword4.put("color", "#173177");
        jsc.put("keyword4", keyword4);
        
        
        
        JSONObject remark = new JSONObject();
        remark.put("value", "请重新分配水站，或致电客人说明原因");
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
	 * 商家撤单
	 * @param orderDto  订单信息
	 * @param productList  商品信息
	 * @param userList   orderId 对应水站人员openId列表
	 */
	public static void sendCancleOrder(OrderDto orderDto, List<JSONObject> productList,
			List<String> userList) {

        //订单拒绝通知模板
        String templatId = Cfg.getConfig("templatId_cancleOrder");
        String clickUrl = Cfg.getConfig("web.url") + "/wx/orderDetail.html?orderBusinessId="+orderDto.getOrderBusinessId()+"&platform="+orderDto.getPlatform()+"&flag=siteFinish&orderId=" + orderDto.getId();
		//拼接推送消息模板
        String topColor = "";
        JSONObject jsc = new JSONObject();
        
        JSONObject first = new JSONObject();
        first.put("value", "商家撤单，无需配送");
        first.put("color", "#173177");
        jsc.put("first", first);
        
        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderDto.getOrderid());
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);
        
        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", "用户要求取消订单");
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);

        JSONObject remark = new JSONObject();
        remark.put("value", "客户信息：" + orderDto.getBuyerfullname() + orderDto.getBuyertelephone());
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
     * 商家催单    用户催单提醒
     * @param orderInfo
     * @param userList
     */
    public static void reSendOrder(OrderDto orderInfo, List<String> userList) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        //新订单通知模板
        String templatId = Cfg.getConfig("templatId_reSendOrder");
        String clickUrl = "";

        //拼接推送消息模板
        String topColor = "";
        JSONObject jsc = new JSONObject();

        if ("L10".equals(orderInfo.getOptCode())) {
            //水站未接单  跳转到水站新订单页面
            clickUrl = Cfg.getConfig("web.url") + "/wx/siteNewOrder.html";
            JSONObject first = new JSONObject();
            first.put("value", "您有一条订单未接单，请及时接单！");
            first.put("color", "#173177");
            jsc.put("first", first);
        }
        if ("L20".equals(orderInfo.getOptCode())) {
            //水站接单  跳转到水站配送中页面
            clickUrl = Cfg.getConfig("web.url") + "/wx/siteSendingOrder.html";
            JSONObject first = new JSONObject();
            first.put("value", "您有一条客户催单请尽快回电，如送达及时点击完成！");
            first.put("color", "#173177");
            jsc.put("first", first);
        }

        JSONObject keyword1 = new JSONObject();
        keyword1.put("value", orderInfo.getOrderid());
        keyword1.put("color", "#173177");
        jsc.put("keyword1", keyword1);

        JSONObject keyword2 = new JSONObject();
        keyword2.put("value", orderInfo.getOptCodeName());    //订单配送状态
        keyword2.put("color", "#173177");
        jsc.put("keyword2", keyword2);

        JSONObject keyword3 = new JSONObject();
        keyword3.put("value", sdf.format(now)); //此处不用改
        keyword3.put("color", "#173177");
        jsc.put("keyword3", keyword3);

        JSONObject remark = new JSONObject();
        remark.put("value", orderInfo.getBuyerfullname() + orderInfo.getBuyertelephone());
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
}
