# Slovenska Trta
Avtorja: Luka Grahor in Jernej Petrovčič
## Delovanje
Slovenska Trta je spletna in mobilna aplikacija za vnos in prikaz podatkov o vinogradih v Sloveniji.
Podatki so vzeti iz spletne strani Odprti Podatki Slovenije: https://podatki.gov.si/dataset/surs1502406s.

Podatkovna baza in spletna aplikacija se nahajata na strežniku Azure, tako da je aplikacija dostopna preko spleta.
povezava do spletne aplikacije: https://slovenskatrta-is.azurewebsites.net/swagger
### Spletna aplikacija
![image](https://user-images.githubusercontent.com/41000453/210832883-c4a640ed-9bc9-453f-b0c7-9c02d45a9e66.png)
Preko gornjega menija lahko prijavljeni uporabniki dostopajo do baz podatkov o vinogradih v Sloveniji.
Za prijavo je na voljo v gornjem desnem kotu gumb za prijavo, če pa uporabnik nima še računa si ga lahko ustvari z gumbom registracija.

![image](https://user-images.githubusercontent.com/41000453/210833933-118543d2-20b4-4037-8862-aecf76e0516c.png)
Posamezna tabela ponuja možnosti ogleda, urejanja, ogleda podrobnosti in brisanja iz nje. Navaden uporabnik ima dostop
le do ogleda in ogleda podrobnosti, ostale operacije so pa dovoljene administratorjem.
Poleg tega lahko iščemo po poljubnih atributih iz tabele, urejamo po posameznem atributu.

### Mobilna aplikacija
Ponuja branje in vpisovanje podatkov preko mobilne aplikacije.

![aplikacija1](https://user-images.githubusercontent.com/41000453/210870995-26638ac9-9df4-45f7-9dc8-856642cf5a30.PNG)

Deluje preko Swagger API-ja

![aplikacija2](https://user-images.githubusercontent.com/41000453/210871475-74fe6d84-1db1-4742-bf86-d8cc661a609a.PNG)

## Izdelava
### Luka Grahor:
- Dodal avtorizacijo in avtentikacijo
- Dodal iskanje po podatkih, urejanje po atributu in prikazovanje podatkov po straneh
- Postavil podatkovno bazo in spletno aplikacijo na strežnik Azure
- vizualno uredil spletno aplikacijo
- Dodal API
- Ustvaril mobilno aplikacijo

### Jernej Petrovčič:
- Poiskal temo in podatke za izdelek
- Ustvaril model za našo končno podatkovno bazo
- Ustvaril in napolnil podatkovno bazo
- Postavil temelje za spletno aplikacijo
  - Generiral in uredil kontrolerje

## Slika podatkovnega modela
![SLovenskaTrta](https://user-images.githubusercontent.com/41000453/210872397-3875f019-f453-4af4-bdeb-85a0f114b3eb.PNG)
