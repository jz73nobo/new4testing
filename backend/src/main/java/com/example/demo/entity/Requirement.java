package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 告诉 Spring JPA：“这是一个数据库实体，请为这个类创建一张表。”
@Entity

// Lombok 注解; 自动生成所有字段的 getter、setter、toString()、equals() 和 hashCode() 方法。极大简化了代码。
@Data

// Lombok 注解; 自动生成一个无参构造函数。
@NoArgsConstructor

// Lombok 注解; 自动生成一个包含所有字段的构造函数。
@AllArgsConstructor

public class Requirement {

    // 核心注解: 标记这个字段是表的主键
    @Id
    
    // 指定主键的生成策略为自增（IDENTITY）。
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    // 主键 ID，每条需求的唯一标识。
    private Long id;

    // 需求标题，对应数据库的 VARCHAR 列。
    private String title;

    // 指定 description 字段在数据库中的长度为 2000 字符（默认通常是 255）。
    @Column(length = 2000)

    private String description;
    private String status;
}
