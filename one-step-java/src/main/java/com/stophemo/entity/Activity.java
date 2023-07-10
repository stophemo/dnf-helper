package com.stophemo.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Activity {

    private Integer id;

    private String name;

    private String url;

    // 日，短期，长期
    private String type;

//    private List<String> obtainedProps;

    // 开始时间 结束时间 是否过期
    private Date startTime;

    private Date endTime;

    private Boolean isExpired;
}
