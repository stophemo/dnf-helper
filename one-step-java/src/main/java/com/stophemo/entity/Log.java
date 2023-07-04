package com.stophemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Log {
    private Integer id;

    private String ip;

    private String qq;

    private String area;

    private String character;

    private String version;

    private String authDate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;
}
