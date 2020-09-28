package com.vnborx.mapper;

import com.vnborx.pojo.Dealer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DealerMapper {
    public List<Dealer> selectDealer();
    public int createDealer(Dealer dealer);
    public int deleteDealer(int id);
}
