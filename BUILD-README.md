# BLChat 多加载器构建说明

## 推荐构建方式：Forge（默认）

当前仓库根目录保留 **Forge** 构建为默认方案。

```bash
# 构建 Forge 1.20.1
./gradlew --no-daemon build
```

产物位于：`build/libs/bilibilichatmcforge-1.20-1.20.1-*.jar`

## 可选构建方式

### NeoForge

```bash
# 构建 NeoForge 1.20.1
./gradlew :neoforge:build
```

产物位于：`neoforge/build/libs/bilibilichatmcforge-1.20-1.20.1-neoforge-*.jar`

### Fabric

```bash
# 构建 Fabric 1.20.1
./gradlew :fabric:build
```

产物位于：`fabric/build/libs/bilibilichatmcforge-1.20-1.20.1-fabric-*.jar`

## 切换构建方案

在 `settings.gradle` 中：

```gradle
include 'neoforge'
include 'fabric'
```

如果只想保留 Forge，可注释掉上述两行，或直接构建根项目即可。

## 版本对应关系

| 加载器 | MC 版本 | Java |
|:---|:---|:---:|
| Forge | 1.20 ~ 1.20.1 | 17 |
| NeoForge | 1.20 ~ 1.20.1 | 17 |
| Fabric | 1.20 ~ 1.20.1 | 17 |