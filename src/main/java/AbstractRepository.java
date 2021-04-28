public abstract class AbstractRepository<TQueryFilter, TEntity> implements IRepository<TQueryFilter, TEntity> {
    @Override
    public void add(TEntity entity) {

    }

    @Override
    public void add(Iterable<TEntity> entities) {

    }

    @Override
    public Iterable<TEntity> get(TQueryFilter queryFilter) {
        return null;
    }

    @Override
    public Iterable<TEntity> getAll() {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void update(TEntity entity) {

    }

    @Override
    public void update(Iterable<TEntity> entities) {

    }

    @Override
    public void delete(TQueryFilter queryFilter) {

    }
}
