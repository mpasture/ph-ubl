/**
 * Copyright (C) 2014-2015 Philip Helger (www.helger.com)
 * philip[at]helger[dot]com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.helger.ubl;

import java.io.File;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.xml.bind.ValidationEventHandler;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;

import oasis.names.specification.ubl.schema.xsd.applicationresponse_21.ApplicationResponseType;
import oasis.names.specification.ubl.schema.xsd.attacheddocument_21.AttachedDocumentType;
import oasis.names.specification.ubl.schema.xsd.awardednotification_21.AwardedNotificationType;
import oasis.names.specification.ubl.schema.xsd.billoflading_21.BillOfLadingType;
import oasis.names.specification.ubl.schema.xsd.callfortenders_21.CallForTendersType;
import oasis.names.specification.ubl.schema.xsd.catalogue_21.CatalogueType;
import oasis.names.specification.ubl.schema.xsd.cataloguedeletion_21.CatalogueDeletionType;
import oasis.names.specification.ubl.schema.xsd.catalogueitemspecificationupdate_21.CatalogueItemSpecificationUpdateType;
import oasis.names.specification.ubl.schema.xsd.cataloguepricingupdate_21.CataloguePricingUpdateType;
import oasis.names.specification.ubl.schema.xsd.cataloguerequest_21.CatalogueRequestType;
import oasis.names.specification.ubl.schema.xsd.certificateoforigin_21.CertificateOfOriginType;
import oasis.names.specification.ubl.schema.xsd.contractawardnotice_21.ContractAwardNoticeType;
import oasis.names.specification.ubl.schema.xsd.contractnotice_21.ContractNoticeType;
import oasis.names.specification.ubl.schema.xsd.creditnote_21.CreditNoteType;
import oasis.names.specification.ubl.schema.xsd.debitnote_21.DebitNoteType;
import oasis.names.specification.ubl.schema.xsd.despatchadvice_21.DespatchAdviceType;
import oasis.names.specification.ubl.schema.xsd.documentstatus_21.DocumentStatusType;
import oasis.names.specification.ubl.schema.xsd.documentstatusrequest_21.DocumentStatusRequestType;
import oasis.names.specification.ubl.schema.xsd.exceptioncriteria_21.ExceptionCriteriaType;
import oasis.names.specification.ubl.schema.xsd.exceptionnotification_21.ExceptionNotificationType;
import oasis.names.specification.ubl.schema.xsd.forecast_21.ForecastType;
import oasis.names.specification.ubl.schema.xsd.forecastrevision_21.ForecastRevisionType;
import oasis.names.specification.ubl.schema.xsd.forwardinginstructions_21.ForwardingInstructionsType;
import oasis.names.specification.ubl.schema.xsd.freightinvoice_21.FreightInvoiceType;
import oasis.names.specification.ubl.schema.xsd.fulfilmentcancellation_21.FulfilmentCancellationType;
import oasis.names.specification.ubl.schema.xsd.goodsitemitinerary_21.GoodsItemItineraryType;
import oasis.names.specification.ubl.schema.xsd.guaranteecertificate_21.GuaranteeCertificateType;
import oasis.names.specification.ubl.schema.xsd.instructionforreturns_21.InstructionForReturnsType;
import oasis.names.specification.ubl.schema.xsd.inventoryreport_21.InventoryReportType;
import oasis.names.specification.ubl.schema.xsd.invoice_21.InvoiceType;
import oasis.names.specification.ubl.schema.xsd.iteminformationrequest_21.ItemInformationRequestType;
import oasis.names.specification.ubl.schema.xsd.order_21.OrderType;
import oasis.names.specification.ubl.schema.xsd.ordercancellation_21.OrderCancellationType;
import oasis.names.specification.ubl.schema.xsd.orderchange_21.OrderChangeType;
import oasis.names.specification.ubl.schema.xsd.orderresponse_21.OrderResponseType;
import oasis.names.specification.ubl.schema.xsd.orderresponsesimple_21.OrderResponseSimpleType;
import oasis.names.specification.ubl.schema.xsd.packinglist_21.PackingListType;
import oasis.names.specification.ubl.schema.xsd.priorinformationnotice_21.PriorInformationNoticeType;
import oasis.names.specification.ubl.schema.xsd.productactivity_21.ProductActivityType;
import oasis.names.specification.ubl.schema.xsd.quotation_21.QuotationType;
import oasis.names.specification.ubl.schema.xsd.receiptadvice_21.ReceiptAdviceType;
import oasis.names.specification.ubl.schema.xsd.reminder_21.ReminderType;
import oasis.names.specification.ubl.schema.xsd.remittanceadvice_21.RemittanceAdviceType;
import oasis.names.specification.ubl.schema.xsd.requestforquotation_21.RequestForQuotationType;
import oasis.names.specification.ubl.schema.xsd.retailevent_21.RetailEventType;
import oasis.names.specification.ubl.schema.xsd.selfbilledcreditnote_21.SelfBilledCreditNoteType;
import oasis.names.specification.ubl.schema.xsd.selfbilledinvoice_21.SelfBilledInvoiceType;
import oasis.names.specification.ubl.schema.xsd.statement_21.StatementType;
import oasis.names.specification.ubl.schema.xsd.stockavailabilityreport_21.StockAvailabilityReportType;
import oasis.names.specification.ubl.schema.xsd.tender_21.TenderType;
import oasis.names.specification.ubl.schema.xsd.tendererqualification_21.TendererQualificationType;
import oasis.names.specification.ubl.schema.xsd.tendererqualificationresponse_21.TendererQualificationResponseType;
import oasis.names.specification.ubl.schema.xsd.tenderreceipt_21.TenderReceiptType;
import oasis.names.specification.ubl.schema.xsd.tradeitemlocationprofile_21.TradeItemLocationProfileType;
import oasis.names.specification.ubl.schema.xsd.transportationstatus_21.TransportationStatusType;
import oasis.names.specification.ubl.schema.xsd.transportationstatusrequest_21.TransportationStatusRequestType;
import oasis.names.specification.ubl.schema.xsd.transportexecutionplan_21.TransportExecutionPlanType;
import oasis.names.specification.ubl.schema.xsd.transportexecutionplanrequest_21.TransportExecutionPlanRequestType;
import oasis.names.specification.ubl.schema.xsd.transportprogressstatus_21.TransportProgressStatusType;
import oasis.names.specification.ubl.schema.xsd.transportprogressstatusrequest_21.TransportProgressStatusRequestType;
import oasis.names.specification.ubl.schema.xsd.transportservicedescription_21.TransportServiceDescriptionType;
import oasis.names.specification.ubl.schema.xsd.transportservicedescriptionrequest_21.TransportServiceDescriptionRequestType;
import oasis.names.specification.ubl.schema.xsd.unawardednotification_21.UnawardedNotificationType;
import oasis.names.specification.ubl.schema.xsd.utilitystatement_21.UtilityStatementType;
import oasis.names.specification.ubl.schema.xsd.waybill_21.WaybillType;

import org.w3c.dom.Document;

import com.helger.commons.annotations.PresentForCodeCoverage;
import com.helger.commons.state.ESuccess;

/**
 * Write all UBL 2.1 document types.
 *
 * @author Philip Helger
 */
@NotThreadSafe
public final class UBL21Writer extends AbstractUBLDocumentMarshaller
{
  @PresentForCodeCoverage
  @SuppressWarnings ("unused")
  private static final UBL21Writer s_aInstance = new UBL21Writer ();

  private UBL21Writer ()
  {}

