package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.Slide;

public interface SlideDao {
    Slide getById(long id);
    List<Slide> getAll();
    void save(Slide slide);
    void delete(Slide slide);
}
