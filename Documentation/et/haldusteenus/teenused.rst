..  IVXV kogumisteenuse haldusliidese kasutusjuhend

Teenuste haldamine
==================

Teenuste haldamise leht avaneb menüüvalikust ``Teenused``.


Teenuste kokkuvõte
------------------

Teenuste kokkuvõttes kuvatakse kõikide registreeritud teenuste ülevaadet
seisundi kaupa. Iga seisundi järel kuvatakse selles seisundis olevate teenuste
arvu:

#. Paigaldamata – teenus pole paigaldatud;

#. Paigaldatud – teenus on paigaldatud ja sellele on rakendatud usaldusjuure
   seadistus, kogumisteenuse tehniline seadistus. Valimiste seadistus on
   teenusele rakendamata;

#. Seadistatud – teenusele on rakendatud kõik seadistused ja teenus on töökorras;

#. Tõrge – teenuse toimimises on avastatud tõrge;


Teenuste nimekiri
-----------------

Teenuste nimekirjas kuvatakse kõiki haldusteenuses registreeritud teenuseid.
Iga teenuse kohta kuvatakse:

Teenuste halduse vaates kuvatakse alamteenuste nimekirja, mis on sorditud
teenuse identifikaatori järgi:

#. Teenuse identifikaator;

#. Teenuse alamvõrk;

#. Teenuse liik;

#. Teenuse seisund;

Nimekirjas teenuse kirjel klõpsates avaneb kirje all tabel täpsema infoga:

#. Vihje teenuse seadistamiseks
   (ainult teenustel, mis on olekus ``paigaldamata`` või ``paigaldatud``);

#. Teenuse korrasoleku kontrolli poolt tuvastatud järjestikune vigade arv
   (ainult teenustel, mis on olekus ``seadistatud`` või ``tõrge``);

#. Teenuse korrasoleku kontrolli viimase läbiviimise aeg
   (ainult teenustel, mis on olekus ``seadistatud`` või ``tõrge``);

#. Teenusele rakendadud tehnilise seadistuse versioon;

#. Teenusele rakendadud valimiste seadistuse versioon;

#. Teenuse IP-aadress ja port;

#. Teenuse TLS-sertifikaadi kontrollsumma (SHA256);

#. Teenuse TLS-sertifikaadile vastava võtme kontrollsumma (SHA256);

#. Mobiil-ID tugiteenuse jagatud krüptimissaladuse kontrollsumma (SHA256);


Seadistuste laadimine kogumisteenusesse
---------------------------------------

Seadistuste laadimiseks kogumisteenusesse on lehe allosas laadimisvorm. Laadida
on lubatud ainult volitatud kasutajate poolt digitaaltelt signeeritud
seadistuspakke.

.. vim: sts=3 sw=3 et:
