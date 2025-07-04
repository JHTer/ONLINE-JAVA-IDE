# OnlineExecutor Frontend

## Development
...

## Production Build

```
cd frontend
npm run build
```
Generates static files in `frontend/dist`. Deploy with Nginx:

```
# frontend/Dockerfile builds and serves via nginx
```

### New features
- Square play button (top right)
- Resizable split panel
- Copy-able terminal output
- **Clear** button in Terminal header
- Ctrl+Enter shortcut to run
- Code persists in browser 

## Docker Production Image

Build and run:
```bash
# from repo root
cd frontend
docker build -t online-executor-fe .
docker run -p 8080:80 online-executor-fe
```
Frontend then served at http://localhost:8080/ and expects the Spring-Boot
backend reachable at http://backend-host:8082 (configure via reverse proxy or
CORS for production). 