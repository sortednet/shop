package net.sorted.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="static_slide")
@PrimaryKeyJoinColumn(name="slide_id")
public class StaticSlide extends Slide {

    
    @Column(name = "content")
    private String content;
    
    @Column(name = "imageName")
    private String imageName;
    
    @Column(name = "videoName")
    private String videoName;
    
    @Column(name = "audioName")
    private String audioName;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
}
