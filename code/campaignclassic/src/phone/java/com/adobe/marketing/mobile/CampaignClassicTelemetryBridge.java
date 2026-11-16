/*
  POC ONLY — telemetry-integration branch. Optional reflective bridge to the telemetry plugin:
  when the plugin artifact is present, calls flow; when absent, everything is a no-op.
  No compile-time dependency of CampaignClassic on the telemetry SDK.
*/
package com.adobe.marketing.mobile;

import java.lang.reflect.Method;

/** Optional bridge from CampaignClassic to the telemetry plugin's public API. */
final class CampaignClassicTelemetryBridge {
    private static final String TELEMETRY_CLASS = "com.adobe.marketing.mobile.telemetry.Telemetry";
    private static final String PRODUCER = "com.adobe.campaignclassic";

    private CampaignClassicTelemetryBridge() {}

    static String operationStarted(final String eventName) {
        try {
            final Class<?> telemetry = Class.forName(TELEMETRY_CLASS);
            final Method m = telemetry.getMethod("operationStarted", String.class, String.class, String.class);
            return (String) m.invoke(null, PRODUCER, eventName, "directHook");
        } catch (Throwable t) {
            return null; // plugin absent or disabled — business path unaffected
        }
    }

    static void operationCompleted(final String operationId, final String eventName,
                                   final String outcome, final String meaning) {
        if (operationId == null) return;
        try {
            final Class<?> telemetry = Class.forName(TELEMETRY_CLASS);
            final Method m = telemetry.getMethod("operationCompleted",
                    String.class, String.class, String.class, String.class, String.class, String.class);
            m.invoke(null, operationId, PRODUCER, eventName, outcome, meaning, "directHook");
        } catch (Throwable ignored) { }
    }
}
