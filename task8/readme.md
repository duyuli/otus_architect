Работа выполнялась с помощью helm 3.5.1  
Был использован Kubernetes v1.19.7, встроенный в Docker Desktop Windows 10

В прошлом домашнем задании приложение уже было сделано с учетом проблем идемпотентности
и коммутативности. Проблема была решена за счет использования естественных ключей
идемпотентности (таких как userId и orderId), которые задавались на стороне клиента как
GUID. А так же за счет использования kafka, которая гарантирует порядок сообщений. 
Ключом к партициям служат userId и orderId.

В данной домашней работе была добавлена валидация на запросы дубликаты, плюс какие-то мелкие
улучшения. В остальном от прошлого задания приложение не сильно отличается 

#Инструкция

Точно такая же как в прошлом задании. Скопировано ниже.

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

Картинка с архитектурным решением лежит в этой же папке