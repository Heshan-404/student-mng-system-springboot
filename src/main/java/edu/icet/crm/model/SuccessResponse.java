package edu.icet.crm.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SuccessResponse {
    private Object data;
    private String status;
}
