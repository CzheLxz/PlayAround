package com.hairuo.tbk.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {

    /**
     * @Description 图片上传
     */
    public static boolean fileupload(MultipartFile file, String filePath, String fileName) {
        String realPath = filePath + "/" + fileName;
        File dest = new File(realPath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }
        try {
            file.transferTo(dest);
            return true;
        } catch (IllegalStateException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * @Description 获取所有头部和左侧菜单的json文件
     */

    public static JSONArray getAllJson(String url, String userName, int page, int size) {
        JSONArray wares;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        String path = "C:\\" + userName;
        File file = new File(path);
        File[] fs = file.listFiles();
        for (int i = 0; i < fs.length; i++) {
            wares = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("show", fs[i].getName());
            File[] files = fs[i].listFiles();
            for (int n = 0; n < files.length; n++) {
                int k = page * size - size + 1;
                int m = page * size;
                for (int j = k; j <= m; j++) {
                    SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
                    String timeString = time.format(new Date());
                    String fileName = files[n].getPath() + "\\" + timeString + "-" + j + ".json";
                    wares.add(FileUtil.getJson(fileName, url));
                }
            }
            jsonObject.put("wares", wares);
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }

    /**
     * @Description 根据头部菜单名读取json文件
     */
    public static JSONArray getJsonByTop(String url, String userName, String top, int page, int size) {
        JSONArray wares = new JSONArray();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("show", top);
        String path = "C:\\" + userName + "\\" + top;
        File file = new File(path);
        File[] fs = file.listFiles();
        for (int i = 0; i < fs.length; i++) {
            int k = page * size - size + 1;
            int m = page * size;
            for (int j = k; j <= m; j++) {
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
                String timeString = time.format(new Date());
                path = fs[i].getPath() + "\\" + timeString + "-" + j + ".json";
                wares.add(FileUtil.getJson(path, url));
            }
        }
        jsonObject.put("wares", wares);
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    /**
     * @Description 根据左侧菜单名读取json文件
     */
    public static JSONArray getJsonByLeft(String url, String userName, String left, int page, int size) {
        JSONArray wares;
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject;
        String path = "C:\\" + userName;
        File file = new File(path);
        File[] fs = file.listFiles();
        for (int i = 0; i < fs.length; i++) {
            wares = new JSONArray();
            jsonObject = new JSONObject();
            jsonObject.put("show", fs[i].getName());
            int k = page * size - size + 1;
            int m = page * size;
            for (int j = k; j <= m; j++) {
                SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
                String timeString = time.format(new Date());
                path = fs[i].getPath() + "\\" + left + "\\" + timeString + "-" + j + ".json";
                JSONObject object = new JSONObject();
                object = FileUtil.getJson(path, url);
                wares.add(object);
            }
            jsonObject.put("wares", wares);
            jsonArray.add(jsonObject);

        }

        return jsonArray;
    }

    /**
     * @Description 根据头部菜单名和左侧菜单名读取json文件
     */
    public static JSONArray getJsonByTopAndLeft(String url, String userName, String top, String left, int page, int size) {
        JSONArray wares = new JSONArray();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("show", top);
        int k = page * size - size + 1;
        int m = page * size;
        for (int j = k; j <= m; j++) {
            SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
            String timeString = time.format(new Date());
            String path = "C:\\" + userName + "\\" + top + "\\" + left + "\\" + timeString + "-" + j + ".json";
            JSONObject object = new JSONObject();
            object = FileUtil.getJson(path, url);
            wares.add(object);
        }
        jsonObject.put("wares", wares);
        jsonArray.add(jsonObject);
        return jsonArray;
    }

    /**
     * @Description 读取json文件公用方法
     */
    public static JSONObject getJson(String path, String url) {
        JSONObject object = null;
        JSONObject jsonObject = new JSONObject();
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(path);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            object = JSONObject.parseObject(laststr);
            //解析并组装json数据
            JSONObject response = object.getJSONObject("tbk_dg_item_coupon_get_response");
            JSONObject results = response.getJSONObject("results");
            JSONArray coupon = results.getJSONArray("tbk_coupon");
            JSONObject objectIndex = coupon.getJSONObject(0);
            if (objectIndex.getJSONObject("small_images") != null) {
                JSONObject images = objectIndex.getJSONObject("small_images");
                JSONArray string = images.getJSONArray("string");
                jsonObject.put("small_images", string);
            }
            jsonObject.put("shop_title", objectIndex.get("shop_title"));
            jsonObject.put("user_type", objectIndex.get("user_type"));
            jsonObject.put("title", objectIndex.get("title"));
            jsonObject.put("nick", objectIndex.get("nick"));
            jsonObject.put("seller_id", objectIndex.get("seller_id"));
            jsonObject.put("volume", objectIndex.get("volume"));
            jsonObject.put("pict_url", objectIndex.get("pict_url"));
            jsonObject.put("item_url", objectIndex.get("item_url"));
            jsonObject.put("coupon_total_count", objectIndex.get("coupon_total_count"));
            jsonObject.put("commission_rate", objectIndex.get("commission_rate"));
            jsonObject.put("coupon_info", objectIndex.get("coupon_info"));
            jsonObject.put("category", objectIndex.get("category"));
            jsonObject.put("num_iid", objectIndex.get("num_iid"));
            jsonObject.put("coupon_remain_count", objectIndex.get("coupon_remain_count"));
            jsonObject.put("coupon_start_time", objectIndex.get("coupon_start_time"));
            jsonObject.put("coupon_end_time", objectIndex.get("coupon_end_time"));
            jsonObject.put("coupon_click_url", objectIndex.get("coupon_click_url"));
            jsonObject.put("item_description", objectIndex.get("item_description"));
            jsonObject.put("click_url", url + "?file=" + path);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }

    /**
     * @Description 获取指定json文件
     */
    public static JSONObject getJsonByFile(String fileUrl) {
        JSONObject object = null;
        BufferedReader reader = null;
        String laststr = "";
        try {
            FileInputStream fileInputStream = new FileInputStream(fileUrl);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            reader = new BufferedReader(inputStreamReader);
            String tempString = null;
            while ((tempString = reader.readLine()) != null) {
                laststr += tempString;
            }
            object = JSONObject.parseObject(laststr);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return object;
    }

}
