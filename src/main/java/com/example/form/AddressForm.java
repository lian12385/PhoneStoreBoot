package com.example.form;

import lombok.Data;

import javax.validation.constraints.NotBlank;


@Data
public class AddressForm {
    private Integer id;
    @NotBlank( message = "姓名不能为空")
    private String name;
    @NotBlank( message = "电话不能为空")
    private String tel;
    @NotBlank( message = "省份不能为空")
    private String province;
    @NotBlank( message = "城市不能为空")
    private String city;
    @NotBlank( message = "区不能为空")
    private String country;
    @NotBlank( message = "邮政编码不能为空")
    private String areaCode;
    @NotBlank( message = "纤细地址不能为空")
    private String addressDetail;

    public AddressForm(String name, String tel, String province, String city, String country, String areaCode, String addressDetail) {
        this.name = name;
        this.tel = tel;
        this.province = province;
        this.city = city;
        this.country = country;
        this.areaCode = areaCode;
        this.addressDetail = addressDetail;
    }
}
