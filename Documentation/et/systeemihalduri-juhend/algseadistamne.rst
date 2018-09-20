..  IVXV kogumisteenuse haldusjuhend

Süsteemi algseadistamine
========================

Süsteemi algseadistamine tähendab süsteemi paigaldamist ning seadistamist
läbiviidavate valimiste tabeks.

.. _nouded-platvormile:

Nõuded kasutatavale platvormile
-------------------------------

Kogumisteenus töötab platvormil ``Ubuntu 16.04 LTS (Xenial Xerus)``.


Väliste teenuste kaardistamine
------------------------------

Kogumisteenuse poolt toetatavate ja läbiviidavas hääletamises kasutatavate
väliste teenuste (DigiDocService, OCSP jms) kaardistamise käigus koostatakse
nimekiri välistest teenustest ja nendega andmevahetuseks vajalikest andmetest
(võrguaadress, port jms).

Väliste teenuste andmed on sisendiks kogumisteenuse tehnilise seadistuse
koostamisel (:ref:`seadistuste_koostamine`).

Väliste teenuste kaardistamise tulemusena on kogumisteenuse osutajal olemas
nimekiri kogumisteenuse poolt kasutatavatest välistest teenustest koos teenuste
kasutamiseks vajalike parameetritega.


Tugiteenuste ettevalmistamine
-----------------------------

Kogumisteenuse tugiteenusteks on:

#. Tehnilise seire teenus;

#. Logiseire teenus;

#. Varundusteenus.

Tehnilise seire ettevalmistamine
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Tehnilise seire teenus on `Zabbix <http://www.zabbix.com/>`_ tarkvaral põhinev
seire- ja teavitussüsteem. Zabbix serveri paigaldab  ja seadistab
kogumisteenuse osutaja iseseisvalt.

Seire toimimiseks on tarvis määrata seire eest vastutavad isikud ning tagada
nende vahetu teavitamine seireprogrammi poolt avastatud kõrvalekalletest.

Lisaks standardsele tehnilisele seirele (teenusmasinate
protsessori-/kettakasutus jms.) viib kogumisteenuse haldusteenus läbi
alamteenuste seiret ja teavitab tehnilise seire serverit avastatud
kõrvalekalletest.

.. todo::

   Lisada haldusteenusele tehnilise seire teavituste tugi!

Tehnilise seire ettevalmistamise tulemusena on kogumisteenuse osutajal olemas
tehnilise seire server, kuhu on paigaldatud seiretarkvara ning kus on
kirjeldatud tehnilise seire eest vastutavad isikud ja nende teavitamise
meetodid.


Logiseire ettevalmistamine
^^^^^^^^^^^^^^^^^^^^^^^^^^

Logiseire teenus koosneb rsyslog logiserverist koos analüüsi- ja
visualiseerimistarkvaraga (Log Monitor, `Grafana <https://grafana.com/>`_).

Logiseire ettevalmistamine ja integreerimine kogumisteenusega on kirjeldatud
dokumendis ``IVXV tegevuslogi
seirelahendus``.

Logiseire ettevalmistamise tulemusena on kogumisteenuse osutajal olemas
logiseire server, kuhu on paigaldatud logiseire tarkvara ning kus on
kirjeldatud logiseire andmetele ligipääsevad isikud.


Varunduse ettevalmistamine
^^^^^^^^^^^^^^^^^^^^^^^^^^

.. todo::

   Käesolev versioon ei sisalda varunduse tuge,
   see lisandub järgmises versioonis.

Varundusteenus on kogumisteenuse osutaja poolt paigaldatud ja seadistatud
varundusserver, mis vastutab kogumisteenuse sisemises varundusserveris
koostatud varukoopiate säilimise eest.

Varunduse ettevalmistamise tulemusena on kogumisteenuse osutajal olemas
varundusserver, mis on suuteline kogumisteenuse varundusliidese kaudu andmeid
varundama.


Kogumisteenuse tarkvara valmendamine
------------------------------------

Kogumisteenuse tarkvara valmendamiseks käivitatakse tarkvara lähtekoodi
kataloogis käsk :command:`dpkg-buildpackage`. Edukaks valmendamiseks on tarvis
tarvilike tarkvarapakkide paigaldamine. Kui käsu :command:`dpkg-buildpackage`
käivitamine katkeb vajalike pakkide puudumise tõttu, tuleb need pakid
paigaldada käsi :command:`apt-get install` abil. Vajaminevate pakkide nimekiri
kuvatakse käsu :command:`dpkg-buildpackage` väljundis, samuti on võimalik neid
tuvastada faili :file:`debian/control` sisu järgi (väli ``Build-Depends``).

