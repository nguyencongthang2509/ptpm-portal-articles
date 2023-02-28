package com.articlesprojectTool;

import com.articlesproject.entity.a.Album;
import com.articlesproject.entity.a.BaiViet;
import com.articlesproject.entity.a.Comments;
import com.articlesproject.entity.a.DanhGia;
import com.articlesproject.entity.a.Diem;
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
        users.setId(userRepository.save(users).getId());

        Users users1 = new Users();
        users1.setMa("PH99999");
        users1.setTen("Tran Van B");
        users1.setEmail("tranVanB@fpt.edu.vn");
        users1.setSoDienThoai("099999999");
        users1.setRole(1);
        users.setId(userRepository.save(users1).getId());


        TheLoai theLoai = new TheLoai();
        theLoai.setMa("SOF1");
        theLoai.setTen("Java");
        theLoai.setId(theLoaiRepository.save(theLoai).getId());

        TheLoai theLoai1 = new TheLoai();
        theLoai1.setMa("FE1");
        theLoai1.setTen("Font-End");
        theLoai.setId(theLoaiRepository.save(theLoai1).getId());


        BaiViet baiViet = new BaiViet();
        baiViet.setTieuDe("Authorization on Angular Routes");
        baiViet.setNoiDung("<H2>Giới thiệu\n</H2>");
        baiViet.setTrangThai(1);
        baiViet.setCreateDate(1900800000L);
        baiViet.setThoiGianKiemDuyet(1900800000L);
        baiViet.setUsersId(users.getId());
        baiViet.setTym(10);
        baiViet.setTheLoaiId(theLoai1.getId());
        baiViet.setId(baiVietRepository.save(baiViet).getId());

        BaiViet baiViet1 = new BaiViet();
        baiViet1.setTieuDe("Abstract Class & Interface");
        baiViet1.setNoiDung("<H2>Abstract Class</H2>");
        baiViet1.setTrangThai(1);
        baiViet1.setCreateDate(1900800000L);
        baiViet1.setThoiGianKiemDuyet(1900800000L);
        baiViet1.setTym(10);
        baiViet1.setUsersId(users1.getId());
        baiViet1.setTheLoaiId(theLoai.getId());
        baiViet.setId(baiVietRepository.save(baiViet1).getId());

        BaiViet baiViet2 = new BaiViet();
        baiViet2.setTieuDe("Types & Printer");
        baiViet2.setNoiDung("<p>Tiếp tục câu chuyện sau khi đã copy/paste ");
        baiViet2.setTrangThai(1);
        baiViet2.setCreateDate(1900800000L);
        baiViet2.setThoiGianKiemDuyet(1900800000L);
        baiViet2.setUsersId(users1.getId());
        baiViet2.setTym(5);
        baiViet2.setTheLoaiId(theLoai.getId());
        baiViet.setId(baiVietRepository.save(baiViet2).getId());


        Comments comments = new Comments();
        comments.setNoiDung("Rất biết ơn về những chia sẻ thực sự bổ ích của bạn.\n" +
                "Mong bạn và gia đình nhiều sức khỏe, bình an.\n" +
                "HH");
        comments.setKieu(1);
        comments.setThoiGianComment(1900800000L);
        comments.setUsersId(users1.getId());
        comments.setBaiVietId(baiViet.getId());
        comments.setId(commentRepository.save(comments).getId());

        Comments comments1 = new Comments();
        comments1.setNoiDung("thank");
        comments1.setKieu(1);
        comments1.setThoiGianComment(1900800000L);
        comments1.setUsersId(users.getId());
        comments1.setRelay(comments.getId());
        comments1.setBaiVietId(baiViet.getId());
        comments.setId(commentRepository.save(comments1).getId());

        Comments comments2 = new Comments();
        comments2.setNoiDung("Anh Thịnh ơi, hiện tại team em đang cần làm slide liên quan tới value của BA, có một số phần cần hình ảnh minh hoạ. Không biết mấy hình sketch anh dùng làm ảnh thumbnail cho bài viết thì anh vẽ ở đâu ạ? Hoặc nếu được ko biết em có thể xin hình ảnh của blog mình được ko ạ? Em cảm ơn anh nhiều ạ.");
        comments2.setKieu(1);
        comments2.setThoiGianComment(1900800000L);
        comments2.setUsersId(users1.getId());
        comments2.setBaiVietId(baiViet1.getId());
        comments.setId(commentRepository.save(comments2).getId());

        Comments comments3 = new Comments();
        comments3.setNoiDung("À hình đó anh tự vẽ á, em cứ lấy thoải mái nhé, nhưng nhớ ghi nguồn thinhnotes.com vào giúp anh nha");
        comments3.setKieu(1);
        comments3.setThoiGianComment(1900800000L);
        comments3.setUsersId(users.getId());
        comments3.setRelay(comments2.getId());
        comments3.setBaiVietId(baiViet1.getId());
        comments.setId(commentRepository.save(comments3).getId());


        Comments comments4 = new Comments();
        comments4.setNoiDung("Em chào anh, bài viết của anh rất bổ ích. Em muốn hỏi thêm 1 chút xíu về User Req trong FS ạ.\n" +
                "Theo như trong bài thì phần này sẽ focus vào Business & Stakeholder Reqs mà theo em được hiểu thì Stakeholder Req nó là requirement chi tiết hơn của Business Req.\n" +
                "Vậy thì trong FS mình cần mention cả 2 loại này 1 cách độc lập hay sao anh nhỉ?");
        comments4.setKieu(1);
        comments4.setThoiGianComment(1900800000L);
        comments4.setUsersId(users.getId());
        comments4.setRelay(comments.getId());
        comments4.setBaiVietId(baiViet2.getId());
        comments.setId(commentRepository.save(comments4).getId());


        Album album = new Album();
        album.setNoiDung("Java");
        album.setBaiVietId(baiViet1.getId());
        album.setUsersId(users1.getId());
        album.setLoai(1);
        album.setId(albumRepository.save(album).getId());

        Album album1 = new Album();
        album1.setNoiDung("Java");
        album1.setBaiVietId(baiViet2.getId());
        album1.setUsersId(users1.getId());
        album1.setLoai(1);
        album.setId(albumRepository.save(album1).getId());

        Album album2 = new Album();
        album2.setBaiVietId(baiViet1.getId());
        album2.setUsersId(users.getId());
        album2.setLoai(0);
        album.setId(albumRepository.save(album2).getId());

        Diem diem = new Diem();
        diem.setDiem(9);
        diem.setCreateAt(1900800000L);
        diem.setPhanHoi("");
        diem.setThoiGianNhanDiem(1900800000L);
        diem.setBaiVietId(baiViet.getId());
        diem.setUsersId(users.getId());
        diem.setId(diemRepository.save(diem).getId());

        Diem diem1 = new Diem();
        diem1.setDiem(8);
        diem1.setCreateAt(1900800000L);
        diem1.setPhanHoi("");
        diem1.setThoiGianNhanDiem(1900800000L);
        diem1.setBaiVietId(baiViet.getId());
        diem1.setUsersId(users.getId());
        diem.setId(diemRepository.save(diem1).getId());

        Diem diem2 = new Diem();
        diem2.setDiem(8);
        diem2.setCreateAt(1900800000L);
        diem2.setPhanHoi("");
        diem2.setThoiGianNhanDiem(1900800000L);
        diem2.setBaiVietId(baiViet1.getId());
        diem2.setUsersId(users.getId());
        diem.setId(diemRepository.save(diem2).getId());

        Diem diem3 = new Diem();
        diem3.setDiem(8);
        diem3.setCreateAt(1900800000L);
        diem3.setPhanHoi("");
        diem3.setThoiGianNhanDiem(1900800000L);
        diem3.setBaiVietId(baiViet2.getId());
        diem3.setUsersId(users.getId());
        diem.setId(diemRepository.save(diem3).getId());

        DanhGia danhGia = new DanhGia();
        danhGia.setSao(2);
        danhGia.setThoiGianDanhGia(1900800000L);
        danhGia.setNoiDung("Bug");
        danhGia.setUsersId(users.getId());
        danhGia.setBaiVietId(baiViet1.getId());
        danhGia.setId(danhGiaRepository.save(danhGia).getId());

        DanhGia danhGia1 = new DanhGia();
        danhGia1.setSao(2);
        danhGia1.setThoiGianDanhGia(1900800000L);
        danhGia1.setNoiDung("ERROR 404");
        danhGia1.setUsersId(users.getId());
        danhGia1.setBaiVietId(baiViet.getId());
        danhGia.setId(danhGiaRepository.save(danhGia1).getId());


        DanhGia danhGia2 = new DanhGia();
        danhGia2.setSao(2);
        danhGia2.setThoiGianDanhGia(1900800000L);
        danhGia2.setNoiDung("ERROR 500");
        danhGia2.setUsersId(users.getId());
        danhGia2.setBaiVietId(baiViet2.getId());
        danhGia.setId(danhGiaRepository.save(danhGia2).getId());


    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(DBGenerator.class);
        ctx.close();
    }

}
