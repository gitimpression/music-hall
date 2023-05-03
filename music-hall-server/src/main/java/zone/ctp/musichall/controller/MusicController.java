package zone.ctp.musichall.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zone.ctp.musichall.service.MusicService;
import zone.ctp.musichall.util.R;
import zone.ctp.musichall.vo.MusicVO;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author ctp
 * @date 2023/5/1 11:29
 */
@RestController
@RequestMapping("/music")
public class MusicController {

    @Resource
    private MusicService musicService;

    @GetMapping("/list")
    public R<List<MusicVO>> list() {
        List<MusicVO> list = musicService.list();
        return R.ok(list);
    }

    @GetMapping("/init")
    public R<List<MusicVO>> init() {
        musicService.init();
        return R.ok();
    }
}
