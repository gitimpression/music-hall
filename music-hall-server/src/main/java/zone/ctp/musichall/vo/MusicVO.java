package zone.ctp.musichall.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ctp
 * @date 2023/5/1 11:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MusicVO {
    private String name;
    private String singer;
    private String album;
    private Integer duration;
    private String size;
    private String img;
}
