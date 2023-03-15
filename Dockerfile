FROM httpd:2.4.56-alpine
LABEL maintainer="Zaitis"
ADD . /usr/local/apache2/htdocs
EXPOSE 80
