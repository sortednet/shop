package net.sorted.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="slide")
@Inheritance(strategy=InheritanceType.JOINED)
public class Slide {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="slide_seq")
    @SequenceGenerator(name="slide_seq", sequenceName="slide_seq", allocationSize=100)
    @Column(name = "slide_id")
    private long slideId;
    
    @Column(name="slide_number", nullable=false)
    private int slideNumber;
    
    @Column(name = "title", nullable=false)
    private String title;
    
    @Column(name = "layout")
    private String layout;

    @ManyToOne
//    @JoinColumn(name="slide_show_id", insertable=false, updatable=false, nullable=false)
    @JoinColumn(name="slide_show_id")
    private SlideShow slideShow;
    
    public long getSlideId() {
        return slideId;
    }

    public void setSlideId(long slideId) {
        this.slideId = slideId;
    }

    public SlideShow getSlideShow() {
        return slideShow;
    }

    public void setSlideShow(SlideShow slideShow) {
        this.slideShow = slideShow;
    }

    public int getSlideNumber() {
        return slideNumber;
    }

    public void setSlideNumber(int slideNumber) {
        this.slideNumber = slideNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }
    
    
    
}
