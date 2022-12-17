## 実行順序

ファイル名の先頭2桁の数字の昇順で実行してください。

## ECRの作成

```sh
$ aws cloudformation create-stack \
--stack-name zio-simple-api-ecr \
--template-body file://01_ecr.yml \
--parameters ParameterKey=EnvType,ParameterValue=dev
```

## IAMの作成

```sh
$ aws cloudformation create-stack \
--stack-name zio-simple-api-iam \
--template-body file://02_iam.yml \
--parameters ParameterKey=EnvType,ParameterValue=dev \
--capabilities CAPABILITY_NAMED_IAM
```

## App Runnerの作成

```sh
$ aws cloudformation create-stack \
--stack-name zio-simple-api-apprunner \
--template-body file://03_apprunner.yml \
--parameters ParameterKey=EnvType,ParameterValue=dev
```

## AppRunnerの削除

```sh
aws cloudformation delete-stack --stack-name zio-simple-api-apprunner
```

## IAMの作成の削除

```sh
aws cloudformation delete-stack --stack-name zio-simple-api-iam
```

## ECRの削除

ECRは削除する前に登録されているイメージを削除してください。

```sh
aws cloudformation delete-stack --stack-name zio-simple-api-ecr
```
