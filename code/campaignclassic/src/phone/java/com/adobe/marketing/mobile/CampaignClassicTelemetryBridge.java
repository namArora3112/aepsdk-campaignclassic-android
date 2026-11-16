/*
  Copyright 2026 Adobe. All rights reserved.
  This file is licensed to you under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0
  Unless required by applicable law or agreed to in writing, software distributed under
  the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR REPRESENTATIONS
  OF ANY KIND, either express or implied. See the License for the specific language
  governing permissions and limitations under the License.
*/

package com.adobe.marketing.mobile;

import com.adobe.marketing.mobile.telemetrybridge.TelemetryBridge;

/** Direct telemetry for rejected calls which never dispatch an EventHub event. */
final class CampaignClassicTelemetryBridge {
    private static final String PRODUCER = "com.adobe.campaignclassic";

    private CampaignClassicTelemetryBridge() {}

    static String operationStarted(final String eventName) {
        return TelemetryBridge.operationStarted(PRODUCER, eventName);
    }

    static void operationCompleted(
            final String operationId,
            final String eventName,
            final String outcome,
            final String meaning) {
        TelemetryBridge.operationCompleted(operationId, PRODUCER, eventName, outcome, meaning);
    }
}
