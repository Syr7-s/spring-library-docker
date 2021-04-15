package com.syrisa.springlibrarydocker.service;

import com.syrisa.springlibrarydocker.model.Model;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

public interface ModelService<T extends Model> {
    T create(T t);

    T update(T t);

    Page<T> getAll(Pageable pageable);
}
