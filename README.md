# Domenski jezik za opis poljoprivrednih biljaka i klijentska veb aplikacija
Ovaj repozitorijum sadrzi implementaciju domenskog jezika i klijentske veb aplikacije za diplomski rad sa temom Domenski jezik za opis poljoprivrednih biljaka.

## Neophodan softver
* Maven - https://maven.apache.org/download.cgi
* MongoDB Community Server - https://www.mongodb.com/download-center/community

## Uputstvo za pokretanje veb aplikacije
Nakon instalacije neophodnog softvera, potrebno je klonirati repozitorijum:
```
git clone https://github.com/vukovic-marko/diplomski-rad
```
Zatim je potrebno pozicionirati se u preuzeti folder diplomski-rad:
```
cd diplomski-rad
```
i izvršiti mavan goal compile, kako bi bile generisane klase skenera (Yylex.java) i parsera (parser.java, sym.java) na osnovu specifikacija. Ovaj korak je već izvršen pre dodavanja koda na repozitorijum i potrebno ga je izvršiti samo u slučaju menjanja specifikacija skenera i parsera ili u slučaju brisanja generisanih klasa.
```
mvn compile
```
Pokretanje aplikacije vrši se izvršavanjem sledećeg maven goal-a:
```
mvn spring-boot:run
```
