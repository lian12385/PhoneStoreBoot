package com.example.phonestore_boot.handler;

import com.example.phonestore_boot.service.PhoneService;
import com.example.phonestore_boot.util.ResultVoUtil;
import com.example.phonestore_boot.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/phone")
public class PhoneHandler {

    @Autowired
    PhoneService phoneService;

    @GetMapping("/index")
    public ResultVo index(){
        return ResultVoUtil.success(phoneService.findDataVo());
    }

    @GetMapping("/findByCategoryType/{categoryType}")
    public ResultVo findByCategoryType(
            @PathVariable("categoryType") Integer categoryType){
        return ResultVoUtil.success(phoneService.findPhoneInfoVoByCategoryType(categoryType));
    }

    @GetMapping("/findSpecsByPhoneId/{phoneId}")
    public ResultVo findSpecsByPhoneId(
            @PathVariable("phoneId") Integer phoneId){
        return ResultVoUtil.success(phoneService.findSpecsByPhoneId(phoneId));
    }
}