.. todo::

   Lisa juhend valmendamiseks vajaliku golang pakkide paigaldamiseks.

.. include:: genereeritud-failid/pakkide_valmendamine.inc

E-hääletamissüsteemi tarkvara valmendamise tulemusena on kogumisteenuse
osutajal olemas hääletustarkvara deb-pakid.


.. _seadistuste_koostamine:

Kogumisteenuse seadistuste koostamine
-------------------------------------

Kogumisteenuse seadistused koosnevad kolmest eraldiseisvast osast:

#. **Usaldusjuure seadistus** sisaldab andmed seadistuste (kaasa arvatud
   usaldusjuure enda) allkirjade kontrollimiseks ja nimekirja kogumisteenuse
   haldurite volitustest.

#. **Kogumisteenuse tehniline seadistus** määrab kogumisteenuse tehnilised
   parameetrid, hääletuse läbiviimiseks kasutatavad teenused, samuti ka
   kogumisteenuse koosseisu kuulvad alamteenused.

#. **Valimiste seadistus** määrab ühe valimise seadistuse.

Seadistuste koostamine on kirjeldatud dokumendis ``Elektroonilise hääletamise
infosüsteemi IVXV seadistuste koostamise juhend``. Kogumisteenusele
rakendatavad seadistused peavad olema pakendatud BDOC konteinerisse ja olema
signeeritud volitatud kasutaja poolt.

Kogumisteenuse seadistuste koostamise tulemusena on kogumisteenuse osutajal
olemas kogumisteenuse seadistamiseks vajalikud seadistuspakid. Kõik seadistused
on signeeritud isiku(te) poolt, kelle volitused on kirjeldatud usaldusjuure
seadistustes.


Kogumisteenuse taristu paigaldamine
-----------------------------------

Kogumisteenuse taristu eraldatakse teenuse osutamiseks vastavalt koostatud
seadistustele (:ref:`seadistuste_koostamine`).

Igas teenusmasinas:

#. peab olema seadistatud hostinimi (fail :file:`/etc/hostname`);

#. peab olema paigaldatud SSH teenus (tarkvarapakk ``openssh-server``);

#. peab olema paigaldatud tehnilise seire teenuse agent
   (tarkvarapakk ``zabbix-agent``);

#. peab olema tagatud õige kellaaeg
   (näiteks õige kellaaja teenuse ``ntp`` abil).

#. peab olema seadistatud nimelahendus, mis võimaldab kõikide teenusmasinate
   aadresse lahendada;

#. peab olema seadistatud Eesti lokaat koos UTF-8 kooditabeli toega
   ``et_EE.UTF-8`` (kas tarkvarapakk ``locales`` koos nimetatud lokaadi
   seadistamisega või tarkvarapakk ``locales-all``, mis paigaldab kõik toetatud
   lokaadid).

.. note::

   Iga teenusmasina poolt kasutatav nimelahendus peab tagama, et suhtluseks
   kasutatavate hostide nimed lahenduvad korrektselt.

   Vältima peab olukordi, kus hostinimi lahendub mitmeks aadressiks või
   teistele hostidele kättesaamatuks aadressiks.

   Järgnev näide kirjeldab võimalikku olukorda failis :file:`/etc/hosts`, kus
   opsüsteemi paigalduse järel on hostinimi ``ivxv123`` määratud kahele
   liidesele. Sellise seadistuse puhul võib tekkida olukord, kus aadressile
   ``ivxv123`` ühendusi vastu võtma seadistatud teenus hakkab kuulama kohalikul
   liidesel ``127.0.0.1`` ja pole avaliku liidese ``192.168.10.1`` kaudu
   teistele teenustele kättesaadav.

   .. code-block:: text

      # /etc/hosts
      127.0.0.1     ivxv123
      192.168.10.1  ivxv123

Kogumisteenuse taristu jaoks eraldatud hostidest tuleb koostada nimekiri, kus
on kirjas hosti asukohaks olev alamvõrk, hosti nimi, IP-aadress, SSH-serveri
avalik võti ja hostile plaanitud teenused.

Kogumisteenuse taristu nimekirjas näide:

.. code-block:: text

   Valimiste infrastruktuuri andmed

     Alamvõrk: zone1

        IP-aadress: 172.16.238.10
        Hostinimi: admin
        SSH-serveri avalik võti:
          ecdsa-sha2-nistp256 AAAAE2VjZHNhLX...SgtbbE= root@admin

        IP-aadress: 172.16.238.41
        Hostinimi: ivxv1
        SSH-serveri avalik võti:
          ecdsa-sha2-nistp256 AAAAE2VjZHNhLX...mN8ul0= root@ivxv1

     Alamvõrk: zone2

        IP-aadress: 172.16.100.63
        Hostinimi: ivxv2
        SSH-serveri avalik võti:
          ecdsa-sha2-nistp256 AAAE2VjZHNhLXN...rtWT7A= root@ivxv2

