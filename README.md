# huggingface-java-sdk

这是一个huggingface语音模型的sdk，可以调用huggingface上的API获取语音文件


### 如何引入

- gradle仓库配置
```
  repositories {
        maven { url "https://s01.oss.sonatype.org/content/repositories/releases"}
        maven { url "https://s01.oss.sonatype.org/content/repositories/snapshots"}
        mavenLocal()
        mavenCentral()
    }
```

- 引入依赖
```
 implementation 'io.github.yakami129:huggingface-java-sdk:1.0.0-RELEASES'
```

### 如何使用

```
   // 参数详情请查阅：https://huggingface.co/docs/api-inference/index
   final HuggingFaceClient huggingFaceClient = HuggingFaceClient.builder()
                .huggingToken("Bearer XXXXXXXXXXXXX")
                .context("今日、私は遊びに行きたい")
                .directoryFile(FileUtil.file("tmp"))
                .modelId("mio/amadeus")
                .build();
   final String fileName = huggingFaceClient.generateAudioFlac().getName();
```

### 作者留言

如果您觉得有用，麻烦客官点个star，作者的邮箱为：okapi0129@outlook.com，如果想加入我们Ai交流群，可以通过邮箱联系我。