  /**
   * Convert the passed {@link ApplicationResponseType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aApplicationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeApplicationResponse (@Nonnull final ApplicationResponseType aApplicationResponse)
  {
    return writeApplicationResponse (aApplicationResponse, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ApplicationResponseType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aApplicationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeApplicationResponse (@Nonnull final ApplicationResponseType aApplicationResponse,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aApplicationResponse,
                                             EUBL21DocumentType.APPLICATION_RESPONSE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ApplicationResponseType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aApplicationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeApplicationResponse (@Nonnull final ApplicationResponseType aApplicationResponse,
                                                   @Nonnull final File aResult)
  {
    return writeApplicationResponse (aApplicationResponse, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ApplicationResponseType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aApplicationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeApplicationResponse (@Nonnull final ApplicationResponseType aApplicationResponse,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final File aResult)
  {
    return writeApplicationResponse (aApplicationResponse, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ApplicationResponseType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aApplicationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeApplicationResponse (@Nonnull final ApplicationResponseType aApplicationResponse,
                                                   @Nonnull final Result aResult)
  {
    return writeApplicationResponse (aApplicationResponse, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ApplicationResponseType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aApplicationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeApplicationResponse (@Nonnull final ApplicationResponseType aApplicationResponse,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aApplicationResponse,
                                             EUBL21DocumentType.APPLICATION_RESPONSE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link AttachedDocumentType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aAttachedDocument
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeAttachedDocument (@Nonnull final AttachedDocumentType aAttachedDocument)
  {
    return writeAttachedDocument (aAttachedDocument, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link AttachedDocumentType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aAttachedDocument
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeAttachedDocument (@Nonnull final AttachedDocumentType aAttachedDocument,
                                                @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aAttachedDocument,
                                             EUBL21DocumentType.ATTACHED_DOCUMENT,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link AttachedDocumentType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aAttachedDocument
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAttachedDocument (@Nonnull final AttachedDocumentType aAttachedDocument,
                                                @Nonnull final File aResult)
  {
    return writeAttachedDocument (aAttachedDocument, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link AttachedDocumentType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aAttachedDocument
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAttachedDocument (@Nonnull final AttachedDocumentType aAttachedDocument,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final File aResult)
  {
    return writeAttachedDocument (aAttachedDocument, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link AttachedDocumentType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aAttachedDocument
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAttachedDocument (@Nonnull final AttachedDocumentType aAttachedDocument,
                                                @Nonnull final Result aResult)
  {
    return writeAttachedDocument (aAttachedDocument, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link AttachedDocumentType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aAttachedDocument
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAttachedDocument (@Nonnull final AttachedDocumentType aAttachedDocument,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aAttachedDocument,
                                             EUBL21DocumentType.ATTACHED_DOCUMENT,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link AwardedNotificationType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aAwardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeAwardedNotification (@Nonnull final AwardedNotificationType aAwardedNotification)
  {
    return writeAwardedNotification (aAwardedNotification, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link AwardedNotificationType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aAwardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeAwardedNotification (@Nonnull final AwardedNotificationType aAwardedNotification,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aAwardedNotification,
                                             EUBL21DocumentType.AWARDED_NOTIFICATION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link AwardedNotificationType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aAwardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAwardedNotification (@Nonnull final AwardedNotificationType aAwardedNotification,
                                                   @Nonnull final File aResult)
  {
    return writeAwardedNotification (aAwardedNotification, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link AwardedNotificationType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aAwardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAwardedNotification (@Nonnull final AwardedNotificationType aAwardedNotification,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final File aResult)
  {
    return writeAwardedNotification (aAwardedNotification, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link AwardedNotificationType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aAwardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAwardedNotification (@Nonnull final AwardedNotificationType aAwardedNotification,
                                                   @Nonnull final Result aResult)
  {
    return writeAwardedNotification (aAwardedNotification, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link AwardedNotificationType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aAwardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeAwardedNotification (@Nonnull final AwardedNotificationType aAwardedNotification,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aAwardedNotification,
                                             EUBL21DocumentType.AWARDED_NOTIFICATION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link BillOfLadingType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aBillOfLading
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeBillOfLading (@Nonnull final BillOfLadingType aBillOfLading)
  {
    return writeBillOfLading (aBillOfLading, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link BillOfLadingType} to a DOM {@link Document} using
   * a custom validation event handler.
   * 
   * @param aBillOfLading
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeBillOfLading (@Nonnull final BillOfLadingType aBillOfLading,
                                            @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aBillOfLading, EUBL21DocumentType.BILL_OF_LADING, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link BillOfLadingType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aBillOfLading
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeBillOfLading (@Nonnull final BillOfLadingType aBillOfLading, @Nonnull final File aResult)
  {
    return writeBillOfLading (aBillOfLading, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link BillOfLadingType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aBillOfLading
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeBillOfLading (@Nonnull final BillOfLadingType aBillOfLading,
                                            @Nullable final ValidationEventHandler aCustomEventHandler,
                                            @Nonnull final File aResult)
  {
    return writeBillOfLading (aBillOfLading, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link BillOfLadingType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aBillOfLading
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeBillOfLading (@Nonnull final BillOfLadingType aBillOfLading, @Nonnull final Result aResult)
  {
    return writeBillOfLading (aBillOfLading, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link BillOfLadingType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aBillOfLading
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeBillOfLading (@Nonnull final BillOfLadingType aBillOfLading,
                                            @Nullable final ValidationEventHandler aCustomEventHandler,
                                            @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aBillOfLading,
                                             EUBL21DocumentType.BILL_OF_LADING,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link CallForTendersType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aCallForTenders
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCallForTenders (@Nonnull final CallForTendersType aCallForTenders)
  {
    return writeCallForTenders (aCallForTenders, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CallForTendersType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aCallForTenders
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCallForTenders (@Nonnull final CallForTendersType aCallForTenders,
                                              @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCallForTenders, EUBL21DocumentType.CALL_FOR_TENDERS, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CallForTendersType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aCallForTenders
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCallForTenders (@Nonnull final CallForTendersType aCallForTenders,
                                              @Nonnull final File aResult)
  {
    return writeCallForTenders (aCallForTenders, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CallForTendersType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aCallForTenders
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCallForTenders (@Nonnull final CallForTendersType aCallForTenders,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final File aResult)
  {
    return writeCallForTenders (aCallForTenders, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CallForTendersType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aCallForTenders
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCallForTenders (@Nonnull final CallForTendersType aCallForTenders,
                                              @Nonnull final Result aResult)
  {
    return writeCallForTenders (aCallForTenders, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link CallForTendersType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aCallForTenders
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCallForTenders (@Nonnull final CallForTendersType aCallForTenders,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCallForTenders,
                                             EUBL21DocumentType.CALL_FOR_TENDERS,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link CatalogueType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aCatalogue
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogue (@Nonnull final CatalogueType aCatalogue)
  {
    return writeCatalogue (aCatalogue, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CatalogueType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aCatalogue
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogue (@Nonnull final CatalogueType aCatalogue,
                                         @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogue, EUBL21DocumentType.CATALOGUE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CatalogueType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aCatalogue
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogue (@Nonnull final CatalogueType aCatalogue, @Nonnull final File aResult)
  {
    return writeCatalogue (aCatalogue, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aCatalogue
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogue (@Nonnull final CatalogueType aCatalogue,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final File aResult)
  {
    return writeCatalogue (aCatalogue, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aCatalogue
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogue (@Nonnull final CatalogueType aCatalogue, @Nonnull final Result aResult)
  {
    return writeCatalogue (aCatalogue, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link CatalogueType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aCatalogue
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogue (@Nonnull final CatalogueType aCatalogue,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogue, EUBL21DocumentType.CATALOGUE, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link CatalogueDeletionType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aCatalogueDeletion
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogueDeletion (@Nonnull final CatalogueDeletionType aCatalogueDeletion)
  {
    return writeCatalogueDeletion (aCatalogueDeletion, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CatalogueDeletionType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aCatalogueDeletion
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogueDeletion (@Nonnull final CatalogueDeletionType aCatalogueDeletion,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogueDeletion,
                                             EUBL21DocumentType.CATALOGUE_DELETION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CatalogueDeletionType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aCatalogueDeletion
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueDeletion (@Nonnull final CatalogueDeletionType aCatalogueDeletion,
                                                 @Nonnull final File aResult)
  {
    return writeCatalogueDeletion (aCatalogueDeletion, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueDeletionType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aCatalogueDeletion
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueDeletion (@Nonnull final CatalogueDeletionType aCatalogueDeletion,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final File aResult)
  {
    return writeCatalogueDeletion (aCatalogueDeletion, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueDeletionType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aCatalogueDeletion
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueDeletion (@Nonnull final CatalogueDeletionType aCatalogueDeletion,
                                                 @Nonnull final Result aResult)
  {
    return writeCatalogueDeletion (aCatalogueDeletion, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link CatalogueDeletionType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aCatalogueDeletion
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueDeletion (@Nonnull final CatalogueDeletionType aCatalogueDeletion,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogueDeletion,
                                             EUBL21DocumentType.CATALOGUE_DELETION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link CatalogueItemSpecificationUpdateType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aCatalogueItemSpecificationUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogueItemSpecificationUpdate (@Nonnull final CatalogueItemSpecificationUpdateType aCatalogueItemSpecificationUpdate)
  {
    return writeCatalogueItemSpecificationUpdate (aCatalogueItemSpecificationUpdate, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CatalogueItemSpecificationUpdateType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aCatalogueItemSpecificationUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogueItemSpecificationUpdate (@Nonnull final CatalogueItemSpecificationUpdateType aCatalogueItemSpecificationUpdate,
                                                                @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogueItemSpecificationUpdate,
                                             EUBL21DocumentType.CATALOGUE_ITEM_SPECIFICATION_UPDATE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CatalogueItemSpecificationUpdateType} to a
   * {@link File} using the global validation event handler.
   * 
   * @param aCatalogueItemSpecificationUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueItemSpecificationUpdate (@Nonnull final CatalogueItemSpecificationUpdateType aCatalogueItemSpecificationUpdate,
                                                                @Nonnull final File aResult)
  {
    return writeCatalogueItemSpecificationUpdate (aCatalogueItemSpecificationUpdate, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueItemSpecificationUpdateType} to a
   * {@link File} using a custom validation event handler.
   * 
   * @param aCatalogueItemSpecificationUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueItemSpecificationUpdate (@Nonnull final CatalogueItemSpecificationUpdateType aCatalogueItemSpecificationUpdate,
                                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                                @Nonnull final File aResult)
  {
    return writeCatalogueItemSpecificationUpdate (aCatalogueItemSpecificationUpdate,
                                                  aCustomEventHandler,
                                                  new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueItemSpecificationUpdateType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aCatalogueItemSpecificationUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueItemSpecificationUpdate (@Nonnull final CatalogueItemSpecificationUpdateType aCatalogueItemSpecificationUpdate,
                                                                @Nonnull final Result aResult)
  {
    return writeCatalogueItemSpecificationUpdate (aCatalogueItemSpecificationUpdate,
                                                  getGlobalValidationEventHandler (),
                                                  aResult);
  }

  /**
   * Convert the passed {@link CatalogueItemSpecificationUpdateType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aCatalogueItemSpecificationUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueItemSpecificationUpdate (@Nonnull final CatalogueItemSpecificationUpdateType aCatalogueItemSpecificationUpdate,
                                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                                @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogueItemSpecificationUpdate,
                                             EUBL21DocumentType.CATALOGUE_ITEM_SPECIFICATION_UPDATE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link CataloguePricingUpdateType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aCataloguePricingUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCataloguePricingUpdate (@Nonnull final CataloguePricingUpdateType aCataloguePricingUpdate)
  {
    return writeCataloguePricingUpdate (aCataloguePricingUpdate, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CataloguePricingUpdateType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aCataloguePricingUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCataloguePricingUpdate (@Nonnull final CataloguePricingUpdateType aCataloguePricingUpdate,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCataloguePricingUpdate,
                                             EUBL21DocumentType.CATALOGUE_PRICING_UPDATE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CataloguePricingUpdateType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aCataloguePricingUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCataloguePricingUpdate (@Nonnull final CataloguePricingUpdateType aCataloguePricingUpdate,
                                                      @Nonnull final File aResult)
  {
    return writeCataloguePricingUpdate (aCataloguePricingUpdate, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CataloguePricingUpdateType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aCataloguePricingUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCataloguePricingUpdate (@Nonnull final CataloguePricingUpdateType aCataloguePricingUpdate,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final File aResult)
  {
    return writeCataloguePricingUpdate (aCataloguePricingUpdate, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CataloguePricingUpdateType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aCataloguePricingUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCataloguePricingUpdate (@Nonnull final CataloguePricingUpdateType aCataloguePricingUpdate,
                                                      @Nonnull final Result aResult)
  {
    return writeCataloguePricingUpdate (aCataloguePricingUpdate, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link CataloguePricingUpdateType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aCataloguePricingUpdate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCataloguePricingUpdate (@Nonnull final CataloguePricingUpdateType aCataloguePricingUpdate,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCataloguePricingUpdate,
                                             EUBL21DocumentType.CATALOGUE_PRICING_UPDATE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link CatalogueRequestType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aCatalogueRequest
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogueRequest (@Nonnull final CatalogueRequestType aCatalogueRequest)
  {
    return writeCatalogueRequest (aCatalogueRequest, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CatalogueRequestType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aCatalogueRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCatalogueRequest (@Nonnull final CatalogueRequestType aCatalogueRequest,
                                                @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogueRequest,
                                             EUBL21DocumentType.CATALOGUE_REQUEST,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CatalogueRequestType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aCatalogueRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueRequest (@Nonnull final CatalogueRequestType aCatalogueRequest,
                                                @Nonnull final File aResult)
  {
    return writeCatalogueRequest (aCatalogueRequest, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueRequestType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aCatalogueRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueRequest (@Nonnull final CatalogueRequestType aCatalogueRequest,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final File aResult)
  {
    return writeCatalogueRequest (aCatalogueRequest, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CatalogueRequestType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aCatalogueRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueRequest (@Nonnull final CatalogueRequestType aCatalogueRequest,
                                                @Nonnull final Result aResult)
  {
    return writeCatalogueRequest (aCatalogueRequest, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link CatalogueRequestType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aCatalogueRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCatalogueRequest (@Nonnull final CatalogueRequestType aCatalogueRequest,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCatalogueRequest,
                                             EUBL21DocumentType.CATALOGUE_REQUEST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link CertificateOfOriginType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aCertificateOfOrigin
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCertificateOfOrigin (@Nonnull final CertificateOfOriginType aCertificateOfOrigin)
  {
    return writeCertificateOfOrigin (aCertificateOfOrigin, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CertificateOfOriginType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aCertificateOfOrigin
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCertificateOfOrigin (@Nonnull final CertificateOfOriginType aCertificateOfOrigin,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCertificateOfOrigin,
                                             EUBL21DocumentType.CERTIFICATE_OF_ORIGIN,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CertificateOfOriginType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aCertificateOfOrigin
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCertificateOfOrigin (@Nonnull final CertificateOfOriginType aCertificateOfOrigin,
                                                   @Nonnull final File aResult)
  {
    return writeCertificateOfOrigin (aCertificateOfOrigin, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CertificateOfOriginType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aCertificateOfOrigin
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCertificateOfOrigin (@Nonnull final CertificateOfOriginType aCertificateOfOrigin,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final File aResult)
  {
    return writeCertificateOfOrigin (aCertificateOfOrigin, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CertificateOfOriginType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aCertificateOfOrigin
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCertificateOfOrigin (@Nonnull final CertificateOfOriginType aCertificateOfOrigin,
                                                   @Nonnull final Result aResult)
  {
    return writeCertificateOfOrigin (aCertificateOfOrigin, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link CertificateOfOriginType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aCertificateOfOrigin
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCertificateOfOrigin (@Nonnull final CertificateOfOriginType aCertificateOfOrigin,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCertificateOfOrigin,
                                             EUBL21DocumentType.CERTIFICATE_OF_ORIGIN,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ContractAwardNoticeType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aContractAwardNotice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeContractAwardNotice (@Nonnull final ContractAwardNoticeType aContractAwardNotice)
  {
    return writeContractAwardNotice (aContractAwardNotice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ContractAwardNoticeType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aContractAwardNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeContractAwardNotice (@Nonnull final ContractAwardNoticeType aContractAwardNotice,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aContractAwardNotice,
                                             EUBL21DocumentType.CONTRACT_AWARD_NOTICE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ContractAwardNoticeType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aContractAwardNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractAwardNotice (@Nonnull final ContractAwardNoticeType aContractAwardNotice,
                                                   @Nonnull final File aResult)
  {
    return writeContractAwardNotice (aContractAwardNotice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ContractAwardNoticeType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aContractAwardNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractAwardNotice (@Nonnull final ContractAwardNoticeType aContractAwardNotice,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final File aResult)
  {
    return writeContractAwardNotice (aContractAwardNotice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ContractAwardNoticeType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aContractAwardNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractAwardNotice (@Nonnull final ContractAwardNoticeType aContractAwardNotice,
                                                   @Nonnull final Result aResult)
  {
    return writeContractAwardNotice (aContractAwardNotice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ContractAwardNoticeType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aContractAwardNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractAwardNotice (@Nonnull final ContractAwardNoticeType aContractAwardNotice,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aContractAwardNotice,
                                             EUBL21DocumentType.CONTRACT_AWARD_NOTICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ContractNoticeType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aContractNotice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeContractNotice (@Nonnull final ContractNoticeType aContractNotice)
  {
    return writeContractNotice (aContractNotice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ContractNoticeType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aContractNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeContractNotice (@Nonnull final ContractNoticeType aContractNotice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aContractNotice, EUBL21DocumentType.CONTRACT_NOTICE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ContractNoticeType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aContractNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractNotice (@Nonnull final ContractNoticeType aContractNotice,
                                              @Nonnull final File aResult)
  {
    return writeContractNotice (aContractNotice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ContractNoticeType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aContractNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractNotice (@Nonnull final ContractNoticeType aContractNotice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final File aResult)
  {
    return writeContractNotice (aContractNotice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ContractNoticeType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aContractNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractNotice (@Nonnull final ContractNoticeType aContractNotice,
                                              @Nonnull final Result aResult)
  {
    return writeContractNotice (aContractNotice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ContractNoticeType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aContractNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeContractNotice (@Nonnull final ContractNoticeType aContractNotice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aContractNotice,
                                             EUBL21DocumentType.CONTRACT_NOTICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link CreditNoteType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCreditNote (@Nonnull final CreditNoteType aCreditNote)
  {
    return writeCreditNote (aCreditNote, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link CreditNoteType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeCreditNote (@Nonnull final CreditNoteType aCreditNote,
                                          @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aCreditNote, EUBL21DocumentType.CREDIT_NOTE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link CreditNoteType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCreditNote (@Nonnull final CreditNoteType aCreditNote, @Nonnull final File aResult)
  {
    return writeCreditNote (aCreditNote, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CreditNoteType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCreditNote (@Nonnull final CreditNoteType aCreditNote,
                                          @Nullable final ValidationEventHandler aCustomEventHandler,
                                          @Nonnull final File aResult)
  {
    return writeCreditNote (aCreditNote, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link CreditNoteType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCreditNote (@Nonnull final CreditNoteType aCreditNote, @Nonnull final Result aResult)
  {
    return writeCreditNote (aCreditNote, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link CreditNoteType} to a custom {@link Result} using
   * a custom validation event handler.
   * 
   * @param aCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeCreditNote (@Nonnull final CreditNoteType aCreditNote,
                                          @Nullable final ValidationEventHandler aCustomEventHandler,
                                          @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aCreditNote, EUBL21DocumentType.CREDIT_NOTE, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link DebitNoteType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aDebitNote
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDebitNote (@Nonnull final DebitNoteType aDebitNote)
  {
    return writeDebitNote (aDebitNote, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link DebitNoteType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aDebitNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDebitNote (@Nonnull final DebitNoteType aDebitNote,
                                         @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aDebitNote, EUBL21DocumentType.DEBIT_NOTE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link DebitNoteType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aDebitNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDebitNote (@Nonnull final DebitNoteType aDebitNote, @Nonnull final File aResult)
  {
    return writeDebitNote (aDebitNote, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DebitNoteType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aDebitNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDebitNote (@Nonnull final DebitNoteType aDebitNote,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final File aResult)
  {
    return writeDebitNote (aDebitNote, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DebitNoteType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aDebitNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDebitNote (@Nonnull final DebitNoteType aDebitNote, @Nonnull final Result aResult)
  {
    return writeDebitNote (aDebitNote, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link DebitNoteType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aDebitNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDebitNote (@Nonnull final DebitNoteType aDebitNote,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aDebitNote, EUBL21DocumentType.DEBIT_NOTE, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link DespatchAdviceType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aDespatchAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDespatchAdvice (@Nonnull final DespatchAdviceType aDespatchAdvice)
  {
    return writeDespatchAdvice (aDespatchAdvice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link DespatchAdviceType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aDespatchAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDespatchAdvice (@Nonnull final DespatchAdviceType aDespatchAdvice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aDespatchAdvice, EUBL21DocumentType.DESPATCH_ADVICE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link DespatchAdviceType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aDespatchAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDespatchAdvice (@Nonnull final DespatchAdviceType aDespatchAdvice,
                                              @Nonnull final File aResult)
  {
    return writeDespatchAdvice (aDespatchAdvice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DespatchAdviceType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aDespatchAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDespatchAdvice (@Nonnull final DespatchAdviceType aDespatchAdvice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final File aResult)
  {
    return writeDespatchAdvice (aDespatchAdvice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DespatchAdviceType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aDespatchAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDespatchAdvice (@Nonnull final DespatchAdviceType aDespatchAdvice,
                                              @Nonnull final Result aResult)
  {
    return writeDespatchAdvice (aDespatchAdvice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link DespatchAdviceType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aDespatchAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDespatchAdvice (@Nonnull final DespatchAdviceType aDespatchAdvice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aDespatchAdvice,
                                             EUBL21DocumentType.DESPATCH_ADVICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link DocumentStatusType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aDocumentStatus
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDocumentStatus (@Nonnull final DocumentStatusType aDocumentStatus)
  {
    return writeDocumentStatus (aDocumentStatus, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link DocumentStatusType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aDocumentStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDocumentStatus (@Nonnull final DocumentStatusType aDocumentStatus,
                                              @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aDocumentStatus, EUBL21DocumentType.DOCUMENT_STATUS, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link DocumentStatusType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aDocumentStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatus (@Nonnull final DocumentStatusType aDocumentStatus,
                                              @Nonnull final File aResult)
  {
    return writeDocumentStatus (aDocumentStatus, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DocumentStatusType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aDocumentStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatus (@Nonnull final DocumentStatusType aDocumentStatus,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final File aResult)
  {
    return writeDocumentStatus (aDocumentStatus, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DocumentStatusType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aDocumentStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatus (@Nonnull final DocumentStatusType aDocumentStatus,
                                              @Nonnull final Result aResult)
  {
    return writeDocumentStatus (aDocumentStatus, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link DocumentStatusType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aDocumentStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatus (@Nonnull final DocumentStatusType aDocumentStatus,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aDocumentStatus,
                                             EUBL21DocumentType.DOCUMENT_STATUS,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link DocumentStatusRequestType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aDocumentStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDocumentStatusRequest (@Nonnull final DocumentStatusRequestType aDocumentStatusRequest)
  {
    return writeDocumentStatusRequest (aDocumentStatusRequest, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link DocumentStatusRequestType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aDocumentStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeDocumentStatusRequest (@Nonnull final DocumentStatusRequestType aDocumentStatusRequest,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aDocumentStatusRequest,
                                             EUBL21DocumentType.DOCUMENT_STATUS_REQUEST,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link DocumentStatusRequestType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aDocumentStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatusRequest (@Nonnull final DocumentStatusRequestType aDocumentStatusRequest,
                                                     @Nonnull final File aResult)
  {
    return writeDocumentStatusRequest (aDocumentStatusRequest, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DocumentStatusRequestType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aDocumentStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatusRequest (@Nonnull final DocumentStatusRequestType aDocumentStatusRequest,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final File aResult)
  {
    return writeDocumentStatusRequest (aDocumentStatusRequest, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link DocumentStatusRequestType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aDocumentStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatusRequest (@Nonnull final DocumentStatusRequestType aDocumentStatusRequest,
                                                     @Nonnull final Result aResult)
  {
    return writeDocumentStatusRequest (aDocumentStatusRequest, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link DocumentStatusRequestType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aDocumentStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeDocumentStatusRequest (@Nonnull final DocumentStatusRequestType aDocumentStatusRequest,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aDocumentStatusRequest,
                                             EUBL21DocumentType.DOCUMENT_STATUS_REQUEST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ExceptionCriteriaType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aExceptionCriteria
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeExceptionCriteria (@Nonnull final ExceptionCriteriaType aExceptionCriteria)
  {
    return writeExceptionCriteria (aExceptionCriteria, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ExceptionCriteriaType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aExceptionCriteria
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeExceptionCriteria (@Nonnull final ExceptionCriteriaType aExceptionCriteria,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aExceptionCriteria,
                                             EUBL21DocumentType.EXCEPTION_CRITERIA,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ExceptionCriteriaType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aExceptionCriteria
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionCriteria (@Nonnull final ExceptionCriteriaType aExceptionCriteria,
                                                 @Nonnull final File aResult)
  {
    return writeExceptionCriteria (aExceptionCriteria, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ExceptionCriteriaType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aExceptionCriteria
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionCriteria (@Nonnull final ExceptionCriteriaType aExceptionCriteria,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final File aResult)
  {
    return writeExceptionCriteria (aExceptionCriteria, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ExceptionCriteriaType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aExceptionCriteria
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionCriteria (@Nonnull final ExceptionCriteriaType aExceptionCriteria,
                                                 @Nonnull final Result aResult)
  {
    return writeExceptionCriteria (aExceptionCriteria, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ExceptionCriteriaType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aExceptionCriteria
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionCriteria (@Nonnull final ExceptionCriteriaType aExceptionCriteria,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aExceptionCriteria,
                                             EUBL21DocumentType.EXCEPTION_CRITERIA,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ExceptionNotificationType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aExceptionNotification
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeExceptionNotification (@Nonnull final ExceptionNotificationType aExceptionNotification)
  {
    return writeExceptionNotification (aExceptionNotification, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ExceptionNotificationType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aExceptionNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeExceptionNotification (@Nonnull final ExceptionNotificationType aExceptionNotification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aExceptionNotification,
                                             EUBL21DocumentType.EXCEPTION_NOTIFICATION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ExceptionNotificationType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aExceptionNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionNotification (@Nonnull final ExceptionNotificationType aExceptionNotification,
                                                     @Nonnull final File aResult)
  {
    return writeExceptionNotification (aExceptionNotification, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ExceptionNotificationType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aExceptionNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionNotification (@Nonnull final ExceptionNotificationType aExceptionNotification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final File aResult)
  {
    return writeExceptionNotification (aExceptionNotification, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ExceptionNotificationType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aExceptionNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionNotification (@Nonnull final ExceptionNotificationType aExceptionNotification,
                                                     @Nonnull final Result aResult)
  {
    return writeExceptionNotification (aExceptionNotification, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ExceptionNotificationType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aExceptionNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeExceptionNotification (@Nonnull final ExceptionNotificationType aExceptionNotification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aExceptionNotification,
                                             EUBL21DocumentType.EXCEPTION_NOTIFICATION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ForecastType} to a DOM {@link Document} using the
   * global validation event handler.
   * 
   * @param aForecast
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeForecast (@Nonnull final ForecastType aForecast)
  {
    return writeForecast (aForecast, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ForecastType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aForecast
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeForecast (@Nonnull final ForecastType aForecast,
                                        @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aForecast, EUBL21DocumentType.FORECAST, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ForecastType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aForecast
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecast (@Nonnull final ForecastType aForecast, @Nonnull final File aResult)
  {
    return writeForecast (aForecast, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ForecastType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aForecast
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecast (@Nonnull final ForecastType aForecast,
                                        @Nullable final ValidationEventHandler aCustomEventHandler,
                                        @Nonnull final File aResult)
  {
    return writeForecast (aForecast, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ForecastType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aForecast
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecast (@Nonnull final ForecastType aForecast, @Nonnull final Result aResult)
  {
    return writeForecast (aForecast, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ForecastType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aForecast
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecast (@Nonnull final ForecastType aForecast,
                                        @Nullable final ValidationEventHandler aCustomEventHandler,
                                        @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aForecast, EUBL21DocumentType.FORECAST, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link ForecastRevisionType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aForecastRevision
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeForecastRevision (@Nonnull final ForecastRevisionType aForecastRevision)
  {
    return writeForecastRevision (aForecastRevision, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ForecastRevisionType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aForecastRevision
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeForecastRevision (@Nonnull final ForecastRevisionType aForecastRevision,
                                                @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aForecastRevision,
                                             EUBL21DocumentType.FORECAST_REVISION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ForecastRevisionType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aForecastRevision
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecastRevision (@Nonnull final ForecastRevisionType aForecastRevision,
                                                @Nonnull final File aResult)
  {
    return writeForecastRevision (aForecastRevision, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ForecastRevisionType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aForecastRevision
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecastRevision (@Nonnull final ForecastRevisionType aForecastRevision,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final File aResult)
  {
    return writeForecastRevision (aForecastRevision, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ForecastRevisionType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aForecastRevision
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecastRevision (@Nonnull final ForecastRevisionType aForecastRevision,
                                                @Nonnull final Result aResult)
  {
    return writeForecastRevision (aForecastRevision, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ForecastRevisionType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aForecastRevision
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForecastRevision (@Nonnull final ForecastRevisionType aForecastRevision,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aForecastRevision,
                                             EUBL21DocumentType.FORECAST_REVISION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ForwardingInstructionsType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aForwardingInstructions
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeForwardingInstructions (@Nonnull final ForwardingInstructionsType aForwardingInstructions)
  {
    return writeForwardingInstructions (aForwardingInstructions, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ForwardingInstructionsType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aForwardingInstructions
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeForwardingInstructions (@Nonnull final ForwardingInstructionsType aForwardingInstructions,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aForwardingInstructions,
                                             EUBL21DocumentType.FORWARDING_INSTRUCTIONS,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ForwardingInstructionsType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aForwardingInstructions
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForwardingInstructions (@Nonnull final ForwardingInstructionsType aForwardingInstructions,
                                                      @Nonnull final File aResult)
  {
    return writeForwardingInstructions (aForwardingInstructions, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ForwardingInstructionsType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aForwardingInstructions
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForwardingInstructions (@Nonnull final ForwardingInstructionsType aForwardingInstructions,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final File aResult)
  {
    return writeForwardingInstructions (aForwardingInstructions, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ForwardingInstructionsType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aForwardingInstructions
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForwardingInstructions (@Nonnull final ForwardingInstructionsType aForwardingInstructions,
                                                      @Nonnull final Result aResult)
  {
    return writeForwardingInstructions (aForwardingInstructions, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ForwardingInstructionsType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aForwardingInstructions
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeForwardingInstructions (@Nonnull final ForwardingInstructionsType aForwardingInstructions,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aForwardingInstructions,
                                             EUBL21DocumentType.FORWARDING_INSTRUCTIONS,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link FreightInvoiceType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aFreightInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeFreightInvoice (@Nonnull final FreightInvoiceType aFreightInvoice)
  {
    return writeFreightInvoice (aFreightInvoice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link FreightInvoiceType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aFreightInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeFreightInvoice (@Nonnull final FreightInvoiceType aFreightInvoice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aFreightInvoice, EUBL21DocumentType.FREIGHT_INVOICE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link FreightInvoiceType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aFreightInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFreightInvoice (@Nonnull final FreightInvoiceType aFreightInvoice,
                                              @Nonnull final File aResult)
  {
    return writeFreightInvoice (aFreightInvoice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link FreightInvoiceType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aFreightInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFreightInvoice (@Nonnull final FreightInvoiceType aFreightInvoice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final File aResult)
  {
    return writeFreightInvoice (aFreightInvoice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link FreightInvoiceType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aFreightInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFreightInvoice (@Nonnull final FreightInvoiceType aFreightInvoice,
                                              @Nonnull final Result aResult)
  {
    return writeFreightInvoice (aFreightInvoice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link FreightInvoiceType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aFreightInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFreightInvoice (@Nonnull final FreightInvoiceType aFreightInvoice,
                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                              @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aFreightInvoice,
                                             EUBL21DocumentType.FREIGHT_INVOICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link FulfilmentCancellationType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aFulfilmentCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeFulfilmentCancellation (@Nonnull final FulfilmentCancellationType aFulfilmentCancellation)
  {
    return writeFulfilmentCancellation (aFulfilmentCancellation, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link FulfilmentCancellationType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aFulfilmentCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeFulfilmentCancellation (@Nonnull final FulfilmentCancellationType aFulfilmentCancellation,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aFulfilmentCancellation,
                                             EUBL21DocumentType.FULFILMENT_CANCELATION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link FulfilmentCancellationType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aFulfilmentCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFulfilmentCancellation (@Nonnull final FulfilmentCancellationType aFulfilmentCancellation,
                                                      @Nonnull final File aResult)
  {
    return writeFulfilmentCancellation (aFulfilmentCancellation, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link FulfilmentCancellationType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aFulfilmentCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFulfilmentCancellation (@Nonnull final FulfilmentCancellationType aFulfilmentCancellation,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final File aResult)
  {
    return writeFulfilmentCancellation (aFulfilmentCancellation, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link FulfilmentCancellationType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aFulfilmentCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFulfilmentCancellation (@Nonnull final FulfilmentCancellationType aFulfilmentCancellation,
                                                      @Nonnull final Result aResult)
  {
    return writeFulfilmentCancellation (aFulfilmentCancellation, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link FulfilmentCancellationType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aFulfilmentCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeFulfilmentCancellation (@Nonnull final FulfilmentCancellationType aFulfilmentCancellation,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aFulfilmentCancellation,
                                             EUBL21DocumentType.FULFILMENT_CANCELATION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link GoodsItemItineraryType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aGoodsItemItinerary
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeGoodsItemItinerary (@Nonnull final GoodsItemItineraryType aGoodsItemItinerary)
  {
    return writeGoodsItemItinerary (aGoodsItemItinerary, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link GoodsItemItineraryType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aGoodsItemItinerary
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeGoodsItemItinerary (@Nonnull final GoodsItemItineraryType aGoodsItemItinerary,
                                                  @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aGoodsItemItinerary,
                                             EUBL21DocumentType.GOODS_ITEM_ITINERARY,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link GoodsItemItineraryType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aGoodsItemItinerary
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGoodsItemItinerary (@Nonnull final GoodsItemItineraryType aGoodsItemItinerary,
                                                  @Nonnull final File aResult)
  {
    return writeGoodsItemItinerary (aGoodsItemItinerary, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link GoodsItemItineraryType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aGoodsItemItinerary
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGoodsItemItinerary (@Nonnull final GoodsItemItineraryType aGoodsItemItinerary,
                                                  @Nullable final ValidationEventHandler aCustomEventHandler,
                                                  @Nonnull final File aResult)
  {
    return writeGoodsItemItinerary (aGoodsItemItinerary, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link GoodsItemItineraryType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aGoodsItemItinerary
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGoodsItemItinerary (@Nonnull final GoodsItemItineraryType aGoodsItemItinerary,
                                                  @Nonnull final Result aResult)
  {
    return writeGoodsItemItinerary (aGoodsItemItinerary, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link GoodsItemItineraryType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aGoodsItemItinerary
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGoodsItemItinerary (@Nonnull final GoodsItemItineraryType aGoodsItemItinerary,
                                                  @Nullable final ValidationEventHandler aCustomEventHandler,
                                                  @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aGoodsItemItinerary,
                                             EUBL21DocumentType.GOODS_ITEM_ITINERARY,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link GuaranteeCertificateType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aGuaranteeCertificate
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeGuaranteeCertificate (@Nonnull final GuaranteeCertificateType aGuaranteeCertificate)
  {
    return writeGuaranteeCertificate (aGuaranteeCertificate, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link GuaranteeCertificateType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aGuaranteeCertificate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeGuaranteeCertificate (@Nonnull final GuaranteeCertificateType aGuaranteeCertificate,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aGuaranteeCertificate,
                                             EUBL21DocumentType.GUARANTEE_CERTIFICATE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link GuaranteeCertificateType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aGuaranteeCertificate
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGuaranteeCertificate (@Nonnull final GuaranteeCertificateType aGuaranteeCertificate,
                                                    @Nonnull final File aResult)
  {
    return writeGuaranteeCertificate (aGuaranteeCertificate, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link GuaranteeCertificateType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aGuaranteeCertificate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGuaranteeCertificate (@Nonnull final GuaranteeCertificateType aGuaranteeCertificate,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler,
                                                    @Nonnull final File aResult)
  {
    return writeGuaranteeCertificate (aGuaranteeCertificate, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link GuaranteeCertificateType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aGuaranteeCertificate
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGuaranteeCertificate (@Nonnull final GuaranteeCertificateType aGuaranteeCertificate,
                                                    @Nonnull final Result aResult)
  {
    return writeGuaranteeCertificate (aGuaranteeCertificate, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link GuaranteeCertificateType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aGuaranteeCertificate
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeGuaranteeCertificate (@Nonnull final GuaranteeCertificateType aGuaranteeCertificate,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler,
                                                    @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aGuaranteeCertificate,
                                             EUBL21DocumentType.GUARANTEE_CERTIFICATE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link InstructionForReturnsType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aInstructionForReturns
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeInstructionForReturns (@Nonnull final InstructionForReturnsType aInstructionForReturns)
  {
    return writeInstructionForReturns (aInstructionForReturns, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link InstructionForReturnsType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aInstructionForReturns
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeInstructionForReturns (@Nonnull final InstructionForReturnsType aInstructionForReturns,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aInstructionForReturns,
                                             EUBL21DocumentType.INSTRUCTION_FOR_RETURNS,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link InstructionForReturnsType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aInstructionForReturns
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInstructionForReturns (@Nonnull final InstructionForReturnsType aInstructionForReturns,
                                                     @Nonnull final File aResult)
  {
    return writeInstructionForReturns (aInstructionForReturns, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link InstructionForReturnsType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aInstructionForReturns
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInstructionForReturns (@Nonnull final InstructionForReturnsType aInstructionForReturns,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final File aResult)
  {
    return writeInstructionForReturns (aInstructionForReturns, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link InstructionForReturnsType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aInstructionForReturns
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInstructionForReturns (@Nonnull final InstructionForReturnsType aInstructionForReturns,
                                                     @Nonnull final Result aResult)
  {
    return writeInstructionForReturns (aInstructionForReturns, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link InstructionForReturnsType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aInstructionForReturns
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInstructionForReturns (@Nonnull final InstructionForReturnsType aInstructionForReturns,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aInstructionForReturns,
                                             EUBL21DocumentType.INSTRUCTION_FOR_RETURNS,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link InventoryReportType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aInventoryReport
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeInventoryReport (@Nonnull final InventoryReportType aInventoryReport)
  {
    return writeInventoryReport (aInventoryReport, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link InventoryReportType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aInventoryReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeInventoryReport (@Nonnull final InventoryReportType aInventoryReport,
                                               @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aInventoryReport, EUBL21DocumentType.INVENTORY_REPORT, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link InventoryReportType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aInventoryReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInventoryReport (@Nonnull final InventoryReportType aInventoryReport,
                                               @Nonnull final File aResult)
  {
    return writeInventoryReport (aInventoryReport, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link InventoryReportType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aInventoryReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInventoryReport (@Nonnull final InventoryReportType aInventoryReport,
                                               @Nullable final ValidationEventHandler aCustomEventHandler,
                                               @Nonnull final File aResult)
  {
    return writeInventoryReport (aInventoryReport, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link InventoryReportType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aInventoryReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInventoryReport (@Nonnull final InventoryReportType aInventoryReport,
                                               @Nonnull final Result aResult)
  {
    return writeInventoryReport (aInventoryReport, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link InventoryReportType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aInventoryReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInventoryReport (@Nonnull final InventoryReportType aInventoryReport,
                                               @Nullable final ValidationEventHandler aCustomEventHandler,
                                               @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aInventoryReport,
                                             EUBL21DocumentType.INVENTORY_REPORT,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link InvoiceType} to a DOM {@link Document} using the
   * global validation event handler.
   * 
   * @param aInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeInvoice (@Nonnull final InvoiceType aInvoice)
  {
    return writeInvoice (aInvoice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link InvoiceType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeInvoice (@Nonnull final InvoiceType aInvoice,
                                       @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aInvoice, EUBL21DocumentType.INVOICE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link InvoiceType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInvoice (@Nonnull final InvoiceType aInvoice, @Nonnull final File aResult)
  {
    return writeInvoice (aInvoice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link InvoiceType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInvoice (@Nonnull final InvoiceType aInvoice,
                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                       @Nonnull final File aResult)
  {
    return writeInvoice (aInvoice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link InvoiceType} to a custom {@link Result} using the
   * global validation event handler.
   * 
   * @param aInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInvoice (@Nonnull final InvoiceType aInvoice, @Nonnull final Result aResult)
  {
    return writeInvoice (aInvoice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link InvoiceType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeInvoice (@Nonnull final InvoiceType aInvoice,
                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                       @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aInvoice, EUBL21DocumentType.INVOICE, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link ItemInformationRequestType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aItemInformationRequest
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeItemInformationRequest (@Nonnull final ItemInformationRequestType aItemInformationRequest)
  {
    return writeItemInformationRequest (aItemInformationRequest, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ItemInformationRequestType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aItemInformationRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeItemInformationRequest (@Nonnull final ItemInformationRequestType aItemInformationRequest,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aItemInformationRequest,
                                             EUBL21DocumentType.ITEM_INFORMATION_REQUEST,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ItemInformationRequestType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aItemInformationRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeItemInformationRequest (@Nonnull final ItemInformationRequestType aItemInformationRequest,
                                                      @Nonnull final File aResult)
  {
    return writeItemInformationRequest (aItemInformationRequest, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ItemInformationRequestType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aItemInformationRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeItemInformationRequest (@Nonnull final ItemInformationRequestType aItemInformationRequest,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final File aResult)
  {
    return writeItemInformationRequest (aItemInformationRequest, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ItemInformationRequestType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aItemInformationRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeItemInformationRequest (@Nonnull final ItemInformationRequestType aItemInformationRequest,
                                                      @Nonnull final Result aResult)
  {
    return writeItemInformationRequest (aItemInformationRequest, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ItemInformationRequestType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aItemInformationRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeItemInformationRequest (@Nonnull final ItemInformationRequestType aItemInformationRequest,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aItemInformationRequest,
                                             EUBL21DocumentType.ITEM_INFORMATION_REQUEST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link OrderType} to a DOM {@link Document} using the
   * global validation event handler.
   * 
   * @param aOrder
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrder (@Nonnull final OrderType aOrder)
  {
    return writeOrder (aOrder, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link OrderType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aOrder
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrder (@Nonnull final OrderType aOrder,
                                     @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aOrder, EUBL21DocumentType.ORDER, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link OrderType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aOrder
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrder (@Nonnull final OrderType aOrder, @Nonnull final File aResult)
  {
    return writeOrder (aOrder, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aOrder
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrder (@Nonnull final OrderType aOrder,
                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                     @Nonnull final File aResult)
  {
    return writeOrder (aOrder, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderType} to a custom {@link Result} using the
   * global validation event handler.
   * 
   * @param aOrder
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrder (@Nonnull final OrderType aOrder, @Nonnull final Result aResult)
  {
    return writeOrder (aOrder, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link OrderType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aOrder
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrder (@Nonnull final OrderType aOrder,
                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                     @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aOrder, EUBL21DocumentType.ORDER, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link OrderCancellationType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aOrderCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderCancellation (@Nonnull final OrderCancellationType aOrderCancellation)
  {
    return writeOrderCancellation (aOrderCancellation, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link OrderCancellationType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aOrderCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderCancellation (@Nonnull final OrderCancellationType aOrderCancellation,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderCancellation,
                                             EUBL21DocumentType.ORDER_CANCELLATION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link OrderCancellationType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aOrderCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderCancellation (@Nonnull final OrderCancellationType aOrderCancellation,
                                                 @Nonnull final File aResult)
  {
    return writeOrderCancellation (aOrderCancellation, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderCancellationType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aOrderCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderCancellation (@Nonnull final OrderCancellationType aOrderCancellation,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final File aResult)
  {
    return writeOrderCancellation (aOrderCancellation, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderCancellationType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aOrderCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderCancellation (@Nonnull final OrderCancellationType aOrderCancellation,
                                                 @Nonnull final Result aResult)
  {
    return writeOrderCancellation (aOrderCancellation, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link OrderCancellationType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aOrderCancellation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderCancellation (@Nonnull final OrderCancellationType aOrderCancellation,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderCancellation,
                                             EUBL21DocumentType.ORDER_CANCELLATION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link OrderChangeType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aOrderChange
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderChange (@Nonnull final OrderChangeType aOrderChange)
  {
    return writeOrderChange (aOrderChange, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link OrderChangeType} to a DOM {@link Document} using
   * a custom validation event handler.
   * 
   * @param aOrderChange
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderChange (@Nonnull final OrderChangeType aOrderChange,
                                           @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderChange, EUBL21DocumentType.ORDER_CHANGE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link OrderChangeType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aOrderChange
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderChange (@Nonnull final OrderChangeType aOrderChange, @Nonnull final File aResult)
  {
    return writeOrderChange (aOrderChange, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderChangeType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aOrderChange
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderChange (@Nonnull final OrderChangeType aOrderChange,
                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                           @Nonnull final File aResult)
  {
    return writeOrderChange (aOrderChange, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderChangeType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aOrderChange
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderChange (@Nonnull final OrderChangeType aOrderChange, @Nonnull final Result aResult)
  {
    return writeOrderChange (aOrderChange, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link OrderChangeType} to a custom {@link Result} using
   * a custom validation event handler.
   * 
   * @param aOrderChange
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderChange (@Nonnull final OrderChangeType aOrderChange,
                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                           @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderChange,
                                             EUBL21DocumentType.ORDER_CHANGE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link OrderResponseType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aOrderResponse
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderResponse (@Nonnull final OrderResponseType aOrderResponse)
  {
    return writeOrderResponse (aOrderResponse, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link OrderResponseType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aOrderResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderResponse (@Nonnull final OrderResponseType aOrderResponse,
                                             @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderResponse, EUBL21DocumentType.ORDER_RESPONSE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link OrderResponseType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aOrderResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponse (@Nonnull final OrderResponseType aOrderResponse,
                                             @Nonnull final File aResult)
  {
    return writeOrderResponse (aOrderResponse, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderResponseType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aOrderResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponse (@Nonnull final OrderResponseType aOrderResponse,
                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                             @Nonnull final File aResult)
  {
    return writeOrderResponse (aOrderResponse, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderResponseType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aOrderResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponse (@Nonnull final OrderResponseType aOrderResponse,
                                             @Nonnull final Result aResult)
  {
    return writeOrderResponse (aOrderResponse, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link OrderResponseType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aOrderResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponse (@Nonnull final OrderResponseType aOrderResponse,
                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                             @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderResponse,
                                             EUBL21DocumentType.ORDER_RESPONSE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link OrderResponseSimpleType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aOrderResponseSimple
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderResponseSimple (@Nonnull final OrderResponseSimpleType aOrderResponseSimple)
  {
    return writeOrderResponseSimple (aOrderResponseSimple, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link OrderResponseSimpleType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aOrderResponseSimple
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeOrderResponseSimple (@Nonnull final OrderResponseSimpleType aOrderResponseSimple,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderResponseSimple,
                                             EUBL21DocumentType.ORDER_RESPONSE_SIMPLE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link OrderResponseSimpleType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aOrderResponseSimple
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponseSimple (@Nonnull final OrderResponseSimpleType aOrderResponseSimple,
                                                   @Nonnull final File aResult)
  {
    return writeOrderResponseSimple (aOrderResponseSimple, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderResponseSimpleType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aOrderResponseSimple
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponseSimple (@Nonnull final OrderResponseSimpleType aOrderResponseSimple,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final File aResult)
  {
    return writeOrderResponseSimple (aOrderResponseSimple, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link OrderResponseSimpleType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aOrderResponseSimple
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponseSimple (@Nonnull final OrderResponseSimpleType aOrderResponseSimple,
                                                   @Nonnull final Result aResult)
  {
    return writeOrderResponseSimple (aOrderResponseSimple, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link OrderResponseSimpleType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aOrderResponseSimple
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeOrderResponseSimple (@Nonnull final OrderResponseSimpleType aOrderResponseSimple,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aOrderResponseSimple,
                                             EUBL21DocumentType.ORDER_RESPONSE_SIMPLE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link PackingListType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aPackingList
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writePackingList (@Nonnull final PackingListType aPackingList)
  {
    return writePackingList (aPackingList, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link PackingListType} to a DOM {@link Document} using
   * a custom validation event handler.
   * 
   * @param aPackingList
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writePackingList (@Nonnull final PackingListType aPackingList,
                                           @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aPackingList, EUBL21DocumentType.PACKING_LIST, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link PackingListType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aPackingList
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePackingList (@Nonnull final PackingListType aPackingList, @Nonnull final File aResult)
  {
    return writePackingList (aPackingList, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link PackingListType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aPackingList
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePackingList (@Nonnull final PackingListType aPackingList,
                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                           @Nonnull final File aResult)
  {
    return writePackingList (aPackingList, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link PackingListType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aPackingList
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePackingList (@Nonnull final PackingListType aPackingList, @Nonnull final Result aResult)
  {
    return writePackingList (aPackingList, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link PackingListType} to a custom {@link Result} using
   * a custom validation event handler.
   * 
   * @param aPackingList
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePackingList (@Nonnull final PackingListType aPackingList,
                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                           @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aPackingList,
                                             EUBL21DocumentType.PACKING_LIST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link PriorInformationNoticeType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aPriorInformationNotice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writePriorInformationNotice (@Nonnull final PriorInformationNoticeType aPriorInformationNotice)
  {
    return writePriorInformationNotice (aPriorInformationNotice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link PriorInformationNoticeType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aPriorInformationNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writePriorInformationNotice (@Nonnull final PriorInformationNoticeType aPriorInformationNotice,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aPriorInformationNotice,
                                             EUBL21DocumentType.PRIOR_INFORMATION_NOTICE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link PriorInformationNoticeType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aPriorInformationNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePriorInformationNotice (@Nonnull final PriorInformationNoticeType aPriorInformationNotice,
                                                      @Nonnull final File aResult)
  {
    return writePriorInformationNotice (aPriorInformationNotice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link PriorInformationNoticeType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aPriorInformationNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePriorInformationNotice (@Nonnull final PriorInformationNoticeType aPriorInformationNotice,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final File aResult)
  {
    return writePriorInformationNotice (aPriorInformationNotice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link PriorInformationNoticeType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aPriorInformationNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePriorInformationNotice (@Nonnull final PriorInformationNoticeType aPriorInformationNotice,
                                                      @Nonnull final Result aResult)
  {
    return writePriorInformationNotice (aPriorInformationNotice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link PriorInformationNoticeType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aPriorInformationNotice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writePriorInformationNotice (@Nonnull final PriorInformationNoticeType aPriorInformationNotice,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aPriorInformationNotice,
                                             EUBL21DocumentType.PRIOR_INFORMATION_NOTICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ProductActivityType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aProductActivity
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeProductActivity (@Nonnull final ProductActivityType aProductActivity)
  {
    return writeProductActivity (aProductActivity, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ProductActivityType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aProductActivity
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeProductActivity (@Nonnull final ProductActivityType aProductActivity,
                                               @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aProductActivity, EUBL21DocumentType.PRODUCT_ACTIVITY, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ProductActivityType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aProductActivity
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeProductActivity (@Nonnull final ProductActivityType aProductActivity,
                                               @Nonnull final File aResult)
  {
    return writeProductActivity (aProductActivity, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ProductActivityType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aProductActivity
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeProductActivity (@Nonnull final ProductActivityType aProductActivity,
                                               @Nullable final ValidationEventHandler aCustomEventHandler,
                                               @Nonnull final File aResult)
  {
    return writeProductActivity (aProductActivity, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ProductActivityType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aProductActivity
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeProductActivity (@Nonnull final ProductActivityType aProductActivity,
                                               @Nonnull final Result aResult)
  {
    return writeProductActivity (aProductActivity, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ProductActivityType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aProductActivity
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeProductActivity (@Nonnull final ProductActivityType aProductActivity,
                                               @Nullable final ValidationEventHandler aCustomEventHandler,
                                               @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aProductActivity,
                                             EUBL21DocumentType.PRODUCT_ACTIVITY,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link QuotationType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeQuotation (@Nonnull final QuotationType aQuotation)
  {
    return writeQuotation (aQuotation, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link QuotationType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeQuotation (@Nonnull final QuotationType aQuotation,
                                         @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aQuotation, EUBL21DocumentType.QUOTATION, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link QuotationType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeQuotation (@Nonnull final QuotationType aQuotation, @Nonnull final File aResult)
  {
    return writeQuotation (aQuotation, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link QuotationType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeQuotation (@Nonnull final QuotationType aQuotation,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final File aResult)
  {
    return writeQuotation (aQuotation, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link QuotationType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeQuotation (@Nonnull final QuotationType aQuotation, @Nonnull final Result aResult)
  {
    return writeQuotation (aQuotation, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link QuotationType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeQuotation (@Nonnull final QuotationType aQuotation,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aQuotation, EUBL21DocumentType.QUOTATION, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link ReceiptAdviceType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aReceiptAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeReceiptAdvice (@Nonnull final ReceiptAdviceType aReceiptAdvice)
  {
    return writeReceiptAdvice (aReceiptAdvice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ReceiptAdviceType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aReceiptAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeReceiptAdvice (@Nonnull final ReceiptAdviceType aReceiptAdvice,
                                             @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aReceiptAdvice, EUBL21DocumentType.RECEIPT_ADVICE, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ReceiptAdviceType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aReceiptAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReceiptAdvice (@Nonnull final ReceiptAdviceType aReceiptAdvice,
                                             @Nonnull final File aResult)
  {
    return writeReceiptAdvice (aReceiptAdvice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ReceiptAdviceType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aReceiptAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReceiptAdvice (@Nonnull final ReceiptAdviceType aReceiptAdvice,
                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                             @Nonnull final File aResult)
  {
    return writeReceiptAdvice (aReceiptAdvice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ReceiptAdviceType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aReceiptAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReceiptAdvice (@Nonnull final ReceiptAdviceType aReceiptAdvice,
                                             @Nonnull final Result aResult)
  {
    return writeReceiptAdvice (aReceiptAdvice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ReceiptAdviceType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aReceiptAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReceiptAdvice (@Nonnull final ReceiptAdviceType aReceiptAdvice,
                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                             @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aReceiptAdvice,
                                             EUBL21DocumentType.RECEIPT_ADVICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link ReminderType} to a DOM {@link Document} using the
   * global validation event handler.
   * 
   * @param aReminder
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeReminder (@Nonnull final ReminderType aReminder)
  {
    return writeReminder (aReminder, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link ReminderType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aReminder
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeReminder (@Nonnull final ReminderType aReminder,
                                        @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aReminder, EUBL21DocumentType.REMINDER, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link ReminderType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aReminder
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReminder (@Nonnull final ReminderType aReminder, @Nonnull final File aResult)
  {
    return writeReminder (aReminder, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ReminderType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aReminder
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReminder (@Nonnull final ReminderType aReminder,
                                        @Nullable final ValidationEventHandler aCustomEventHandler,
                                        @Nonnull final File aResult)
  {
    return writeReminder (aReminder, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link ReminderType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aReminder
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReminder (@Nonnull final ReminderType aReminder, @Nonnull final Result aResult)
  {
    return writeReminder (aReminder, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link ReminderType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aReminder
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeReminder (@Nonnull final ReminderType aReminder,
                                        @Nullable final ValidationEventHandler aCustomEventHandler,
                                        @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aReminder, EUBL21DocumentType.REMINDER, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link RemittanceAdviceType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aRemittanceAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeRemittanceAdvice (@Nonnull final RemittanceAdviceType aRemittanceAdvice)
  {
    return writeRemittanceAdvice (aRemittanceAdvice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link RemittanceAdviceType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aRemittanceAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeRemittanceAdvice (@Nonnull final RemittanceAdviceType aRemittanceAdvice,
                                                @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aRemittanceAdvice,
                                             EUBL21DocumentType.REMITTANCE_ADVICE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link RemittanceAdviceType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aRemittanceAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRemittanceAdvice (@Nonnull final RemittanceAdviceType aRemittanceAdvice,
                                                @Nonnull final File aResult)
  {
    return writeRemittanceAdvice (aRemittanceAdvice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link RemittanceAdviceType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aRemittanceAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRemittanceAdvice (@Nonnull final RemittanceAdviceType aRemittanceAdvice,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final File aResult)
  {
    return writeRemittanceAdvice (aRemittanceAdvice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link RemittanceAdviceType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aRemittanceAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRemittanceAdvice (@Nonnull final RemittanceAdviceType aRemittanceAdvice,
                                                @Nonnull final Result aResult)
  {
    return writeRemittanceAdvice (aRemittanceAdvice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link RemittanceAdviceType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aRemittanceAdvice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRemittanceAdvice (@Nonnull final RemittanceAdviceType aRemittanceAdvice,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aRemittanceAdvice,
                                             EUBL21DocumentType.REMITTANCE_ADVICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link RequestForQuotationType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aRequestForQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeRequestForQuotation (@Nonnull final RequestForQuotationType aRequestForQuotation)
  {
    return writeRequestForQuotation (aRequestForQuotation, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link RequestForQuotationType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aRequestForQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeRequestForQuotation (@Nonnull final RequestForQuotationType aRequestForQuotation,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aRequestForQuotation,
                                             EUBL21DocumentType.REQUEST_FOR_QUOTATION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link RequestForQuotationType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aRequestForQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRequestForQuotation (@Nonnull final RequestForQuotationType aRequestForQuotation,
                                                   @Nonnull final File aResult)
  {
    return writeRequestForQuotation (aRequestForQuotation, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link RequestForQuotationType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aRequestForQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRequestForQuotation (@Nonnull final RequestForQuotationType aRequestForQuotation,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final File aResult)
  {
    return writeRequestForQuotation (aRequestForQuotation, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link RequestForQuotationType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aRequestForQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRequestForQuotation (@Nonnull final RequestForQuotationType aRequestForQuotation,
                                                   @Nonnull final Result aResult)
  {
    return writeRequestForQuotation (aRequestForQuotation, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link RequestForQuotationType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aRequestForQuotation
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRequestForQuotation (@Nonnull final RequestForQuotationType aRequestForQuotation,
                                                   @Nullable final ValidationEventHandler aCustomEventHandler,
                                                   @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aRequestForQuotation,
                                             EUBL21DocumentType.REQUEST_FOR_QUOTATION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link RetailEventType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aRetailEvent
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeRetailEvent (@Nonnull final RetailEventType aRetailEvent)
  {
    return writeRetailEvent (aRetailEvent, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link RetailEventType} to a DOM {@link Document} using
   * a custom validation event handler.
   * 
   * @param aRetailEvent
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeRetailEvent (@Nonnull final RetailEventType aRetailEvent,
                                           @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aRetailEvent, EUBL21DocumentType.RETAIL_EVENT, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link RetailEventType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aRetailEvent
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRetailEvent (@Nonnull final RetailEventType aRetailEvent, @Nonnull final File aResult)
  {
    return writeRetailEvent (aRetailEvent, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link RetailEventType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aRetailEvent
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRetailEvent (@Nonnull final RetailEventType aRetailEvent,
                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                           @Nonnull final File aResult)
  {
    return writeRetailEvent (aRetailEvent, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link RetailEventType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aRetailEvent
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRetailEvent (@Nonnull final RetailEventType aRetailEvent, @Nonnull final Result aResult)
  {
    return writeRetailEvent (aRetailEvent, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link RetailEventType} to a custom {@link Result} using
   * a custom validation event handler.
   * 
   * @param aRetailEvent
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeRetailEvent (@Nonnull final RetailEventType aRetailEvent,
                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                           @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aRetailEvent,
                                             EUBL21DocumentType.RETAIL_EVENT,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link SelfBilledCreditNoteType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aSelfBilledCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeSelfBilledCreditNote (@Nonnull final SelfBilledCreditNoteType aSelfBilledCreditNote)
  {
    return writeSelfBilledCreditNote (aSelfBilledCreditNote, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link SelfBilledCreditNoteType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aSelfBilledCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeSelfBilledCreditNote (@Nonnull final SelfBilledCreditNoteType aSelfBilledCreditNote,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aSelfBilledCreditNote,
                                             EUBL21DocumentType.SELF_BILLED_CREDIT_NOTE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link SelfBilledCreditNoteType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aSelfBilledCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledCreditNote (@Nonnull final SelfBilledCreditNoteType aSelfBilledCreditNote,
                                                    @Nonnull final File aResult)
  {
    return writeSelfBilledCreditNote (aSelfBilledCreditNote, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link SelfBilledCreditNoteType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aSelfBilledCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledCreditNote (@Nonnull final SelfBilledCreditNoteType aSelfBilledCreditNote,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler,
                                                    @Nonnull final File aResult)
  {
    return writeSelfBilledCreditNote (aSelfBilledCreditNote, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link SelfBilledCreditNoteType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aSelfBilledCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledCreditNote (@Nonnull final SelfBilledCreditNoteType aSelfBilledCreditNote,
                                                    @Nonnull final Result aResult)
  {
    return writeSelfBilledCreditNote (aSelfBilledCreditNote, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link SelfBilledCreditNoteType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aSelfBilledCreditNote
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledCreditNote (@Nonnull final SelfBilledCreditNoteType aSelfBilledCreditNote,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler,
                                                    @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aSelfBilledCreditNote,
                                             EUBL21DocumentType.SELF_BILLED_CREDIT_NOTE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link SelfBilledInvoiceType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aSelfBilledInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeSelfBilledInvoice (@Nonnull final SelfBilledInvoiceType aSelfBilledInvoice)
  {
    return writeSelfBilledInvoice (aSelfBilledInvoice, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link SelfBilledInvoiceType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aSelfBilledInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeSelfBilledInvoice (@Nonnull final SelfBilledInvoiceType aSelfBilledInvoice,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aSelfBilledInvoice,
                                             EUBL21DocumentType.SELF_BILLED_INVOICE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link SelfBilledInvoiceType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aSelfBilledInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledInvoice (@Nonnull final SelfBilledInvoiceType aSelfBilledInvoice,
                                                 @Nonnull final File aResult)
  {
    return writeSelfBilledInvoice (aSelfBilledInvoice, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link SelfBilledInvoiceType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aSelfBilledInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledInvoice (@Nonnull final SelfBilledInvoiceType aSelfBilledInvoice,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final File aResult)
  {
    return writeSelfBilledInvoice (aSelfBilledInvoice, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link SelfBilledInvoiceType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aSelfBilledInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledInvoice (@Nonnull final SelfBilledInvoiceType aSelfBilledInvoice,
                                                 @Nonnull final Result aResult)
  {
    return writeSelfBilledInvoice (aSelfBilledInvoice, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link SelfBilledInvoiceType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aSelfBilledInvoice
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeSelfBilledInvoice (@Nonnull final SelfBilledInvoiceType aSelfBilledInvoice,
                                                 @Nullable final ValidationEventHandler aCustomEventHandler,
                                                 @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aSelfBilledInvoice,
                                             EUBL21DocumentType.SELF_BILLED_INVOICE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link StatementType} to a DOM {@link Document} using
   * the global validation event handler.
   * 
   * @param aStatement
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeStatement (@Nonnull final StatementType aStatement)
  {
    return writeStatement (aStatement, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link StatementType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeStatement (@Nonnull final StatementType aStatement,
                                         @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aStatement, EUBL21DocumentType.STATEMENT, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link StatementType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStatement (@Nonnull final StatementType aStatement, @Nonnull final File aResult)
  {
    return writeStatement (aStatement, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link StatementType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStatement (@Nonnull final StatementType aStatement,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final File aResult)
  {
    return writeStatement (aStatement, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link StatementType} to a custom {@link Result} using
   * the global validation event handler.
   * 
   * @param aStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStatement (@Nonnull final StatementType aStatement, @Nonnull final Result aResult)
  {
    return writeStatement (aStatement, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link StatementType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStatement (@Nonnull final StatementType aStatement,
                                         @Nullable final ValidationEventHandler aCustomEventHandler,
                                         @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aStatement, EUBL21DocumentType.STATEMENT, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link StockAvailabilityReportType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aStockAvailabilityReport
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeStockAvailabilityReport (@Nonnull final StockAvailabilityReportType aStockAvailabilityReport)
  {
    return writeStockAvailabilityReport (aStockAvailabilityReport, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link StockAvailabilityReportType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aStockAvailabilityReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeStockAvailabilityReport (@Nonnull final StockAvailabilityReportType aStockAvailabilityReport,
                                                       @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aStockAvailabilityReport,
                                             EUBL21DocumentType.STOCK_AVAILABILITY_REPORT,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link StockAvailabilityReportType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aStockAvailabilityReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStockAvailabilityReport (@Nonnull final StockAvailabilityReportType aStockAvailabilityReport,
                                                       @Nonnull final File aResult)
  {
    return writeStockAvailabilityReport (aStockAvailabilityReport, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link StockAvailabilityReportType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aStockAvailabilityReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStockAvailabilityReport (@Nonnull final StockAvailabilityReportType aStockAvailabilityReport,
                                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                                       @Nonnull final File aResult)
  {
    return writeStockAvailabilityReport (aStockAvailabilityReport, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link StockAvailabilityReportType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aStockAvailabilityReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStockAvailabilityReport (@Nonnull final StockAvailabilityReportType aStockAvailabilityReport,
                                                       @Nonnull final Result aResult)
  {
    return writeStockAvailabilityReport (aStockAvailabilityReport, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link StockAvailabilityReportType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aStockAvailabilityReport
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeStockAvailabilityReport (@Nonnull final StockAvailabilityReportType aStockAvailabilityReport,
                                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                                       @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aStockAvailabilityReport,
                                             EUBL21DocumentType.STOCK_AVAILABILITY_REPORT,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TenderType} to a DOM {@link Document} using the
   * global validation event handler.
   * 
   * @param aTender
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTender (@Nonnull final TenderType aTender)
  {
    return writeTender (aTender, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TenderType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aTender
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTender (@Nonnull final TenderType aTender,
                                      @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTender, EUBL21DocumentType.TENDER, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TenderType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aTender
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTender (@Nonnull final TenderType aTender, @Nonnull final File aResult)
  {
    return writeTender (aTender, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TenderType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aTender
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTender (@Nonnull final TenderType aTender,
                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                      @Nonnull final File aResult)
  {
    return writeTender (aTender, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TenderType} to a custom {@link Result} using the
   * global validation event handler.
   * 
   * @param aTender
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTender (@Nonnull final TenderType aTender, @Nonnull final Result aResult)
  {
    return writeTender (aTender, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TenderType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aTender
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTender (@Nonnull final TenderType aTender,
                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                      @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTender, EUBL21DocumentType.TENDER, aCustomEventHandler, aResult);
  }

  /**
   * Convert the passed {@link TendererQualificationType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTendererQualification
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTendererQualification (@Nonnull final TendererQualificationType aTendererQualification)
  {
    return writeTendererQualification (aTendererQualification, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TendererQualificationType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTendererQualification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTendererQualification (@Nonnull final TendererQualificationType aTendererQualification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTendererQualification,
                                             EUBL21DocumentType.TENDER_QUALIFICATION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TendererQualificationType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aTendererQualification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualification (@Nonnull final TendererQualificationType aTendererQualification,
                                                     @Nonnull final File aResult)
  {
    return writeTendererQualification (aTendererQualification, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TendererQualificationType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aTendererQualification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualification (@Nonnull final TendererQualificationType aTendererQualification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final File aResult)
  {
    return writeTendererQualification (aTendererQualification, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TendererQualificationType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTendererQualification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualification (@Nonnull final TendererQualificationType aTendererQualification,
                                                     @Nonnull final Result aResult)
  {
    return writeTendererQualification (aTendererQualification, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TendererQualificationType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTendererQualification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualification (@Nonnull final TendererQualificationType aTendererQualification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTendererQualification,
                                             EUBL21DocumentType.TENDER_QUALIFICATION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TendererQualificationResponseType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTendererQualificationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTendererQualificationResponse (@Nonnull final TendererQualificationResponseType aTendererQualificationResponse)
  {
    return writeTendererQualificationResponse (aTendererQualificationResponse, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TendererQualificationResponseType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTendererQualificationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTendererQualificationResponse (@Nonnull final TendererQualificationResponseType aTendererQualificationResponse,
                                                             @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTendererQualificationResponse,
                                             EUBL21DocumentType.TENDER_QUALIFICATION_RESPONSE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TendererQualificationResponseType} to a
   * {@link File} using the global validation event handler.
   * 
   * @param aTendererQualificationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualificationResponse (@Nonnull final TendererQualificationResponseType aTendererQualificationResponse,
                                                             @Nonnull final File aResult)
  {
    return writeTendererQualificationResponse (aTendererQualificationResponse, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TendererQualificationResponseType} to a
   * {@link File} using a custom validation event handler.
   * 
   * @param aTendererQualificationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualificationResponse (@Nonnull final TendererQualificationResponseType aTendererQualificationResponse,
                                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                                             @Nonnull final File aResult)
  {
    return writeTendererQualificationResponse (aTendererQualificationResponse,
                                               aCustomEventHandler,
                                               new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TendererQualificationResponseType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTendererQualificationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualificationResponse (@Nonnull final TendererQualificationResponseType aTendererQualificationResponse,
                                                             @Nonnull final Result aResult)
  {
    return writeTendererQualificationResponse (aTendererQualificationResponse,
                                               getGlobalValidationEventHandler (),
                                               aResult);
  }

  /**
   * Convert the passed {@link TendererQualificationResponseType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTendererQualificationResponse
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTendererQualificationResponse (@Nonnull final TendererQualificationResponseType aTendererQualificationResponse,
                                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                                             @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTendererQualificationResponse,
                                             EUBL21DocumentType.TENDER_QUALIFICATION_RESPONSE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TenderReceiptType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aTenderReceipt
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTenderReceipt (@Nonnull final TenderReceiptType aTenderReceipt)
  {
    return writeTenderReceipt (aTenderReceipt, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TenderReceiptType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aTenderReceipt
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTenderReceipt (@Nonnull final TenderReceiptType aTenderReceipt,
                                             @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTenderReceipt, EUBL21DocumentType.TENDER_RECEIPT, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TenderReceiptType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aTenderReceipt
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTenderReceipt (@Nonnull final TenderReceiptType aTenderReceipt,
                                             @Nonnull final File aResult)
  {
    return writeTenderReceipt (aTenderReceipt, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TenderReceiptType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aTenderReceipt
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTenderReceipt (@Nonnull final TenderReceiptType aTenderReceipt,
                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                             @Nonnull final File aResult)
  {
    return writeTenderReceipt (aTenderReceipt, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TenderReceiptType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aTenderReceipt
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTenderReceipt (@Nonnull final TenderReceiptType aTenderReceipt,
                                             @Nonnull final Result aResult)
  {
    return writeTenderReceipt (aTenderReceipt, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TenderReceiptType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aTenderReceipt
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTenderReceipt (@Nonnull final TenderReceiptType aTenderReceipt,
                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                             @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTenderReceipt,
                                             EUBL21DocumentType.TENDER_RECEIPT,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TradeItemLocationProfileType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTradeItemLocationProfile
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTradeItemLocationProfile (@Nonnull final TradeItemLocationProfileType aTradeItemLocationProfile)
  {
    return writeTradeItemLocationProfile (aTradeItemLocationProfile, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TradeItemLocationProfileType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTradeItemLocationProfile
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTradeItemLocationProfile (@Nonnull final TradeItemLocationProfileType aTradeItemLocationProfile,
                                                        @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTradeItemLocationProfile,
                                             EUBL21DocumentType.TRADE_ITEM_LOCATION_PROFILE,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TradeItemLocationProfileType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aTradeItemLocationProfile
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTradeItemLocationProfile (@Nonnull final TradeItemLocationProfileType aTradeItemLocationProfile,
                                                        @Nonnull final File aResult)
  {
    return writeTradeItemLocationProfile (aTradeItemLocationProfile, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TradeItemLocationProfileType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aTradeItemLocationProfile
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTradeItemLocationProfile (@Nonnull final TradeItemLocationProfileType aTradeItemLocationProfile,
                                                        @Nullable final ValidationEventHandler aCustomEventHandler,
                                                        @Nonnull final File aResult)
  {
    return writeTradeItemLocationProfile (aTradeItemLocationProfile, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TradeItemLocationProfileType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTradeItemLocationProfile
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTradeItemLocationProfile (@Nonnull final TradeItemLocationProfileType aTradeItemLocationProfile,
                                                        @Nonnull final Result aResult)
  {
    return writeTradeItemLocationProfile (aTradeItemLocationProfile, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TradeItemLocationProfileType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTradeItemLocationProfile
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTradeItemLocationProfile (@Nonnull final TradeItemLocationProfileType aTradeItemLocationProfile,
                                                        @Nullable final ValidationEventHandler aCustomEventHandler,
                                                        @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTradeItemLocationProfile,
                                             EUBL21DocumentType.TRADE_ITEM_LOCATION_PROFILE,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportationStatusType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportationStatus
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportationStatus (@Nonnull final TransportationStatusType aTransportationStatus)
  {
    return writeTransportationStatus (aTransportationStatus, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportationStatusType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportationStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportationStatus (@Nonnull final TransportationStatusType aTransportationStatus,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportationStatus,
                                             EUBL21DocumentType.TRANSPORTATION_STATUS,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportationStatusType} to a {@link File} using
   * the global validation event handler.
   * 
   * @param aTransportationStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatus (@Nonnull final TransportationStatusType aTransportationStatus,
                                                    @Nonnull final File aResult)
  {
    return writeTransportationStatus (aTransportationStatus, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportationStatusType} to a {@link File} using
   * a custom validation event handler.
   * 
   * @param aTransportationStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatus (@Nonnull final TransportationStatusType aTransportationStatus,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler,
                                                    @Nonnull final File aResult)
  {
    return writeTransportationStatus (aTransportationStatus, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportationStatusType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTransportationStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatus (@Nonnull final TransportationStatusType aTransportationStatus,
                                                    @Nonnull final Result aResult)
  {
    return writeTransportationStatus (aTransportationStatus, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TransportationStatusType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTransportationStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatus (@Nonnull final TransportationStatusType aTransportationStatus,
                                                    @Nullable final ValidationEventHandler aCustomEventHandler,
                                                    @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportationStatus,
                                             EUBL21DocumentType.TRANSPORTATION_STATUS,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportationStatusRequestType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportationStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportationStatusRequest (@Nonnull final TransportationStatusRequestType aTransportationStatusRequest)
  {
    return writeTransportationStatusRequest (aTransportationStatusRequest, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportationStatusRequestType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportationStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportationStatusRequest (@Nonnull final TransportationStatusRequestType aTransportationStatusRequest,
                                                           @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportationStatusRequest,
                                             EUBL21DocumentType.TRANSPORTATION_STATUS_REQUEST,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportationStatusRequestType} to a
   * {@link File} using the global validation event handler.
   * 
   * @param aTransportationStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatusRequest (@Nonnull final TransportationStatusRequestType aTransportationStatusRequest,
                                                           @Nonnull final File aResult)
  {
    return writeTransportationStatusRequest (aTransportationStatusRequest, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportationStatusRequestType} to a
   * {@link File} using a custom validation event handler.
   * 
   * @param aTransportationStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatusRequest (@Nonnull final TransportationStatusRequestType aTransportationStatusRequest,
                                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                                           @Nonnull final File aResult)
  {
    return writeTransportationStatusRequest (aTransportationStatusRequest,
                                             aCustomEventHandler,
                                             new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportationStatusRequestType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTransportationStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatusRequest (@Nonnull final TransportationStatusRequestType aTransportationStatusRequest,
                                                           @Nonnull final Result aResult)
  {
    return writeTransportationStatusRequest (aTransportationStatusRequest, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TransportationStatusRequestType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTransportationStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportationStatusRequest (@Nonnull final TransportationStatusRequestType aTransportationStatusRequest,
                                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                                           @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportationStatusRequest,
                                             EUBL21DocumentType.TRANSPORTATION_STATUS_REQUEST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportExecutionPlanType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportExecutionPlan
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportExecutionPlan (@Nonnull final TransportExecutionPlanType aTransportExecutionPlan)
  {
    return writeTransportExecutionPlan (aTransportExecutionPlan, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportExecutionPlanType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportExecutionPlan
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportExecutionPlan (@Nonnull final TransportExecutionPlanType aTransportExecutionPlan,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportExecutionPlan,
                                             EUBL21DocumentType.TRANSPORT_EXECUTION_PLAN,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportExecutionPlanType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aTransportExecutionPlan
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlan (@Nonnull final TransportExecutionPlanType aTransportExecutionPlan,
                                                      @Nonnull final File aResult)
  {
    return writeTransportExecutionPlan (aTransportExecutionPlan, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportExecutionPlanType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aTransportExecutionPlan
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlan (@Nonnull final TransportExecutionPlanType aTransportExecutionPlan,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final File aResult)
  {
    return writeTransportExecutionPlan (aTransportExecutionPlan, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportExecutionPlanType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTransportExecutionPlan
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlan (@Nonnull final TransportExecutionPlanType aTransportExecutionPlan,
                                                      @Nonnull final Result aResult)
  {
    return writeTransportExecutionPlan (aTransportExecutionPlan, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TransportExecutionPlanType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTransportExecutionPlan
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlan (@Nonnull final TransportExecutionPlanType aTransportExecutionPlan,
                                                      @Nullable final ValidationEventHandler aCustomEventHandler,
                                                      @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportExecutionPlan,
                                             EUBL21DocumentType.TRANSPORT_EXECUTION_PLAN,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportExecutionPlanRequestType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportExecutionPlanRequest
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportExecutionPlanRequest (@Nonnull final TransportExecutionPlanRequestType aTransportExecutionPlanRequest)
  {
    return writeTransportExecutionPlanRequest (aTransportExecutionPlanRequest, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportExecutionPlanRequestType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportExecutionPlanRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportExecutionPlanRequest (@Nonnull final TransportExecutionPlanRequestType aTransportExecutionPlanRequest,
                                                             @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportExecutionPlanRequest,
                                             EUBL21DocumentType.TRANSPORT_EXECUTION_PLAN_REQUEST,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportExecutionPlanRequestType} to a
   * {@link File} using the global validation event handler.
   * 
   * @param aTransportExecutionPlanRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlanRequest (@Nonnull final TransportExecutionPlanRequestType aTransportExecutionPlanRequest,
                                                             @Nonnull final File aResult)
  {
    return writeTransportExecutionPlanRequest (aTransportExecutionPlanRequest, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportExecutionPlanRequestType} to a
   * {@link File} using a custom validation event handler.
   * 
   * @param aTransportExecutionPlanRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlanRequest (@Nonnull final TransportExecutionPlanRequestType aTransportExecutionPlanRequest,
                                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                                             @Nonnull final File aResult)
  {
    return writeTransportExecutionPlanRequest (aTransportExecutionPlanRequest,
                                               aCustomEventHandler,
                                               new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportExecutionPlanRequestType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTransportExecutionPlanRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlanRequest (@Nonnull final TransportExecutionPlanRequestType aTransportExecutionPlanRequest,
                                                             @Nonnull final Result aResult)
  {
    return writeTransportExecutionPlanRequest (aTransportExecutionPlanRequest,
                                               getGlobalValidationEventHandler (),
                                               aResult);
  }

  /**
   * Convert the passed {@link TransportExecutionPlanRequestType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTransportExecutionPlanRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportExecutionPlanRequest (@Nonnull final TransportExecutionPlanRequestType aTransportExecutionPlanRequest,
                                                             @Nullable final ValidationEventHandler aCustomEventHandler,
                                                             @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportExecutionPlanRequest,
                                             EUBL21DocumentType.TRANSPORT_EXECUTION_PLAN_REQUEST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportProgressStatusType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportProgressStatus
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportProgressStatus (@Nonnull final TransportProgressStatusType aTransportProgressStatus)
  {
    return writeTransportProgressStatus (aTransportProgressStatus, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportProgressStatusType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportProgressStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportProgressStatus (@Nonnull final TransportProgressStatusType aTransportProgressStatus,
                                                       @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportProgressStatus,
                                             EUBL21DocumentType.TRANSPORT_PROGRESS_STATUS,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportProgressStatusType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aTransportProgressStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatus (@Nonnull final TransportProgressStatusType aTransportProgressStatus,
                                                       @Nonnull final File aResult)
  {
    return writeTransportProgressStatus (aTransportProgressStatus, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportProgressStatusType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aTransportProgressStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatus (@Nonnull final TransportProgressStatusType aTransportProgressStatus,
                                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                                       @Nonnull final File aResult)
  {
    return writeTransportProgressStatus (aTransportProgressStatus, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportProgressStatusType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTransportProgressStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatus (@Nonnull final TransportProgressStatusType aTransportProgressStatus,
                                                       @Nonnull final Result aResult)
  {
    return writeTransportProgressStatus (aTransportProgressStatus, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TransportProgressStatusType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTransportProgressStatus
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatus (@Nonnull final TransportProgressStatusType aTransportProgressStatus,
                                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                                       @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportProgressStatus,
                                             EUBL21DocumentType.TRANSPORT_PROGRESS_STATUS,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportProgressStatusRequestType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportProgressStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportProgressStatusRequest (@Nonnull final TransportProgressStatusRequestType aTransportProgressStatusRequest)
  {
    return writeTransportProgressStatusRequest (aTransportProgressStatusRequest, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportProgressStatusRequestType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportProgressStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportProgressStatusRequest (@Nonnull final TransportProgressStatusRequestType aTransportProgressStatusRequest,
                                                              @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportProgressStatusRequest,
                                             EUBL21DocumentType.TRANSPORT_PROGRESS_STATUS_REQUEST,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportProgressStatusRequestType} to a
   * {@link File} using the global validation event handler.
   * 
   * @param aTransportProgressStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatusRequest (@Nonnull final TransportProgressStatusRequestType aTransportProgressStatusRequest,
                                                              @Nonnull final File aResult)
  {
    return writeTransportProgressStatusRequest (aTransportProgressStatusRequest, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportProgressStatusRequestType} to a
   * {@link File} using a custom validation event handler.
   * 
   * @param aTransportProgressStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatusRequest (@Nonnull final TransportProgressStatusRequestType aTransportProgressStatusRequest,
                                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                                              @Nonnull final File aResult)
  {
    return writeTransportProgressStatusRequest (aTransportProgressStatusRequest,
                                                aCustomEventHandler,
                                                new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportProgressStatusRequestType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTransportProgressStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatusRequest (@Nonnull final TransportProgressStatusRequestType aTransportProgressStatusRequest,
                                                              @Nonnull final Result aResult)
  {
    return writeTransportProgressStatusRequest (aTransportProgressStatusRequest,
                                                getGlobalValidationEventHandler (),
                                                aResult);
  }

  /**
   * Convert the passed {@link TransportProgressStatusRequestType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTransportProgressStatusRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportProgressStatusRequest (@Nonnull final TransportProgressStatusRequestType aTransportProgressStatusRequest,
                                                              @Nullable final ValidationEventHandler aCustomEventHandler,
                                                              @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportProgressStatusRequest,
                                             EUBL21DocumentType.TRANSPORT_PROGRESS_STATUS_REQUEST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportServiceDescription
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportServiceDescription (@Nonnull final TransportServiceDescriptionType aTransportServiceDescription)
  {
    return writeTransportServiceDescription (aTransportServiceDescription, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportServiceDescription
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportServiceDescription (@Nonnull final TransportServiceDescriptionType aTransportServiceDescription,
                                                           @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportServiceDescription,
                                             EUBL21DocumentType.TRANSPORT_SERVICE_DESCRIPTION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionType} to a
   * {@link File} using the global validation event handler.
   * 
   * @param aTransportServiceDescription
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescription (@Nonnull final TransportServiceDescriptionType aTransportServiceDescription,
                                                           @Nonnull final File aResult)
  {
    return writeTransportServiceDescription (aTransportServiceDescription, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionType} to a
   * {@link File} using a custom validation event handler.
   * 
   * @param aTransportServiceDescription
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescription (@Nonnull final TransportServiceDescriptionType aTransportServiceDescription,
                                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                                           @Nonnull final File aResult)
  {
    return writeTransportServiceDescription (aTransportServiceDescription,
                                             aCustomEventHandler,
                                             new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aTransportServiceDescription
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescription (@Nonnull final TransportServiceDescriptionType aTransportServiceDescription,
                                                           @Nonnull final Result aResult)
  {
    return writeTransportServiceDescription (aTransportServiceDescription, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aTransportServiceDescription
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescription (@Nonnull final TransportServiceDescriptionType aTransportServiceDescription,
                                                           @Nullable final ValidationEventHandler aCustomEventHandler,
                                                           @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportServiceDescription,
                                             EUBL21DocumentType.TRANSPORT_SERVICE_DESCRIPTION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionRequestType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aTransportServiceDescriptionRequest
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportServiceDescriptionRequest (@Nonnull final TransportServiceDescriptionRequestType aTransportServiceDescriptionRequest)
  {
    return writeTransportServiceDescriptionRequest (aTransportServiceDescriptionRequest,
                                                    getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionRequestType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aTransportServiceDescriptionRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeTransportServiceDescriptionRequest (@Nonnull final TransportServiceDescriptionRequestType aTransportServiceDescriptionRequest,
                                                                  @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportServiceDescriptionRequest,
                                             EUBL21DocumentType.TRANSPORT_SERVICE_DESCRIPTION_REQUEST,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionRequestType} to a
   * {@link File} using the global validation event handler.
   * 
   * @param aTransportServiceDescriptionRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescriptionRequest (@Nonnull final TransportServiceDescriptionRequestType aTransportServiceDescriptionRequest,
                                                                  @Nonnull final File aResult)
  {
    return writeTransportServiceDescriptionRequest (aTransportServiceDescriptionRequest, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionRequestType} to a
   * {@link File} using a custom validation event handler.
   * 
   * @param aTransportServiceDescriptionRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescriptionRequest (@Nonnull final TransportServiceDescriptionRequestType aTransportServiceDescriptionRequest,
                                                                  @Nullable final ValidationEventHandler aCustomEventHandler,
                                                                  @Nonnull final File aResult)
  {
    return writeTransportServiceDescriptionRequest (aTransportServiceDescriptionRequest,
                                                    aCustomEventHandler,
                                                    new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionRequestType} to a
   * custom {@link Result} using the global validation event handler.
   * 
   * @param aTransportServiceDescriptionRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescriptionRequest (@Nonnull final TransportServiceDescriptionRequestType aTransportServiceDescriptionRequest,
                                                                  @Nonnull final Result aResult)
  {
    return writeTransportServiceDescriptionRequest (aTransportServiceDescriptionRequest,
                                                    getGlobalValidationEventHandler (),
                                                    aResult);
  }

  /**
   * Convert the passed {@link TransportServiceDescriptionRequestType} to a
   * custom {@link Result} using a custom validation event handler.
   * 
   * @param aTransportServiceDescriptionRequest
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeTransportServiceDescriptionRequest (@Nonnull final TransportServiceDescriptionRequestType aTransportServiceDescriptionRequest,
                                                                  @Nullable final ValidationEventHandler aCustomEventHandler,
                                                                  @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aTransportServiceDescriptionRequest,
                                             EUBL21DocumentType.TRANSPORT_SERVICE_DESCRIPTION_REQUEST,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link UnawardedNotificationType} to a DOM
   * {@link Document} using the global validation event handler.
   * 
   * @param aUnawardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeUnawardedNotification (@Nonnull final UnawardedNotificationType aUnawardedNotification)
  {
    return writeUnawardedNotification (aUnawardedNotification, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link UnawardedNotificationType} to a DOM
   * {@link Document} using a custom validation event handler.
   * 
   * @param aUnawardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeUnawardedNotification (@Nonnull final UnawardedNotificationType aUnawardedNotification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aUnawardedNotification,
                                             EUBL21DocumentType.UNAWARDED_NOTIFICATION,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link UnawardedNotificationType} to a {@link File}
   * using the global validation event handler.
   * 
   * @param aUnawardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUnawardedNotification (@Nonnull final UnawardedNotificationType aUnawardedNotification,
                                                     @Nonnull final File aResult)
  {
    return writeUnawardedNotification (aUnawardedNotification, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link UnawardedNotificationType} to a {@link File}
   * using a custom validation event handler.
   * 
   * @param aUnawardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUnawardedNotification (@Nonnull final UnawardedNotificationType aUnawardedNotification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final File aResult)
  {
    return writeUnawardedNotification (aUnawardedNotification, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link UnawardedNotificationType} to a custom
   * {@link Result} using the global validation event handler.
   * 
   * @param aUnawardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUnawardedNotification (@Nonnull final UnawardedNotificationType aUnawardedNotification,
                                                     @Nonnull final Result aResult)
  {
    return writeUnawardedNotification (aUnawardedNotification, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link UnawardedNotificationType} to a custom
   * {@link Result} using a custom validation event handler.
   * 
   * @param aUnawardedNotification
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUnawardedNotification (@Nonnull final UnawardedNotificationType aUnawardedNotification,
                                                     @Nullable final ValidationEventHandler aCustomEventHandler,
                                                     @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aUnawardedNotification,
                                             EUBL21DocumentType.UNAWARDED_NOTIFICATION,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link UtilityStatementType} to a DOM {@link Document}
   * using the global validation event handler.
   * 
   * @param aUtilityStatement
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeUtilityStatement (@Nonnull final UtilityStatementType aUtilityStatement)
  {
    return writeUtilityStatement (aUtilityStatement, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link UtilityStatementType} to a DOM {@link Document}
   * using a custom validation event handler.
   * 
   * @param aUtilityStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeUtilityStatement (@Nonnull final UtilityStatementType aUtilityStatement,
                                                @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aUtilityStatement,
                                             EUBL21DocumentType.UTILITY_STATEMENT,
                                             aCustomEventHandler);
  }

  /**
   * Convert the passed {@link UtilityStatementType} to a {@link File} using the
   * global validation event handler.
   * 
   * @param aUtilityStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUtilityStatement (@Nonnull final UtilityStatementType aUtilityStatement,
                                                @Nonnull final File aResult)
  {
    return writeUtilityStatement (aUtilityStatement, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link UtilityStatementType} to a {@link File} using a
   * custom validation event handler.
   * 
   * @param aUtilityStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUtilityStatement (@Nonnull final UtilityStatementType aUtilityStatement,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final File aResult)
  {
    return writeUtilityStatement (aUtilityStatement, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link UtilityStatementType} to a custom {@link Result}
   * using the global validation event handler.
   * 
   * @param aUtilityStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUtilityStatement (@Nonnull final UtilityStatementType aUtilityStatement,
                                                @Nonnull final Result aResult)
  {
    return writeUtilityStatement (aUtilityStatement, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link UtilityStatementType} to a custom {@link Result}
   * using a custom validation event handler.
   * 
   * @param aUtilityStatement
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeUtilityStatement (@Nonnull final UtilityStatementType aUtilityStatement,
                                                @Nullable final ValidationEventHandler aCustomEventHandler,
                                                @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aUtilityStatement,
                                             EUBL21DocumentType.UTILITY_STATEMENT,
                                             aCustomEventHandler,
                                             aResult);
  }

  /**
   * Convert the passed {@link WaybillType} to a DOM {@link Document} using the
   * global validation event handler.
   * 
   * @param aWaybill
   *          the source object to convert. May not be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeWaybill (@Nonnull final WaybillType aWaybill)
  {
    return writeWaybill (aWaybill, getGlobalValidationEventHandler ());
  }

  /**
   * Convert the passed {@link WaybillType} to a DOM {@link Document} using a
   * custom validation event handler.
   * 
   * @param aWaybill
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @return The created DOM document or <code>null</code> in case of conversion
   *         error
   */
  @Nullable
  public static Document writeWaybill (@Nonnull final WaybillType aWaybill,
                                       @Nullable final ValidationEventHandler aCustomEventHandler)
  {
    return UBL21Marshaller.writeUBLDocument (aWaybill, EUBL21DocumentType.WAYBILL, aCustomEventHandler);
  }

