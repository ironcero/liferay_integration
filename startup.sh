#!/bin/sh
mkdir liferay/deploy
mkdir -p elastic/data
mkdir -p jenkins/home
mkdir fakemail
chown 1000:1000 liferay/deploy
chown -R 1000:1000 elastic
chown -R 1000:1000 jenkins
chown -R 1000:1000 fakemail
docker-compose up