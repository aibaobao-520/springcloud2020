package com.ns.service.Impl;

import com.ns.dto.TypeDto;
import com.ns.repository.TypeRepository;
import com.ns.service.TypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl  implements TypeService {
    @Resource
    private TypeRepository typeRepository;
    @Override
    public List<TypeDto> findAll() {
        return typeRepository.findAll();
    }

    @Override
    public TypeDto findById(long id) {
        return typeRepository.findById(id);
    }
}
