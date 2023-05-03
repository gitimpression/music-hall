package zone.ctp.musichall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ctp
 * @date 2023/5/1 8:53
 */
@SpringBootApplication
@MapperScan("zone.ctp.musichall.mapper")
public class MusicHallApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicHallApplication.class, args);
    }
}
