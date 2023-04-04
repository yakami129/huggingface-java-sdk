package io.github.yakami129.huggingface;


import cn.hutool.core.io.FileUtil;
import io.github.yakami129.huggingface.utils.FfmpegUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by alan on 2023/4/1.
 */
class HuggingFaceClientTest {

    @Test
    void generateAudioFlac() {

        final HuggingFaceClient huggingFaceClient = HuggingFaceClient.builder()
                .huggingToken("Bearer XXXXXXXXXXXXX")
                .context("今日、私は遊びに行きたい")
                .directoryFile(FileUtil.file("tmp"))
                .modelId("mio/amadeus")
                .build();
        final String fileName = huggingFaceClient.generateAudioFlac().getName();
    }

    @Test
    void flacToWav() throws IOException {

        final HuggingFaceClient huggingFaceClient = HuggingFaceClient.builder()
                .huggingToken("Bearer XXXXXXXXXXXXXXX")
                .context("俺が変わってやる。と言いたいところだが、今回はお前一人に任せよう")
                .directoryFile(FileUtil.file("tmp"))
                .modelId("mio/amadeus")
                .build();

        final File flacFile = huggingFaceClient.generateAudioFlac();
        String fileName = UUID.randomUUID().toString() + ".wav";
        final File wavTmp = FileUtil.file("tmp/" + fileName);

        // 将flac转换为wav，你的ffmpeg的地址
        String ffmpeg = "/Users/alan/Documents/tool/ffmpeg/ffmpeg";
        final String log = FfmpegUtils.flacToWav(ffmpeg, flacFile.getAbsolutePath(), wavTmp.getAbsolutePath());
        System.out.println(log);
    }

}