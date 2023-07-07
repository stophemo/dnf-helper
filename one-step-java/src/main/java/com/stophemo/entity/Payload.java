package com.stophemo.entity;

import lombok.Data;

@Data
public class Payload {
    private Integer id;

    private Integer activityId;

    private String interfaceUrl;

    private Integer method;

    private String headers;

    private String params;

    private String note;

    private Integer times;

    private Integer timeout;
}
