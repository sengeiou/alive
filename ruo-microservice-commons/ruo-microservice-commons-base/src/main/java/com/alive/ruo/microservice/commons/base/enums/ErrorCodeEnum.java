package com.alive.ruo.microservice.commons.base.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
/**
 * @author Somnus
 * @packageName com.alive.ruo.microservice.commons.base.enums
 * @title: ErrorCodeEnum
 * @description: The class Error code enum.
 * @date 2019/3/15 15:55
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCodeEnum {
    COMMON90001(90001, "OSS文件不存在"),

    COMMON90002(90002, "NOS文件不存在"),

    /*************************** 用户账户服务错误码 *********************************/
    /**
     * UAC 10002 error code enum.
     */
    UAC10002(10002, "secondVerifyFail"),
    /**
     * UAC 10003 error code enum.
     */
    UAC10003(10003, "failed to send sms code,cause by:{0}"),
    /**
     * UAC 10004 error code enum.
     */
    UAC10004(10004, "smsCodeNotExist"),
    /**
     * UAC 10005 error code enum.
     */
    UAC10005(10005, "booked"),
    /**
     * UAC 10006 error code enum.
     */
    UAC10006(10006, "smsCodeError"),
    /**
     * UAC 10007 error code enum.
     */
    UAC10007(10007, "standbyAtLeastOne"),
    /**
     * UAC 10008 error code enum.
     */
    UAC10008(10008, "teamExists"),
    /**
     * UAC 10009 error code enum.
     */
    UAC10009(10009, "unSignup"),
    /**
     * UAC 10010 error code enum.
     */
    UAC10010(10010, "signuped"),
    /**
     * UAC 10011 error code enum.
     */
    UAC10011(10011, "outnumber"),
    /**
     * UAC 10012 error code enum.
     */
    UAC10012(10012, "activityEnd"),
    /**
     * UAC 10013 error code enum.
     */
    UAC10013(10013, "luckyDrawRunOut"),
    /**
     * UAC 10014 error code enum.
     */
    UAC10014(10014, "infoRepeat"),
    /**
     * UAC 10015 error code enum.
     */
    UAC10015(10015, "frequently"),
    /**
     * UAC 10016 error code enum.
     */
    UAC10016(10016, "unseasonal"),
    /**
     * UAC 10017 error code enum.
     */
    UAC10017(10017, "full"),
    /**
     * CMC 10018 error code enum.
     */
    UAC10018(10018, "作品id[{0}]不存在"),
    /**
     * UAC 10019 error code enum.
     */
    UAC10019(10019, "undo"),
    /**
     * UAC 10020 error code enum.
     */
    UAC10020(10020, "unlogin"),
    /**
     * UAC 10021 error code enum.
     */
    UAC10021(10021, "[{0}]游戏，用户:[{1}]不存在"),
    /**
     * CMC 10022 error code enum.
     */
    UAC10022(10022, "活动id[{0}]不存在"),
    /**
     * CMC 10023 error code enum.
     */
    UAC10023(10023, "活动[{0}]已存在"),
    /**
     * UAC 10024 error code enum.
     */
    UAC10024(10024, "mismatch"),
    /*************************** 兑换服务错误码 *********************************/
    /**
     * REC 20001 error code enum.
     */
    REC20001(20001, "redeemCodeNotExist"),

    /**
     * REC 20002 error code enum.
     */
    REC20002(20002, "redeemCodeOccupied"),
    /*************************** 微信公众号服务错误码 *********************************/
    /**
     * WECHAT 10001 error code enum.
     */
    WECHAT10001(10001, "微信公众号配置出错，请检查"),
    /**
     * Gl 000500 error code enum.
     */
    GL99990500(500, "未知异常"),
    /**
     * Gl 99990100 error code enum.
     */
    GL99990100(9999100, "参数异常"),
    /**
     * Gl 99990401 error code enum.
     */
    GL99990401(99990401, "无访问权限"),
    /**
     * Gl 000403 error code enum.
     */
    GL99990403(9999403, "无权访问"),
    /**
     * Gl 000404 error code enum.
     */
    GL9999404(9999404, "找不到指定资源"),
    /**
     * Gl 99990009 error code enum.
     */
    GL99990002(99990009, "微服务不在线,或者网络超时"),
    /**
     * Uac 10010001 error code enum.
     */
    UAC10010001(10010001, "会话超时,请刷新页面重试"),
    /**
     * Uac 10010002 error code enum.
     */
    UAC10010002(10010002, "TOKEN解析失败"),
    /**
     * Uac 10010003 error code enum.
     */
    UAC10010003(10010003, "操作频率过快, 您的帐号已被冻结"),
    /**
     * Uac 10011001 error code enum.
     */
    UAC10011001(10011001, "用户Id不能为空"),
    /**
     * Uac 10011002 error code enum.
     */
    UAC10011002(10011002, "找不到用户,loginName={0}"),
    /**
     * Uac 10011003 error code enum.
     */
    UAC10011003(10011003, "找不到用户,userId={0}"),
    /**
     * Uac 10011004 error code enum.
     */
    UAC10011004(10011004, "找不到用户,email={0}"),
    /**
     * Uac 10011006 error code enum.
     */
    UAC10011006(10012006, "手机号不能为空"),
    /**
     * Uac 10011007 error code enum.
     */
    UAC10011007(10011007, "登录名不能为空"),
    /**
     * Uac 10011008 error code enum.
     */
    UAC10011008(10011008, "新密码不能为空"),
    /**
     * Uac 10011009 error code enum.
     */
    UAC10011009(10011009, "确认密码不能为空"),
    /**
     * Uac 10011010 error code enum.
     */
    UAC10011010(10011010, "两次密码不一致"),
    /**
     * Uac 10011011 error code enum.
     */
    UAC10011011(10011011, "用户不存在, userId={0}"),
    /**
     * Uac 10011012 error code enum.
     */
    UAC10011012(10011012, "登录名已存在"),
    /**
     * Uac 10011013 error code enum.
     */
    UAC10011013(10011013, "手机号已存在"),
    /**
     * Uac 10011014 error code enum.
     */
    UAC10011014(10011014, "密码不能为空"),
    /**
     * Uac 10011016 error code enum.
     */
    UAC10011016(10011016, "用户名或密码错误"),
    /**
     * Uac 10011017 error code enum.
     */
    UAC10011017(10011017, "验证类型错误"),
    /**
     * Uac 10011018 error code enum.
     */
    UAC10011018(10011018, "邮箱不能为空"),
    /**
     * Uac 10011019 error code enum.
     */
    UAC10011019(10011019, "邮箱已存在"),
    /**
     * Uac 10011020 error code enum.
     */
    UAC10011020(10011020, "短信模板不能为空"),
    /**
     * Uac 10011021 error code enum.
     */
    UAC10011021(10011021, "发送短信验证码对象转换为json字符串失败"),
    /**
     * Uac 10011022 error code enum.
     */
    UAC10011022(10011022, "发送短信验证码失败"),
    /**
     * Uac 10011023 error code enum.
     */
    UAC10011023(10011023, "越权操作"),
    /**
     * Uac 10011024 error code enum.
     */
    UAC10011024(10011024, "找不到绑定的用户, userId={0}"),
    /**
     * Uac 10011025 error code enum.
     */
    UAC10011025(10011025, "用户已存在, loginName={0}"),
    /**
     * Uac 10011026 error code enum.
     */
    UAC10011026(10011026, "更新用户失败, userId=%"),
    /**
     * Uac 10011027 error code enum.
     */
    UAC10011027(10011027, "找不到用户,mobile={0}"),
    /**
     * Uac 10011028 error code enum.
     */
    UAC10011028(10011028, "链接已失效"),
    /**
     * Uac 10011029 error code enum.
     */
    UAC10011029(10011029, "重置密码失败"),
    /**
     * Uac 10011030 error code enum.
     */
    UAC10011030(10011030, "激活失败, 链接已过期"),
    /**
     * Uac 10011031 error code enum.
     */
    UAC10011031(10011031, "验证码超时, 请重新发送验证码"),
    /**
     * Uac 10011032 error code enum.
     */
    UAC10011032(10011032, "邮箱不存在, loginName={0},email={1}"),
    /**
     * Uac 10011033 error code enum.
     */
    UAC10011033(10011033, "清空该用户常用菜单失败"),
    /**
     * Uac 10011034 error code enum.
     */
    UAC10011034(10011034, "不允许操作admin用户"),
    /**
     * Uac 10011035 error code enum.
     */
    UAC10011035(10011035, "原始密码输入错误"),
    /**
     * Uac 10011036 error code enum.
     */
    UAC10011036(10011036, "新密码和原始密码不能相同"),
    /**
     * Uac 10011037 error code enum.
     */
    UAC10011037(10011037, "修改用户失败,userId={0}"),
    /**
     * Uac 10011038 error code enum.
     */
    UAC10011038(10011038, "激活用户失败,userId={0}"),
    /**
     * Uac 10011039 error code enum.
     */
    UAC10011039(10011039, "验证token失败"),
    /**
     * Uac 10011040 error code enum.
     */
    UAC10011040(10011040, "解析header失败"),
    /**
     * Uac 10011041 error code enum.
     */
    UAC10011041(10011041, "页面已过期,请重新登录"),
    /**
     * Uac 10011042 error code enum.
     */
    UAC10011042(10011042, "Cookie转码异常"),
    /**
     * Uac 10012001 error code enum.
     */
    UAC10012001(10012001, "角色ID不能为空"),
    /**
     * Uac 10012002 error code enum.
     */
    UAC10012002(10012002, "拥有的角色不允许禁用"),
    /**
     * Uac 10012003 error code enum.
     */
    UAC10012003(10012003, "系统角色不能删除"),
    /**
     * Uac 10012004 error code enum.
     */
    UAC10012004(10012004, "超级角色Id不能为空"),

    /**
     * Uac 10012005 error code enum.
     */
    UAC10012005(10012005, "找不到角色信息,roleId={0}"),
    /**
     * Uac 10012006 error code enum.
     */
    UAC10012006(10012006, "删除角色失败, roleId={0}"),
    /**
     * Uac 10012007 error code enum.
     */
    UAC10012007(10012007, "批量删除角色失败, roleId={0}"),
    /**
     * Uac 10012008 error code enum.
     */
    UAC10012008(10012008, "找不到绑定的角色, roleId={0}"),


    /**
     * Uac 10013001 error code enum.
     */
    UAC10013001(10013001, "父菜单不存在,menuId={0}"),
    /**
     * Uac 10013002 error code enum.
     */
    UAC10013002(10013002, "更新上级菜单失败,menuId={0}"),
    /**
     * Uac 10013003 error code enum.
     */
    UAC10013003(10013003, "菜单不存在,menuId={0}"),
    /**
     * Uac 10013004 error code enum.
     */
    UAC10013004(10013004, "启用菜单失败,menuId={0}"),
    /**
     * Uac 10013005 error code enum.
     */
    UAC10013005(10013005, "禁用菜单失败,menuId={0}"),
    /**
     * Uac 10013006 error code enum.
     */
    UAC10013006(10013006, "更新菜单状态失败,menuId={0}"),
    /**
     * Uac 10013007 error code enum.
     */
    UAC10013007(10013007, "根菜单不能禁用"),
    /**
     * Uac 10013008 error code enum.
     */
    UAC10013008(10013008, "删除菜单失败, menuId={0}"),
    /**
     * Uac 10013009 error code enum.
     */
    UAC10013009(10013009, "请先分配菜单"),
    /**
     * Uac 10013010 error code enum.
     */
    UAC10013010(10013010, "选择菜单不是根目录,menuId={0}"),


    /**
     * Uac 10014001 error code enum.
     */
    UAC10014001(10014001, "找不到权限信息, actionId={0}"),
    /**
     * Uac 10014002 error code enum.
     */
    UAC10014002(10014002, "删除失败, actionId={0}"),
    /**
     * Uac 10014003 error code enum.
     */
    UAC10014003(10014003, "保存权限信息失败"),
    /**
     * Uac 10015001 error code enum.
     */
    UAC10015001(10015001, "找不到组织信息,groupId={0}"),
    /**
     * Uac 10015002 error code enum.
     */
    UAC10015002(10015002, "组织状态不存在"),
    /**
     * Uac 10015003 error code enum.
     */
    UAC10015003(10015003, "操作越权, 启用子节点, 必须先启用父节点"),
    /**
     * Uac 10015004 error code enum.
     */
    UAC10015004(10015004, "找不到组织信息,groupId={0}"),
    /**
     * Uac 10015006 error code enum.
     */
    UAC10015006(10015006, "更新组织信息失败,groupId={0}"),
    /**
     * Uac 10015007 error code enum.
     */
    UAC10015007(10015007, "该组织下还存在子节点，不能将其删除, Pid={0}"),
    /**
     * Uac 10015008 error code enum.
     */
    UAC10015008(10015008, "该组织下绑定的用户，不能将其删除, groupId={0}"),
    /**
     * Uac 10015009 error code enum.
     */
    UAC10015009(10015009, "找不到上级组织, groupId={0}"),

    /**
     * Uac 10015010 error code enum.
     */
    UAC10015010(10015010, "今日投票次数已达上限"),

    /**
     * Uac 10015011 error code enum.
     */
    UAC10015011(10015011, "收货地址不存在"),
    /**
     * Uac 10015012 error code enum.
     */
    UAC10015012(10015012, "激活失败"),
    /**
     * Uac 10015011 error code enum.
     */
    UAC10015013(10015013, "该冰神卡已绑定"),
    /**
     * UAC 10015014 error code enum.
     */
    UAC10015014(10015014, "该活动类型已经存在"),
    /**
     * UAC 10015015 error code enum.
     */
    UAC10015015(10015015, "该活动已经下线"),
    /**
     * UAC 10015015 error code enum.
     */
    UAC10015016(10015016, "您的抽奖次数已经用完"),
    /**
     * UAC 10015017 error code enum.
     */
    UAC10015017(10015017, "快去激活冰神卡，可以额外获得一次抽奖激活哦！"),
    /**
     * Mdc 10021001 error code enum.
     */
    MDC10021001(10021001, "获取地址信息失败"),
    /**
     * Mdc 10021002 error code enum.
     */
    MDC10021002(10021002, "找不到该地址信息"),
    /**
     * Mdc 10021003 error code enum.
     */
    MDC10021003(10021003, "获取商品信息失败"),
    /**
     * Mdc 10021004 error code enum.
     */
    MDC10021004(10021004, "找不到该商品信息,productId={0}"),
    /**
     * Mdc 10021015 error code enum.
     */
    MDC10021015(10021015, "商品不是在线售卖状态, productId={0}"),
    /**
     * Mdc 10021016 error code enum.
     */
    MDC10021016(10021016, "商品库存不足, productId={0}"),
    /**
     * Mdc 10021017 error code enum.
     */
    MDC10021017(10021017, "产品已下架或者删除, productId={0}"),
    /**
     * Mdc 10021018 error code enum.
     */
    MDC10021018(10021018, "找不到数据字典信息, dictId={0}"),
    /**
     * Mdc 10021019 error code enum.
     */
    MDC10021019(10021019, "更新字典状态失败, dictId={0}"),
    /**
     * Mdc 10021020 error code enum.
     */
    MDC10021020(10021020, "上级数据字典不存在, dictId={0}"),
    /**
     * Mdc 10021021 error code enum.
     */
    MDC10021021(10021021, "商品ID不能为空"),
    /**
     * Mdc 10021024 error code enum.
     */
    MDC10021024(10021024, "商品编码不能为空"),

    /**
     * Mdc 10023001 error code enum.
     */
    MDC10023001(10023001, "找不到商品分类信息, categoryId={0}"),
    /**
     * Mdc 10023002 error code enum.
     */
    MDC10023002(10023002, "上级商品分类不存在, categoryId={0}"),
    /**
     * Mdc 10023003 error code enum.
     */
    MDC10023003(10023003, "更新商品分类状态失败, categoryId={0}"),
    /**
     * Mdc 10021022 error code enum.
     */
    MDC10021022(10021022, "更新商品信息失败, productId={0}"),
    /**
     * Mdc 10021023 error code enum.
     */
    MDC10021023(10021023, "删除商品信息失败, productId={0}"),
    /**
     * Omc 10031001 error code enum.
     */
