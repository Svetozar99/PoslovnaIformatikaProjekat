INSERT INTO PREDUZECE(id_preduzeca, naziv_preduzeca, adresa, telefon, PIB, MIB) VALUES(1, 'Koka produkt', 'Tvrdoski put bb Ljubinje', '0644094513', 12345, 54321);
INSERT INTO PREDUZECE(id_preduzeca, naziv_preduzeca, adresa, telefon, PIB, MIB) VALUES(2, 'Farmavit', 'Svetosavska bb Ljubinje', '065192738', 98765, 56789);


INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Metar','m');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Kilogram','kg');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Metar kubni','m3');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Metar kvadratni','m2');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Litar','l');
INSERT INTO jedinica_mere(naziv,skraceni_naziv) VALUES ('Komad','kom');

INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Hleb',6);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Sok',5);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Pivo',5);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Crep',6);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Plocice',4);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Stol',6);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Dzak cementa',6);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Ogrev',3);

INSERT INTO magacin(sifra_magacina, naziv_magacina, preduzece) VALUES (1, 'Magacin 1', 1);
INSERT INTO magacin(sifra_magacina, naziv_magacina, preduzece) VALUES (2, 'Magacin 2', 2);
INSERT INTO magacin(sifra_magacina, naziv_magacina, preduzece) VALUES (3, 'Magacin 3', 1);

INSERT INTO poslovna_godina(id_godine, broj_godine, zakljucena, preduzece) VALUES (1, 2020, 0, 1);
INSERT INTO poslovna_godina(id_godine, broj_godine, zakljucena, preduzece) VALUES (2, 2021, 1, 1);

INSERT INTO magacinska_kartica(id, pocetno_stanje_kolicinski, promet_ulaza_kolicinski, promet_izlaza_kolicinski, ukupna_kolicina, pocetno_stanje_vrednosno, promet_ulaza_vrednosno, promet_izlaza_vrednosno, ukupna_vrednost, cena, magacin, poslovna_godina, roba_ili_usluga)
VALUE (1, 200, 70, 30, 240,500000, 171000, 78000, 593000, 2471, 1, 2, 8);
INSERT INTO magacinska_kartica(id, pocetno_stanje_kolicinski, promet_ulaza_kolicinski, promet_izlaza_kolicinski, ukupna_kolicina, pocetno_stanje_vrednosno, promet_ulaza_vrednosno, promet_izlaza_vrednosno, ukupna_vrednost, cena, magacin, poslovna_godina, roba_ili_usluga)
VALUE (2, 500, 350, 400, 450,375000, 259000, 304000, 330000, 734, 1, 2, 7);
INSERT INTO magacinska_kartica(id, pocetno_stanje_kolicinski, promet_ulaza_kolicinski, promet_izlaza_kolicinski, ukupna_kolicina, pocetno_stanje_vrednosno, promet_ulaza_vrednosno, promet_izlaza_vrednosno, ukupna_vrednost, cena, magacin, poslovna_godina, roba_ili_usluga)
VALUE (3, 1.2, 1.6, 1.8, 1.1,1.1, 1.1, 1.1, 1.1, 1.1, 1, 2, 3);

INSERT INTO promet_magacinske_kartice(id_prometa, vrsta_prometa, smer, kolicina, cena,vrednost, dokument, datum_prometa, magacinska_kartica) VALUES(1, 1, 1, 1.7, 117.1, 4.5, 'document1', '2020-12-15', 1);

INSERT INTO poslovni_partner(naziv_partnera,adresa_partnera,broj_telefona,email,PIB,MIB,preduzece) VALUES ('Partner1','Adresa1','123456789','email@example.com',123123,321321,1)
