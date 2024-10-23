package edu.icet.crm.model;

import lombok.Data;
import org.springframework.util.StringUtils;

@Data
public class IncompleteStudent {
    private String studentId;
    private String name;
    private String age;
    private String address;
    private String guardianName;
    private String guardianContactNo;
}

