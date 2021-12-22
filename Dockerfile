FROM node:17-slim AS frontend
RUN mkdir /build
COPY ./src/main/resources/client /build
WORKDIR /build
RUN npm install
RUN NODE_OPTIONS=--openssl-legacy-provider npm run build

FROM gradle:alpine AS backend
RUN mkdir /build
COPY . /build
COPY --from=frontend /build/dist /build/main/resources/static
WORKDIR /build
RUN gradle bootJar