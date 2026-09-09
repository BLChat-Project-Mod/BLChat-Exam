# BLChat 考试仓库 / Exam Repository

<div align="center">

![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green)
![Forge](https://img.shields.io/badge/Forge-47-orange)
![Java](https://img.shields.io/badge/Java-17-red)
![License](https://img.shields.io/badge/License-LGPL--2.1-blue)

[English](README.md) | **简体中文**

</div>

---

## 🎓 考试项目声明 / Exam Project

本仓库为 **BLChat 开发组项目招聘考核** 专属仓库，用于考试提交与评审。

This repository is dedicated to the **BLChat dev team recruitment exam**, used for exam submission and review.

**考核问卷 / Exam Questionnaire**: <https://f.wps.cn/g/RsQ215rx/>

---

## 项目简介 / Project Intro

BLChat 是一个 Minecraft Forge Mod（Java 17 · MC 1.20.1 · Forge 47），将 B 站直播间的弹幕、礼物、Super Chat、大航海等事件实时显示在 Minecraft 游戏聊天栏中。

BLChat is a Minecraft Forge mod (Java 17 · MC 1.20.1 · Forge 47) that displays Bilibili live danmaku, gifts, Super Chats, and guard events in the Minecraft in-game chat in real time.

---

## 环境要求 / Requirements

| 依赖 | 版本 |
|------|------|
| JDK | 17 |
| Minecraft | 1.20.1 |
| Forge | 47.4.20 |

---

## 如何运行 / How to Run

### 1. 构建 / Build

```bash
./gradlew build
```

构建产物输出到 `build/libs/*.jar`。

Output jar: `build/libs/*.jar`

### 2. 调试运行客户端 / Run client for debugging

```bash
./gradlew runClient
```

会启动一个带本 Mod 的 Minecraft 客户端。

Launches a Minecraft client with this mod loaded.

### 3. 配置身份码 / Configure identity code

在游戏内使用指令 / Use the in-game command (requires OP):

```
/bilibili identitycode <身份码>
```

或编辑配置文件 `config/bilibilichat-config.json`：

```json
{
  "identityCode": "你的身份码"
}
```

> **注意 / Note**: 哔哩哔哩开放平台凭据（`ACCESS_KEY_ID` / `ACCESS_SECRET` / `APP_ID`）有意不提交到仓库，请在本地 `BilibiliClient.java` 中填写后再构建可使用弹幕的 jar。

---

## 许可证 / License

本项目基于 [LGPL-2.1](LICENSE) 许可证发布。

Licensed under the [LGPL-2.1](LICENSE).
