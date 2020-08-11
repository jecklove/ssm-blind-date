package org.jecklove.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Integer id;

    private String username;

    private String password;

    private String realName;

    private Integer age;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    private String sex;

    private String tel;

    private String email;

    private String remark;

    private Integer photo;

    private Long regionId;



    private List<Photo> photoList = new ArrayList<>();

    private Map<String,Object> dataMap = new HashMap<>();

}