package codegym.service;

import codegym.model.Book;
import codegym.model.Oder;
import codegym.repository.IOderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class OderService {
    @Autowired
    IOderRepo iOderRepo;

    public ArrayList<Oder> findAll(){
        return (ArrayList<Oder>) iOderRepo.findAll();
    }

    public Oder findById(long id){
        return  iOderRepo.findById(id).get();
    }

    public Oder save(Oder oder){
        return  iOderRepo.save(oder);
    }
}
