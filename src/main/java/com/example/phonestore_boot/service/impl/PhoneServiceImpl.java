package com.example.phonestore_boot.service.impl;

import com.example.phonestore_boot.entity.PhoneCategory;
import com.example.phonestore_boot.entity.PhoneInfo;
import com.example.phonestore_boot.entity.PhoneSpecs;
import com.example.phonestore_boot.enums.ResultEnums;
import com.example.phonestore_boot.exception.PhoneException;
import com.example.phonestore_boot.repository.PhoneCategoryRepository;
import com.example.phonestore_boot.repository.PhoneInfoRepository;
import com.example.phonestore_boot.repository.PhoneSpecsRepository;
import com.example.phonestore_boot.service.PhoneService;
import com.example.phonestore_boot.util.PhoneUtil;
import com.example.phonestore_boot.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PhoneServiceImpl implements PhoneService {

    @Autowired
    private PhoneCategoryRepository phoneCategoryRepository;

    @Autowired
    private PhoneInfoRepository phoneInfoRepository;

    @Autowired
    private PhoneSpecsRepository phoneSpecsRepository;

    @Override
    public DataVo findDataVo(){
        DataVo dataVo = new DataVo();
        List<PhoneCategory> phoneCategoryList = phoneCategoryRepository.findAll();

        //常规写法
        //List<PhoneCategoryVo> phoneCategoryVoList = new ArrayList<>();
        //for (PhoneCategory phoneCategory : phoneCategoryList) {
        //    PhoneCategoryVo phoneCategoryVo = new PhoneCategoryVo();
        //    phoneCategoryVo.setCategoryName(phoneCategory.getCategoryName());
        //    phoneCategoryVo.setCategoryType(phoneCategory.getCategoryType());
        //    phoneCategoryVoList.add(phoneCategoryVo);
        //}

        //stream
        List<PhoneCategoryVo> phoneCategoryVoList = phoneCategoryList.stream()
                .map(e -> new PhoneCategoryVo(e.getCategoryName(),e.getCategoryType()))
                .collect(Collectors.toList());
        dataVo.setCategories(phoneCategoryVoList);

        //手机
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(phoneCategoryList.get(0).getCategoryType());

        //常规写法
        //List<PhoneInfoVo> phoneInfoVoList = new ArrayList<>();
        //for (PhoneInfo phoneInfo : phoneInfoList) {
        //    PhoneInfoVo phoneInfoVo = new PhoneInfoVo();
        //    BeanUtils.copyProperties(phoneInfo, phoneInfoVo);
        //    phoneInfoVo.setTag(PhoneUtil.creatTag(phoneInfo.getPhoneTag()));
        //    phoneInfoVoList.add(phoneInfoVo);
        //}

        List<PhoneInfoVo> phoneInfoVoList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVo(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.creatTag(e.getPhoneTag()),
                        e.getPhoneIcon()))
                .collect(Collectors.toList());
        dataVo.setPhones(phoneInfoVoList);

        return dataVo;
    }


    @Override
    public List<PhoneInfoVo> findPhoneInfoVoByCategoryType(Integer categoryType) {
        List<PhoneInfo> phoneInfoList = phoneInfoRepository.findAllByCategoryType(categoryType);
        List<PhoneInfoVo> phoneInfoVoList = phoneInfoList.stream()
                .map(e -> new PhoneInfoVo(
                        e.getPhoneId(),
                        e.getPhoneName(),
                        e.getPhonePrice()+".00",
                        e.getPhoneDescription(),
                        PhoneUtil.creatTag(e.getPhoneTag()),
                        e.getPhoneIcon()))
                .collect(Collectors.toList());
        return phoneInfoVoList;
    }

    @Override
    public SpecsPackageVo findSpecsByPhoneId(Integer phoneId) {
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneId).get();
        List<PhoneSpecs> phoneSpecs = phoneSpecsRepository.findAllByPhoneId(phoneId);

        //tree
        List<PhoneSpecsVo> phoneSpecsVoList = new ArrayList<>();
        List<PhoneSpecsCasVo> phoneSpecsCasVoList = new ArrayList<>();
        PhoneSpecsVo phoneSpecsVo;
        PhoneSpecsCasVo phoneSpecsCasVo;
        for (PhoneSpecs phoneSpec : phoneSpecs) {
            phoneSpecsVo = new PhoneSpecsVo();
            phoneSpecsCasVo = new PhoneSpecsCasVo();
            BeanUtils.copyProperties(phoneSpec,phoneSpecsVo);
            BeanUtils.copyProperties(phoneSpec,phoneSpecsCasVo);
            phoneSpecsVoList.add(phoneSpecsVo);
            phoneSpecsCasVoList.add(phoneSpecsCasVo);

        }
        TreeVo treeVo = new TreeVo();
        treeVo.setV(phoneSpecsVoList);
        List<TreeVo> treeVoList = new ArrayList<>();
        treeVoList.add(treeVo);

        SkuVo skuVo = new SkuVo();
        Integer price = phoneInfo.getPhonePrice().intValue();
        skuVo.setPrice(price + ".00");
        skuVo.setStock_num(phoneInfo.getPhoneStock());
        skuVo.setTree(treeVoList);
        skuVo.setList(phoneSpecsCasVoList);


        SpecsPackageVo specsPackageVo = new SpecsPackageVo();
        specsPackageVo.setSku(skuVo);
        Map<String,String> goods = new HashMap<>();
        goods.put("picture",phoneInfo.getPhoneIcon());
        specsPackageVo.setGoods(goods);
        return specsPackageVo;
    }

    @Override
    public void subStock(Integer specsId, Integer quantity) {
        PhoneSpecs phoneSpecs = phoneSpecsRepository.findById(specsId).get();
        PhoneInfo phoneInfo = phoneInfoRepository.findById(phoneSpecs.getPhoneId()).get();
        Integer result = phoneSpecs.getSpecsStock() - quantity;
        if ( result < 0 ){
            log.error("【扣库存】库存不足");
            throw new PhoneException(ResultEnums.PHONE_STOCK_ERROR);
        }
        phoneSpecs.setSpecsStock(result);
        phoneSpecsRepository.save(phoneSpecs);

        result = phoneInfo.getPhoneStock() - quantity;

        if ( result < 0 ){
            log.error("【扣库存】库存不足");
            throw new PhoneException(ResultEnums.PHONE_STOCK_ERROR);
        }

        phoneInfo.setPhoneStock(result);
        phoneInfoRepository.save(phoneInfo);
    }
}
