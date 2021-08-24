package codegym.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int count;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "oder_book",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "oder_id"))
    List<Oder> list;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Oder> getList() {
        return list;
    }

    public void setList(List<Oder> list) {
        this.list = list;
    }

    public Book(long id, String name, int count, List<Oder> list) {
        this.id = id;
        this.name = name;
        this.count = count;
        this.list = list;
    }
}
