FROM clojure:openjdk-14-boot-2.8.3

COPY . /work

WORKDIR /work

RUN boot deps