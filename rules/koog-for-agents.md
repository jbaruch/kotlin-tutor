---
alwaysApply: true
---

# Koog for AI Agents on the JVM

## Koog Is the Kotlin-Native Agent Framework

- For building AI agents, agent-driven pipelines, sub-agent decomposition, and multi-agent orchestration on JVM, use **[Koog](https://github.com/JetBrains/koog)** (`ai.koog:koog-agents`, current **1.0.0-preview3** — release candidate ahead of the upcoming 1.0 GA; **0.8.0** is the latest stable if you can't take a pre-release)
- Koog is JetBrains' Kotlin-native AI agent framework — type-safe DSL, MCP integration, multiplatform targets, multi-LLM providers (Anthropic, OpenAI, Google, Ollama)
- Don't reach for Python-only frameworks (Claude Agent SDK, LangChain, AutoGen, CrewAI) if your application is Kotlin/JVM. The Python orchestrator + Kotlin sub-agents split is a smell.

## Versions

- **Koog 1.0.0-preview3** runs on **Kotlin 2.3.x** and requires **JDK 17 minimum** (JDK 21 still recommended). The 1.0 preview line splits modules into stable (`1.0.0-preview*`) and beta (`1.0.0-beta-preview*`) streams so production code can pin to APIs that won't break without a deprecation cycle
- Pin via: `implementation("ai.koog:koog-agents:1.0.0-preview3")`
- If you're migrating from 0.7.x / 0.8.0: HTTP transport is now decoupled from Ktor (LLM clients take a `KoogHttpClient.Factory`), the `AgentMemory` feature is removed in favor of `LongTermMemory`, Java blocking methods are renamed to the `*Blocking` suffix, and planners moved to a separate `agents:agents-planners` module. The 1.0 preview line's full breaking-change list lives in the initial [`1.0.0-preview` release notes](https://github.com/JetBrains/koog/releases/tag/1.0.0-preview) — `preview2` / `preview3` add only follow-up bug fixes on top.

## Idiomatic Use

- An `AIAgent` is a value with a `promptExecutor`, `llmModel`, optional `systemPrompt`, and optional `toolRegistry`
- `agent.run(input)` is `suspend` — call from `runBlocking { }` at the top level
- Sub-agent pattern: wrap an agent as a tool via `AIAgentService.fromAgent(...).createAgentTool(...)`, then register that tool in the parent's `ToolRegistry`
- Each sub-agent invocation gets a **fresh context** — pass anything the sub-agent needs explicitly via its system prompt (this is the "context engineering for the orchestrator" pattern; see `jbaruch/sub-agent-delegation`)

## LLM Provider

- For Anthropic: `simpleAnthropicExecutor(System.getenv("ANTHROPIC_API_KEY"))` + `AnthropicModels.Sonnet_4` (or `Opus_4_*`)
- For OpenAI: `simpleOpenAIExecutor(...)` + `OpenAIModels.Chat.GPT4o`
- For Google: `simpleGoogleExecutor(...)` + the relevant Gemini model
- For local: configure via Ollama integration

## Structured Output

- When sub-agents need to return decisions for downstream code, prompt them to emit JSON with a schema, then parse with `kotlinx-serialization-json`. This is more robust than free-form natural language responses.

## Anti-patterns

- ❌ Spawning a Python subprocess (`anthropic-sdk-python`, `claude-agent-sdk`) from a Kotlin app to orchestrate sub-agents
- ❌ Hand-rolling agent loops on top of raw `HttpClient` calls to Anthropic — you'll reinvent retries, conversation history, tool calling, and break before you ship
- ❌ Using `GlobalScope.launch { agent.run(...) }` — unowned scope, leaks; use structured concurrency
- ❌ Sharing a `Predictor` or LLM client across non-coroutine threads
