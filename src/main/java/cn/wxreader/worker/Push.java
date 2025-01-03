package cn.wxreader.worker;

import cn.wxreader.domain.User;
import cn.wxreader.enums.PushTypeEnum;
import cn.wxreader.enums.WxTaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Push {
    private static  final Logger log = LoggerFactory.getLogger(Push.class);
    private PushTypeEnum pushType;
    private String pushToken;
    private String pushMsg;
    private String wrName;

    public Push(User user, String pushMsg) {
        this.pushType = PushTypeEnum.findByType(user.getPushType());
        this.pushToken = user.getPushToken();
        this.pushMsg = pushMsg;
        this.wrName = user.getWrName();
    }

    public void push(WxTaskEnum task) {
        if (pushType == null || pushToken.isEmpty()){
            return;
        }
        Boolean pushRes = false;
        switch (pushType) {
            case PUSH_PLUS:
                pushRes = new PushPlus(pushToken, pushMsg).push(task);
                break;
            default:
                break;
        }
        if (pushRes) {
            log.info("{}：已通过 {} 推送今日【{}】结果，推送消息内容：{}。", wrName, pushType.getDesc(), task.getDesc(), pushMsg);
        } else {
            log.error("{}：通过 {} 推送今日【{}】结果失败，推送消息内容：{}。", wrName, pushType.getDesc(), task.getDesc(), pushMsg);
        }
    }
}
