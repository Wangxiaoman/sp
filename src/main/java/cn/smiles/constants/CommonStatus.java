package cn.smiles.constants;


public enum CommonStatus{

    FAIL(-1, ""),

    SUCCESS(200,"成功"),
    
    PARAM_ERROR(400,"参数异常,请检查"),
    USER_NOT_EXIST(401,"用户不存在"),
    USER_PHONE_ERROR(402,"用户电话无效"),
    USER_PHONE_ACCOUNT_EXIST(403,"用户已经存在"),
    USER_PASSWORD_ERROR(404,"用户密码错误"),
    
    SERVER_ERROR(500,"系统错误，请稍后重试");
    
    private int    value;
    private String text; 

    private static final KV<Integer, CommonStatus> lookUp = new KV<Integer, CommonStatus>();

    static {
        for (CommonStatus status : CommonStatus.values()) {
            lookUp.put(status.getValue(), status);
        }
    }

    private CommonStatus(int value, String text) {
        this.value = value;
        this.text = text;
    }

    public int getValue() {
        return this.value;
    }

    public String getText() {
        return this.text;
    }
    
    public static CommonStatus of(Integer value) {
        return lookUp.get(value);
    }

}
