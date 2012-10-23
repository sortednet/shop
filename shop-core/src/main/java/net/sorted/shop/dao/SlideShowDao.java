package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.SlideShow;

public interface SlideShowDao {
    SlideShow getById(long id);
    List<SlideShow> getAll();
    void save(SlideShow slide);
    void delete(SlideShow slide);
}
