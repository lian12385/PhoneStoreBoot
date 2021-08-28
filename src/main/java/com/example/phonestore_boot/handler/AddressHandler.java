package com.example.phonestore_boot.handler;

import com.example.phonestore_boot.exception.PhoneException;
import com.example.phonestore_boot.form.AddressForm;
import com.example.phonestore_boot.service.AddressService;
import com.example.phonestore_boot.util.ResultVoUtil;
import com.example.phonestore_boot.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping ( "/address" )
public class AddressHandler {

    @Autowired
    private AddressService addressService;

    @GetMapping("list")
    public ResultVo list(){
        return ResultVoUtil.success(addressService.findAll());

    }

    @PostMapping("create")
    public ResultVo create(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【添加地址】参数错误,addressForm={},addressForm");
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVoUtil.success(null);

    }

    @PutMapping("create")
    public ResultVo update(@Valid @RequestBody AddressForm addressForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【修改地址】参数错误,addressForm={},addressForm");
            throw new PhoneException(bindingResult.getFieldError().getDefaultMessage());
        }
        addressService.saveOrUpdate(addressForm);
        return ResultVoUtil.success(null);

    }
}
