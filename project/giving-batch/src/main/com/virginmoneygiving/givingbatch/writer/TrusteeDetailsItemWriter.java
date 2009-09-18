package com.virginmoneygiving.givingbatch.writer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.XMLEventWriter;
import javax.xml.stream.XMLStreamException;

import org.apache.log4j.Logger;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.xml.StaxEventItemWriter;
import org.springframework.batch.item.xml.StaxWriterCallback;
import org.springframework.oxm.XmlMappingException;

import com.virginmoneygiving.givingbatch.domain.CharityTrusteeDetails;
import com.virginmoneygiving.givingbatch.exception.GivingBatchException;

/**
 * Anti-Money Laundering XML item writer.
 * <br/>RB: reworked to meet spec.
 * 
 * @author Srinivas Nageli.
 * @author Robin Bramley, Ospera Ltd.
 */
public class TrusteeDetailsItemWriter extends StaxEventItemWriter implements
        StepExecutionListener {

    /** instance for Logger. * */
    private static Logger LOGGER =
            Logger.getLogger(TrusteeDetailsItemWriter.class);

    /** instance for ExecutionContext. * */
    private ExecutionContext executionContext;

    /**
     * Default constructor - sets a custom header callback.
     */
    public TrusteeDetailsItemWriter() {

        // invoke super constructor
        super();
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("TrusteeDetailsItemWriter:TrusteeDetailsItemWriter() constructor - START");
        }
        // set our header callback
        setHeaderCallback(new StaxWriterCallback() {
            public void write(XMLEventWriter writer) throws IOException {
                try {
                    XMLEventFactory factory = XMLEventFactory.newInstance();
                    writer.add(factory.createStartElement("", "", "extractDate"));
                    //TODO: Simple Date Format / JodaTime?
                    writer.add(factory.createCharacters(new Date().toString()));
                    writer.add(factory.createEndElement("", "", "extractDate"));
                } catch (XMLStreamException xse) {
                    LOGGER.error("Couldn't add extract date", xse);
                    throw new IOException(xse.getMessage());
                }
            }
        });
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("TrusteeDetailsItemWriter:TrusteeDetailsItemWriter() constructor - END");
        }
    }
    
    /** {@inheritDoc} **/
    public void write(List list) throws XmlMappingException, IOException {
        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("TrusteeDetailsItemWriter:write() method - START");
        }

        Map<Long, CharityTrusteeDetails> charityTrusteeDetails =
                (Map<Long, CharityTrusteeDetails>) executionContext
                        .get("CHARITY_TRUSTEE_DETAILS");

        if (charityTrusteeDetails != null && charityTrusteeDetails.entrySet() != null) {
            Set<Entry<Long, CharityTrusteeDetails>> entries = charityTrusteeDetails.entrySet();
            List<CharityTrusteeDetails> chTrusteeDetails = new ArrayList<CharityTrusteeDetails>();

            for (Entry<Long, CharityTrusteeDetails> entry : entries) {
                chTrusteeDetails.add(entry.getValue());
            }

            writeCharityTrusteeDetailsObj(chTrusteeDetails);
        }
        else {
            if(LOGGER.isDebugEnabled()) {
                LOGGER.debug("In TrusteeDetailsItemWriter's write method: No processing performed.");
            }
        }

        if(LOGGER.isTraceEnabled()) {
            LOGGER.trace("TrusteeDetailsItemWriter:write() method - END");
        }
    }

    /**
     * This method used to write the charity trustee details objects.
     * 
     * @param chTrusteeDetails the ch trustee details
     * 
     * @return void.
     * 
     * @throws IOException Signals that an I/O exception has occurred.
     */
    protected void writeCharityTrusteeDetailsObj(List<CharityTrusteeDetails> chTrusteeDetails)
             {
        try {
            super.write(Collections.singletonList(chTrusteeDetails));
        }
        catch (XmlMappingException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : TrusteeDetailsItemWriter while writing summarised object", e);
        }
        catch (IOException e) {
            throw new GivingBatchException(LOGGER, "Batch Failed : TrusteeDetailsItemWriter while writing summarised object", e);
        }
    }

    /** {@inheritDoc} **/
    public void beforeStep(StepExecution stepExecution) {
        executionContext = stepExecution.getJobExecution().getExecutionContext();
    }

    /** {@inheritDoc} **/
    public ExitStatus afterStep(StepExecution stepExecution) {
        return ExitStatus.COMPLETED;
    }

    /**
     * The superclass tries to append a closing tag - we override this
     * behaviour because it can result in two close tags.
     * 
     * @param writer XML Event writer
     * 
     * @throws XMLStreamException the XML stream exception
     */
    @Override
    protected void endDocument(XMLEventWriter writer) throws XMLStreamException {
        // do nothing :)
    }
}
