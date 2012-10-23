package net.sorted.shop.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;

@Entity
@Table(name="slide_show")
public class SlideShow {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="slide_show_seq")
    @SequenceGenerator(name="slide_show_seq", sequenceName="slide_show_seq", allocationSize=100)
    @Column(name = "slide_show_id")
    private long slideShowId;
    
    @Column(name="title")
    private String title;
    
    @Column(name="style")
    private String style;
    
    @OneToMany(cascade={CascadeType.ALL})
    @JoinColumn(name = "slide_show_id")
    @IndexColumn(name="slide_number")
    private List<Slide> slides = new ArrayList<Slide>();

    public long getSlideShowId() {
        return slideShowId;
    }

    public void setSlideShowId(long slideShowId) {
        this.slideShowId = slideShowId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public Slide getSlide(int num) {
        return slides.get(num);
    }
    
    public void addSlide(Slide slide) {
        slides.add(slide);
    }
    
    public int getNumSlides() {
        return slides.size();
    }

}
