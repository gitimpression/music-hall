package zone.ctp.musichall.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zone.ctp.musichall.entity.Music;
import zone.ctp.musichall.mapper.MusicMapper;
import zone.ctp.musichall.service.MusicService;
import zone.ctp.musichall.vo.MusicVO;
import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ctp
 * @date 2023/5/3 4:34
 */
@Service
public class MusicServiceImpl implements MusicService {

    @Resource
    private MusicMapper musicMapper;

    @Value("${music-hall.init.dir-source}")
    private String dirPath;
    @Value("${music-hall.init.file-extend-name}")
    private String fileExtendName;

    @Override
    public List<MusicVO> list() {
        return musicMapper.list();
    }

    @Override
    public void init() {
        System.out.println("开始初始化数据库...");
        System.out.println("文件目录：" + dirPath);
        File dir = new File(dirPath);
        if (dir.exists()){
            String[] files = dir.list();
            if (files == null){
                System.out.println("初始化 - 没有文件");
                return;
            }
            // 过滤出音乐文件
            List<String> fileList = Stream.of(files).filter(f -> f.endsWith(fileExtendName)).collect(Collectors.toList());
            if (fileList.size() == 0){
                System.out.println("初始化 - 没有文件");
                return;
            }
            // 数据库现有记录不必再添加 取差集
            List<MusicVO> musicVOS = musicMapper.list();
            List<String> dbFiles = musicVOS.stream().map(MusicVO::getName).collect(Collectors.toList());
            fileList.removeAll(dbFiles);
            if (fileList.size() == 0){
                System.out.println("歌曲已全部记录在数据库");
                return;
            }
            // 需要添加的对象信息
            List<Music> list = new ArrayList<>();
            for (String file : fileList) {
                System.out.println("正在处理 " + file);
                String path = dirPath + File.separator + file;
                Music music = Music.of(path);
                list.add(music);
            }
            // 添加到数据库
            int insert = musicMapper.batchInsert(list);
            System.out.println(String.format("共 %d 个文件，成功添加 %d 个文件", fileList.size(), insert));
        }else{
            System.out.println("文件目录有误：" + dirPath);
        }
    }
}
