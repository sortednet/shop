package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.Slide;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SlideDaoImpl implements SlideDao {
    private final HibernateTemplate hibernateTemplate;
    
    public SlideDaoImpl(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Override
    public Slide getById(long id) {
        return hibernateTemplate.get(Slide.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Slide> getAll() {
        return hibernateTemplate.find("from " + Slide.class.getName());
    }

    @Override
    public void save(Slide item) {
        hibernateTemplate.saveOrUpdate(item);
    }

    @Override
    public void delete(Slide item) {
        hibernateTemplate.delete(item);
    }

}
