## Propuesta Endpoints: Gráficas Balance Diario(Entregable3) y datos de BankAccout y CreditCard (entregable4)

### Documentación
[Documentación Postman] (https://documenter.getpostman.com/view/13054206/TzXuoLPT)
[Documentación Swagger] (https://bancaproyect.herokuapp.com/swagger-ui/)

### Grafica1:
```Balance diario: Saldo actual por día entre dos fechas y el mismo balance con el mismo periodo del año anterior.```
### Grafica2:
```Total transacciones: número total de transacciones por día entre dos fechas.```
### Grafica3:
```
Suma total del importe de las transacciones totales por día entre dos fechas.
Suma total del importe de las transacciones positivas por día entre dos fechas.
Suma total del importe de las transacciones negativas por día entre dos fechas.
```

## Endpoints necesarios:
#### Entregable3:
### Cuenta:
```
• GetDailyBalanceByDateRangeByNumAccount: devuelve el saldo actual por día entre dos fechas de una cuenta bancaria. Además, se le puede añadir otro año para devolver el mismo balance del mismo rango de fechas, pero del año anterior.
 y el número total de transacciones por día entre dos fechas de una cuenta bancaria.
```
### Tarjeta:
```
• GetNumTotalTransactionsByDateRangeByCreditCard: número total de transacciones por día entre dos fechas de una tarjeta de crédito.
```
### Global:
```
• GetDailyBalanceByDateRangeByUser: devuelve el saldo actual por día entre dos fechas de todas las cuentas bancarias de un usuario. Además, devuelve el mismo balance del mismo rango de fechas, pero del año anterior.
• GetNumTotalTransactionsByDateRangeByUser: número total de transacciones por día entre dos fechas de todas las cuentas de un usuario.
```

#### Entregable4:
### Datos agrupados por categorias
```
• getAllOperationsByCategoryBankAccount: devuelve todas las operaciones realizadas en una fecha de una cuenta de banco
• getAllOperationsByCategoryCreditCard: devuelve todas las operaciones realizadas en una fecha de una tarjeta de credito

```
