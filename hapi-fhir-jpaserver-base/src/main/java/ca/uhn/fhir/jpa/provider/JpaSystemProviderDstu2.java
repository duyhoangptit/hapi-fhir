package ca.uhn.fhir.jpa.provider;

import ca.uhn.fhir.jpa.api.dao.IFhirSystemDao;
import ca.uhn.fhir.jpa.dao.FulltextSearchSvcImpl.Suggestion;
import ca.uhn.fhir.jpa.dao.IFulltextSearchSvc;
import ca.uhn.fhir.jpa.model.util.JpaConstants;
import ca.uhn.fhir.jpa.provider.dstu3.JpaSystemProviderDstu3;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.dstu2.composite.MetaDt;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Parameters;
import ca.uhn.fhir.model.dstu2.resource.Parameters.Parameter;
import ca.uhn.fhir.model.primitive.BooleanDt;
import ca.uhn.fhir.model.primitive.DecimalDt;
import ca.uhn.fhir.model.primitive.IntegerDt;
import ca.uhn.fhir.model.primitive.StringDt;
import ca.uhn.fhir.rest.annotation.IdParam;
import ca.uhn.fhir.rest.annotation.Operation;
import ca.uhn.fhir.rest.annotation.OperationParam;
import ca.uhn.fhir.rest.annotation.Transaction;
import ca.uhn.fhir.rest.annotation.TransactionParam;
import ca.uhn.fhir.rest.api.server.RequestDetails;
import ca.uhn.fhir.rest.server.exceptions.InvalidRequestException;
import ca.uhn.fhir.rest.server.servlet.ServletRequestDetails;
import org.hl7.fhir.instance.model.api.IBaseBundle;
import org.hl7.fhir.instance.model.api.IIdType;
import org.hl7.fhir.r4.model.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import static org.apache.commons.lang3.StringUtils.isBlank;

