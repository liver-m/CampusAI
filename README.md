# CampusAI | 校园智能体
![Java CI](https://github.com/liver-m/StudentManagementSystem/actions/workflows/maven.yml/badge.svg)

## 📖 项目简介 (Introduction)
- **中文**：用 Spring Boot + Tool-Calling Agent 构建的生产级多智能体系统，支持自主规划、知识检索、工具调用与可观测性，演示 AI 如何真正落地到真实业务场景。
- **English**: The production-level multi-agent system built with Spring Boot + Tool-Calling Agent supports autonomous planning, knowledge retrieval, tool calling, and observability, demonstrating how AI can truly be implemented in real business scenarios.

## 🚀 进化路线图 (Roadmap)
- [x] **阶段 0 (Phase 0)**: 环境配置与自动化构建 (CI/CD)。
- [x] **阶段 1a**: Spring Boot 4.0.5 骨架 + Docker（已完成）。
- [x] **阶段 1b**: Java 21 虚拟线程（已完成）。
- [x] **阶段 2 (Phase 2)**: 云原生化docker-compose + MySQL(已完成)。
- [ ] **阶段 3 (Phase 3)**: AI 注入 (接入 LangChain4j 与 RAG 架构)。

## 🛠️ 技术栈 (Tech Stack)
- **后端 (Backend)**: Java 21 (虚拟线程), Spring Boot 4.0.5, JPA.
- **数据库 (Database)**: MySQL.
- **工具 (Tools)**: Docker, docker-compose, Maven, GitHub Actions.

## 🐳 快速启动 (Quick Start)
1. 复制配置文件: `cp docker-compose.example.yml docker-compose.yml`
2. 修改 `docker-compose.yml` 里的密码
3. 一键启动: `docker-compose up --build`

## 📡 API 接口 (Endpoints)
| Method | URL            | 说明                      |
|--------|----------------|-------------------------|
| GET    | /students      | 搜索所有学生                  |
| GET    | /students/{id} | 搜索单个学生                  |
| GET    | /students/slow | Virtual Threads 高并发演示接口 |
| POST   | /students      | 添加学生                    |
| PUT    | /students/{id} | 修改学生信息                  |
| DELETE | /students/{id} | 删除学生                    |

## 🏗️ 系统架构 (Architecture)
```
 HTTP Request
      ↓
[Controller层]    负责接受请求，调用service
      ↓
 [Service层]      负责执行业务逻辑
      ↓
[Repository层]    负责与MySQL连接
      ↓
   [MySQL]
```

## ⚡ Virtual Threads
- 技术：Java 21 Virtual Threads
- 问题：传统平台线程在高并发 I/O 阻塞时，什么都干不了，大量并发就需要大量线程，浪费资源
- 解决：阻塞时自动卸载，平台线程去干别的，资源利用率大幅提升
- 验证：通过 VisualVM 对比线程视图，确认虚拟线程与平台线程的差异
