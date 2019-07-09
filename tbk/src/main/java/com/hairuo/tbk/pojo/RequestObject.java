package com.hairuo.tbk.pojo;

import java.util.Date;

/**
 * @Description 淘宝客API 请求参数对象保存所有参数
 * @Author czhe
 */
public class RequestObject {

    private String sessionKey;//用户授权密钥
    private String fields;//需要返回的字段列表
    private String q;//查询词
    private String cat;//后台类目ID 用,分割
    private String itemloc;//所在地
    private String sort;//排序
    private Boolean is_tmall;//是否为商城商品
    private Boolean is_overseas;//是否为海外商品
    private Long start_price;//折扣价下限
    private Long end_price;//折扣价上限
    private Long start_tk_rate;//淘客佣金比率上限
    private Long end_tk_rate;//淘客佣金比率下限
    private Long platform;//链接形式 1:pc 2:无线 默认:1
    private Long page_no;//第几页
    private Long page_size;//页大小
    private Long num_iid;//商品Id
    private Long count;//返回数量 default:20 max:40
    private String num_iids;//商品ID串 用,分割 最大40
    private String ip;//ip地址
    private Long start_credit;//信用等级下限
    private Long end_credit;//信用等级上限
    private Long start_commission_rate;//淘客佣金比率下限
    private Long end_commission_rate;//淘客佣金比率上限
    private Long start_total_action;//店铺商品总数下限
    private Long end_total_action;//店铺商品总数上限
    private Long start_auction_count;//累计推广商品下限
    private Long end_auction_count;//累计推广商品上限
    private Long user_id;//卖家ID
    private Long adzone_id;//推广位Id
    private Long event_id;//招商活动Id
    private String unid;//自定义自定义输入串区分不同的推广渠道
    private Long favorites_id;//选品库Id
    private Long type;//默认值-1 选品库类型1：普通选品组，2：高佣选品组
    private Date start_time;//最早开团时间
    private Date end_time;//最晚开团时间
    private String me;//带券ID与商品ID的加密串
    private Long item_id;//商品ID
    private String activity_id;//劵ID活动ID共用
    private String tuser_id;//生成口令的淘宝用户ID
    private String text;//口令弹框内容
    private String url;//口令跳转目标页
    private String logo;//口令弹框logoURL
    private String ext;//扩展字段JSON格式
    private Long site_id;//mm_xxx_xxx_xxx的第二位
    private Long material_id;//官方的物料Id
    private String device_value;//设备号加密后的值
    private String device_encrypt;//设备号加密类型：MD5
    private String device_type;//设备号类型:IMEI,或者IDFA,或者UTDID
    private String settle_month;//结算月份 需按照活动的结算月份传入具体的值:201807
    private Long start_dsr;//店铺dsr评分，筛选高于等于当前设置的店铺dsr评分的商品0-50000之间
    private Boolean has_coupon;//是否有优惠券
    private Boolean need_free_shipment;//是否包邮
    private Boolean need_prepay;//是否加入消费者保障
    private Boolean include_pay_rate_30;//成交转化是否高于行业均值
    private Boolean include_good_rate;//好评率是否高于行业均值
    private Boolean include_rfd_rate;//退款率是否低于行业均值
    private Long npx_level;//牛皮癣程度 取值：1:不限2:无3:轻微
    private Long start_ka_tk_rate;//KA媒体淘客佣金比率下限
    private Long end_ka_tk_rate;//KA媒体淘客佣金比率上限

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public Long getStart_dsr() {
        return start_dsr;
    }

    public void setStart_dsr(Long start_dsr) {
        this.start_dsr = start_dsr;
    }

    public Boolean getHas_coupon() {
        return has_coupon;
    }

    public void setHas_coupon(Boolean has_coupon) {
        this.has_coupon = has_coupon;
    }

    public Boolean getNeed_free_shipment() {
        return need_free_shipment;
    }

    public void setNeed_free_shipment(Boolean need_free_shipment) {
        this.need_free_shipment = need_free_shipment;
    }

    public Boolean getNeed_prepay() {
        return need_prepay;
    }

    public void setNeed_prepay(Boolean need_prepay) {
        this.need_prepay = need_prepay;
    }

    public Boolean getInclude_pay_rate_30() {
        return include_pay_rate_30;
    }

    public void setInclude_pay_rate_30(Boolean include_pay_rate_30) {
        this.include_pay_rate_30 = include_pay_rate_30;
    }

    public Boolean getInclude_good_rate() {
        return include_good_rate;
    }

    public void setInclude_good_rate(Boolean include_good_rate) {
        this.include_good_rate = include_good_rate;
    }

    public Boolean getInclude_rfd_rate() {
        return include_rfd_rate;
    }

    public void setInclude_rfd_rate(Boolean include_rfd_rate) {
        this.include_rfd_rate = include_rfd_rate;
    }

    public Long getNpx_level() {
        return npx_level;
    }

    public void setNpx_level(Long npx_level) {
        this.npx_level = npx_level;
    }

    public Long getStart_ka_tk_rate() {
        return start_ka_tk_rate;
    }

