package ee.ivxv.audit;

import ch.qos.cal10n.BaseName;
import ch.qos.cal10n.LocaleData;
import ee.ivxv.common.util.NameHolder;

@BaseName("i18n.audit-msg")
@LocaleData(defaultCharset = "UTF-8", value = {})
public enum Msg implements NameHolder {
    /*-
     * The part of the enum name until the first '_' (including) is excluded from the getName().
     * This is a means to provide multiple translations for the same tool or argument name.
     */

    // App
    app_audit,

    // Error messages
    e_proof_verif_false, e_proof_verif_exception,

    // Tools
    tool_decrypt, tool_mixer, tool_convert,

    // Tool arguments
    arg_hash, arg_input("i"), arg_links("l"), arg_out("o"), arg_pbb("p"), arg_pub("p"), //
    arg_revoke("r"), arg_seed("s"), arg_storage("s"), arg_signaturepub, arg_threads("t"), //
    arg_input_bb, arg_output_bb, arg_protinfo, arg_proofdir,//

    // Messages
    m_pub_loading, m_pub_loaded, m_failurecount, m_verify_start, m_verify_finish, //
    m_shuffle_proof_loading, m_shuffle_proof_failed_reason, m_shuffle_proof_succeeded, //
    m_shuffle_proof_failed;

    private final String shortName;

    Msg() {
        this(null);
    }

    Msg(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public String getShortName() {
        return shortName;
    }

    @Override
    public String getName() {
        return extractName(name());
    }

    @Override
    public Enum<?> getKey() {
        return this;
    }
}
