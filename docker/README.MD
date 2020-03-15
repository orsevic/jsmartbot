docker-compose up - запуск докера

docker-compose stop - остановка

docker-compose down - остановка и удаление контейнеров

docker-compose down -v - остановка и удаление контейнеров и волумов

docker ps - посмотреть какие контейнеры запущены

docker images - посомтреть какие есть "имиджи"

docker system prune --volumes - очищение всего 

У кого Windows home ip:192.168.99.100 

kill $(ps aux | grep "java" | grep -v 'grep' | awk '{print $2}') - остановка сервисов java
