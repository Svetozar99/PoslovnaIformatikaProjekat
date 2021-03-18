INSERT INTO PREDUZECE(id_preduzeca, naziv_preduzeca, adresa, telefon, PIB, MIB) VALUES(1, 'Koka produkt', 'Tvrdoski put bb Ljubinje', '0644094513', 12345, 54321);
INSERT INTO PREDUZECE(id_preduzeca, naziv_preduzeca, adresa, telefon, PIB, MIB) VALUES(2, 'Farmavit', 'Svetosavska bb Ljubinje', '065192738', 98765, 56789);


INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Metar','m');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Kilogram','kg');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Metar kubni','m3');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Metar kvadratni','m2');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Litar','l');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Komad','kom');

INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Hleb',6,50);
INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Sok',5,120);
INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Pivo',5,120);
INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Crep',6,500);
INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Plocice',4,300);
INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Stol',6,1500);
INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Dzak cementa',6,1000);
INSERT INTO roba_usluga(naziv,jedinica_mere,cena) VALUES ('Ogrev',3,2500);

INSERT INTO magacin(sifra_magacina, naziv_magacina, preduzece) VALUES (1, 'Magacin 1', 1);
INSERT INTO magacin(sifra_magacina, naziv_magacina, preduzece) VALUES (2, 'Magacin 2', 2);
INSERT INTO magacin(sifra_magacina, naziv_magacina, preduzece) VALUES (3, 'Magacin 3', 1);

INSERT INTO poslovna_godina(id_godine, broj_godine, zakljucena, preduzece) VALUES (1, 2020, 1, 1);
INSERT INTO poslovna_godina(id_godine, broj_godine, zakljucena, preduzece) VALUES (2, 2021, 0, 1);

INSERT INTO magacinska_kartica(id, pocetno_stanje_kolicinski, promet_ulaza_kolicinski, promet_izlaza_kolicinski, ukupna_kolicina, pocetno_stanje_vrednosno, promet_ulaza_vrednosno, promet_izlaza_vrednosno, ukupna_vrednost, cena, magacin, poslovna_godina, roba_ili_usluga)
VALUE (1, 200, 70, 30, 240,500000, 171000, 78000, 593000, 2471, 1, 2, 8);
INSERT INTO magacinska_kartica(id, pocetno_stanje_kolicinski, promet_ulaza_kolicinski, promet_izlaza_kolicinski, ukupna_kolicina, pocetno_stanje_vrednosno, promet_ulaza_vrednosno, promet_izlaza_vrednosno, ukupna_vrednost, cena, magacin, poslovna_godina, roba_ili_usluga)
VALUE (2, 500, 350, 400, 450,375000, 259000, 304000, 330000, 734, 1, 1, 7);
INSERT INTO magacinska_kartica(id, pocetno_stanje_kolicinski, promet_ulaza_kolicinski, promet_izlaza_kolicinski, ukupna_kolicina, pocetno_stanje_vrednosno, promet_ulaza_vrednosno, promet_izlaza_vrednosno, ukupna_vrednost, cena, magacin, poslovna_godina, roba_ili_usluga)
VALUE (3, 1.2, 1.6, 1.8, 1.1,1.1, 1.1, 1.1, 1.1, 1.1, 1, 2, 3);

INSERT INTO magacinska_kartica(id, cena, pocetno_stanje_kolicinski, pocetno_stanje_vrednosno, promet_izlaza_kolicinski, promet_izlaza_vrednosno, promet_ulaza_kolicinski, promet_ulaza_vrednosno, ukupna_kolicina, ukupna_vrednost, magacin, poslovna_godina, roba_ili_usluga)
VALUES(4, 20, 0, 0, 0, 0, 200, 4000, 200, 4000, 1, 2, 4);


INSERT INTO poslovni_partner(naziv_partnera,adresa_partnera,broj_telefona,email,PIB,MIB,preduzece) VALUES ('Partner1','Adresa1','123456789','email@example1.com',123124,321321,1);
INSERT INTO poslovni_partner(naziv_partnera,adresa_partnera,broj_telefona,email,PIB,MIB,preduzece) VALUES ('Partner2','Adresa2','234234234','email@example2.com',235232,457454,2);
INSERT INTO poslovni_partner(naziv_partnera,adresa_partnera,broj_telefona,email,PIB,MIB,preduzece) VALUES ('Partner3','Adresa3','346346436','email@example3.com',235542,745745,1);

INSERT INTO promet_magacinske_kartice(redni_broj, cena, datum_prometa, dokument, kolicina, smer, vrednost, vrsta_prometa, magacinska_kartica)
VALUES('1-2021', 20, '2021-03-18', 'Primka', 200, 0, 4000, 0, 4);

INSERT INTO poslovni_partner(naziv_partnera,adresa_partnera,broj_telefona,email,PIB,MIB,preduzece) VALUES ('Partner1','Adresa1','123456789','email@example.com',123123,321321,1);

INSERT INTO prometni_dokument(id, datum, redni_broj, status, vrsta_dokumenta, sifra_magacina_izlaz, poslovna_godina, poslovni_partner, id_preduzeca, sifra_magacina_ulaz) 
VALUES(1, '2021-03-18', '1-2021', 1, 0, NULL, 2, 1, 1, 1);

INSERT INTO stavka_dokumenta(id_stavke,kolicina,cena,vrednost,prometni_dokument,roba_usluga)
VALUES(1, 20, 200, 4000, 1, 4);
