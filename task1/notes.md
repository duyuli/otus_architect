Домашняя работа выполнялась на Docker Desktop для Windows с включенным в нем Kubernetes

Возникли вопросы по поводу Ingress

Ingress контроллеры пришлось добавлять при помощи следующей команды  
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.43.0/deploy/static/provider/cloud/deploy.yaml
Без нее вообще ничего не работает.

После применения этой команды в сервис можно постучаться через URL  
curl  http://localhost/otusapp/dubinina/health

При этом так же сервис доступен по  
curl http://localhost:31092/health
(порт тут меняется, смотрела порт через kubectl get services)

Результат отличается от того, что делали на лекции. Вопроса два  
1. На лекции команда curl не работала без хоста (например -H 'Host: arch.homework'). Здесь оно все без этого работает. Почему?
2. Нормально ли это, что сервис можно дергать напрямую по порту (например 31092), т.е. получается в обход Ingress?