package plujezi.core;

public enum BankAcronym {

    ABC("中国农业银行"),
    ABC2B("中国农业银行（企业）"),
    BOC("中国银行"),
    BOC2B("中国银行（企业）"),
    BOCOM("交通银行"),
    BOCOM2B("交通银行（企业）"),
    CCB("中国建设银行"),
    CCB2B("中国建设银行（企业）"),
    ICBC("中国工商银行"),
    ICBC2B("中国工商银行（企业）");

    private String fullName;

    BankAcronym(String fullName){
        this.fullName = fullName;
    }

}
