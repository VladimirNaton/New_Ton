package com.new_ton.dao;

import com.new_ton.domain.dto.ProductTableRequestDto;
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
    public ProductTableResponseEntityDto getProductTableData(ProductTableRequestDto productTableRequestDto) {

        try {
            ProductTableResponseEntityDto productTableResponseEntityDto = new ProductTableResponseEntityDto();

            StringBuilder query = new StringBuilder();
            StringBuilder count = new StringBuilder();
            String where = " where ";

            if (productTableRequestDto.getStartDate().equals("all") && productTableRequestDto.getEndDate().equals("all") &&
                    productTableRequestDto.getBrend().equals("") && productTableRequestDto.getProductName().equals("") &&
                    productTableRequestDto.getSpecification().equals("")) {
                where = "";
            }

            List<Integer> filterCount = new ArrayList<>();

            query.append(" select me from MainEntity me ").append(where);
            count.append(" select count(me) from MainEntity me ").append(where);

            if (!productTableRequestDto.getTypeDate().equals("") && !productTableRequestDto.getStartDate().equals("all")) {

                String typeDate = getTypeDateService.getTypeDate(productTableRequestDto.getTypeDate());
                query.append("me.").append(typeDate).append(" >= '").append(productTableRequestDto.getStartDate()).append("'");
                count.append("me.").append(typeDate).append(" >= '").append(productTableRequestDto.getStartDate()).append("'");
                filterCount.add(1);
            }

            if (!productTableRequestDto.getTypeDate().equals("") && !productTableRequestDto.getStartDate().equals("all")) {
                String typeDate = getTypeDateService.getTypeDate(productTableRequestDto.getTypeDate());

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }

                query.append("me.").append(typeDate).append(" <= '").append(productTableRequestDto.getEndDate()).append("'");
                count.append("me.").append(typeDate).append(" <= '").append(productTableRequestDto.getEndDate()).append("'");
                filterCount.add(1);
            }

            if (!productTableRequestDto.getBrend().equals("")) {

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }
                query.append(" LOWER(me.brend) LIKE LOWER ('").append("%").append(productTableRequestDto.getBrend()).append("%')");
                count.append(" LOWER(me.brend) LIKE LOWER ('").append("%").append(productTableRequestDto.getBrend()).append("%')");

            }

            if (!productTableRequestDto.getProductName().equals("")) {

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }
                query.append(" LOWER(me.nameprod) LIKE LOWER ('").append("%").append(productTableRequestDto.getProductName()).append("%')");
                count.append(" LOWER(me.nameprod) LIKE LOWER ('").append("%").append(productTableRequestDto.getProductName()).append("%')");

            }

            if (!productTableRequestDto.getSpecification().equals("")) {

                if (filterCount.size() > 0) {
                    query.append(" and ");
                    count.append(" and ");
                }
                query.append(" LOWER(me.sp) LIKE LOWER ('").append("%").append(productTableRequestDto.getSpecification()).append("%')");
                count.append(" LOWER(me.sp) LIKE LOWER ('").append("%").append(productTableRequestDto.getSpecification()).append("%')");
            }

            query.append(" order by me.").append(productTableRequestDto.getOrderColumn()).append(" ").append(productTableRequestDto.getOrderType());

            Query queryCount;
            Long pageCount = null;
            if (productTableRequestDto.getRequestFlag().equals("request")) {
                queryCount = entityManager.createQuery(count.toString(), Long.class);
                List<Long> resultList = queryCount.getResultList();
                pageCount = resultList.get(0).longValue();
            }

            Query queryResult = entityManager.createQuery(query.toString(), MainEntity.class);
            if (productTableRequestDto.getRequestFlag().equals("request") && productTableRequestDto.getLength() != -1) {
                queryResult.setFirstResult(productTableRequestDto.getStart());
                queryResult.setMaxResults(productTableRequestDto.getLength());
            }
            List<MainEntity> mainEntityList = queryResult.getResultList();

            productTableResponseEntityDto.setMainEntityList(mainEntityList);
            productTableResponseEntityDto.setRecordsTotal(pageCount);

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
