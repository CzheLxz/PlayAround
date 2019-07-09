package com.hairuo.tbk.schedule;

import com.alibaba.fastjson.JSONObject;
import com.hairuo.tbk.entity.BleftMenuConfig;
import com.hairuo.tbk.entity.BtopMenuConfig;
import com.hairuo.tbk.entity.Buser;
import com.hairuo.tbk.repository.BleftMenuConfigRepository;
import com.hairuo.tbk.repository.BtopMenuConfigRepository;
import com.hairuo.tbk.repository.BuserRepository;
import com.hairuo.tbk.pojo.RequestObject;
import com.hairuo.tbk.service.TbkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Description 定时任务每天晚上12点执行
 * @Author czhe
 */
@Component
public class Jobs {

    @Autowired
    private BuserRepository buserRepository;

    @Autowired
    private BtopMenuConfigRepository btopMenuConfigRepository;

    @Autowired
    private BleftMenuConfigRepository bleftMenuConfigRepository;

    @Autowired
    private TbkService tbkService;

    private RequestObject requestObject;

    public final static long ONE_Minute = 600 * 1000;

    @Transactional
    @Scheduled(fixedDelay = ONE_Minute)
    public void cronJob() throws IOException {
        File file;
        String fileName = "C:/";
        //查询用户根据ID以用户名创建文件
        List<Buser> buserList = buserRepository.findAll();
        for (int m = 0; m < buserList.size(); m++) {
            String pathName = fileName + buserList.get(m).getUserName();
            file = new File(pathName);
            file.delete();
            if (!file.exists()) {
                file.mkdir();
            } else {
                delFolder(pathName);//清空文件夹
                file.mkdir();
            }
            //根据用户标识user_uuid获取头部菜单列表以菜单名称创建文件
            List<BtopMenuConfig> btopMenuConfigList = btopMenuConfigRepository.findAllByUserUuid(buserList.get(m).getUserUuid());
            for (int i = 0; i < btopMenuConfigList.size(); i++) {
                file = new File(fileName + buserList.get(m).getUserName() + "/" + btopMenuConfigList.get(i).getbShow());
                if (!file.exists()) {
                    file.mkdir();
                }
                //根据用户标识user_uuid获取左菜单列表以左菜单名创建文件
                List<BleftMenuConfig> bleftMenuConfigList = bleftMenuConfigRepository.findAllByUserUuid(buserList.get(m).getUserUuid());
                for (int j = 0; j < bleftMenuConfigList.size(); j++) {
                    file = new File(fileName + buserList.get(m).getUserName() + "/" + btopMenuConfigList.get(i).getbShow()
                            + "/" + bleftMenuConfigList.get(j).getbShow());
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    //创建保存json数据的文件
                    for (int k = 1; k <= 100; k++) {
                        SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd");
                        String timeString = time.format(new Date());
                        file = new File(fileName + buserList.get(m).getUserName() + "/" + btopMenuConfigList.get(i).getbShow()
                                + "/" + bleftMenuConfigList.get(j).getbShow() + "/" + timeString + "-" + k + ".json");
                        if (!file.exists()) {
                            file.createNewFile();
                        }
                        //调用Api将json对象保存到文件
                        requestObject = new RequestObject();
                        requestObject.setAdzone_id(Long.parseLong(btopMenuConfigList.get(i).getRemark()));
                        requestObject.setQ(bleftMenuConfigList.get(j).getbShow());
                        requestObject.setPage_size(1L);
                        requestObject.setPage_no(new Long(k));
                        JSONObject json = tbkService.getTbkDgItemCoupon(requestObject);
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);
                        bw.write(json.toJSONString());
                        bw.close();
                    }

                }

            }
        }
        System.out.println("------->>>>>>>");
        System.out.println("数据保存完毕------->>>>>>>");
        System.out.println("保存成功------->>>>>>>");
        System.out.println("------->>>>>>>");
    }

    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }

}
