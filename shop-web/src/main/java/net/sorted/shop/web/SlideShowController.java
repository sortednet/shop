package net.sorted.shop.web;

import net.sorted.shop.dao.SlideShowDao;
import net.sorted.shop.domain.Slide;
import net.sorted.shop.domain.SlideShow;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/slideshow")
public class SlideShowController {
    
    
    protected static Logger logger = LoggerFactory.getLogger(SlideShowController.class);
    

    private SlideShowDao slideShowDao;
    
    @RequestMapping(value = "/slide/{id}/{num}", method = RequestMethod.GET)
    @Transactional
    public ModelAndView getSlideShowPage(@PathVariable long id, @PathVariable int num) {
    	SlideShow ss = slideShowDao.getById(id);
    	
    	ModelAndView mav = new ModelAndView();
    	
    	Slide s = ss.getSlide(num);
    	mav.setViewName("slide/"+s.getLayout());
    	mav.addObject("slideShow", ss);
    	mav.addObject("slide", s);
    	
    	return mav;
    }
    

    @Required
    public void setSlideShowDao(SlideShowDao slideShowDao) {
		this.slideShowDao = slideShowDao;
	}
    
}
