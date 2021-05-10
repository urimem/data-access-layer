// Repository representing specific entity corresponds to a table or collection
public interface IRepository<TQueryFilter, TEntity> {
    void add(TEntity entity);
    void add(Iterable<TEntity> entities);
    Iterable<TEntity> get(TQueryFilter queryFilter);
    Iterable<TEntity> getAll();
    long count();
    void update(TEntity entity);
    void update(Iterable<TEntity> entities);
    void delete(TQueryFilter queryFilter); // return count?
}