// 1003 订单中心
    OMC10031001(10031001, "购物车为空, userId={0}"),
    /**
     * Omc 10031002 error code enum.
     */
    OMC10031002(10031002, "生成订单失败"),
    /**
     * Omc 10031003 error code enum.
     */
    OMC10031003(10031003, "该用户此订单不存在"),
    /**
     * Omc 10031004 error code enum.
     */
    OMC10031004(10031004, "已付款, 无法取消订单"),
    /**
     * Omc 10031005 error code enum.
     */
    OMC10031005(10031005, "找不到订单信息, orderNo={0}"),
    /**
     * Omc 10031006 error code enum.
     */
    OMC10031006(10031006, "清空购物车失败"),
    /**
     * Omc 10031007 error code enum.
     */
    OMC10031007(10031007, "不存在默认地址"),
    /**
     * Omc 10031008 error code enum.
     */
    OMC10031008(10031008, "更新默认地址失败, addressId={0}"),
    /**
     * Omc 10031009 error code enum.
     */
    OMC10031009(10031009, "批量插入订单明细失败"),
    /**
     * Omc 10031010 error code enum.
     */
    OMC10031010(10031010, "非快乐学习网的订单, 回调忽略"),
    /**
     * Omc 10031011 error code enum.
     */
    OMC10031011(10031011, "支付宝重复调用"),
    /**
     * Omc 10031012 error code enum.
     */
    OMC10031012(10031012, "上传失败"),
    /**
     * Omc 10031013 error code enum.
     */
    OMC10031013(10031013, "获取附件地址失败"),
    /**
     * Omc 10031014 error code enum.
     */
    OMC10031014(10031014, "更新购物车数据失败, cartId={0}"),
    /**
     * Omc 10031016 error code enum.
     */
    OMC10031016(10031016, "更新购物车数据失败, cartId={0}"),
    /**
     * Opc 10040001 error code enum.
     */
    OPC10040001(10040001, "根据IP定位失败"),
    /**
     * Opc 10040002 error code enum.
     */
    OPC10040002(10040002, "上传文件失败"),
    /**
     * Opc 10040003 error code enum.
     */
    OPC10040003(10040003, "文件类型不符"),
    /**
     * Opc 10040004 error code enum.
     */
    OPC10040004(10040004, "发送短信失败"),
    /**
     * Opc 10040005 error code enum.
     */
    OPC10040005(10040005, "生成邮件消息体失败"),
    /**
     * Opc 10040006 error code enum.
     */
    OPC10040006(10040006, "获取模板信息失败"),
    /**
     * Opc 10040007 error code enum.
     */
    OPC10040007(10040007, "更新附件失败, id={0}"),
    /**
     * Opc 10040008 error code enum.
     */
    OPC10040008(10040008, "找不到该附件信息, id={0}"),
    /**
     * Opc 10040009 error code enum.
     */
    OPC10040009(10040009, "上传图片失败"),
    /**
     * Tpc 10050001 error code enum.
     */
    OPC10040010(10040010, "文件名不能为空"),
    /**
     * Opc 10040011 error code enum.
     */
    OPC10040011(10040011, "今日流量已用尽, 请明天再试"),
    /**
     * Tpc 10050001 error code enum.
     */
    TPC10050001(10050001, "消息的消费Topic不能为空"),
    /**
     * Tpc 10050002 error code enum.
     */
    TPC10050002(10050002, "根据消息key查找的消息为空"),
    /**
     * Tpc 10050003 error code enum.
     */
    TPC10050003(10050003, "删除消息失败,messageKey={0}"),
    /**
     * Tpc 10050004 error code enum.
     */
    TPC10050004(10050004, "消息中心接口异常,message={0}, messageKey={1}"),
    /**
     * Tpc 10050005 error code enum.
     */
    TPC10050005(10050005, "目标接口参数不能为空"),
    /**
     * Tpc 10050006 error code enum.
     */
    TPC10050006(10050006, "根据任务Id查找的消息为空"),

    /**
     * Tpc 10050007 error code enum.
     */
    TPC10050007(10050007, "消息数据不能为空"),
    /**
     * Tpc 10050008 error code enum.
     */
    TPC10050008(10050008, "消息体不能为空,messageKey={0}"),
    /**
     * Tpc 10050009 error code enum.
     */
    TPC10050009(10050009, "消息KEY不能为空"),
    /**
     * Tpc 100500010 error code enum.
     */
    TPC100500010(10050010, "Topic={0}, 无消费者订阅"),
    /**
     * Tpc 100500011 error code enum.
     */
    TPC100500011(10050011, "Mq编码转换异常, MessageKey={0}"),
    /**
     * Tpc 100500012 error code enum.
     */
    TPC100500012(10050012, "发送MQ失败, MessageKey={0}"),
    /**
     * Tpc 100500013 error code enum.
     */
    TPC100500013(10050013, "延迟级别错误, Topic={0}, MessageKey={1}"),
    /**
     * Tpc 100500014 error code enum.
     */
    TPC100500014(10050014, "MQ重试三次,仍然发送失败, Topic={0}, MessageKey={1}"),
    /**
     * Tpc 100500015 error code enum.
     */
    TPC100500015(10050015, "消息PID不能为空, messageKey={0}"),;

    @Getter
    private int code;

    @Getter
    private String msg;

    /**
     * Gets enum.
     *
     * @param code the code
     *
     * @return the enum
     */
    public static ErrorCodeEnum codeOf(int code) {
        for (ErrorCodeEnum ele : ErrorCodeEnum.values()) {
            if (ele.getCode() == code) {
                return ele;
            }
        }
        return null;
    }
}