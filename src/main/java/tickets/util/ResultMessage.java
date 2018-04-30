package tickets.util;

/**
 * Created by disinuo on 17/3/2.
 */
public enum ResultMessage {

    SUCCESS("操作成功"),

    FAILURE("操作失败"),

    ROOM_FULL("房间已订满"),
    /**
     * 晚于可操作的时间
     */
    LATE_TIME("晚于可操作的时间"),
    /**
     * 注册时重名
     */
    DUPLICATE_NAME("注册时重名"),
    /**
     * 预订时用的，预订的入住时间早于当天时反馈的
     */
    EARLY_THAN_TODAY("早于今天"),
    /**
     * 原始密码错误
     */
    WRONG_ORIGINAL("原始密码错误"),
    /**
     * 没有权限
     */
    NO_AUTHORITY("没有权限"),
    /**
     * 没有结算的必要，就是各个客栈都没有消费记录
     */
    NO_NEED_COUNT("无需结算"),
    /**
     *新密码未做改变
     */
    NOT_CHANGE("新密码未做改变"),
    /**
     * 余额不足
     */
    NOT_ENOUGH_MONEY("余额不足"),
    /**
     * 积分不足
     */
    NOT_ENOUGH_SCORE("积分不足"),
    /**
     * 不存在
     */
    NOT_EXIST("不存在"),
    /**
     * 密码错误
     */
    WRONG_PASSWORD("密码错误"),
    /**
     * 已经停卡
     */
    VIP_STATE_STOP("已经停卡"),
    /**
     * 卡已被暂停
     */
    VIP_STATE_PAUSED("卡已被暂停"),
    /**
     * 卡未激活
     */
    VIP_STATE_UNACTIVATED("卡未激活"),
    /**
     * 请求还在等待中~
     */
    REQUEST_UNCHECKED("请求还在等待中~"),
    /**
     * 请求被拒绝
     */
    REQUEST_DENIED("请求被拒绝"),
    /**
     * 提醒客栈去提交申请~
     */
    REMIND_REQUEST("提醒客栈去提交申请~");
    public String toShow(){
        return this.name;
    }
    private ResultMessage(String name){
        this.name=name;
    }
    private String name;
}
