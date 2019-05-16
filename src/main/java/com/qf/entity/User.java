package com.qf.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @version 1.0
 * @user reading
 * @date 2019/5/16 19:55
 */
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User implements Serializable {
    private int id;
    private String username;
    private String password;
    private int sex;
    private String email;
}
