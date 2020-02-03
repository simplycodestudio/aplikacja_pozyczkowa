## Aplikacja mikropożyczkowa - Spring Boot

Przy jej pomocy możemy:

+ Składać wniosek o pożyczkę (amount:int, repayment date format: YYYY-MM-DD)
```javascript
POST
http://localhost:8080/loans;
body
    {
        "amount":1500,
        "repaymentDate": "2020-12-02"
    }
```
+ Listować treści pożyczki i wniosków (zarówno pojedynczo jak)
```javascript
GET
http://localhost:8080/loans;
http://localhost:8080/loans/{id}
http://localhost:8080/props;
```


+ Jednorazowo przedłużać okres spłaty na dodatkowe 14 dni
(bez podawania dodatkowych informacji w body):
```javascript
PUT
http://localhost:8080/loans/{id}
```

**Logika bizesowa**

Zasady:
+ Kredytobiorca może złożyć wniosek o przyznanie pożyczki na określoną kwotę oraz preferowany czas spłaty. 
+ Kredytobiorca może przedłużyć czas spłaty pożyczki tylko raz.

Przeciwwskazania:
+ Kredytobiorcy nie udziela się pożyczki na maksymalną kwotę, jeśli składa on wniosek w godzinach 00:00 - 06:00;
+ Kredytobiorca nie może złożyć więcej jak 3 wnioski z jednego adresu IP

**variables.properties**

Zawiera stałe dane podane w treści wytycznych:
+ amount.max - maksymalna kwota pożyczki;
+ is.night - flaga wprowadzająca w tryb nocny (00:00 - 6:00);
+ repayment.prolongation - stały czas jednorazowej prolongaty;
+ proposals.from.same.ip - ilość wniosków, które można złożyć z tego samego adresu.

**Uruchomienie** 

Aplikacja uruchamia się w IDE lub przy pomocy komendy:
```javascript
 mvn spring-boot:run
``` 
Dodatkowo w celu uzyskania IPv4 można dodać flagę:     
```javascript                                    
-Djava.net.preferIPv4Stack=true
``` 
