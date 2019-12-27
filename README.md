# liferay_base

Liferay Base Repository for generate a complete development environment

## Summary

This setup lets us launch a complete environment based on Liferay. This environment has a Liferay DXP Container, a MySQL Container, an ElasticSearch Container and a FakeSMTP Container.

## Usage

As another Docker-compose environment, you will only need to run "docker-compose up" command. But, before you run "docker-compose up" command you will need to create the deploy folder on the liferay folder. You could run "startup.sh" script which will create the deploy folder and run "docker-compose up" command for you.