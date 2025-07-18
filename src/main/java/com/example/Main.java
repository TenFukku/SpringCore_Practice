package com.example;

import com.example.config.AppConfig;
import com.example.dao.BookDao;
import com.example.model.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Khởi động Spring Container
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // Lấy bean BookDao
        BookDao bookDao = context.getBean(BookDao.class);

        // 1. Thêm sách mới
        System.out.println("------ Thêm sách mới ------");
        Book book1 = new Book();
        book1.setTitle("The Lord of the Rings");
        book1.setAuthor("J.R.R. Tolkien");
        bookDao.save(book1);

        Book book2 = new Book();
        book2.setTitle("Dune");
        book2.setAuthor("Frank Herbert");
        bookDao.save(book2);

        // 2. Lấy tất cả sách và in ra
        System.out.println("\n------ Danh sách tất cả sách ------");
        List<Book> books = bookDao.findAll();
        books.forEach(System.out::println);

        // 3. Tìm sách theo ID
        System.out.println("\n------ Tìm sách có ID = 1 ------");
        Book foundBook = bookDao.findById(1);
        System.out.println(foundBook);

        // 4. Cập nhật sách
        System.out.println("\n------ Cập nhật sách có ID = 1 ------");
        foundBook.setTitle("The Hobbit");
        bookDao.update(foundBook);
        System.out.println(bookDao.findById(1));

        // 5. Xóa sách
        System.out.println("\n------ Xóa sách có ID = 2 ------");
        bookDao.deleteById(2);
        bookDao.findAll().forEach(System.out::println);
    }
}