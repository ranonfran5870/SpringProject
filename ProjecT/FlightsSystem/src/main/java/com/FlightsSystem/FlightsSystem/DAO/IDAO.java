package com.FlightsSystem.FlightsSystem.DAO;

import java.util.List;

public interface IDAO <T,V>{

    public List<T> getAll();
    public T getById(V _id);
    public void Add(T t);
    public void Remove(T t);
    public void Update(T t);
}
