# How to setup dev DB

```bash
docker run --detach --name some-mariadb --env MARIADB_DATABASE=hoteldb --env MARIADB_ROOT_PASSWORD=root  mariadb:latest
```