/*
 * #%L
 * HAPI FHIR JPA Server
 * %%
 * Copyright (C) 2014 - 2020 University Health Network
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

public class JpaSystemProviderDstu2 extends BaseJpaSystemProviderDstu2Plus<Bundle, MetaDt> {

	@Autowired()
	@Qualifier("mySystemDaoDstu2")
	private IFhirSystemDao<Bundle, MetaDt> mySystemDao;

	@Autowired(required = false)
	private IFulltextSearchSvc mySearchDao;

	@Operation(name = JpaConstants.OPERATION_EXPUNGE, idempotent = false, returnParameters = {
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_OUT_PARAM_EXPUNGE_COUNT, type = IntegerDt.class)
	})
	public Parameters expunge(
		@IdParam IIdType theIdParam,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_LIMIT) IntegerDt theLimit,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_DELETED_RESOURCES) BooleanDt theExpungeDeletedResources,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_PREVIOUS_VERSIONS) BooleanDt theExpungeOldVersions,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_EVERYTHING) BooleanDt theExpungeEverything,
		RequestDetails theRequestDetails
	) {
		org.hl7.fhir.r4.model.Parameters retVal = super.doExpunge(theLimit, theExpungeDeletedResources, theExpungeOldVersions, theExpungeEverything, theRequestDetails);
		return toExpungeResponse(retVal);
	}

	@Operation(name = JpaConstants.OPERATION_EXPUNGE, idempotent = false, returnParameters = {
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_OUT_PARAM_EXPUNGE_COUNT, type = IntegerDt.class)
	})
	public Parameters expunge(
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_LIMIT) IntegerDt theLimit,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_DELETED_RESOURCES) BooleanDt theExpungeDeletedResources,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_PREVIOUS_VERSIONS) BooleanDt theExpungeOldVersions,
		@OperationParam(name = JpaConstants.OPERATION_EXPUNGE_PARAM_EXPUNGE_EVERYTHING) BooleanDt theExpungeEverything,
		RequestDetails theRequestDetails
	) {
		org.hl7.fhir.r4.model.Parameters retVal = super.doExpunge(theLimit, theExpungeDeletedResources, theExpungeOldVersions, theExpungeEverything, theRequestDetails);
		return toExpungeResponse(retVal);
	}

	//@formatter:off
	// This is generated by hand:
	// ls hapi-fhir-structures-dstu2/target/generated-sources/tinder/ca/uhn/fhir/model/dstu2/resource/ | sort | sed "s/.java//" | sed "s/^/@OperationParam(name=\"/" | sed "s/$/\", type=IntegerDt.class, min=0, max=1),/"
	@Operation(name = JpaConstants.OPERATION_GET_RESOURCE_COUNTS, idempotent = true, returnParameters = {
		@OperationParam(name = "AllergyIntolerance", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Appointment", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "AppointmentResponse", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "AuditEvent", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Basic", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Binary", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "BodySite", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Bundle", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "CarePlan", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "CarePlan2", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Claim", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ClaimResponse", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ClinicalImpression", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Communication", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "CommunicationRequest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Composition", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ConceptMap", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Condition", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Conformance", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Contract", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Contraindication", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Coverage", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DataElement", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Device", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DeviceComponent", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DeviceMetric", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DeviceUseRequest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DeviceUseStatement", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DiagnosticOrder", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DiagnosticReport", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DocumentManifest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "DocumentReference", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "EligibilityRequest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "EligibilityResponse", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Encounter", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "EnrollmentRequest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "EnrollmentResponse", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "EpisodeOfCare", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ExplanationOfBenefit", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "FamilyMemberHistory", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Flag", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Goal", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Group", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "HealthcareService", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ImagingObjectSelection", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ImagingStudy", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Immunization", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ImmunizationRecommendation", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ListResource", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Location", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Media", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Medication", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "MedicationAdministration", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "MedicationDispense", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "MedicationPrescription", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "MedicationStatement", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "MessageHeader", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "NamingSystem", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "NutritionOrder", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Observation", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "OperationDefinition", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "OperationOutcome", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Order", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "OrderResponse", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Organization", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Parameters", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Patient", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "PaymentNotice", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "PaymentReconciliation", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Person", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Practitioner", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Procedure", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ProcedureRequest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ProcessRequest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ProcessResponse", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Provenance", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Questionnaire", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "QuestionnaireAnswers", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ReferralRequest", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "RelatedPerson", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "RiskAssessment", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Schedule", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "SearchParameter", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Slot", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Specimen", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "StructureDefinition", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Subscription", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Substance", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "Supply", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "ValueSet", type = IntegerDt.class, min = 0, max = 1),
		@OperationParam(name = "VisionPrescription", type = IntegerDt.class, min = 0, max = 1)
	})
	@Description(shortDefinition = "Provides the number of resources currently stored on the server, broken down by resource type")
	//@formatter:on
	public Parameters getResourceCounts() {
		Parameters retVal = new Parameters();

		Map<String, Long> counts = mySystemDao.getResourceCountsFromCache();
		counts = defaultIfNull(counts, Collections.emptyMap());
		counts = new TreeMap<>(counts);
		for (Entry<String, Long> nextEntry : counts.entrySet()) {
			retVal.addParameter().setName(new StringDt(nextEntry.getKey())).setValue(new IntegerDt(nextEntry.getValue().intValue()));
		}

		return retVal;
	}

	@Operation(name = JpaConstants.OPERATION_META, idempotent = true, returnParameters = {
		@OperationParam(name = "return", type = MetaDt.class)
	})
	public Parameters meta(RequestDetails theRequestDetails) {
		Parameters parameters = new Parameters();
		parameters.addParameter().setName("return").setValue(getDao().metaGetOperation(theRequestDetails));
		return parameters;
	}

	@Operation(name = JpaConstants.OPERATION_SUGGEST_KEYWORDS, idempotent = true)
	public Parameters suggestKeywords(
		@OperationParam(name = "context", min = 1, max = 1) String theContext,
		@OperationParam(name = "searchParam", min = 1, max = 1) String theSearchParam,
		@OperationParam(name = "text", min = 1, max = 1) String theText,
		RequestDetails theRequest) {
		JpaSystemProviderDstu3.validateFulltextSearchEnabled(mySearchDao);

		if (isBlank(theContext)) {
			throw new InvalidRequestException("Parameter 'context' must be provided");
		}
		if (isBlank(theSearchParam)) {
			throw new InvalidRequestException("Parameter 'searchParam' must be provided");
		}
		if (isBlank(theText)) {
			throw new InvalidRequestException("Parameter 'text' must be provided");
		}

		List<Suggestion> keywords = mySearchDao.suggestKeywords(theContext, theSearchParam, theText, theRequest);

		Parameters retVal = new Parameters();
		for (Suggestion next : keywords) {
			retVal.addParameter()
				.addPart(new Parameter().setName("keyword").setValue(new StringDt(next.getTerm())))
				.addPart(new Parameter().setName("score").setValue(new DecimalDt(next.getScore())));
		}

		return retVal;
	}

	@Transaction
	public Bundle transaction(RequestDetails theRequestDetails, @TransactionParam Bundle theResources) {
		startRequest(((ServletRequestDetails) theRequestDetails).getServletRequest());
		try {
			return getDao().transaction(theRequestDetails, theResources);
		} finally {
			endRequest(((ServletRequestDetails) theRequestDetails).getServletRequest());
		}
	}

	/**
	 * /$process-message
	 */
	@Operation(name = JpaConstants.OPERATION_PROCESS_MESSAGE, idempotent = false)
	public IBaseBundle processMessage(
		HttpServletRequest theServletRequest,
		RequestDetails theRequestDetails,

		@OperationParam(name = "content", min = 1, max = 1)
		@Description(formalDefinition = "The message to process (or, if using asynchronous messaging, it may be a response message to accept)")
			Bundle theMessageToProcess
	) {

		startRequest(theServletRequest);
		try {
			return getDao().processMessage(theRequestDetails, theMessageToProcess);
		} finally {
			endRequest(theServletRequest);
		}

	}

	public static Parameters toExpungeResponse(org.hl7.fhir.r4.model.Parameters theRetVal) {
		Integer count = ((IntegerType) theRetVal.getParameterFirstRep().getValue()).getValue();
		return new Parameters()
			.addParameter(new Parameter().setName("count").setValue(new IntegerDt(count)));
	}

}
