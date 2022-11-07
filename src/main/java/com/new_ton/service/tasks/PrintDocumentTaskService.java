package com.new_ton.service.tasks;

import com.new_ton.dao.PrintDao;
import com.new_ton.service.PrintDischargePageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class PrintDocumentTaskService {

    private final PrintDischargePageService printDischargePageService;
    private final PrintDao printDao;


    @Scheduled(fixedDelay = 5000L)
    void printDocument() {
        try {
            List<Integer> idList = printDao.getIdPrintTask();
            for (Integer id : idList) {
                log.info("Start print task id_pr -" + id);
                boolean result = printDischargePageService.printDischargePage(id);
                if (result) {
                    printDao.changeCode(id);
                }
                log.info("End print task id_pr -" + id);
            }
        } catch (Exception e) {
            log.error("Error PrintDocumentTaskService printDocument : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }
    }
}
