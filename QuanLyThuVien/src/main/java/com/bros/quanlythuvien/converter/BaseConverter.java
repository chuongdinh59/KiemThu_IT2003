/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bros.quanlythuvien.converter;

import org.modelmapper.ModelMapper;

/**
 *
 * @author Dinh Chuong
 * @param <Model>
 */
public class BaseConverter<Model> {

    public static ModelMapper modelMapper = new ModelMapper();

    public Model entityToModel(Object obj, Class<?> modelClass) {
        Object model = modelMapper.map(obj, modelClass);
        return (Model) model;
    }

//    public Entity modelToEntity(Model model, Class<? extends Entity> entityClass) {
//        Entity entity = modelMapper.map(model, entityClass);
//        return entity;
//    }

    public Object AToB(Object obj, Class<?> modelClass) {
        Object o = modelMapper.map(obj, modelClass);
        return o;
    }
}
