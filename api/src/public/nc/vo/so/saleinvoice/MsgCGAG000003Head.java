package nc.vo.so.saleinvoice;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by shangguoqiang on 2017-03-20 09:38:29
 */
@XmlRootElement(name = "Message")//�ɹ��Ƽ۽��㵥ͷ
public class MsgCGAG000003Head {
    String msgId;              //��ϢID
    String resourceId;         //ResourceID
    List<MsgCGAG000003> msgBody;    //������Ϣ

    @XmlElement(name = "DataRow")
    public List<MsgCGAG000003> getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(List<MsgCGAG000003> msgBody) {
        this.msgBody = msgBody;
    }

    @XmlAttribute(name = "MsgID")
    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    @XmlAttribute(name = "ResourceID")
    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

}
