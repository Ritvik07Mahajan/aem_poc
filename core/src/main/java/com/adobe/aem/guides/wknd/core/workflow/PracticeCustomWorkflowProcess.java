package com.adobe.aem.guides.wknd.core.workflow;

import com.day.cq.workflow.WorkflowException;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.ParticipantStepChooser;
import com.day.cq.workflow.exec.WorkItem;
import com.day.cq.workflow.metadata.MetaDataMap;
import org.osgi.service.component.annotations.Component;
import java.util.Objects;

@Component(service = ParticipantStepChooser.class,
        property={"chooser.label=Practice Custom Workflow Process"})
public class PracticeCustomWorkflowProcess implements ParticipantStepChooser {

//Implemented Dynamic participant step, add or split for decision making if process step fir reject and page activation

    @Override
    public String getParticipant(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap) throws WorkflowException {
        String path = workItem.getWorkflowData().getPayload().toString();
        String countryReviewer = null;

        if (Objects.nonNull(path)) {
            if (path.contains("in")) {
                countryReviewer = "practice-admin";

            } else if (path.contains("us")) {
                countryReviewer = "practice-author";
            }
        }
        return countryReviewer;
    }
}