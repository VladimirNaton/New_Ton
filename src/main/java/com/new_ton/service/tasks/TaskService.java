package com.new_ton.service.tasks;

import com.new_ton.dao.PrintDao;
import com.new_ton.dao.SearchDataForTablesDao;
import com.new_ton.dao.UpdateDataDao;
import com.new_ton.domain.entities.MainEntity;
import com.new_ton.service.PrintDischargePageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class TaskService {

    private final PrintDischargePageService printDischargePageService;
    private final PrintDao printDao;
    private final SearchDataForTablesDao searchDataForTablesDao;
    private final UpdateDataDao updateDataDao;


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


    @Scheduled(fixedDelay = 3600000L)
    void returnRecipeToTechnologist() {
        List<MainEntity> mainEntityList = searchDataForTablesDao.returnRecipe();
        LocalDate localDate = LocalDate.now();
        int yearNow = localDate.getYear();
        int dayNow = localDate.getDayOfMonth();
        int monthNow = localDate.getMonthValue();
        mainEntityList.forEach(elem -> {
            LocalDate returnDate = elem.getReturndate().toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            int year = returnDate.getYear();
            int day = returnDate.getDayOfMonth();
            int month = returnDate.getMonthValue();
            if (yearNow == year && monthNow == month && dayNow == day) {
                elem.setState(1);
                Calendar calendar = new GregorianCalendar(1111, 10, 11, 11, 11, 11);
                Date date = calendar.getTime();
                elem.setReturndate(date);
                updateDataDao.updateStatusRecipe(elem);
            }
        });

    }
}
