@echo off
:: ------------------------------------------------------
:: Dev launcher – starts Spring-Boot backend + Vite frontend
:: Requires: JDK 17+, Maven or mvnw, Node.js & npm
:: ------------------------------------------------------

SETLOCAL ENABLEDELAYEDEXPANSION

REM ---------- Always rebuild backend ----------
if exist mvnw.cmd (
  call mvnw.cmd -q clean package -DskipTests
) else (
  mvn -q clean package -DskipTests
)
if %ERRORLEVEL% NEQ 0 (
  echo Build failed.
  goto :eof
)

REM ---------- Locate jar ----------
set "JAR_PATH="
for %%f in (target\*-exec.jar) do set "JAR_PATH=%%f"
if "%JAR_PATH%"=="" (
  for %%f in (target\OnlineExecutor-*.jar) do set "JAR_PATH=%%f"
)
if "%JAR_PATH%"=="" (
  echo Could not find built jar.
  goto :eof
)

REM ---------- Start backend ----------
start "Backend" cmd /k "java -jar %JAR_PATH% --server.port=8082"

REM ---------- Start frontend ----------
pushd frontend
if not exist node_modules (
  echo Installing frontend dependencies...
  npm install --loglevel=error
)

REM Vite dev server (will proxy /run to 8082)
start "Frontend" cmd /k "npm run dev"
popd

REM ---------- Open browser ----------
REM Wait a few seconds to allow servers to boot
ping 127.0.0.1 -n 4 > nul
start http://localhost:5173/

ENDLOCAL 