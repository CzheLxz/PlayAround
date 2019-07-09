package com.hairuo.tbk.service;

import com.alibaba.fastjson.JSONObject;
import com.hairuo.tbk.pojo.RequestObject;

public interface TbkService {

    //淘宝客商品查询
    public JSONObject getTbkItem(RequestObject requestObject);

    // 淘宝客商品关联推荐查询
    public JSONObject getTbkTtemRecommend(RequestObject requestObject);

    //淘宝客商品详情
    public JSONObject getTbkItemInfo(RequestObject requestObject);

    //淘宝客店铺查询
    public JSONObject getTkbShop(RequestObject requestObject);

    //淘宝客店铺关联推荐查询
    public JSONObject getTbkShopRecommend(RequestObject requestObject);

    //枚举正在进行中的定向招商的活动列表
    public JSONObject getTbkUatmEvent(RequestObject requestObject);

    //获取淘宝联盟定向招商的宝贝信息
    public JSONObject getTbkUatmEventItem(RequestObject requestObject);

    //获取淘宝联盟选品库的宝贝信息
    public JSONObject getTbkUatmFavoritesItem(RequestObject requestObject);

    //获取淘宝联盟选品库列表
    public JSONObject getTbkUatmFavorites(RequestObject requestObject);

    //好券清单-导购
    public JSONObject getTbkDgItemCoupon(RequestObject requestObject);

    //淘抢购
    public JSONObject getTbkJuTqg(RequestObject requestObject);

    //阿里妈妈推广券信息查询
    public JSONObject getTbkCoupon(RequestObject requestObject);

    //淘宝客淘口令
    public JSONObject createTbkTpwd(RequestObject requestObject);

    //淘宝客新用户订单-导购
    public JSONObject getTbkDgNewuserOrder(RequestObject requestObject);

    //淘宝客新用户订单-社交
    public JSONObject getTbkScNewuserOrder(RequestObject requestObject);

    //淘宝客物料下行-导购
    public JSONObject materialTbkDgOptimus(RequestObject requestObject);

    //通用物料搜索 导购
    public JSONObject optionalTbkDgMaterial(RequestObject requestObject);

    //拉新活动汇总API-导购
    public JSONObject sumTbkDgNewuserOrder(RequestObject requestObject);

    //拉新活动汇总API-社交
    public JSONObject sumTbkScNewuserOrder(RequestObject requestObject);

    //淘宝客擎天柱通用物料API-社交
    public JSONObject materialTbkScOptimus(RequestObject requestObject);


}
