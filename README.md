# Student Management System | 学生管理系统
![Java CI](https://github.com/liver-m/StudentManagementSystem/actions/workflows/maven.yml/badge.svg)

## 📖 项目简介 (Introduction)
- **中文**：一个从传统的 JDBC/Servlet 架构起步，最终将进化为支持高并发、集成 AI 助手的生产级 Java 项目。
- **English**: A Java project evolving from legacy JDBC/Servlet to a high-concurrency, AI-integrated production platform.

## 🚀 进化路线图 (Roadmap)
- [x] **阶段 0 (Phase 0)**: 环境配置与自动化构建 (CI/CD)。
- [ ] **阶段 1 (Phase 1)**: 重构为 Spring Boot 4 + Java 21 虚拟线程 (解决高并发)。
- [ ] **阶段 2 (Phase 2)**: 云原生化 (Docker & Kubernetes)。
- [ ] **阶段 3 (Phase 3)**: AI 注入 (接入 LangChain4j 与 RAG 架构)。

## 🛠️ 技术栈 (Tech Stack)
- **后端 (Backend)**: Java 21 (虚拟线程), JDBC, Servlet.
- **数据库 (Database)**: MySQL.
- **工具 (Tools)**: Docker, Maven, GitHub Actions.

## 💻 如何运行 (How to Run)
1. 克隆项目: `git clone https://github.com/liver-m/StudentManagementSystem.git`
2. 确保安装了 JDK 21 和 Maven。
3. 修改数据库配置，然后运行 `mvn spring-boot:run`。