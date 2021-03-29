Работа выполнялась с помощью helm 3.5.1  
Был использован Kubernetes v1.19.7, встроенный в Docker Desktop Windows 10

В тестовом приложении нет авторизации, валидации входных данных и.т.п. вещей.
Настройки кафки урезаны, чтобы не тормозил ноутбук (например нет репликации итп).
Надеюсь для промежуточного учебного домашнего задания можно оставить так, но если будут замечания, то переделаю. 

#Инструкция

Ставим ингрес

```
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/controller-v0.44.0/deploy/static/provider/cloud/deploy.yaml
```

Ставим кафку
```
helm repo add confluentinc https://confluentinc.github.io/cp-helm-charts/
helm repo update
helm install cp confluentinc/cp-helm-charts -f cp_values.yaml
```

Ставим клиент кафки
```
kubectl apply -f kafka-client.yaml
```

Создаем топики

```
kubectl exec kafka-client -- kafka-topics --zookeeper cp-cp-zookeeper:2181 --topic DEV_notification_1_0_pu --create --partitions 1 --replication-factor 1
kubectl exec kafka-client -- kafka-topics --zookeeper cp-cp-zookeeper:2181 --topic DEV_order_1_0_pu --create --partitions 1 --replication-factor 1
kubectl exec kafka-client -- kafka-topics --zookeeper cp-cp-zookeeper:2181 --topic DEV_billing_1_0_pu --create --partitions 1 --replication-factor 1
```

Выполнить  

```
helm install api-gateway ./api-gateway  
helm dependency update notification  
helm install notification ./notification  
helm dependency update billing  
helm install billing ./billing  
helm dependency update order  
helm install order ./order  
```

Postman коллекция лежит в той же папке, что и readme.md