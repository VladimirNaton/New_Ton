package com.new_ton.dao;

import com.new_ton.domain.dto.RequestDataTableDto;
import com.new_ton.domain.dto.ProductTableResponseEntityDto;
import com.new_ton.domain.entities.MainEntity;
import com.new_ton.repository.MainRepository;
import com.new_ton.service.GetTypeDateService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Repository
public class MainTableDaoImpl implements MainTableDao {

    private final MainRepository mainRepository;
    private final EntityManager entityManager;
    private final GetTypeDateService getTypeDateService;


    @Override
    public ProductTableResponseEntityDto getProductTableData(RequestDataTableDto requestDataTableDto) {

        try {
            ProductTableResponseEntityDto productTableResponseEntityDto = new ProductTableResponseEntityDto();

            StringBuilder query = new StringBuilder();
            StringBuilder count = new StringBuilder();
            String where = " where ";

            if (requestDataTableDto.getStartDate().equals("all") && requestDataTableDto.getEndDate().equals("all") &&
                    requestDataTableDto.getBrend().equals("") && requestDataTableDto.getProductName().equals("") &&
                    requestDataTableDto.getSpecification().equals("")) {
                where = "";
            }

            List<Integer> filterCount = new ArrayList<>();

            query.append(" select me from MainEntity me ").append(where);
            count.append(" select count(me) from MainEntity me ").append(where);

            if (!requestDataTableDto.getTypeDate().equals("") && !requestDataTableDto.getStartDate().equals("all")) {

                String typeDate = getTypeDateService.getTypeDate(requestDataTableDto.getTypeDate());
                query.append("me.").append(typeDate).append(" >= '").append(requestDataTableDto.getStartDate()).append("'");
                count.append("me.").append(typeDate).append(" >= '").append(requestDataTableDto.getStartDate()).append("'");
                filterCount.add(1);
            }

            if (!requestDataTableDto.getTypeDate().equals("") && !requestDataTableDto.getStartDate().equals("all")) {
                String typeDate = getTypeDateService.getTypeDate(requestDataTableDto.getTypeDate());

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }

                query.append("me.").append(typeDate).append(" <= '").append(requestDataTableDto.getEndDate()).append("'");
                count.append("me.").append(typeDate).append(" <= '").append(requestDataTableDto.getEndDate()).append("'");
                filterCount.add(1);
            }

            if (!requestDataTableDto.getBrend().equals("")) {

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }
                query.append(" LOWER(me.brend) LIKE LOWER ('").append("%").append(requestDataTableDto.getBrend()).append("%')");
                count.append(" LOWER(me.brend) LIKE LOWER ('").append("%").append(requestDataTableDto.getBrend()).append("%')");

            }

            if (!requestDataTableDto.getProductName().equals("")) {

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }
                query.append(" LOWER(me.nameprod) LIKE LOWER ('").append("%").append(requestDataTableDto.getProductName()).append("%')");
                count.append(" LOWER(me.nameprod) LIKE LOWER ('").append("%").append(requestDataTableDto.getProductName()).append("%')");

            }

            if (!requestDataTableDto.getSpecification().equals("")) {

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }
                query.append(" LOWER(me.sp) LIKE LOWER ('").append("%").append(requestDataTableDto.getSpecification()).append("%')");
                count.append(" LOWER(me.sp) LIKE LOWER ('").append("%").append(requestDataTableDto.getSpecification()).append("%')");
            }

            query.append(" order by me.").append(requestDataTableDto.getOrderColumn()).append(" ").append(requestDataTableDto.getOrderType());

            Query queryCount;
            Long pageCount = null;
            if (requestDataTableDto.getRequestFlag().equals("request")) {
                queryCount = entityManager.createQuery(count.toString(), Long.class);
                List<Long> resultList = queryCount.getResultList();
                pageCount = resultList.get(0).longValue();
            }

            Query queryResult = entityManager.createQuery(query.toString(), MainEntity.class);
            if (requestDataTableDto.getRequestFlag().equals("request") && requestDataTableDto.getLength() != -1) {
                queryResult.setFirstResult(requestDataTableDto.getStart());
                queryResult.setMaxResults(requestDataTableDto.getLength());
            }
            List<MainEntity> mainEntityList = queryResult.getResultList();

            productTableResponseEntityDto.setMainEntityList(mainEntityList);
            productTableResponseEntityDto.setRecordsTotal(pageCount);
            entityManager.close();

            return productTableResponseEntityDto;
        } catch (Exception e) {
            log.error("Error MainTableDaoImpl getProductTableData : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
        }

        return null;
    }

    public Optional<MainEntity> findByIdpr(int id) {
        try {
            return mainRepository.findByIdpr(id);
        } catch (Exception e) {
            log.error("Error findByIdpr : {}, {}", ExceptionUtils.getMessage(e), ExceptionUtils.getMessage(e.getCause()));
            return Optional.empty();
        }
    }
}
