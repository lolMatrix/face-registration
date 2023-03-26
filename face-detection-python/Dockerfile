FROM ubuntu:18.04 as base
MAINTAINER Alexey MatriX

WORKDIR /app

RUN  apt-get update -y && apt-get install -y && apt-get upgrade -y

RUN apt-get install -y python python-pip
RUN apt install -y python3-opencv

COPY requirements.txt requirements.txt
RUN pip3 install -r requirements.txt

COPY . .

CMD ["/app/main.py"]
ENTRYPOINT ["python"]