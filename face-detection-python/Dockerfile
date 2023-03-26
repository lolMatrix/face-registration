FROM ubuntu:18.04 as base
MAINTAINER Alexey MatriX
ENV TZ=Asia/Dubai

WORKDIR /app

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN  apt-get update -y && apt-get install -y && apt-get upgrade -y

RUN apt-get install -y python3 python3-pip
RUN apt install -y python3-opencv

COPY requirements.txt requirements.txt
RUN pip3 install --upgrade pip
RUN python3 -m pip install -r requirements.txt

COPY . .

CMD ["/app/main.py"]
ENTRYPOINT ["python3"]