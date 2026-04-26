# Festival Management System

Aplicatie Java pentru managementul unui festival de muzica electronica.

---

## 1.1 Actiuni posibile in sistem

1. Inregistreaza un artist in line-up
2. Elimina un artist din line-up dupa nume
3. Vinde un bilet unui participant
4. Afiseaza line-up-ul sortat alfabetic
5. Calculeaza venitul total din bilete vandute
6. Cauta artisti dupa gen muzical
7. Actualizeaza locatia festivalului
8. Listeaza participantii cu bilet VIP
9. Verifica capacitatea disponibila a locatiei
10. Cauta un participant dupa adresa de email
11. Sterge un participant din sistem
12. Afiseaza biletele vandute sortate dupa pret

---

## 1.2 Tipuri de obiecte din domeniu

- `Festival` - evenimentul principal, contine toate colectiile
- `Locatie` - locul de desfasurare (nume, oras, capacitate)
- `Persoana` - clasa abstracta de baza cu metoda abstracta `getRol()`
- `Angajat` - extinde Persoana (nivel 2 in ierarhia de mostenire)
- `Staff` - extinde Angajat, are un rol specific (nivel 3)
- `Artist` - extinde Persoana, are gen muzical si tarif
- `Participant` - extinde Persoana, detine un bilet
- `BiletId` - clasa imutabila, identificator unic al unui bilet
- `Bilet` - tichet de intrare standard
- `BiletVIP` - extinde Bilet, adauga facilitati suplimentare
- `Scena` - scena de concert a festivalului

---

## Structura proiectului

```
src/com/pao/festival/
├── model/
│   ├── Persoana.java
│   ├── Angajat.java
│   ├── Staff.java
│   ├── Artist.java
│   ├── Participant.java
│   ├── BiletId.java
│   ├── Bilet.java
│   ├── BiletVIP.java
│   ├── Festival.java
│   ├── Locatie.java
│   └── Scena.java
├── service/
│   ├── FestivalService.java
│   └── ParticipantService.java
├── exception/
│   ├── ArtistNegasitException.java
│   └── CapacitateDepasitaException.java
└── Main.java
```
