package edu.icet.crm.model;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class Student {
    private Integer studentId;
    private String name;
    private String age;
    private String address;
    private String guardianName;
    private String guardianContactNo;

    public boolean checkAllFieldsAreValid() {
        return (StringUtils.hasText(this.name) &&
                StringUtils.hasText(this.age) &&
                StringUtils.hasText(this.address) &&
                StringUtils.hasText(this.guardianName) &&
                StringUtils.hasText(this.guardianContactNo));
    }
}

