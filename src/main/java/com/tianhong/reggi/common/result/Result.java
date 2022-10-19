package com.tianhong.reggi.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result<T> {

    private Integer code;

    private String msg;

    private T data;

    private Map map = new HashMap();

    public static <T> Result<T> success(T object){
        Result<T> r = new Result<T>();
        r.data = object;
        r.code = 1;
        return r;
    }

    public static <T> Result<T> error(String msg){
        Result<T> r = new Result<T>();
        r.msg = msg;
        r.code = 0;
        return r;
    }

    public Result<T> add(String key, Object value){
        this.map.put(key, value);
        return this;
    }
}
