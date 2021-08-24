package codegym.repository;

import codegym.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface IBookRepo extends CrudRepository<Book,Long> {
}
