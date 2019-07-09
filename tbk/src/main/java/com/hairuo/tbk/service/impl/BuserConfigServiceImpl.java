package com.hairuo.tbk.service.impl;

import com.hairuo.tbk.entity.BuserConfig;
import com.hairuo.tbk.repository.BuserConfigRepository;
import com.hairuo.tbk.service.BuserConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("buserConfigService")
public class BuserConfigServiceImpl implements BuserConfigService {

    @Autowired
    private BuserConfigRepository buserConfigRepository;

    @Override
    public BuserConfig getBuserConfigByUserUrl(String url) {
        return buserConfigRepository.getBuserConfigByUserUrl(url);
    }
}