    public void setStart_ka_tk_rate(Long start_ka_tk_rate) {
        this.start_ka_tk_rate = start_ka_tk_rate;
    }

    public Long getEnd_ka_tk_rate() {
        return end_ka_tk_rate;
    }

    public void setEnd_ka_tk_rate(Long end_ka_tk_rate) {
        this.end_ka_tk_rate = end_ka_tk_rate;
    }

    public String getSettle_month() {
        return settle_month;
    }

    public void setSettle_month(String settle_month) {
        this.settle_month = settle_month;
    }

    public Long getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Long material_id) {
        this.material_id = material_id;
    }

    public String getDevice_value() {
        return device_value;
    }

    public void setDevice_value(String device_value) {
        this.device_value = device_value;
    }

    public String getDevice_encrypt() {
        return device_encrypt;
    }

    public void setDevice_encrypt(String device_encrypt) {
        this.device_encrypt = device_encrypt;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public Long getSite_id() {
        return site_id;
    }

    public void setSite_id(Long site_id) {
        this.site_id = site_id;
    }

    public String getTuser_id() {
        return tuser_id;
    }

    public void setTuser_id(String tuser_id) {
        this.tuser_id = tuser_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getMe() {
        return me;
    }

    public void setMe(String me) {
        this.me = me;
    }

    public Long getItem_id() {
        return item_id;
    }

    public void setItem_id(Long item_id) {
        this.item_id = item_id;
    }

    public String getActivity_id() {
        return activity_id;
    }

    public void setActivity_id(String activity_id) {
        this.activity_id = activity_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getFavorites_id() {
        return favorites_id;
    }

    public void setFavorites_id(Long favorites_id) {
        this.favorites_id = favorites_id;
    }

    public Long getAdzone_id() {
        return adzone_id;
    }

    public void setAdzone_id(Long adzone_id) {
        this.adzone_id = adzone_id;
    }

    public Long getEvent_id() {
        return event_id;
    }

    public void setEvent_id(Long event_id) {
        this.event_id = event_id;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getStart_credit() {
        return start_credit;
    }

    public void setStart_credit(Long start_credit) {
        this.start_credit = start_credit;
    }

    public Long getEnd_credit() {
        return end_credit;
    }

    public void setEnd_credit(Long end_credit) {
        this.end_credit = end_credit;
    }

    public Long getStart_commission_rate() {
        return start_commission_rate;
    }

    public void setStart_commission_rate(Long start_commission_rate) {
        this.start_commission_rate = start_commission_rate;
    }

    public Long getEnd_commission_rate() {
        return end_commission_rate;
    }

    public void setEnd_commission_rate(Long end_commission_rate) {
        this.end_commission_rate = end_commission_rate;
    }

    public Long getStart_total_action() {
        return start_total_action;
    }

    public void setStart_total_action(Long start_total_action) {
        this.start_total_action = start_total_action;
    }

    public Long getEnd_total_action() {
        return end_total_action;
    }

    public void setEnd_total_action(Long end_total_action) {
        this.end_total_action = end_total_action;
    }

    public Long getStart_auction_count() {
        return start_auction_count;
    }

    public void setStart_auction_count(Long start_auction_count) {
        this.start_auction_count = start_auction_count;
    }

    public Long getEnd_auction_count() {
        return end_auction_count;
    }

    public void setEnd_auction_count(Long end_auction_count) {
        this.end_auction_count = end_auction_count;
    }

    public String getNum_iids() {
        return num_iids;
    }

    public void setNum_iids(String num_iids) {
        this.num_iids = num_iids;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Long getNum_iid() {
        return num_iid;
    }

    public void setNum_iid(Long num_iid) {
        this.num_iid = num_iid;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getItemloc() {
        return itemloc;
    }

    public void setItemloc(String itemloc) {
        this.itemloc = itemloc;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Boolean getIs_tmall() {
        return is_tmall;
    }

    public void setIs_tmall(Boolean is_tmall) {
        this.is_tmall = is_tmall;
    }

    public Boolean getIs_overseas() {
        return is_overseas;
    }

    public void setIs_overseas(Boolean is_overseas) {
        this.is_overseas = is_overseas;
    }

    public Long getStart_price() {
        return start_price;
    }

    public void setStart_price(Long start_price) {
        this.start_price = start_price;
    }

    public Long getEnd_price() {
        return end_price;
    }

    public void setEnd_price(Long end_price) {
        this.end_price = end_price;
    }

    public Long getStart_tk_rate() {
        return start_tk_rate;
    }

    public void setStart_tk_rate(Long start_tk_rate) {
        this.start_tk_rate = start_tk_rate;
    }

    public Long getEnd_tk_rate() {
        return end_tk_rate;
    }

    public void setEnd_tk_rate(Long end_tk_rate) {
        this.end_tk_rate = end_tk_rate;
    }

    public Long getPlatform() {
        return platform;
    }

    public void setPlatform(Long platform) {
        this.platform = platform;
    }

    public Long getPage_no() {
        return page_no;
    }

    public void setPage_no(Long page_no) {
        this.page_no = page_no;
    }

    public Long getPage_size() {
        return page_size;
    }

    public void setPage_size(Long page_size) {
        this.page_size = page_size;
    }
}
