# zio-simple-api

hello worldを返す単純なAPIです。


## ECRにイメージをプッシュする手順

### 1. ECRにログインする

```sh
aws ecr get-login-password | docker login --username AWS --password-stdin <aws account_id>.dkr.ecr.<region name>.amazonaws.com/<repository name>
```

### 2. ローカルでイメージをビルドする

```sh
sbt docker:publishLocal
```

### 3. イメージをECRにプッシュする

```sh
docker push <aws account_id>.dkr.ecr.<region name>.amazonaws.com/<repository name>:<tag>
```

## CloudFormationの実行方法について

以下のREADMEを参考にしてください。

[./cloudformations/README.md](https://github.com/ryskit/zio-simple-api/blob/main/cloudformations/README.md)
