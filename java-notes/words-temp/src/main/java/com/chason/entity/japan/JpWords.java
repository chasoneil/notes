package com.chason.entity.japan;

import lombok.Data;

import java.util.Set;

@Data
public class JpWords {

    // 中文含义
    private Set<String> chMeans;

    // 日语中的书写
    private String jpWrite;

    // 日语中的读音拼写
    private String jpRead;

    public JpWords () {}
}
