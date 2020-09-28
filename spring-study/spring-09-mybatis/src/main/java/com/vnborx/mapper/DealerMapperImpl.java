package com.vnborx.mapper;

import com.vnborx.pojo.Dealer;
import org.mybatis.spring.SqlSessionTemplate;

import java.util.List;

public class DealerMapperImpl implements DealerMapper {
    private SqlSessionTemplate sqlSession;

    public void setSqlSession(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Dealer> selectDealer() {
        DealerMapper mapper = sqlSession.getMapper(DealerMapper.class);
        return mapper.selectDealer();
    }
}
