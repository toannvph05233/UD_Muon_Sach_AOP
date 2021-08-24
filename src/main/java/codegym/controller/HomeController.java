package codegym.controller;

import codegym.model.Book;
import codegym.model.Oder;
import codegym.model.User;
import codegym.service.BookService;
import codegym.service.OderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class HomeController {
    @Autowired
    BookService bookService;

    @Autowired
    OderService oderService;

    @GetMapping("/home")
    public ModelAndView home(HttpSession httpSession,@CookieValue(value = "views",defaultValue = "0") long views, HttpServletResponse response) {
        Cookie cookie = new Cookie("views",String.valueOf(views+1));
        cookie.setMaxAge(60*60*24);
        response.addCookie(cookie);

        User user = (User) httpSession.getAttribute("user");

        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("list", bookService.findAll());
        modelAndView.addObject("cookie", cookie);
        modelAndView.addObject("xinchao", "xin chao " + user.getEmail());
        return modelAndView;
    }

    @GetMapping("/remove")
    public ModelAndView remove(HttpServletResponse response) {
        Cookie cookie = new Cookie("views","");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }



    @GetMapping("/oder/{idBook}")
    public ModelAndView oder(@PathVariable long idBook) throws Exception {
        Book book = bookService.findById(idBook);
        if (book.getCount() == 0){
            throw new Exception();
        }
        book.setCount(book.getCount() - 1);

        Oder oder = new Oder();
        long code = (long) (Math.random() * (99999 - 10000) + 10000);
        oder.setCode(code);

        long millis = System.currentTimeMillis();
        oder.setDate(new java.sql.Date(millis));

        List<Oder> oders = book.getList();
        oders.add(oder);
        book.setList(oders);

        oderService.save(oder);
        bookService.save(book);

        ModelAndView modelAndView = new ModelAndView("redirect:/home");
        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleError(Exception e) {
        ModelAndView modelAndView = new ModelAndView("error");
        return modelAndView;
    }

}
