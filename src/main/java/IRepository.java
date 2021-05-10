// Repository for a specific entity usually representing a table or collection
// TEntity should have TKey
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
