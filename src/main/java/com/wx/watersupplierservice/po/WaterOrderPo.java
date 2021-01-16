package com.wx.watersupplierservice.po;

import java.util.Date;

import com.xdf.pscommon.annotation.alias.ID;
import com.xdf.pscommon.annotation.alias.Identity;
import com.xdf.pscommon.annotation.alias.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description: water_order
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
@Table(name = "water_order")
@Data
public class WaterOrderPo {

    @ID
    @ApiModelProperty(value = "主键")
    private Integer id;
    @ApiModelProperty(value = "订单来源平台")
    private String platform;
    @ApiModelProperty(value = "订单号")
    private String orderid;
    @ApiModelProperty(value = "订单来源类型(0:原订单，10:退款单，20:补货单，30:直赔商品 ，40:退货)")
    private String srcinnertype;
    @ApiModelProperty(value = "订单类型（10000：从门店出的订单）")
    private String ordertype;
    @ApiModelProperty(value = "订单状态")
    private String orderstatus;
    @ApiModelProperty(value = "订单状态最新更改时间")
    private Date orderstatustime;
    @ApiModelProperty(value = "下单时间")
    private Date orderstarttime;
    @ApiModelProperty(value = "订单成交时间(在线支付类型订单的付款完成时间)")
    private Date orderpurchasetime;
    @ApiModelProperty(value = "订单时效类型")
    private String orderagingtype;
    @ApiModelProperty(value = "预计送达开始时间")
    private Date orderprestartdeliverytime;
    @ApiModelProperty(value = "预计送达结束时间")
    private Date orderpreenddeliverytime;
    @ApiModelProperty(value = "商家最晚拣货完成时间")
    private Date pickdeadline;
    @ApiModelProperty(value = "订单取消时间")
    private Date ordercanceltime;
    @ApiModelProperty(value = "订单取消备注")
    private String ordercancelremark;
    @ApiModelProperty(value = "商家编码")
    private String orgcode;
    @ApiModelProperty(value = "买家账号")
    private String buyerpin;
    @ApiModelProperty(value = "收货人名称")
    private String buyerfullname;
    @ApiModelProperty(value = "收货人地址")
    private String buyerfulladdress;
    @ApiModelProperty(value = "收货人电话")
    private String buyertelephone;
    @ApiModelProperty(value = "收货人手机号")
    private String buyermobile;
    @ApiModelProperty(value = "收货人真实手机号后四位")
    private String lastfourdigitsofbuyermobile;
    @ApiModelProperty(value = "到家配送门店编码")
    private String deliverystationno;
    @ApiModelProperty(value = "商家门店编码")
    private String deliverystationnoisv;
    @ApiModelProperty(value = "配送门店名称")
    private String deliverystationname;
    @ApiModelProperty(value = "承运商编号(9966:京东众包;2938:商家自送;1130:达达同城送;9999:到店自提)")
    private String deliverycarrierno;
    @ApiModelProperty(value = "承运商名称")
    private String deliverycarriername;
    @ApiModelProperty(value = "承运单号，通常情况下和订单号一致")
    private String deliverybillno;
    @ApiModelProperty(value = "包裹重量（单位：kg）")
    private String deliverypackageweight;
    @ApiModelProperty(value = "妥投时间")
    private Date deliveryconfirmtime;
    @ApiModelProperty(value = "订单支付类型(1：货到付款，4:在线支付;)")
    private String orderpaytype;
    @ApiModelProperty(value = "订单支付渠道")
    private String paychannel;
    @ApiModelProperty(value = "订单商品销售价总金额，等于sum（京东到家销售价skuJdPrice*商品下单数量skuCount）")
    private String ordertotalmoney;
    @ApiModelProperty(value = "订单级别优惠商品金额")
    private String orderdiscountmoney;
    @ApiModelProperty(value = "用户支付的实际订单运费")
    private String orderfreightmoney;
    @ApiModelProperty(value = "达达同城送运费(单位：分)")
    private String localdeliverymoney;
    @ApiModelProperty(value = "商家支付远距离运费(单位：分)")
    private String merchantpaymentdistancefreightmoney;
    @ApiModelProperty(value = "订单应收运费：用户应该支付的订单运费，即未优惠前应付运费(商家自送，则订单应收运费为设置的门店自送运费)")
    private String orderreceivablefreight;
    @ApiModelProperty(value = "用户积分抵扣金额")
    private String platformpointsdeductionmoney;
    @ApiModelProperty(value = "用户应付金额（单位为分）=商品销售价总金额orderTotalMoney -订单优惠总金额 orderDiscountMoney+实际订单运费orderFreightMoney +包装金额packagingMoney -用户积分抵扣金额platformPointsDeductionMoney")
    private String orderbuyerpayablemoney;
    @ApiModelProperty(value = "订单货款总金额 [订单货款金额（订单总金额-商家优惠金额）]")
    private String ordergoodsmoney;
    @ApiModelProperty(value = "包装金额")
    private String packagingmoney;
    @ApiModelProperty(value = "商家给配送员加的小费")
    private String tips;
    @ApiModelProperty(value = "是否存在订单调整(false:否;true:是)")
    private String adjustisexists;
    @ApiModelProperty(value = "调整单编号")
    private String adjustid;
    @ApiModelProperty(value = "是否拼团订单(false:否;true:是)")
    private String isgroupon;
    @ApiModelProperty(value = "收货人地址定位类型（buyerCoordType值为空或为1时，定位类型为gps，如为其他值时，定位类型为非gps类型。)")
    private String buyercoordtype;
    @ApiModelProperty(value = "收货人地址腾讯坐标经度")
    private String buyerlng;
    @ApiModelProperty(value = "收货人地址腾讯坐标纬度")
    private String buyerlat;
    @ApiModelProperty(value = "收货人市ID")
    private String buyercity;
    @ApiModelProperty(value = "收货人市名称")
    private String buyercityname;
    @ApiModelProperty(value = "收货人县(区)ID")
    private String buyercountry;
    @ApiModelProperty(value = "收货人县(区)名称")
    private String buyercountryname;
    @ApiModelProperty(value = "订单买家备注")
    private String orderbuyerremark;
    @ApiModelProperty(value = "业务标识，用英文分号分隔")
    private String businesstag;
    @ApiModelProperty(value = "设备id")
    private String equipmentid;
    @ApiModelProperty(value = "收货人POI信息")
    private String buyerpoi;
    @ApiModelProperty(value = "当天门店订单序号")
    private String ordernum;
    @ApiModelProperty(value = "用户小费（用户给配送员加小费）")
    private String usertip;
    @ApiModelProperty(value = "收货人电话中间号有效期")
    private Date middlenumbindingtime;
    @ApiModelProperty(value = "订单抛入达达抢单池时间")
    private Date deliverinputtime;
    @ApiModelProperty(value = "订单业务类型")
    private String businesstype;
    @ApiModelProperty(value = "VIP卡号")
    private String vendervipcardid;
    @ApiModelProperty(value = "订单开发票标识（1.开发票；2.不开发票）")
    private String orderinvoiceopenmark;
    @ApiModelProperty(value = "订单来源系统")
    private String srcordertype;
    @ApiModelProperty(value = "订单来源系统(比如京东订单号)")
    private String srcorderid;
    @ApiModelProperty(value = "发票类型：0.纸质发票1.电子发票")
    private String invoiceformtype;
    @ApiModelProperty(value = "发票抬头")
    private String invoicetitle;
    @ApiModelProperty(value = "发票税号")
    private String invoicedutyno;
    @ApiModelProperty(value = "发票邮箱地址")
    private String invoicemail;
    @ApiModelProperty(value = "发票金额")
    private String invoicemoney;
    @ApiModelProperty(value = "发票内容")
    private String invoicecontent;
    @ApiModelProperty(value = "公司注册地址")
    private String invoiceaddress;
    @ApiModelProperty(value = "公司注册电话")
    private String invoicetelno;
    @ApiModelProperty(value = "公司开户银行名称")
    private String invoicebankname;
    @ApiModelProperty(value = "公司开户银行账户")
    private String invoiceaccountno;
    @ApiModelProperty(value = "创建人")
    private String createBy;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "更新人")
    private String updateBy;
    @ApiModelProperty(value = "更新时间")
    private Date updateTime;
    private Integer orgId;
    private Integer version;


}