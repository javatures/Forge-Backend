FROM gradle:jdk8-hotspot

RUN apt update && apt install -y lsof

WORKDIR /Forge-Backend

COPY . /Forge-Backend

CMD ["gradle", "clean", "bootRun"]
