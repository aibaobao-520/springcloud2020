package com.ns.service;

import com.ns.dto.TypeDto;

import java.util.List;

public interface TypeService {
     List<TypeDto> findAll();
    TypeDto findById(long id);
}
