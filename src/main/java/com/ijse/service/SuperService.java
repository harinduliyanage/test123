package com.ijse.service;

public interface SuperService <T>{
    public T add(T t) throws Exception;
    public boolean update(T t) throws Exception;
    public boolean delete(T t)throws Exception;
    public T findById(Integer id)throws Exception;
    public Iterable<T> getAll()throws Exception;
}
