package com.ns.service.impl;

import com.ns.entity.Type;
import com.ns.repository.TypeRepository;
import com.ns.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TypeServiceImpl  implements TypeService {
    @Resource
    private TypeRepository typeRepository;
    @Override
    public List<Type> findAll() {
        return typeRepository.findAll();
    }
}
