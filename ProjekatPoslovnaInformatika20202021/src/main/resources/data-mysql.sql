INSERT INTO PREDUZECE(id_preduzeca, naziv_preduzeca, adresa, telefon, PIB, MIB) VALUES(1, 'Koka produkt', 'Tvrdoski put bb Ljubinje', '0644094513', 12345, 54321);
INSERT INTO PREDUZECE(id_preduzeca, naziv_preduzeca, adresa, telefon, PIB, MIB) VALUES(2, 'Farmavit', 'Svetosavska bb Ljubinje', '065192738', 98765, 56789);

INSERT INTO magacin(sifra_magacina, naziv_magacina, preduzece) VALUES (1, 'Magacin 1', 1);

INSERT INTO poslovna_godina(broj_godine, zakljucena, preduzece) VALUES (1, 0, 1);

INSERT INTO jedinica_mere(id_jedinice_mere, naziv, skraceni_naziv) VALUES(1, 'mera1', 'm1');

INSERT INTO roba_usluga(sifra, naziv, jedinica_mere) VALUES(1, 'roba1', 1);

INSERT INTO magacinska_kartica(id, pocetno_stanje_kolicinski, promet_ulaza_kolicinski, promet_izlaza_kolicinski, ukupna_kolicina, pocetno_stanje_vrednosno, promet_ulaza_vrednosno, promet_izlaza_vrednosno, ukupna_vrednost, cena, magacin, poslovna_godina, roba_ili_usluga)
VALUE (1, 1.1, 1.1, 1.1, 1.1,1.1, 1.1, 1.1, 1.1, 1.1, 1, 1, 1);

INSERT INTO promet_magacinske_kartice(id_prometa, vrsta_prometa, smer, kolicina, cena,vrednost, dokument, datum_prometa, magacinska_kartica) VALUES(1, 1, 1, 1.7, 117.1, 4.5, 'document1', '2020-12-15', 1);