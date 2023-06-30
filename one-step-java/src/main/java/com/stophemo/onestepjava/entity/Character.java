package com.stophemo.onestepjava.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("character")
public class Character {

    @TableId("characterNo")
    private String characterNo;

    @TableField("characterName")
    private String characterName;

    @TableField("characterOrder")
    private Integer characterOrder;
}
