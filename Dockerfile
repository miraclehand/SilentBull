FROM openjdk:17-slim

# 필수 패키지 설치 및 Gradle 다운로드/설치
RUN apt-get update && apt-get install -y wget unzip && \
    wget https://services.gradle.org/distributions/gradle-8.6-bin.zip -O gradle.zip && \
    unzip gradle.zip -d /opt && \
    ln -s /opt/gradle-8.6 /opt/gradle && \
    rm gradle.zip

# 환경변수 설정
ENV PATH="/opt/gradle/bin:${PATH}"

# 작업 디렉토리 지정 (여기에 소스코드 마운트 할 예정)
WORKDIR /project

