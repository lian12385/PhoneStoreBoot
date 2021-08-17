package com.example.phonestore_boot.util;

import com.example.phonestore_boot.vo.ResultVo;

public class ResultVoUtil {
    public static ResultVo success(Object data){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        resultVo.setData(data);
        return resultVo;
    }
    public static ResultVo error(String error){
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(1);
        resultVo.setMsg(error);
        resultVo.setData(null);
        return resultVo;
    }
}
