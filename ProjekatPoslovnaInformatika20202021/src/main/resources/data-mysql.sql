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
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Cement',2);
INSERT INTO roba_usluga(naziv,jedinica_mere) VALUES ('Ogrev',3);