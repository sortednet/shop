package net.sorted.shop.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="shop_user")
public class ShopUser {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shop_user_seq")
    @SequenceGenerator(name="shop_user_seq", sequenceName="shop_user_seq", allocationSize=100)
    @Column(name = "shop_user_id")
    private long id;
    
    @Column(name="user_name", unique=true)
    private String userName;
    
    @Column(name="password")
    private String password;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinColumn(name="site_role", nullable=false)
    private List<SiteRole> siteRoles;
    
    public long getShopUserId() {
        return id;
    }

    public void setShopUserId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<SiteRole> getSiteRoles() {
        return siteRoles;
    }

    public void setSiteRoles(List<SiteRole> siteRoles) {
        this.siteRoles = siteRoles;
    }
    
}
