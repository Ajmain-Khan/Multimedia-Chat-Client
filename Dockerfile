FROM python:3.9.4-slim-buster
ENTRYPOINT python3.9 backend/server
WORKDIR /root
COPY ./ /root/backend
RUN pip install click
