package io.github.yakami129.huggingface.utils;

import cn.hutool.core.io.IoUtil;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * Created by alan on 2023/4/3.
 */
public class FfmpegUtils {

    public static String flacToWav(String ffmpegPath, String flacFilePath, String wavFilePath) throws IOException {
        final Runtime runtime = Runtime.getRuntime();
        StringBuffer cmdStr = new StringBuffer();
        cmdStr.append(ffmpegPath);
        cmdStr.append(" -i");
        cmdStr.append("\t");
        cmdStr.append(flacFilePath);
        cmdStr.append("\t");
        cmdStr.append(wavFilePath);
        final Process process = runtime.exec(cmdStr.toString());
        wait(process);
        return getLogs(process);
    }

    private static void wait(Process process) {
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static String getLogs(Process process) {
        InputStream in = null;

        StringBuilder sb = new StringBuilder();

        try {

            try {
                in = process.getInputStream();
                sb.append(IoUtil.read(in, Charset.forName("UTF-8")));
            } finally {
                IoUtil.close(in);
            }

            try {
                // 如果有error日志，直接抛出异常
                in = process.getErrorStream();
                String errorLog = IoUtil.read(in, Charset.forName("UTF-8"));
                sb.append(errorLog);
            } finally {
                IoUtil.close(in);
            }
        } finally {
            process.destroy();
        }

        return sb.toString();
    }

}
