package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.StockItem;

public interface StockItemDao {
    StockItem getById(long id);
    List<StockItem> getAll();
    void save(StockItem item);
    void delete(StockItem item);
}
