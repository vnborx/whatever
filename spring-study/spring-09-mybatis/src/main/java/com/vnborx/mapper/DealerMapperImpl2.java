package com.vnborx.mapper;

import com.vnborx.pojo.Dealer;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

public class DealerMapperImpl2 extends SqlSessionDaoSupport implements DealerMapper {
    public List<Dealer> selectDealer() {
        return getSqlSession().getMapper(DealerMapper.class).selectDealer();
    }
}