Kogumisteenuse taristusse kuuluvad hostid tuleb lisada tehnilisse seiresse.

Kogumisteenuse taristu paigaldamise tulemusena on kogumisteenuse osutajal
olemas dokumenteeritud platvorm kogumisteenuse paigaldamiseks ettenähtud
konfiguratsiooniga. Kõik taristusse kuuluvad (virtuaal)masinad on tehnilise
seire teenuse poolt kättesaadavad ja nende seisundis pole tuvastatud probleeme.


Haldusteenuse paigaldamine
--------------------------

Haldusteenuse paigaldamine toimub haldusteenuse hostil.

Haldusteenuse paigaldamiseks tuleb kopeerida **kõik** kogumisteenuse
tarkvarapakid haldusteenuse masina kataloogi :file:`/opt/`. Nendest pakkidest
paigaldatakse haldusteenus, samuti kasutab haldusteenus neid pakke alamteenuste
paigaldamiseks.

Haldusteenuse sõltuvuste paigaldamine:

.. include:: genereeritud-failid/haldusteenuse_soltuvuste_paigaldamine.inc

Haldusteenuse paigaldamine:

.. include:: genereeritud-failid/haldusteenuse_paigaldamine.inc

.. important::

   Haldusteenuse edasine kasutamine toimub haldusteenuse konto alt. Selleks
   tuleb halduril luua SSH-ligipääs haldusteenuse kontole ``ivxv-admin``.
   Soovitav on autentimine teha ID-kaardi põhiseks.

Haldusteenuse paigaldamise tulemusena on kogumisteenuse osutajal teenuse
haldamiseks vajalik liides.


Kogumisteenuse taristu hõlmamine haldusesse
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Haldusteenus kasutab kogumisteenuse haldamiseks SSH protokolli. Selleks, et
haldusteenusel oleks võimalik teisi teenushoste usaldada, tuleb
haldusteenusesse lisada hallatavate teenushostide SSH-serveri võtmed.

Näide hosti ``ivxv1`` SSH-võtmete lisamisest haldusteenuse usaldatavate hostide
hulka::

   ivxv-admin@admin $ ssh-keyscan ivxv1 >> ~/.ssh/known_hosts
   # ivxv1:22 SSH-2.0-OpenSSH_7.2p2 Ubuntu-4ubuntu2.2
   # ivxv1:22 SSH-2.0-OpenSSH_7.2p2 Ubuntu-4ubuntu2.2
   # ivxv1:22 SSH-2.0-OpenSSH_7.2p2 Ubuntu-4ubuntu2.2

Selleks, et haldusteenusel oleks võimalik teenushostidesse tarkvara paigaldada,
tuleb haldusteenuse kontole ``ivxv-admin`` luua SSH-ligipääs teenushostide
juurkasutaja kontole.

.. note::

   Haldusteenus vajab juurkasutaja ligipääsu alamteenuse tarkvara
   paigaldamiseks. Pärast edukat paigaldamist ühel hostil eemaldab
   haldusteenus ligipääsu selle hosti juurkasutaja kontole.

Haldusteenuse konto SSH-võtmepaari avalik võti asub kasutaja ``ivxv-admin``
kodukataloogi all failis :file:`.ssh/id_rsa.pub` ja see on genereeritud
haldusteenuse paigaldamise käigus. Vajadusel võib haldur selle võtme asendada
(kuid see peab toimuma enne, kui võti on üle kantud hallatavatesse
teenusmasinatesse).

Teenusmasinas tuleb haldusteenuse konto SSH avalik võti panna faili
:file:`/root/.ssh/authorized_keys`. See fail peab kuuluma juurkasutajale ja
olema loetav ainult juurkasutaja poolt (faili pääsuõigused ``0600``).

Kogumisteenuse taristu haldusesse hõlmamise tulemusena on haldusteenusel
usaldusväärne ligipääs kogumisteenuse taristusse kuuluvatele teenusmasinate
juurkasutaja kontodele.


Haldusteenuse veebiliidese vaikimisi TLS-sertifikaadi asendamine
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Haldusteenuse paigalduse käigus genereeritakse kasutajaliidese veebiserveri
TLS-sertifikaat koos krüptovõtmega ja tugeva Diffie-Hellman grupifail (vaata
https://weakdh.org/). Vajadusel on halduril võimalik need asendada (vaata
märkust lõigus ":ref:`nouded-platvormile`").

