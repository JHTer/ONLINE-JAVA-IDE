# ---------------------------
# Stage 1 : Build frontend
# ---------------------------
FROM node:lts-alpine AS build
WORKDIR /app
COPY package*.json tsconfig.json vite.config.ts tailwind.config.js postcss.config.js index.html ./
COPY src ./src
RUN npm install --silent && npm run build

# ---------------------------
# Stage 2 : Serve with nginx
# ---------------------------
FROM nginx:alpine
COPY --from=build /app/dist /usr/share/nginx/html
COPY nginx.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"] 