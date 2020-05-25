package com.czhe.sysmanage.schedule;

import com.czhe.sysmanage.entity.Person;
import com.czhe.sysmanage.service.PersonService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;
@Configuration
public class SynchronizeSlave {

    private static final Logger log = LoggerFactory.getLogger(SynchronizeSlave.class);

    @Autowired
    private PersonService personService;

    @Scheduled(cron = "0 28 18 ? * *")
    public void synchronizeData() {
        log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 开始同步数据 start >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        PageInfo<Person> personList = personService.findAllPerson(1, 10000);
        SynchronizeData synchronizeData = new SynchronizeData(personList);
        synchronizeData.run();
    }

    class SynchronizeData implements Runnable {
        private List<Person> personList;

        public SynchronizeData(PageInfo<Person> personList) {
            this.personList = personList.getList();
        }

        @Override
        public void run() {
            for (Person ps : personList) {
                personService.insert(ps);
            }
            log.info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 同步数据完毕 end >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }

    }
}
