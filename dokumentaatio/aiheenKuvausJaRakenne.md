# Aiheen kuvaus ja rakenne

## Aihe
*Prosessimuistinlukija ja -kirjoittaja*

Toteutetaan kirjasto, jolla voidaan kirjoittaa ja lukea toisen, samalla 
tietokoneella py�riv�n prosessin muistia. Kirjaston rinnalle toteutetaan
graafinen k�ytt�liittym�. 

Kirjaston tulee tukea Windows 10 ja Ubuntu 16.** -k�ytt�j�rjestelmi�.
Ohjelman toimintaa ei m��ritell� muilla k�ytt�j�rjestelmill�, eik�
alustoilla, jotka eiv�t k�yt� little-endian -tavuj�rjestyst� ja kahden
komplementtij�rjestelm��.

Kirjastolla tulee pysty� selaamaan tietokoneen prosessilistausta, sek�
manipuloimaan tietyn k�ytt�j�tilassa py�riv�n prosessin muistia. Kirjastolla
tulee pysty� kirjoittamaan ja lukemaan yleisimpien tietotyyppien lis�ksi
mielivaltaisen pituisia tavum��ri� prosessin muistin rajoissa.

Yleiset tietotyypit m��ritell��n seuraavanlaisesti:

Nimi | Lukukoko biteiss� | Kirjaston tyyppi
--------------------
bool | 8 | boolean
char | 8 | char
byte | 8 | byte
wchar | 16 | char
short | 16 | short
int | 32 | int
float | 32 | float
long | 64 | long
double | 64 | double

K�ytt�liittym�n tulee tarjota k�ytt�j�lle tapa lukea ja tallentaa omia
"kirjanmerkki"-muistiosotteita, esimerkiksi XML- tai JSON-tiedostoon. Muistiosoitteita
ei k�sitell� pysyvyystarkoituksissa absoluuttisina, vaan suhteessa
prosessin kuvan pohjaan (esim. kun kuvan pohjaosoite on 0x400000 ja k�ytt�j�n
kirjanmerkki on 0x49FDAA, tallennetaan osoite muodossa 0x9FDAA, eik� 0x49FDAA.

## K�ytt�j�t
K�ytt�j�t

## Toiminnot

