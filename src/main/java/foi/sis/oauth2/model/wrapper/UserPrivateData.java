package foi.sis.oauth2.model.wrapper;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserPrivateData {

    private int id;
    private String data;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime creationDate;
    private String user;
    private String resourceType;

}
