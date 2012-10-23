package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.SlideShow;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class SlideShowDaoImpl implements SlideShowDao {
    private final HibernateTemplate hibernateTemplate;
    
    public SlideShowDaoImpl(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Override
    public SlideShow getById(long id) {
        return hibernateTemplate.get(SlideShow.class, id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SlideShow> getAll() {
        return hibernateTemplate.find("from " + SlideShow.class.getName());
    }

    @Override
    public void save(SlideShow item) {
        hibernateTemplate.saveOrUpdate(item);
    }

    @Override
    public void delete(SlideShow item) {
        hibernateTemplate.delete(item);
    }

}
