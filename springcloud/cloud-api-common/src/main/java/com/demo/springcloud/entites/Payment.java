package com.demo.springcloud.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author hongde
 * @version 1.0.0
 * @Description TODO
 * @ClassName Payment.java
 * @createTime 2021-10-14 16:17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {

    private Long id;

    private String serial;

}
