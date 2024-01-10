# MQTT Data APIs

## 1. Tüm MQTT Verilerini Al

- **Endpoint:** `GET http://localhost:8080/api/mqtt-data/all`
- **Açıklama:** Bu endpoint ile tüm MQTT verileri alınabilir.
- **Yanıt Dosyası:** `all-mqtt-data-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": 2,
    "topic": "test",
    "message": "333",
    "hostName": "192.168.1.24",
    "timestamp": "2023-11-12T03:04:52.56145",
    "connection": {
      "id": 1,
      "host": "192.168.1.24",
      "port": 1883,
      "username": "bllraslen",
      "password": "bll.raslen",
      "clientId": "my_client_id",
      "connectionTimeout": 60,
      "keepAliveInterval": 120
    }
  },
  {
    "id": 5,
    "topic": "test/host",
    "message": "L",
    "hostName": "192.168.1.24",
    "timestamp": "2023-11-12T03:29:14.903471",
    "connection": {
      "id": 1,
      "host": "192.168.1.24",
      "port": 1883,
      "username": "bllraslen",
      "password": "bll.raslen",
      "clientId": "my_client_id",
      "connectionTimeout": 60,
      "keepAliveInterval": 120
    }
  }
]
```

## 2. Tüm Birleştirilmiş MQTT Verilerini Al

- **Endpoint:** `GET http://localhost:8080/api/mqtt-data/hepsi`
- **Açıklama:** Bu endpoint ile tüm birleştirilmiş MQTT verileri alınabilir.
- **Yanıt Dosyası:** `all-joined-mqtt-data-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  ["test/host", "2023-11-12T00:11:28.370+00:00", "333", "192.168.1.24", "bllraslen", "bll.raslen", 1883, 120, 60],
  ["test", "2023-11-12T00:04:52.561+00:00", "333", "192.168.1.24", "bllraslen", "bll.raslen", 1883, 120, 60],
  ["test/host", "2023-11-12T00:29:14.903+00:00", "L", "192.168.1.24", "bllraslen", "bll.raslen", 1883, 120, 60]
]
```

## 3. Bağlantı ID'sine Göre MQTT Verilerini Al

- **Endpoint:** `GET http://localhost:8080/api/mqtt-data/by-connection?connectionId=1`
- **Açıklama:** Bu endpoint ile belirli bir bağlantı ID'sine sahip MQTT verileri alınabilir.
- **Yanıt Dosyası:** `mqtt-data-by-connection-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": 1,
    "topic": "test/host",
    "message": "39",
    "hostName": "192.168.1.24",
    "timestamp": "2023-11-12T03:04:51.547339",
    "connection": {
      "id": 1,
      "host": "192.168.1.24",
      "port": 1883,
      "username": "bllraslen",
      "password": "bll.raslen",
      "clientId": "my_client_id",
      "connectionTimeout": 60,
      "keepAliveInterval": 120
    }
  },
  // ... (diğer MQTT verileri)
]
```

## 4. Farklı Konuları Al

- **Endpoint:** `GET http://localhost:8080/api/mqtt-data/distinct-topics`
- **Açıklama:** Bu endpoint ile farklı konular alınabilir.
- **Yanıt Dosyası:** `distinct-topics-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  "test",
  "test/host"
]
```

## 5. Bir Konuya Ait Tüm Mesajları Al

- **Endpoint:** `GET http://localhost:8080/api/mqtt-data/all-messages/test`
- **Açıklama:** Bu endpoint ile belirli bir konuya ait tüm mesajlar alınabilir.
- **Yanıt Dosyası:** `all-messages-by-topic-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": 2,
    "topic": "test",
    "message": "333",
    "hostName": "192.168.1.24",
    "timestamp": "2023-11-12T03:04:52.56145",
    "connection": {
      "id": 1,
      "host": "192.168.1.24",
      "port": 1883,
      "username": "bllraslen",
      "password": "bll.raslen",
      "clientId": "my_client_id",
      "connectionTimeout": 60,
      "keepAliveInterval": 120
    }
  }
]
```

## 6. Tüm Bağlantı Detaylarını Al

- **Endpoint:** `GET http://localhost:8080/api/connection-details/all`
- **Açıklama:** Bu endpoint ile tüm bağlantı detayları alınabilir.
- **Yanıt Dosyası:** `all-connection-details-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  {
    "id": 1,
    "host": "192.168.1.24",
    "port": 1883,
    "username": "bllraslen",
    "password": "bll.raslen",
    "clientId": "my_client_id",
    "connectionTimeout": 60,
    "keepAliveInterval": 120
  }
]
```

## 7. Farklı Bağlantı Noktalarını Al

- **Endpoint:** `GET http://localhost:8080/api/connection-details/distinct-ports`
- **Açıklama:** Bu endpoint ile farklı bağlantı noktaları alınabilir.
- **Yanıt Dosyası:** `distinct-

ports-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  8081,
  8082
]
```

## 8. Özel Alanları Al

- **Endpoint:** `GET http://localhost:8080/api/connection-details/custom-fields`
- **Açıklama:** Bu endpoint ile özel alanlar alınabilir.
- **Yanıt Dosyası:** `custom-fields-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  [1, "192.168.1.24", 1883, "bllraslen"],
  [3, "localhost", 1883, "bllraslen"]
]
```

## 9. Farklı Ana Bilgisayar Adlarını Al

- **Endpoint:** `GET http://localhost:8080/api/connection-details/distinct-host-names`
- **Açıklama:** Bu endpoint ile farklı ana bilgisayar adları alınabilir.
- **Yanıt Dosyası:** `distinct-host-names-response.json`

```json
HTTP/1.1 200 OK
Content-Type: application/json

[
  "192.168.1.24",
  "localhost"
]
```
