package com.vnborx.mapper;

import com.vnborx.pojo.Dealer;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class DealerMapperImpl extends SqlSessionDaoSupport implements DealerMapper {
    public List<Dealer> selectDealer() {
//        Dealer dealer = new Dealer("0", "Test_dealer", "Test_city", "Test_province", "Test_telephone");
//        DealerMapper mapper = getSqlSession().getMapper(DealerMapper.class);
//        mapper.createDealer(dealer);
//        mapper.selectDealer();
        return getSqlSession().getMapper(DealerMapper.class).selectDealer();
    }

    public int createDealer(Dealer dealer) {
        return getSqlSession().getMapper(DealerMapper.class).createDealer(dealer);
    }

    public int deleteDealer(int id) {
        return getSqlSession().getMapper(DealerMapper.class).deleteDealer(id);
    }
}
