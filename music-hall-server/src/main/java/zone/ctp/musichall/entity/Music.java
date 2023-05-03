package zone.ctp.musichall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.id3.ID3v23Frame;

import java.io.File;

/**
 * @author ctp
 * @date 2023/5/3 4:37
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Music {
    private Long id;
    private String name;
    private String singer;
    private String album;
    private Integer duration;
    private Long size;
    private String img;

    public static Music of(String path){
        return createMusicObject(new File(path));
    }

    public static Music of(File mp3File){
        return createMusicObject(mp3File);
    }

    /**
     * 构建音乐文件对象
     */
    private static Music createMusicObject(File file){
        try {
            MP3File mp3File = (MP3File) AudioFileIO.read(file);
            AudioHeader audioHeader = mp3File.getAudioHeader();

            // 歌名 使用文件名作为歌名
            // ID3v23Frame songName = (ID3v23Frame)mp3File.getID3v2Tag().frameMap.get("TIT2");

            // 歌手
            ID3v23Frame singer = (ID3v23Frame)mp3File.getID3v2Tag().frameMap.get("TPE1");

            // 专辑
            ID3v23Frame alb = (ID3v23Frame)mp3File.getID3v2Tag().frameMap.get("TALB");

            // 时长
            int duration = audioHeader.getTrackLength();
            Music music = Music.builder()
                    .album(alb.getContent())
                    .singer(singer.getContent())
                    .name(file.getName())
                    .duration(duration)
                    .size(file.length())
                    .build();
            return music;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            Music music = Music.builder()
                    .name(file.getName())
                    .size(file.length())
                    .build();
            return music;
        }
    }
}