Failide asukohad:

* Veebiserveri TLS-sertifikaadi võti:
  :file:`/etc/ssl/private/ivxv-admin-default.key`

* Veebiserveri TLS-sertifikaat: :file:`/etc/ssl/certs/ivxv-admin-default.crt`

* Diffie-Hellmani grupifail: :file:`/etc/ssl/dhparams.pem`

Asendatud failide rakendamiseks tuleb veebiserver taaskäivitada käsuga
:command:`service apache2 restart` ja veenduda, et veebiliides töötab.

Haldusteenuse veebiliidese vaikimisi TLS-sertifikaadi asendamise tulemusena
kasutab haldusteenuse veebiliides turvalist sertifikaati.


Haldusteenuse lähtestamine
--------------------------

Haldusteenuse lähtestamine toimub käsuga :ref:`ivxv-collector-init`. Selle
käigus puhastatakse haldusteenuse andmekataloogid ja lähtestatakse andmebaas.


Seadistuste ja valimisnimekirjade rakendamine kogumisteenusele
--------------------------------------------------------------

.. note::

   Käesolevas lõigus ja alamlõikudes tähendab "seadistuspakk" nii seadistusi
   sisaldavat faili kui valimisnimekirja faili, mis on signeeritud volitatud
   isiku poolt.

Kogumisteenusele tuleb rakendada järgmised seadistuspakid:

#. Usaldusjuur – laaditakse alati esimesena;

#. Kogumisteenuse tehniline seadistus – laaditakse enne valimiste seadistust;

#. Valimiste seadistus – laaditakse enne nimekirju;

#. Valikute nimekiri;

#. Valijate nimekiri.

Ettevalmistatud seadistuspakkide rakendamiseks tuleb läbi viia järgmised
tegevused:

#. Ülekandmine haldusteenuse masinasse;

#. Laadimine haldusteenusesse;

#. Rakendamine alamteenustele.

.. hint::

   Seadistuspakkide ettevalmistamine on kirjeldatud lõigus
   ":ref:`seadistuste_koostamine`".

.. attention::

   Usaldusjuure seadistuse laadimisega kaasneb alati ka
   kogumisteenuse haldusteenuse andmebaasi lähtestamine!

Seadistuste ja valimisnimekirjade kogumisteenusele rakendamise tulemusena on
kogumisteenus seadistatud ettenähtud perioodil osutama nõuetekohast häälte
kogumise teenust.


Seadistuspaki ülekandmine haldusteenuse masinasse
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Seadistuspaki transportimiseks haldusteenuse masinasse toimub üle `SCP
<https://en.wikipedia.org/wiki/Secure_copy>`_ protokolli. Seadistuspakk peab
olema kättesaadav haldusteenuse kasutajakontole ``ivxv-admin``.

Näide::

   $ scp seadistus.bdoc ivxv-admin@admin:
   seadistus.bdoc              100%   15KB  79.5KB/s   00:00

.. note::

   Kogumisteenus osutaja võib seadustuspakkide ülekandmiseks kasutada ka muid
   meetodeid, näiteks irdmeediat.

Seadistuspaki ülekandmise tulemusena on seadistuspakk haldusteenuse poolt
ligipääsetaval andmekandjal.


Seadistuspaki laadimine haldusteenusesse
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Seadistuspakk laaditakse haldusteenusesse käsuga :ref:`ivxv-cmd-load`. Selle
käigus kontrollib haldusteenus seadistuspaki signeerinud isiku volitusi ja
valideerib seadistuste sisu. Laadimise tulemusena on seadistuspakk valmis
rakemdamiseks hallatavatele teenustele.

Näide: Usaldusjuure laadimine haldusteenusesse:

.. include:: genereeritud-failid/haldusteenus-laadi_usaldusjuure_seadistused.inc

Seadistuspaki haldusteenusesse laadimise tulemusena on haldusteenus valmis
rakendama seadistuspakki alamteenustele. Seadistuspaki versiooni kuvatakse
haldusteenuse olekuandmetes.


Seadistuste rakendamine alamteenustele
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

Haldusteenusesse laaditud seadistuspakid rakendatakse hallatavatele teenustele
käsuga :ref:`ivxv-config-apply`. Rakendamine on võimalik tehniliste seadistuse
laadimise järel, kuna tehnilise seadistusega tekivad haldusteenusesse andmed
hallatavate teenuste kohta.

Seadistuste rakendamise käigus haldusteenus:

