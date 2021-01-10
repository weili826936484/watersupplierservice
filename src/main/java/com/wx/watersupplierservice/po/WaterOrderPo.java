package com.wx.watersupplierservice.po;

import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Description: water_order
 * @author 82693
 * @date 2021年01月10日
 */
@ApiModel
public class WaterOrderPo implements Serializable {
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer orderId;
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

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getSrcinnertype() {
        return srcinnertype;
    }

    public void setSrcinnertype(String srcinnertype) {
        this.srcinnertype = srcinnertype;
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Date getOrderstatustime() {
        return orderstatustime;
    }

    public void setOrderstatustime(Date orderstatustime) {
        this.orderstatustime = orderstatustime;
    }

    public Date getOrderstarttime() {
        return orderstarttime;
    }

    public void setOrderstarttime(Date orderstarttime) {
        this.orderstarttime = orderstarttime;
    }

    public Date getOrderpurchasetime() {
        return orderpurchasetime;
    }

    public void setOrderpurchasetime(Date orderpurchasetime) {
        this.orderpurchasetime = orderpurchasetime;
    }

    public String getOrderagingtype() {
        return orderagingtype;
    }

    public void setOrderagingtype(String orderagingtype) {
        this.orderagingtype = orderagingtype;
    }

    public Date getOrderprestartdeliverytime() {
        return orderprestartdeliverytime;
    }

    public void setOrderprestartdeliverytime(Date orderprestartdeliverytime) {
        this.orderprestartdeliverytime = orderprestartdeliverytime;
    }

    public Date getOrderpreenddeliverytime() {
        return orderpreenddeliverytime;
    }

    public void setOrderpreenddeliverytime(Date orderpreenddeliverytime) {
        this.orderpreenddeliverytime = orderpreenddeliverytime;
    }

    public Date getPickdeadline() {
        return pickdeadline;
    }

    public void setPickdeadline(Date pickdeadline) {
        this.pickdeadline = pickdeadline;
    }

    public Date getOrdercanceltime() {
        return ordercanceltime;
    }

    public void setOrdercanceltime(Date ordercanceltime) {
        this.ordercanceltime = ordercanceltime;
    }

    public String getOrdercancelremark() {
        return ordercancelremark;
    }

    public void setOrdercancelremark(String ordercancelremark) {
        this.ordercancelremark = ordercancelremark;
    }

    public String getOrgcode() {
        return orgcode;
    }

    public void setOrgcode(String orgcode) {
        this.orgcode = orgcode;
    }

    public String getBuyerpin() {
        return buyerpin;
    }

    public void setBuyerpin(String buyerpin) {
        this.buyerpin = buyerpin;
    }

    public String getBuyerfullname() {
        return buyerfullname;
    }

    public void setBuyerfullname(String buyerfullname) {
        this.buyerfullname = buyerfullname;
    }

    public String getBuyerfulladdress() {
        return buyerfulladdress;
    }

    public void setBuyerfulladdress(String buyerfulladdress) {
        this.buyerfulladdress = buyerfulladdress;
    }

    public String getBuyertelephone() {
        return buyertelephone;
    }

    public void setBuyertelephone(String buyertelephone) {
        this.buyertelephone = buyertelephone;
    }

    public String getBuyermobile() {
        return buyermobile;
    }

    public void setBuyermobile(String buyermobile) {
        this.buyermobile = buyermobile;
    }

    public String getLastfourdigitsofbuyermobile() {
        return lastfourdigitsofbuyermobile;
    }

    public void setLastfourdigitsofbuyermobile(String lastfourdigitsofbuyermobile) {
        this.lastfourdigitsofbuyermobile = lastfourdigitsofbuyermobile;
    }

    public String getDeliverystationno() {
        return deliverystationno;
    }

    public void setDeliverystationno(String deliverystationno) {
        this.deliverystationno = deliverystationno;
    }

    public String getDeliverystationnoisv() {
        return deliverystationnoisv;
    }

    public void setDeliverystationnoisv(String deliverystationnoisv) {
        this.deliverystationnoisv = deliverystationnoisv;
    }

    public String getDeliverystationname() {
        return deliverystationname;
    }

    public void setDeliverystationname(String deliverystationname) {
        this.deliverystationname = deliverystationname;
    }

    public String getDeliverycarrierno() {
        return deliverycarrierno;
    }

    public void setDeliverycarrierno(String deliverycarrierno) {
        this.deliverycarrierno = deliverycarrierno;
    }

    public String getDeliverycarriername() {
        return deliverycarriername;
    }

    public void setDeliverycarriername(String deliverycarriername) {
        this.deliverycarriername = deliverycarriername;
    }

    public String getDeliverybillno() {
        return deliverybillno;
    }

    public void setDeliverybillno(String deliverybillno) {
        this.deliverybillno = deliverybillno;
    }

    public String getDeliverypackageweight() {
        return deliverypackageweight;
    }

    public void setDeliverypackageweight(String deliverypackageweight) {
        this.deliverypackageweight = deliverypackageweight;
    }

    public Date getDeliveryconfirmtime() {
        return deliveryconfirmtime;
    }

    public void setDeliveryconfirmtime(Date deliveryconfirmtime) {
        this.deliveryconfirmtime = deliveryconfirmtime;
    }

    public String getOrderpaytype() {
        return orderpaytype;
    }

    public void setOrderpaytype(String orderpaytype) {
        this.orderpaytype = orderpaytype;
    }

    public String getPaychannel() {
        return paychannel;
    }

    public void setPaychannel(String paychannel) {
        this.paychannel = paychannel;
    }

    public String getOrdertotalmoney() {
        return ordertotalmoney;
    }

    public void setOrdertotalmoney(String ordertotalmoney) {
        this.ordertotalmoney = ordertotalmoney;
    }

    public String getOrderdiscountmoney() {
        return orderdiscountmoney;
    }

    public void setOrderdiscountmoney(String orderdiscountmoney) {
        this.orderdiscountmoney = orderdiscountmoney;
    }

    public String getOrderfreightmoney() {
        return orderfreightmoney;
    }

    public void setOrderfreightmoney(String orderfreightmoney) {
        this.orderfreightmoney = orderfreightmoney;
    }

    public String getLocaldeliverymoney() {
        return localdeliverymoney;
    }

    public void setLocaldeliverymoney(String localdeliverymoney) {
        this.localdeliverymoney = localdeliverymoney;
    }

    public String getMerchantpaymentdistancefreightmoney() {
        return merchantpaymentdistancefreightmoney;
    }

    public void setMerchantpaymentdistancefreightmoney(String merchantpaymentdistancefreightmoney) {
        this.merchantpaymentdistancefreightmoney = merchantpaymentdistancefreightmoney;
    }

    public String getOrderreceivablefreight() {
        return orderreceivablefreight;
    }

    public void setOrderreceivablefreight(String orderreceivablefreight) {
        this.orderreceivablefreight = orderreceivablefreight;
    }

    public String getPlatformpointsdeductionmoney() {
        return platformpointsdeductionmoney;
    }

    public void setPlatformpointsdeductionmoney(String platformpointsdeductionmoney) {
        this.platformpointsdeductionmoney = platformpointsdeductionmoney;
    }

    public String getOrderbuyerpayablemoney() {
        return orderbuyerpayablemoney;
    }

    public void setOrderbuyerpayablemoney(String orderbuyerpayablemoney) {
        this.orderbuyerpayablemoney = orderbuyerpayablemoney;
    }

    public String getOrdergoodsmoney() {
        return ordergoodsmoney;
    }

    public void setOrdergoodsmoney(String ordergoodsmoney) {
        this.ordergoodsmoney = ordergoodsmoney;
    }

    public String getPackagingmoney() {
        return packagingmoney;
    }

    public void setPackagingmoney(String packagingmoney) {
        this.packagingmoney = packagingmoney;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getAdjustisexists() {
        return adjustisexists;
    }

    public void setAdjustisexists(String adjustisexists) {
        this.adjustisexists = adjustisexists;
    }

    public String getAdjustid() {
        return adjustid;
    }

    public void setAdjustid(String adjustid) {
        this.adjustid = adjustid;
    }

    public String getIsgroupon() {
        return isgroupon;
    }

    public void setIsgroupon(String isgroupon) {
        this.isgroupon = isgroupon;
    }

    public String getBuyercoordtype() {
        return buyercoordtype;
    }

    public void setBuyercoordtype(String buyercoordtype) {
        this.buyercoordtype = buyercoordtype;
    }

    public String getBuyerlng() {
        return buyerlng;
    }

    public void setBuyerlng(String buyerlng) {
        this.buyerlng = buyerlng;
    }

    public String getBuyerlat() {
        return buyerlat;
    }

    public void setBuyerlat(String buyerlat) {
        this.buyerlat = buyerlat;
    }

    public String getBuyercity() {
        return buyercity;
    }

    public void setBuyercity(String buyercity) {
        this.buyercity = buyercity;
    }

    public String getBuyercityname() {
        return buyercityname;
    }

    public void setBuyercityname(String buyercityname) {
        this.buyercityname = buyercityname;
    }

    public String getBuyercountry() {
        return buyercountry;
    }

    public void setBuyercountry(String buyercountry) {
        this.buyercountry = buyercountry;
    }

    public String getBuyercountryname() {
        return buyercountryname;
    }

    public void setBuyercountryname(String buyercountryname) {
        this.buyercountryname = buyercountryname;
    }

    public String getOrderbuyerremark() {
        return orderbuyerremark;
    }

    public void setOrderbuyerremark(String orderbuyerremark) {
        this.orderbuyerremark = orderbuyerremark;
    }

    public String getBusinesstag() {
        return businesstag;
    }

    public void setBusinesstag(String businesstag) {
        this.businesstag = businesstag;
    }

    public String getEquipmentid() {
        return equipmentid;
    }

    public void setEquipmentid(String equipmentid) {
        this.equipmentid = equipmentid;
    }

    public String getBuyerpoi() {
        return buyerpoi;
    }

    public void setBuyerpoi(String buyerpoi) {
        this.buyerpoi = buyerpoi;
    }

    public String getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(String ordernum) {
        this.ordernum = ordernum;
    }

    public String getUsertip() {
        return usertip;
    }

    public void setUsertip(String usertip) {
        this.usertip = usertip;
    }

    public Date getMiddlenumbindingtime() {
        return middlenumbindingtime;
    }

    public void setMiddlenumbindingtime(Date middlenumbindingtime) {
        this.middlenumbindingtime = middlenumbindingtime;
    }

    public Date getDeliverinputtime() {
        return deliverinputtime;
    }

    public void setDeliverinputtime(Date deliverinputtime) {
        this.deliverinputtime = deliverinputtime;
    }

    public String getBusinesstype() {
        return businesstype;
    }

    public void setBusinesstype(String businesstype) {
        this.businesstype = businesstype;
    }

    public String getVendervipcardid() {
        return vendervipcardid;
    }

    public void setVendervipcardid(String vendervipcardid) {
        this.vendervipcardid = vendervipcardid;
    }

    public String getOrderinvoiceopenmark() {
        return orderinvoiceopenmark;
    }

    public void setOrderinvoiceopenmark(String orderinvoiceopenmark) {
        this.orderinvoiceopenmark = orderinvoiceopenmark;
    }

    public String getSrcordertype() {
        return srcordertype;
    }

    public void setSrcordertype(String srcordertype) {
        this.srcordertype = srcordertype;
    }

    public String getSrcorderid() {
        return srcorderid;
    }

    public void setSrcorderid(String srcorderid) {
        this.srcorderid = srcorderid;
    }

    public String getInvoiceformtype() {
        return invoiceformtype;
    }

    public void setInvoiceformtype(String invoiceformtype) {
        this.invoiceformtype = invoiceformtype;
    }

    public String getInvoicetitle() {
        return invoicetitle;
    }

    public void setInvoicetitle(String invoicetitle) {
        this.invoicetitle = invoicetitle;
    }

    public String getInvoicedutyno() {
        return invoicedutyno;
    }

    public void setInvoicedutyno(String invoicedutyno) {
        this.invoicedutyno = invoicedutyno;
    }

    public String getInvoicemail() {
        return invoicemail;
    }

    public void setInvoicemail(String invoicemail) {
        this.invoicemail = invoicemail;
    }

    public String getInvoicemoney() {
        return invoicemoney;
    }

    public void setInvoicemoney(String invoicemoney) {
        this.invoicemoney = invoicemoney;
    }

    public String getInvoicecontent() {
        return invoicecontent;
    }

    public void setInvoicecontent(String invoicecontent) {
        this.invoicecontent = invoicecontent;
    }

    public String getInvoiceaddress() {
        return invoiceaddress;
    }

    public void setInvoiceaddress(String invoiceaddress) {
        this.invoiceaddress = invoiceaddress;
    }

    public String getInvoicetelno() {
        return invoicetelno;
    }

    public void setInvoicetelno(String invoicetelno) {
        this.invoicetelno = invoicetelno;
    }

    public String getInvoicebankname() {
        return invoicebankname;
    }

    public void setInvoicebankname(String invoicebankname) {
        this.invoicebankname = invoicebankname;
    }

    public String getInvoiceaccountno() {
        return invoiceaccountno;
    }

    public void setInvoiceaccountno(String invoiceaccountno) {
        this.invoiceaccountno = invoiceaccountno;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


}