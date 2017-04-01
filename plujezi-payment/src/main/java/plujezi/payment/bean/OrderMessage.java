package plujezi.payment.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import plujezi.core.*;

import java.time.LocalDateTime;

@ApiModel(value = "OrderMessage", description = "Entity for order creation")
@Setter
@Getter
@Builder
public class OrderMessage {

    @ApiModelProperty(value = "merchant no", required = true)
    private String merchantNo;
    private String merchantOrderNo;
    private String merchantReturnUrl;
    private String merchantNotifyUrl;
    private LocalDateTime merchantOrderTime;

    private String orderNo;
    private LocalDateTime createTime;
    private LocalDateTime executeTime;
    private LocalDateTime endTime;

    private BusinessMode businessMode = BusinessMode.SIMPLE;
    private BusinessType businessType;
    private PaymentType paymentType;
    private PaymentMode paymentMode;
    private ChannelType channelType;

    private BankAccountType bankAccountType;
    private BankAcronym bankAcronym;

    private String prodNo;







}