* Paigaldab seadistatava teenuse tarkvara (tehnilise seadistuse laadimisel, kui
  pole eelnevalt paigaldatud);

* Kannab seadistuspaki üle hallatava teenuse hosti failisüsteemi;

* Valimiste seadistuse laadimisel lubab ja käivitab seadistatava teenuse.

.. note::

   Seadistuste rakendamise järjekord on kirjeldatud utiliidi ivxv-config-apply
   abiteabe lõigus (vaata :ref:`ivxv-config-apply`).

Näide: Haldusteenusesse laaditud seadistuste rakendamine hallatavatele
teenusele::

   ivxv-admin@admin $ ivxv-config-apply
   INFO: Technical config is signed by ÕIGE,VALIK,44444444444 2017-06-07T12:05:44Z
   INFO: Service choices@choices1.ivxv.ee: Applying technical config
   SERVICE choices@choices1.ivxv.ee: Installing service to host "ivxv1"
   SERVICE choices@choices1.ivxv.ee: Querying state of the service software package "ivxv-choices"
   SERVICE choices@choices1.ivxv.ee: Copying software package files to service host
   SERVICE choices@choices1.ivxv.ee: Checking state of dpkg database in service host
   SERVICE choices@choices1.ivxv.ee: Installing dependencies for package "ivxv-common"
   Reading package lists...
   Building dependency tree...
   Reading state information...
   ...
   SERVICE voting@voting3.ivxv.ee: Set trust config file permissions in service host
   SERVICE voting@voting3.ivxv.ee: Trust root config successfully applied to service
   SERVICE voting@voting3.ivxv.ee: Applying technical config to service
   SERVICE voting@voting3.ivxv.ee: Copying technical config to service host
   SERVICE voting@voting3.ivxv.ee: Set technical config file permissions in service host
   SERVICE voting@voting3.ivxv.ee: Technical config successfully applied to service
   SERVICE voting@voting3.ivxv.ee: Registering technical config version "ÕIGE,VALIK,44444444444 2017-06-07T12:05:44Z" in management database
   SERVICE voting@voting3.ivxv.ee: Registering service state as "INSTALLED" in management database
   INFO: Service voting@voting3.ivxv.ee: technical config config applied successfully
   INFO: 15 configuration packages successfully applied

Seadistuste alamteenustele rakendamise tulemusena on hallatavad teenused
seadistatud ja nende seisund on haldusteenusest jälgitav.


Kogumisteenuse krüptovõtmete rakendamine
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

**Teenuste krüptovõtmete ja TLS-sertifikaatide** rakendamine toimub käsuga
:ref:`ivxv-secret-import`.

.. hint::

   Teenuse krüptovõtmete seisundit on võimalik väljastada käsuga
   :command:`ivxv-status --service=<service-id>` (vaata :ref:`ivxv-status`)

Võtme laadimine teenusele:

.. code-block:: shell-session

   $ ivxv-secret-import --service=<teenuse-id> tls-key tls.key

Sertifikaadi laadimine teenusele:

.. code-block:: shell-session

   $ ivxv-secret-import --service=<teenuse-id> tls-cert tls.pem

.. important::

   Igale teenuse isendile tuleb rakendada selle
   isendi jaoks genereeritud võti ja sertifikaat!

**Hääletamisteenuse ajatemplipäringute signeerimisvõtme** rakendamine toimub
käsuga :ref:`ivxv-secret-import`:

.. code-block:: shell-session

   $ ivxv-secret-import tsp-regkey tspreg.key

.. note::

   Hääletamisteenuse ajatemplipäringute signeerimisvõti on vaja rakendada vaid
   juhul, kui ajatempliteenust kasutatakse registreerimisteenuseks (tehnilises
   seadistuses on ``qualification/protocol`` välja väärtuseks ``tspreg``).

**Mobiil-ID identsustõendi võtme** rakendamine toimub
käsuga :ref:`ivxv-secret-import`:

.. code-block:: shell-session

   $ ivxv-secret-import dds-token-key mobid-shared-secret.key

.. note::

   Mobiil-ID identsustõendi võti on vaja rakendada vaid
   juhul, kui Mobiil-ID tugiteenus on kasutusel
   (tehnilises seadistuses on olemas plokk ``auth.ticket``).

Kogumisteenuse krüptovõtmete rakendamise tulemusena on hallatavate teenusete
suhtluskanalid varustatud kanali turvamiseks vajalike krüptovõtmetega, samuti
on teenustel olemas krüptovõtmed muude oluliste operatsioonide jaoks.

.. vim: sts=3 sw=3 et:
