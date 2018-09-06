package com.wt.config;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.Map;

/**
 * <p> 系统常量定义 </p>
 *
 * @author mengk
 * @version 1.0
 * @since JDK 1.7
 */
public interface Const {

    /**
     * 错误的提示信息
     */
    String MESSAGE = "message";

    TypeReference<Map<String, JSONObject>> MAPO_TR = new TypeReference<Map<String, JSONObject>>() {
    };


}
