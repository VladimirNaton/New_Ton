package com.new_ton.service;

import com.new_ton.dao.DeleteDataDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DeleteDataServiceImpl implements DeleteDataService {

    private final DeleteDataDao deleteDataDao;

    @Override
    public boolean deleteDataSelectedRowRightTableTechnologistPage(Integer idProd) {
        return deleteDataDao.deleteDataSelectedRowRightTableTechnologistPage(idProd);
    }
}
