package com.sample;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Entity;
import org.kie.dmn.api.core.event.AfterEvaluateAllEvent;
import org.kie.dmn.api.core.event.AfterEvaluateDecisionEvent;
import org.kie.dmn.api.core.event.DMNRuntimeEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ApplicationScoped
public class MyListener implements DMNRuntimeEventListener{
    private static final Logger LOG = LoggerFactory.getLogger(MyListener.class);

    @Override
    public void afterEvaluateDecision(AfterEvaluateDecisionEvent event) {
        LOG.debug("Decision {} evaluate to: {}", event.getDecision(), event.getResult().getDecisionResultByName(event.getDecision().getName()).getResult());
    }
}
