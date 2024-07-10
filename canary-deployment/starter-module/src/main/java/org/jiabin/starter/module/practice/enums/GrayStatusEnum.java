package org.jiabin.starter.module.practice.enums;


public enum GrayStatusEnum {
    ALL("ALL","可以调用全部版本的服务"),
    PROD("PROD","只能调用生产版本的服务"),
    GRAY("GRAY","只能调用灰度版本的服务");
    GrayStatusEnum(String val, String desc) {
        this.val = val;
        this.desc = desc;
    }
    private String val;
    private String desc;
    public String getVal() {
        return val;
    }
    public static GrayStatusEnum getByVal(String val){
        if(val == null){
            return null;
        }
        for (GrayStatusEnum value : values()) {
            if(value.val.equals(val)){
                return value;
            }
        }
        return null;
    }
}
