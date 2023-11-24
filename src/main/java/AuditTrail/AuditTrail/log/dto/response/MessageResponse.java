package AuditTrail.AuditTrail.log.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse {


    private String message;
    private Boolean status;
    private Object data;

    private int code;


    public MessageResponse(String message) {
        this.message = message;
    }

    public MessageResponse(String message, Boolean status) {
        this.message = message;
        this.status = status;
    }

    public MessageResponse(String message, Boolean status, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public MessageResponse(boolean status, String message,Integer code) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public MessageResponse(boolean status, String message, int code, Object data) {
        this.message = message;
        this.status = status;
        this.data = data;
        this.code = code;

    }
}


