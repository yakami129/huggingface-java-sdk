package io.github.yakami129.huggingface;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * Created by alan on 2023/4/1.
 */
@Slf4j
@Builder
public class HuggingFaceClient {

    private String huggingToken;
    private String modelId;
    private String context;
    private File directoryFile;

    @Builder.Default
    private String huggingHost = "https://api-inference.huggingface.co";

    public File generateAudioFlac() {
        try (InputStream inputStream = HttpUtil.createPost(huggingHost + "/models/" + modelId)
                .header("Authorization", huggingToken)
                .body(context)
                .execute().bodyStream();) {
            String fileName = UUID.randomUUID().toString() + ".flac";
            return witerDirectoryFile(fileName, directoryFile, inputStream);
        } catch (IOException e) {
            log.warn("[WARN] witerDirectoryFile is error ,directoryPath:{}", directoryFile.getPath());
            return null;
        }
    }

    private File witerDirectoryFile(String fileName, File directoryFile, InputStream inputStream) throws IOException {
        // 在目录下面创建一个文件
        final File newFile = FileUtil.file(directoryFile.getPath(), fileName);
        if (log.isDebugEnabled()) {
            log.debug("filePath is {}", newFile.getPath());
        }
        return FileUtil.writeFromStream(inputStream, newFile);
    }

}
