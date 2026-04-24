# CampusAI | 校园智能体

[![Java CI](https://github.com/liver-m/CampusAI/actions/workflows/maven.yml/badge.svg)](https://github.com/liver-m/CampusAI/actions/workflows/maven.yml)

## 📖 项目简介 (Introduction)

- **中文**：用 Spring Boot + Tool-Calling Agent 构建的生产级多智能体系统，支持自主规划、知识检索、工具调用与可观测性，演示 AI 如何真正落地到真实业务场景。
- **English**: The production-level multi-agent system built with Spring Boot + Tool-Calling Agent supports autonomous planning, knowledge retrieval, tool calling, and observability, demonstrating how AI can truly be implemented in real business scenarios.

## 🚀 进化路线图 (Roadmap)

- [x] **Phase 0**：环境配置 + GitHub Actions CI ✅
- [x] **Phase 1**：Spring Boot 骨架 + Docker + MySQL + Java 21 虚拟线程验证 ✅
- [x] **Phase 2**：Tool-Calling Agent —— LangChain4j + Ollama + 4个业务Tool + 结构化输出 + 多轮记忆 ✅
- [ ] **Phase 3（4.27-5.3）**：生产地基 —— DTO/VO分层 + 全局异常 + 参数校验 + 事务进阶 + Redis缓存 + 完整重构 🚧
- [ ] **Phase 4（5.4-6.10）**：Multi-Agent + RAG —— Supervisor Agent + Specialist Agent + 向量检索 + Redis持久化记忆 + JWT + CI/CD + 公网部署
- [ ] **Phase 5（6.11-8.31）**：核弹化 —— Prometheus + Grafana可观测性 + Resilience4j限流熔断 + 压测报告 + OpenTelemetry

## 🛠️ 技术栈 (Tech Stack)

- **后端 (Backend)**: Java 21 (虚拟线程), Spring Boot 4.0.5, JPA.
- **数据库 (Database)**: MySQL.
- **工具 (Tools)**: Docker, docker-compose, Maven, GitHub Actions.
- **AI框架 (AI)**: LangChain4j 1.13.0-beta23, Ollama (qwen3.5:4b)

## 🐳 快速启动 (Quick Start)

1. 复制配置文件: `cp docker-compose.example.yml docker-compose.yml`
2. 修改 `docker-compose.yml` 里的密码
3. 一键启动: `docker-compose up --build`

## 📡 API 接口 (Endpoints)

| Method | URL            | 说明                           |
| ------ | -------------- | ------------------------------ |
| GET    | /students      | 搜索所有学生                   |
| GET    | /students/{id} | 搜索单个学生                   |
| GET    | /students/slow | Virtual Threads 高并发演示接口 |
| POST   | /students      | 添加学生                       |
| PUT    | /students/{id} | 修改学生信息                   |
| DELETE | /students/{id} | 删除学生                       |
| POST   | /ai/ask        | 向 Agent 发送自然语言指令      |

## 🏗️ 系统架构 (Architecture)

```
          HTTP Request
                ↓
          [Controller]
           ↙         ↘
    [AI Agent]        直接调用
    (LangChain4j)         ↘
         ↓                 ↘
      [Tool层]  ──────→ [Service层]
                               ↓
                        [Repository层]
                               ↓
                            [MySQL]
```

- **普通接口**：Controller → Service → Repository → MySQL
- **AI接口**：Controller → AI Agent（理解意图 + 自主规划）→ Tool → Service → Repository → MySQL

## ⚡ Virtual Threads

- 技术：Java 21 Virtual Threads
- 问题：传统平台线程在高并发 I/O 阻塞时，什么都干不了，大量并发就需要大量线程，浪费资源
- 解决：阻塞时自动卸载，平台线程去干别的，资源利用率大幅提升
- 验证：通过 VisualVM 对比线程视图，确认虚拟线程与平台线程的差异