  /**
   * Convert the passed {@link WaybillType} to a {@link File} using the global
   * validation event handler.
   * 
   * @param aWaybill
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeWaybill (@Nonnull final WaybillType aWaybill, @Nonnull final File aResult)
  {
    return writeWaybill (aWaybill, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link WaybillType} to a {@link File} using a custom
   * validation event handler.
   * 
   * @param aWaybill
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the file to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeWaybill (@Nonnull final WaybillType aWaybill,
                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                       @Nonnull final File aResult)
  {
    return writeWaybill (aWaybill, aCustomEventHandler, new StreamResult (aResult));
  }

  /**
   * Convert the passed {@link WaybillType} to a custom {@link Result} using the
   * global validation event handler.
   * 
   * @param aWaybill
   *          the source object to convert. May not be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeWaybill (@Nonnull final WaybillType aWaybill, @Nonnull final Result aResult)
  {
    return writeWaybill (aWaybill, getGlobalValidationEventHandler (), aResult);
  }

  /**
   * Convert the passed {@link WaybillType} to a custom {@link Result} using a
   * custom validation event handler.
   * 
   * @param aWaybill
   *          the source object to convert. May not be <code>null</code>.
   * @param aCustomEventHandler
   *          The custom event handler to use. May be <code>null</code>.
   * @param aResult
   *          the result object to write to. May not be <code>null</code>.
   * @return {@link ESuccess#SUCCESS} in case of success,
   *         {@link ESuccess#FAILURE} in case of an error
   */
  @Nonnull
  public static ESuccess writeWaybill (@Nonnull final WaybillType aWaybill,
                                       @Nullable final ValidationEventHandler aCustomEventHandler,
                                       @Nonnull final Result aResult)
  {
    return UBL21Marshaller.writeUBLDocument (aWaybill, EUBL21DocumentType.WAYBILL, aCustomEventHandler, aResult);
  }
}
