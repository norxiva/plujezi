package plujezi.example.vertx.bean;

import lombok.Getter;
import lombok.Setter;
import plujezi.example.vertx.defination.BusinessMode;
import plujezi.example.vertx.defination.BusinessType;
import plujezi.example.vertx.defination.PaymentMode;
import plujezi.example.vertx.defination.PaymentType;

import java.time.LocalDateTime;

@Setter
@Getter
public class BusinessOrder {
    private String orderNo;
    private String merchantNo;
    private String merchantOrderNo;
    private LocalDateTime merchantOrderTime;
    private String merchantNotifyUrl;
    private String merchantReturnUrl;
    private LocalDateTime createTime;
    private LocalDateTime executeTime;
    private LocalDateTime endTime;
    private String prodNo;
    private BusinessMode businessMode;
    private BusinessType businessType;
    private PaymentType paymentType;
    private PaymentMode paymentMode;
    private String channelType;
    private String channelNo;
    private String merchantAccountType;
    private String BankCode;
    private String bankAccountNo;
    private String bankAccountName;
    private String idNo;
    private String idType;
    private String reservedPhone;
    private String orderAmount;
    private String feeAmount;
    private String currencyCode;
    private String isBatch;
    private String batchNo;
    private String orderStatus;
    private String EventStatus;
    private String requestMsg;
    private String notifyCounter;
    private String respCode;
    private String respMsg;
}
