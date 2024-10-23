package edu.icet.crm.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponse {
    private String error;
    private String status;
}
