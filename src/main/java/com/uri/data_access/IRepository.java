package com.uri.data_access;

// Repository representing specific entity corresponds to a table or collection
public interface IRepository<TQueryFilter, TEntity> {
    void add(TEntity entity);
    void add(Iterable<TEntity> entities);
    Iterable<TEntity> get(TQueryFilter queryFilter);
    Iterable<TEntity> getAll();
    long count();
    void update(TQueryFilter queryFilter, TEntity entity);
    long delete(TQueryFilter queryFilter); // return count?
}
