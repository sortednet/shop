package net.sorted;

import net.sorted.shop.dao.SlideShowDao;
import net.sorted.shop.domain.SlideShow;
import net.sorted.shop.domain.StaticSlide;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.transaction.annotation.Transactional;



public class CreateData {

    
    private SlideShowDao slideShowDao;
    
    
    
    
//    public CreateData(SlideShowDao slideShowDao) {
//        super();
//        this.slideShowDao = slideShowDao;
//    }

    @Transactional
    public void createData() {
        
        SlideShow ss = new SlideShow();
        ss.setTitle("Test slide show");
        ss.setStyle("basic");
        
        StaticSlide s1 = getDefaultStaticSlide();
        s1.setTitle("Slide 1");
        StaticSlide s2 = getDefaultStaticSlide();
        s2.setTitle("Slide 2");
        s2.setContent("This is another slide.\nNot much going on.\n\nPretty boring really.\n\nOr is it ????");
        s2.setImageName("hmm-man-in-suit.jpg");
        StaticSlide s3 = getDefaultStaticSlide();
        s3.setTitle("Slide 3");
        s3.setContent("This is the last slide.\n\nThank god!");
        s3.setImageName("group-of-suits.jpg");
        
        ss.addSlide(s1);
        ss.addSlide(s2);
        ss.addSlide(s3);
        
        slideShowDao.save(ss);
    }
    
    
    @Required
    public void setSlideShowDao(SlideShowDao slideShowDao) {
        this.slideShowDao = slideShowDao;
    }

    /**
     * This does not work.
     * 
     * The DAOs are using a different session to the one opened here - need to work out why
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("etc/spring/shop-create-data-context.xml");
        
        CreateData create = context.getBean("createData", CreateData.class);
        create.createData();        
    }
    
    private static StaticSlide getDefaultStaticSlide() {
        StaticSlide ss = new StaticSlide();
        ss.setContent("Blah blah blah\nRubarb Rubarb Rubarb");
        ss.setLayout("flow");
        ss.setTitle("Test Static Slide");        
        
        return ss;
    }

}
