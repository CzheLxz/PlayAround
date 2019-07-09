package com.hairuo.tbk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.hairuo.tbk.pojo.RequestObject;
import com.hairuo.tbk.service.TbkService;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.*;
import com.taobao.api.response.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author czhe
 * @description AllTbkAPI
 * @create 2018-09-10 17:00
 */
@Service
public class TbkApiServiceImpl implements TbkService {

    static final String URL = "http://gw.api.taobao.com/router/rest";
    static final String APPKEY = "24879023";
    static final String SECRET = "62620e7b5d55d938d11c537d1a16fb6f";

    @Override
    public JSONObject getTbkItem(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkItemGetRequest req = new TbkItemGetRequest();
        if (StringUtils.isBlank(requestObject.getFields()) || StringUtils.isBlank(requestObject.getQ())) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setFields(requestObject.getFields());
        }
        if (StringUtils.isNotBlank(requestObject.getQ())) {
            req.setQ(requestObject.getQ());
        }
        if (StringUtils.isNotBlank(requestObject.getCat())) {
            req.setCat(requestObject.getCat());
        }
        if (StringUtils.isNotBlank(requestObject.getItemloc())) {
            req.setItemloc(requestObject.getItemloc());
        }
        if (StringUtils.isNotBlank(requestObject.getSort())) {
            req.setSort(requestObject.getSort());
        }
        if (requestObject.getIs_tmall() != null) {
            req.setIsTmall(requestObject.getIs_tmall());
        }
        if (requestObject.getIs_overseas() != null) {
            req.setIsOverseas(requestObject.getIs_overseas());
        }
        if (requestObject.getStart_price() != null) {
            req.setStartPrice(requestObject.getStart_price());
        }
        if (requestObject.getEnd_price() != null) {
            req.setEndPrice(requestObject.getEnd_price());
        }
        if (requestObject.getStart_tk_rate() != null) {
            req.setStartTkRate(requestObject.getStart_tk_rate());
        }
        if (requestObject.getEnd_tk_rate() != null) {
            req.setEndTkRate(requestObject.getEnd_tk_rate());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        try {
            TbkItemGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkTtemRecommend(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkItemRecommendGetRequest req = new TbkItemRecommendGetRequest();
        if (StringUtils.isBlank(requestObject.getFields()) || requestObject.getNum_iid() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setFields(requestObject.getFields());
            req.setNumIid(requestObject.getNum_iid());
        }
        if (requestObject.getCount() != null) {
            req.setCount(requestObject.getCount());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        try {
            TbkItemRecommendGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JSONObject getTbkItemInfo(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkItemInfoGetRequest req = new TbkItemInfoGetRequest();
        if (StringUtils.isBlank(requestObject.getNum_iids())) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setNumIids(requestObject.getNum_iids());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        if (StringUtils.isNotBlank(requestObject.getIp())) {
            req.setIp(requestObject.getIp());
        }
        try {
            TbkItemInfoGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JSONObject getTkbShop(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkShopGetRequest req = new TbkShopGetRequest();
        if (StringUtils.isBlank(requestObject.getFields()) || StringUtils.isBlank(requestObject.getQ())) {
            throw new RuntimeException("缺少必要参数");
        } else {
            req.setFields(requestObject.getFields());
            req.setQ(requestObject.getQ());
        }
        if (StringUtils.isNotBlank(requestObject.getSort())) {
            req.setSort(requestObject.getSort());
        }
        if (requestObject.getIs_tmall() != null) {
            req.setIsTmall(requestObject.getIs_tmall());
        }
        if (requestObject.getStart_credit() != null) {
            req.setStartCredit(requestObject.getStart_credit());
        }
        if (requestObject.getEnd_credit() != null) {
            req.setEndCredit(requestObject.getEnd_credit());
        }
        if (requestObject.getStart_commission_rate() != null) {
            req.setStartCommissionRate(requestObject.getStart_commission_rate());
        }
        if (requestObject.getEnd_commission_rate() != null) {
            req.setEndCommissionRate(requestObject.getEnd_commission_rate());
        }
        if (requestObject.getStart_total_action() != null) {
            req.setStartTotalAction(requestObject.getStart_total_action());
        }
        if (requestObject.getEnd_total_action() != null) {
            req.setEndTotalAction(requestObject.getEnd_total_action());
        }
        if (requestObject.getStart_auction_count() != null) {
            req.setStartAuctionCount(requestObject.getStart_auction_count());
        }
        if (requestObject.getEnd_auction_count() != null) {
            req.setEndAuctionCount(requestObject.getEnd_auction_count());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        try {
            TbkShopGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkShopRecommend(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkShopRecommendGetRequest req = new TbkShopRecommendGetRequest();
        if (StringUtils.isBlank(requestObject.getFields()) || requestObject.getUser_id() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setFields(requestObject.getFields());
            req.setUserId(requestObject.getUser_id());
        }
        if (requestObject.getCount() != null) {
            req.setCount(requestObject.getCount());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        try {
            TbkShopRecommendGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkUatmEvent(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkUatmEventGetRequest req = new TbkUatmEventGetRequest();
        if (StringUtils.isBlank(requestObject.getFields())) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setFields(requestObject.getFields());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        try {
            TbkUatmEventGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkUatmEventItem(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkUatmEventItemGetRequest req = new TbkUatmEventItemGetRequest();
        if (StringUtils.isBlank(requestObject.getFields()) || requestObject.getAdzone_id() == null || requestObject.getEvent_id() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setFields(requestObject.getFields());
            req.setAdzoneId(requestObject.getAdzone_id());
            req.setEventId(requestObject.getEvent_id());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (StringUtils.isNotBlank(requestObject.getUnid())) {
            req.setUnid(requestObject.getUnid());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        try {
            TbkUatmEventItemGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public JSONObject getTbkUatmFavoritesItem(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkUatmFavoritesItemGetRequest req = new TbkUatmFavoritesItemGetRequest();
        if (StringUtils.isBlank(requestObject.getFields()) || requestObject.getAdzone_id() == null || requestObject.getFavorites_id() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setFields(requestObject.getFields());
            req.setAdzoneId(requestObject.getAdzone_id());
            req.setFavoritesId(requestObject.getFavorites_id());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (StringUtils.isNotBlank(requestObject.getUnid())) {
            req.setUnid(requestObject.getUnid());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        try {
            TbkUatmFavoritesItemGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkUatmFavorites(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkUatmFavoritesGetRequest req = new TbkUatmFavoritesGetRequest();
        if (StringUtils.isBlank(requestObject.getFields())) {
            throw new RuntimeException("缺少必要参数");
        } else {
            req.setFields(requestObject.getFields());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getType() != null) {
            req.setType(requestObject.getType());
        }
        try {
            TbkUatmFavoritesGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkDgItemCoupon(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkDgItemCouponGetRequest req = new TbkDgItemCouponGetRequest();
        if (requestObject.getAdzone_id() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setAdzoneId(requestObject.getAdzone_id());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        if (StringUtils.isNotBlank(requestObject.getCat())) {
            req.setCat(requestObject.getCat());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (StringUtils.isNotBlank(requestObject.getQ())) {
            req.setQ(requestObject.getQ());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        try {
            TbkDgItemCouponGetResponse rsp = client.execute(req);
            JSONObject josn = JSONObject.parseObject(rsp.getBody());
            return josn;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkJuTqg(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkJuTqgGetRequest req = new TbkJuTqgGetRequest();
        if (StringUtils.isBlank(requestObject.getFields()) || requestObject.getAdzone_id() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setAdzoneId(requestObject.getAdzone_id());
            req.setFields(requestObject.getFields());
        }
        if (requestObject.getStart_time() == null || requestObject.getEnd_time() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setStartTime(requestObject.getStart_time());
            req.setEndTime(requestObject.getEnd_time());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        try {
            TbkJuTqgGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkCoupon(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkCouponGetRequest req = new TbkCouponGetRequest();
        if (StringUtils.isNotBlank(requestObject.getMe())) {
            req.setMe(requestObject.getMe());
        }
        if (StringUtils.isNotBlank(requestObject.getActivity_id())) {
            req.setActivityId(requestObject.getActivity_id());
        }
        if (requestObject.getItem_id() != null) {
            req.setItemId(requestObject.getItem_id());
        }
        try {
            TbkCouponGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject createTbkTpwd(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkTpwdCreateRequest req = new TbkTpwdCreateRequest();
        if (StringUtils.isBlank(requestObject.getText()) || StringUtils.isBlank(requestObject.getUrl())) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setText(requestObject.getText());
            req.setUrl(requestObject.getUrl());
        }
        if (StringUtils.isNotBlank(requestObject.getTuser_id())) {
            req.setUserId(requestObject.getTuser_id());
        }
        if (StringUtils.isNotBlank(requestObject.getLogo())) {
            req.setLogo(requestObject.getLogo());
        }
        if (StringUtils.isNotBlank(requestObject.getExt())) {
            req.setExt(requestObject.getExt());
        }
        try {
            TbkTpwdCreateResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;

        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkDgNewuserOrder(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkDgNewuserOrderGetRequest req = new TbkDgNewuserOrderGetRequest();
        if (StringUtils.isBlank(requestObject.getActivity_id())) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setActivityId(requestObject.getActivity_id());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getAdzone_id() != null) {
            req.setAdzoneId(requestObject.getAdzone_id());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getStart_time() != null) {
            req.setStartTime(requestObject.getStart_time());
        }
        if (requestObject.getEnd_time() != null) {
            req.setEndTime(requestObject.getEnd_time());
        }
        try {
            TbkDgNewuserOrderGetResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject getTbkScNewuserOrder(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkScNewuserOrderGetRequest req = new TbkScNewuserOrderGetRequest();
        if (StringUtils.isBlank(requestObject.getActivity_id())) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setActivityId(requestObject.getActivity_id());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getAdzone_id() != null) {
            req.setAdzoneId(requestObject.getAdzone_id());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getSite_id() != null) {
            req.setSiteId(requestObject.getSite_id());
        }
        if (requestObject.getEnd_time() != null) {
            req.setEndTime(requestObject.getEnd_time());
        }
        if (requestObject.getStart_time() != null) {
            req.setStartTime(requestObject.getStart_time());
        }
        try {
            TbkScNewuserOrderGetResponse rsp = client.execute(req, requestObject.getSessionKey());
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject materialTbkDgOptimus(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkDgOptimusMaterialRequest req = new TbkDgOptimusMaterialRequest();
        if (requestObject.getAdzone_id() != null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setAdzoneId(requestObject.getAdzone_id());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getMaterial_id() != null) {
            req.setMaterialId(requestObject.getMaterial_id());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_value())) {
            req.setDeviceValue(requestObject.getDevice_value());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_encrypt())) {
            req.setDeviceEncrypt(requestObject.getDevice_encrypt());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_type())) {
            req.setDeviceType(requestObject.getDevice_type());
        }
        try {
            TbkDgOptimusMaterialResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject optionalTbkDgMaterial(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkDgMaterialOptionalRequest req = new TbkDgMaterialOptionalRequest();
        if (requestObject.getAdzone_id() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setAdzoneId(requestObject.getAdzone_id());
        }
        if (StringUtils.isBlank(requestObject.getQ()) && StringUtils.isBlank(requestObject.getCat())) {
            throw new RuntimeException("参数:q与cat不能同时都为空");
        } else {
            if (StringUtils.isNotBlank(requestObject.getQ())) {
                req.setQ(requestObject.getQ());
            }
            if (StringUtils.isNotBlank(requestObject.getCat())) {
                req.setCat(requestObject.getCat());
            }
        }
        if (requestObject.getStart_dsr() != null) {
            req.setStartDsr(requestObject.getStart_dsr());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getPlatform() != null) {
            req.setPlatform(requestObject.getPlatform());
        }
        if (requestObject.getEnd_tk_rate() != null) {
            req.setEndTkRate(requestObject.getEnd_tk_rate());
        }
        if (requestObject.getStart_tk_rate() != null) {
            req.setStartTkRate(requestObject.getStart_tk_rate());
        }
        if (requestObject.getEnd_price() != null) {
            req.setEndPrice(requestObject.getEnd_price());
        }
        if (requestObject.getStart_price() != null) {
            req.setStartPrice(requestObject.getStart_price());
        }
        if (requestObject.getIs_overseas() != null) {
            req.setIsOverseas(requestObject.getIs_overseas());
        }
        if (requestObject.getIs_tmall() != null) {
            req.setIsTmall(requestObject.getIs_tmall());
        }
        if (StringUtils.isNotBlank(requestObject.getSort())) {
            req.setSort(requestObject.getSort());
        }
        if (StringUtils.isNotBlank(requestObject.getItemloc())) {
            req.setItemloc(requestObject.getItemloc());
        }
        if (requestObject.getMaterial_id() != null) {
            req.setMaterialId(requestObject.getMaterial_id());
        }
        if (StringUtils.isNotBlank(requestObject.getIp())) {
            req.setIp(requestObject.getIp());
        }
        if (requestObject.getHas_coupon() != null) {
            req.setHasCoupon(requestObject.getHas_coupon());
        }
        if (requestObject.getNeed_free_shipment() != null) {
            req.setNeedFreeShipment(requestObject.getNeed_free_shipment());
        }
        if (requestObject.getNeed_prepay() != null) {
            req.setNeedPrepay(requestObject.getNeed_prepay());
        }
        if (requestObject.getInclude_pay_rate_30() != null) {
            req.setIncludePayRate30(requestObject.getInclude_pay_rate_30());
        }
        if (requestObject.getInclude_good_rate() != null) {
            req.setIncludeGoodRate(requestObject.getInclude_good_rate());
        }
        if (requestObject.getInclude_rfd_rate() != null) {
            req.setIncludeRfdRate(requestObject.getInclude_rfd_rate());
        }
        if (requestObject.getNpx_level() != null) {
            req.setNpxLevel(requestObject.getNpx_level());
        }
        if (requestObject.getEnd_ka_tk_rate() != null) {
            req.setEndKaTkRate(requestObject.getEnd_ka_tk_rate());
        }
        if (requestObject.getStart_ka_tk_rate() != null) {
            req.setStartTkRate(requestObject.getStart_ka_tk_rate());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_encrypt())) {
            req.setDeviceEncrypt(requestObject.getDevice_encrypt());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_value())) {
            req.setDeviceValue(requestObject.getDevice_value());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_type())) {
            req.setDeviceType(requestObject.getDevice_type());
        }
        try {
            TbkDgMaterialOptionalResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject sumTbkDgNewuserOrder(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkDgNewuserOrderSumRequest req = new TbkDgNewuserOrderSumRequest();
        if (StringUtils.isBlank(requestObject.getActivity_id()) || requestObject.getPage_no() == null || requestObject.getPage_size() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setActivityId(requestObject.getActivity_id());
            req.setPageNo(requestObject.getPage_no());
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getAdzone_id() != null) {
            req.setAdzoneId(requestObject.getAdzone_id());
        }
        if (requestObject.getSite_id() != null) {
            req.setSiteId(requestObject.getSite_id());
        }
        if (StringUtils.isNotBlank(requestObject.getSettle_month())) {
            req.setSettleMonth(requestObject.getSettle_month());
        }
        try {
            TbkDgNewuserOrderSumResponse rsp = client.execute(req);
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject sumTbkScNewuserOrder(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkScNewuserOrderSumRequest req = new TbkScNewuserOrderSumRequest();
        if (StringUtils.isBlank(requestObject.getActivity_id()) || requestObject.getPage_no() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setActivityId(requestObject.getActivity_id());
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getPage_size() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getAdzone_id() != null) {
            req.setAdzoneId(requestObject.getAdzone_id());
        }
        if (requestObject.getSite_id() != null) {
            req.setSiteId(requestObject.getSite_id());
        }
        if (StringUtils.isNotBlank(requestObject.getSettle_month())) {
            req.setSettleMonth(requestObject.getSettle_month());
        }
        try {
            TbkScNewuserOrderSumResponse rsp = client.execute(req, requestObject.getSessionKey());
            JSONObject json = JSONObject.parseObject(rsp.getBody());
            return json;
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JSONObject materialTbkScOptimus(RequestObject requestObject) {
        TaobaoClient client = new DefaultTaobaoClient(URL, APPKEY, SECRET);
        TbkScOptimusMaterialRequest req = new TbkScOptimusMaterialRequest();
        if (requestObject.getAdzone_id() == null || requestObject.getSite_id() == null) {
            throw new RuntimeException("缺少必要的参数");
        } else {
            req.setAdzoneId(requestObject.getAdzone_id());
            req.setSiteId(requestObject.getSite_id());
        }
        if (requestObject.getPage_size() != null) {
            req.setPageSize(requestObject.getPage_size());
        }
        if (requestObject.getPage_no() != null) {
            req.setPageNo(requestObject.getPage_no());
        }
        if (requestObject.getMaterial_id() != null) {
            req.setMaterialId(requestObject.getMaterial_id());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_type())) {
            req.setDeviceType(requestObject.getDevice_type());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_encrypt())) {
            req.setDeviceEncrypt(requestObject.getDevice_encrypt());
        }
        if (StringUtils.isNotBlank(requestObject.getDevice_value())) {
            req.setDeviceValue(requestObject.getDevice_value());
        }
        try {
            TbkScOptimusMaterialResponse rsp = client.execute(req, requestObject.getSessionKey());
            JSONObject json = JSONObject.parseObject(rsp.getBody());
        } catch (ApiException e) {
            e.printStackTrace();
        }
        return null;

    }
}
