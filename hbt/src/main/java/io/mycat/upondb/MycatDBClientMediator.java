package io.mycat.upondb;

import io.mycat.api.collector.RowBaseIterator;
import io.mycat.api.collector.UpdateRowIterator;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class MycatDBClientMediator implements MycatDBContext {
    private String schema;
    private long maxRow;
    private final Map<String, Object> varbables = new HashMap<>();


    @Override
    public PrepareObject prepare(String sql) {
        return getUponDBSharedServer().prepare(sql, (MycatDBContext) this);
    }

    @Override
    public RowBaseIterator executeQuery(Long id, List<Object> params) {
        return getUponDBSharedServer().execute(id, params,(MycatDBContext) this);
    }

    @Override
    public UpdateRowIterator executeUpdate(Long id, List<Object> params) {
        return (UpdateRowIterator) getUponDBSharedServer().execute(id, params, (MycatDBContext) this);
    }

    @Override
    public Iterator<RowBaseIterator> executeSqls(String sql) {
        return getUponDBSharedServer().executeSqls(sql, (MycatDBContext) this);
    }

    @Override
    public UpdateRowIterator update(String sql) {
        return getUponDBSharedServer().update(sql, (MycatDBContext) this);
    }

    @Override
    public RowBaseIterator query(String sql) {
        return getUponDBSharedServer().query(sql, (MycatDBContext) this);
    }

    @Override
    public UpdateRowIterator loadData(String sql) {
        return getUponDBSharedServer().loadData(sql,(MycatDBContext) this);
    }

    @Override
    public RowBaseIterator executeRel(String text) {
        return getUponDBSharedServer().executeRel(text,(MycatDBContext) this);
    }

    @Override
    public String getSchema() {
        return schema;
    }

    @Override
    public void useSchema(String normalize) {
        schema = normalize;
    }

    @Override
    public long getMaxRow() {
        return maxRow;
    }

    @Override
    public void setMaxRow(long value) {
        maxRow = value;
    }

    @Override
    public void set(String target, Object value) {
        varbables.put(target, value);
    }

    @Override
    public Object get(String target) {
        Object o = varbables.get(target);
        if (o == null) {
            return getUponDBSharedServer().get(target);
        } else {
            return o;
        }
    }

    @Override
    public void closePrepare(Long id) {
        getUponDBSharedServer().closePrepare(id);
    }

    @Override
    public List<String> explain(String sql) {
        return getUponDBSharedServer().explain(sql,this);
    }


    @Override
    public List<String> explainRel(String text) {
        return getUponDBSharedServer().explain(text,this);
    }
}
