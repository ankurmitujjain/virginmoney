package com.virginmoneygiving.givingbatch.writer;

import org.springframework.batch.item.file.transform.LineAggregator;

import com.virginmoneygiving.givingbatch.domain.GiftAidClaimEnd;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimSchedule;
import com.virginmoneygiving.givingbatch.domain.GiftAidClaimStart;

/**
 * This class provides to aggregate the instances based on conditions.
 * 
 * @author Srinivas Nageli
 */
public class DelegatingGiftAidLineAggregator implements LineAggregator<Object> {

    /** LineAggregator for GiftAidClaimStart. */
    private LineAggregator<GiftAidClaimStart> giftAidClaimStartAggregator;

    /** LineAggregator for GiftAidClaimSchedule. */
    private LineAggregator<GiftAidClaimSchedule> giftAidClaimScheduleAggregator;

    /** LineAggregator for GiftAidClaimEnd. */
    private LineAggregator<GiftAidClaimEnd> giftAidClaimEndAggregator;

    /**
     * This method used to call the super class aggregate method based on
     * conditions.
     * 
     * @param object of type Object.
     * 
     * @return string
     */
    public String aggregate(Object object) {
        if (object instanceof GiftAidClaimStart) {
            return this.giftAidClaimStartAggregator
                    .aggregate((GiftAidClaimStart) object);
        }
        else if (object instanceof GiftAidClaimSchedule) {
            return this.giftAidClaimScheduleAggregator
                    .aggregate((GiftAidClaimSchedule) object);
        }
        else if (object instanceof GiftAidClaimEnd) {
            return this.giftAidClaimEndAggregator
                    .aggregate((GiftAidClaimEnd) object);
        }
        else {
            throw new RuntimeException();
        }
    }

    /**
     * Gets the gift aid claim start aggregator.
     * 
     * @return the giftAidClaimStartAggregator
     */
    public LineAggregator<GiftAidClaimStart> getGiftAidClaimStartAggregator() {
        return giftAidClaimStartAggregator;
    }

    /**
     * Sets the gift aid claim start aggregator.
     * 
     * @param giftAidClaimStartAggregator the giftAidClaimStartAggregator to set
     */
    public void setGiftAidClaimStartAggregator(
            LineAggregator<GiftAidClaimStart> giftAidClaimStartAggregator) {
        this.giftAidClaimStartAggregator = giftAidClaimStartAggregator;
    }

    /**
     * Gets the gift aid claim schedule aggregator.
     * 
     * @return the giftAidClaimScheduleAggregator
     */
    public LineAggregator<GiftAidClaimSchedule> getGiftAidClaimScheduleAggregator() {
        return giftAidClaimScheduleAggregator;
    }

    /**
     * Sets the gift aid claim schedule aggregator.
     * 
     * @param giftAidClaimScheduleAggregator the giftAidClaimScheduleAggregator to set
     */
    public void setGiftAidClaimScheduleAggregator(
            LineAggregator<GiftAidClaimSchedule> giftAidClaimScheduleAggregator) {
        this.giftAidClaimScheduleAggregator = giftAidClaimScheduleAggregator;
    }

    /**
     * Gets the gift aid claim end aggregator.
     * 
     * @return the giftAidClaimEndAggregator
     */
    public LineAggregator<GiftAidClaimEnd> getGiftAidClaimEndAggregator() {
        return giftAidClaimEndAggregator;
    }

    /**
     * Sets the gift aid claim end aggregator.
     * 
     * @param giftAidClaimEndAggregator the giftAidClaimEndAggregator to set
     */
    public void setGiftAidClaimEndAggregator(
            LineAggregator<GiftAidClaimEnd> giftAidClaimEndAggregator) {
        this.giftAidClaimEndAggregator = giftAidClaimEndAggregator;
    }

}
