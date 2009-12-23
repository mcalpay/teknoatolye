package org.mca.ewall.jsf.lifecycle;

/**
 * @author malpay
 */
public enum CycleId {
    ANY, 
    RESTORE_VIEW,
    APPLY_REQUEST_VALUES,
    PROCESS_VALIDATIONS,
    UPDATE_MODEL_VALUES,
    INVOKE_APPLICATION,
    RENDER_RESPONSE
}
