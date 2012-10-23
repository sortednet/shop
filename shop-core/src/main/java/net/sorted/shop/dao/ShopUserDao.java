package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.ShopUser;

public interface ShopUserDao {
    ShopUser getById(long id);
    ShopUser getByName(String name);
    List<ShopUser> getAll();
    void save(ShopUser item);
    void delete(ShopUser item);
}
