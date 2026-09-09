# 贡献指南（Contributing）

## 🎓 考试项目声明

本仓库为 **BLChat 开发组项目招聘考核** 专属仓库，用于考试提交与评审。请遵守考核要求并在截止前完成提交。

考核问卷：<https://f.wps.cn/g/RsQ215rx/>

## 项目简介

BLChat 是一个 Minecraft Forge Mod（Java 17 · MC 1.20.1 · Forge 47），将 B 站直播间的弹幕、礼物、Super Chat、大航海等事件实时显示在游戏聊天栏中。

## 配置

- 身份码：通过指令 `/bilibili identitycode <身份码>` 或配置文件 `config/bilibilichat-config.json` 设置
- 哔哩哔哩开放平台凭据（`ACCESS_KEY_ID` / `ACCESS_SECRET` / `APP_ID`）**有意不提交**到仓库，请在本地 `BilibiliClient.java` 中填写后再构建可分发的 jar
- **不要**将任何真实的密钥、token 或个人敏感信息提交到仓库

## 许可证

本项目基于 [LGPL-2.1](LICENSE) 许可证发布。
