.. Elektroonilise hääletamise infosüsteem IVXV

.. Käsureautiliidi ivxv-cmd-load kasutusjuhend

.. SEE FAIL ON GENEREERITUD AUTOMAATTESTI POOLT:
.. * features/steps/autodoc.py
.. * features/autodoc-utilities.feature

.. _ivxv-cmd-load:

ivxv-cmd-load
`````````````

:command:`ivxv-cmd-load --help`:

.. code-block:: text

   Load command to IVXV Collector Management Service.

   Usage: ivxv-cmd-load [--validate-only] <type> FILE

   Options:
       <type>              Command type. Possible values are:
                           - election: election config
                           - technical: collector technical config
                           - trust: trust root config
                           - choices: choices list
                           - voters: voters list
                           - user: user account and role(s)
       --validate-only     Validate only.
