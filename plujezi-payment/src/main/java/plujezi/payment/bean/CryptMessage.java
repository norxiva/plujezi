package plujezi.payment.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@Builder
@ToString
public class CryptMessage {

    @NotNull
    private String merchantNo;
    @NotNull
    private String version;
    @NotNull
    private String msg;
    @NotNull
    private String sign;
}
