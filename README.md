# Dự án Thực hành Spring Core & JDBC - Quản lý Sách

Đây là một dự án thực hành đơn giản được xây dựng nhằm củng cố các kiến thức cốt lõi của **Spring Framework**. Mục tiêu chính của dự án là áp dụng các nguyên lý **Inversion of Control (IoC)**, **Dependency Injection (DI)** và tương tác với cơ sở dữ liệu bằng **Spring JDBC (`JdbcTemplate`)**.

Ứng dụng cho phép thực hiện các thao tác quản lý sách cơ bản (CRUD) với một cơ sở dữ liệu trong bộ nhớ (H2 Database).

## Công nghệ sử dụng
- **Ngôn ngữ:** Java 1.8
- **Framework:** Spring Framework 5.3.x
    - `spring-context` (Core Container)
    - `spring-jdbc` (Hỗ trợ JDBC)
- **Công cụ Build & Quản lý Dependency:** Apache Maven
- **Cơ sở dữ liệu:** H2 Database (In-memory)
- **Thư viện Test:** JUnit 4

## Các chức năng chính
Ứng dụng có khả năng thực hiện các thao tác sau với đối tượng `Book`:
-   ✅ Thêm một cuốn sách mới vào cơ sở dữ liệu.
-   ✅ Lấy danh sách tất cả các sách.
-   ✅ Tìm kiếm một cuốn sách cụ thể dựa trên ID.
-   ✅ Cập nhật thông tin của một cuốn sách đã có.
-   ✅ Xóa một cuốn sách khỏi cơ sở dữ liệu dựa trên ID.

## Cấu trúc Dự án
Dự án được tổ chức theo cấu trúc package tiêu chuẩn để tách biệt các tầng logic:
```
src/main/
├── java/com/example/
│   ├── config/      # Chứa lớp cấu hình Spring (AppConfig)
│   ├── dao/         # Chứa Data Access Object (BookDao, BookDaoImpl)
│   ├── model/       # Chứa các lớp đối tượng (Book)
│   └── Main.java    # Lớp chính để chạy và kiểm thử ứng dụng
└── resources/
    └── schema.sql   # Script SQL để tự động tạo bảng khi ứng dụng khởi động
```

## Quá trình thực hiện
Để hoàn thành bài thực hành, tôi đã thực hiện các bước sau:

1.  **Thiết lập Dự án:**
    -   Tạo một dự án mới bằng **Apache Maven**.
    -   Cấu hình file `pom.xml` để khai báo các thư viện cần thiết: `spring-context`, `spring-jdbc`, và `h2database`.

2.  **Tạo Model và DAO Interface:**
    -   Định nghĩa lớp `Book` để đại diện cho đối tượng sách.
    -   Thiết kế `BookDao` interface, định nghĩa các phương thức trừu tượng cho việc thao tác dữ liệu, tuân thủ nguyên tắc lập trình theo Interface.

3.  **Cấu hình Spring bằng Java (Java-based Configuration):**
    -   Tạo lớp `AppConfig` với annotation `@Configuration` và `@ComponentScan` để Spring có thể tự động quét và tìm các bean.
    -   Định nghĩa bean `DataSource` để cấu hình kết nối tới H2 Database.
    -   Định nghĩa bean `JdbcTemplate` và **tiêm (inject)** `DataSource` vào nó.

4.  **Triển khai Tầng DAO:**
    -   Tạo lớp `BookDaoImpl` triển khai từ `BookDao` interface.
    -   Đánh dấu lớp bằng annotation `@Repository` để Spring nhận diện nó là một bean của tầng dữ liệu.
    -   Sử dụng **`@Autowired`** để tiêm `JdbcTemplate` vào `BookDaoImpl`.
    -   Viết code cho các phương thức CRUD bằng cách sử dụng các hàm tiện ích của `JdbcTemplate` (`update()`, `queryForObject()`, `query()`).
    -   Sử dụng `RowMapper` để ánh xạ dữ liệu từ `ResultSet` sang đối tượng `Book`.

5.  **Khởi tạo Cơ sở dữ liệu:**
    -   Viết một file `schema.sql` để định nghĩa cấu trúc bảng `books`.
    -   Cấu hình một bean `DataSourceInitializer` trong `AppConfig` để tự động chạy file `schema.sql` này mỗi khi ứng dụng khởi động, giúp tạo bảng một cách tự động.

6.  **Thực thi và Kiểm thử:**
    -   Tạo lớp `Main` với hàm `main()` để đóng vai trò là điểm khởi đầu của ứng dụng.
    -   Khởi tạo Spring Container bằng `AnnotationConfigApplicationContext` và nạp vào lớp `AppConfig`.
    -   Lấy `BookDao` bean từ container và lần lượt gọi các phương thức CRUD để thêm, sửa, xóa, truy vấn dữ liệu và in kết quả ra màn hình console để xác minh ứng dụng hoạt động chính xác.