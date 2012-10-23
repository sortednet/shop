package net.sorted.shop.web;

import java.util.ArrayList;
import java.util.List;

import net.sorted.shop.dao.ShopUserDao;
import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.SiteRole;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ShopUserDetailsService implements UserDetailsService {

    private ShopUserDao shopUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ShopUser u = shopUserDao.getByName(username);
//        boolean enabled = true;
//        boolean accountNonExpired = true;
//        boolean credentialsNonExpired = true;
//        boolean accountNonLocked = true;

        return new User(u.getUserName(), u.getPassword(), getGrantedAuthorities(u.getSiteRoles()));
    }

    public ShopUserDao getShopUserDao() {
        return shopUserDao;
    }

    @Required
    public void setShopUserDao(ShopUserDao shopUserDao) {
        this.shopUserDao = shopUserDao;
    }
    
    
    public static List<GrantedAuthority> getGrantedAuthorities(List<SiteRole> roles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (SiteRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }
}
