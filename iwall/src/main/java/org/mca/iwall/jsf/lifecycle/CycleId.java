package org.mca.iwall.jsf.lifecycle;

/**
 * Defines the phases.
 * Phase ordinals must match with jsf phase ordinals.
 * @author mcalpay
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
