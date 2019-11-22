import com.demo.dao.entity.MSG_TYPE;
import com.demo.dao.entity.MessageInfo;
import com.demo.service.MessageService;
import com.demo.web.WebApplication;
import com.demo.web.listener.MessageServiceContext;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author czhe
 * @version 1.0
 * @create 2019/11/22 15:22
 * @description 测试类
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class TestApplication {
    @Autowired
    MessageServiceContext messageServiceContext;

    @Test
    public void contextLoads() {
        messageServiceContext.showHandlerMap();
        MessageInfo messageInfo = new MessageInfo(MSG_TYPE.TEXT.code, "消息内容: 将近酒 杯莫停");
        MessageService messageService = messageServiceContext.getMessageService(messageInfo.getCode());
        messageService.handleMessage(messageInfo);
    }
}
