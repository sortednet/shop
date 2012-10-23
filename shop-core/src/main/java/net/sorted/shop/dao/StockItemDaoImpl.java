package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.StockItem;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class StockItemDaoImpl implements StockItemDao {
    private final HibernateTemplate hibernateTemplate;
    
    public StockItemDaoImpl(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Override
    public StockItem getById(long id) {
        return hibernateTemplate.get(StockItem.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<StockItem> getAll() {
        return hibernateTemplate.find("from " + StockItem.class.getName());
    }

    @Override
    public void save(StockItem item) {
        hibernateTemplate.saveOrUpdate(item);
    }

    @Override
    public void delete(StockItem item) {
        hibernateTemplate.delete(item);
    }

}
