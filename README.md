
# Poc Pismo Account Manager

Project carried out as proof of concept for Pismo
using SpringBoot 3


## API documentation

####  Return Account by Id

```http
  GET /accounts/${id}
```

| Parameter   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Mandatory**. Account ID |


#### Create Account

```http
  POST /accounts
```

| Parameter   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `documentNumber` | `string` | **Mandatory**. DocumentNumber |


####  Return Transaction by Id

```http
  GET /transactions/${id}
```

| Parameter   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Mandatory**. Transaction ID |


#### Create Transaction

```http
  POST /transactions
```

| Parameter   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `operationTypeId` | `Long` | **Mandatory**. OperationTypeId |
| `accountId` | `Long` | **Mandatory**. AccountId |
| `amount` | `BigDecimal` | **Mandatory**. Amount |


## Author

- [@sergiogoncalves](https://www.github.com/sergiogoncalves)


## Run

`Use Java Version 17`

To run this project execute command

```bash
  java -jar /account-manager.jar
```

