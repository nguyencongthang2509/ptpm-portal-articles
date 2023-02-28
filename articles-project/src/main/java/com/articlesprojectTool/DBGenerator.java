package com.articlesprojectTool;

import com.articlesproject.entity.a.BaiViet;
import com.articlesproject.entity.a.Comments;
import com.articlesproject.entity.a.TheLoai;
import com.articlesproject.entity.a.Users;
import com.articlesproject.repository.AlbumRepository;
import com.articlesproject.repository.BaiVietRepository;
import com.articlesproject.repository.CommentRepository;
import com.articlesproject.repository.DanhGiaRepository;
import com.articlesproject.repository.DiemRepository;
import com.articlesproject.repository.TheLoaiRepository;
import com.articlesproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(
        basePackages = "com.articlesproject.repository"
)
public class DBGenerator implements CommandLineRunner {


    private final boolean IS_RELEASE = false;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private BaiVietRepository baiVietRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private DanhGiaRepository danhGiaRepository;

    @Autowired
    private DiemRepository diemRepository;

    @Autowired
    private TheLoaiRepository theLoaiRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        Users users = new Users();
        users.setMa("PH27676");
        users.setTen("Le Van A");
        users.setEmail("leVanA@fpt.edu.vn");
        users.setSoDienThoai("0343434343");
        users.setRole(0);
        userRepository.save(users);

        Users users1 = new Users();
        users1.setMa("PH99999");
        users1.setTen("Tran Van B");
        users1.setEmail("tranVanB@fpt.edu.vn");
        users1.setSoDienThoai("099999999");
        users1.setRole(1);
        userRepository.save(users1);

        TheLoai theLoai = new TheLoai();
        theLoai.setMa("SOF1");
        theLoai.setTen("Java");
        theLoaiRepository.save(theLoai);

        TheLoai theLoai1 = new TheLoai();
        theLoai1.setMa("FE1");
        theLoai1.setTen("Font-End");
        theLoaiRepository.save(theLoai1);


        BaiViet baiViet = new BaiViet();
        baiViet.setTieuDe("Authorization on Angular Routes");
        baiViet.setNoiDung("<H2>Giới thiệu\n</H2></br><p>AngularJS đã đi một chặng đường dài kể từ khi được giới thiệu. Nó là một template JavaScript toàn diện cho Single Page Application (SPA) phát triển. Nó có một số tính năng tuyệt vời như 2 chiều ràng buộc, chỉ thị, vv và chủ đề này sẽ tập trung vào security ở trong Angular JS.\n" +
                "\n" +
                "Để hiểu được bài này, bạn cần có kiến thức cơ bản về AngularJS, Angular Routing và Promises. Tôi sẽ hướng dẫn bạn từng bước để thực hiện security ở trong AngularJS</p>");
        baiViet.setTrangThai(1);
        baiViet.setCreateDate(1900800000L);
        baiViet.setThoiGianKiemDuyet(1900800000L);
        baiViet.setUsersId(users.getId());
        baiViet.setTheLoaiId(theLoai1.getId());
        baiVietRepository.save(baiViet);

        BaiViet baiViet1 = new BaiViet();
        baiViet1.setTieuDe("Abstract Class & Interface");
        baiViet1.setNoiDung("<H2>Abstract Class</H2></br><p>Được sử dụng cho hai mục đích:\n" +
                "1. Giảm thiểu lượng code lặp khi chúng ta có nhiều class khá giống nhau về lượng thuộc tính đặc tả và chức năng tương tác.\n" +
                "\n" +
                "Ví dụ như trước đó chúng ta có các class mở rộng là Worker và Teacher cùng kế thừa lại các đặc tính của class Person. Trong trường hợp logic của chương trình không cần tạo object trực tiếp từ thao tác new Person() thì chúng ta có thể gắn từ khóa abstract vào phía trước từ khóa class trong định nghĩa của class Person.</p>");
        baiViet1.setTrangThai(1);
        baiViet1.setCreateDate(1900800000L);
        baiViet1.setThoiGianKiemDuyet(1900800000L);
        baiViet1.setUsersId(users1.getId());
        baiViet1.setTheLoaiId(theLoai.getId());
        baiVietRepository.save(baiViet1);

        BaiViet baiViet2 = new BaiViet();
        baiViet2.setTieuDe("Types & Printer");
        baiViet2.setNoiDung("<p>Tiếp tục câu chuyện sau khi đã copy/paste và chạy được chương trình \"Hello World\" của C; Nói về cái từ khóa int được đặt ở phía trước khối main() thì mình cũng biết được lơ mơ về cái công dụng của nó. Cái này được gọi là type hinting - dịch nôm na là chỉ dẫn, định dạng kiểu dữ liệu - tức là kiểu dữ liệu sẽ xuất hiện ở vị trí nào đó trong code ví dụ như kiểu dữ liệu mà một biến sẽ lưu hoặc một hàm sẽ trả về khi được gọi.</p></br>" +
                "<p> Và những ngôn ngữ mà bắt buộc thao tác type hinting trong code thì thường được gọi là static typing, bởi vì kiểu của dữ liệu cần làm việc đã được cố định static chứ không được linh động dynamic như trường hợp của JavaScript nữa. Nếu chúng ta đặt một chuỗi vào vị trí đã được hint là một giá trị số học thì trình biên dịch compiler sẽ báo lỗi ngay khi thực hiện biên dịch sang tệp thực thi; Hoặc nếu sử dụng một trình thông dịch interpreter để chạy code trực tiếp thì khi gặp dữ liệu thực thi không đúng với hint cũng sẽ báo lỗi tức thì.</p>");
        baiViet2.setTrangThai(1);
        baiViet2.setCreateDate(1900800000L);
        baiViet2.setThoiGianKiemDuyet(1900800000L);
        baiViet2.setUsersId(users1.getId());
        baiViet2.setTheLoaiId(theLoai.getId());
        baiVietRepository.save(baiViet2);

        Comments comments = new Comments();
        comments.setNoiDung("Rất biết ơn về những chia sẻ thực sự bổ ích của bạn.\n" +
                "Mong bạn và gia đình nhiều sức khỏe, bình an.\n" +
                "HH");
        comments.setKieu(1);
        comments.setThoiGianComment(1900800000L);
        comments.setUsersId(users1.getId());
        comments.setBaiVietId(baiViet.getId());


    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}
