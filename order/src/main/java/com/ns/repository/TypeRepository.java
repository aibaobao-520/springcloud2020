package com.ns.repository;

import com.ns.dto.TypeDto;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TypeRepository extends Mapper<TypeDto> {
     List<TypeDto> findAll();
     TypeDto findById(long id);
}
