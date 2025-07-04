# Online Executor

Run Java code (and other languages in the future) straight from your browser.

This monorepo contains:

| Layer      | Path                     | Tech                            |
|------------|--------------------------|---------------------------------|
| Back-end   | `/src/main/java/**`      | Spring Boot 2, JDK 17           |
| Front-end  | `/frontend`              | Vite + React 18 (TypeScript)    |
| Docs       | `/doc`                   | Architecture diagrams & notes   |

---

## 1 · Getting Started (Dev)

### Windows
```bash
# from repo root
./dev-start.bat   # rebuilds + starts backend (8082) & Vite (5173)
```
The script will open <http://localhost:5173/> automatically.

### macOS / Linux
```bash
# back-end
./mvnw clean package -DskipTests && \
  java -jar target/OnlineExecutor-0.0.1-SNAPSHOT.jar --server.port=8082

# front-end
cd frontend
npm install
npm run dev   # http://localhost:5173/
```

---

## 2 · Production Build

1. **Back-end**
   ```bash
   ./mvnw clean package -DskipTests
   java -jar target/OnlineExecutor-0.0.1-SNAPSHOT.jar --server.port=8082
   ```
2. **Front-end** (static files served via Nginx)
   ```bash
   cd frontend
   docker build -t online-executor-fe .
   docker run -d --name oe-fe -p 8080:80 online-executor-fe
   # Front-end on http://localhost:8080/
   ```
   Adjust Nginx / reverse-proxy so `/api/**` is forwarded to the back-end on 8082.

---

## 3 · API

```
POST /api/run
{
  "code": "public class Main { … }",
  "stdin": "optional\ninput"
}
```
Returns JSON with `stdout`, `stderr`, `status`, `execTimeMs`.

---

## 4 · Front-end Shortcuts

* **Run** – click the blue **Run** button or press **Ctrl + Enter**
* **Clear** – trash-can icon in Terminal header
* Code is auto-saved in `localStorage` between sessions.

---

## 5 · Cleaning Up

Obsolete utilities have been removed; only the following helper scripts remain:

* `dev-start.bat` – full stack dev launcher (Windows)

---

## License

MIT


