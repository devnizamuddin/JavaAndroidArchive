package com.example.nizamuddinshamrat.librarymanagement;

/**
 * Created by Nizam Uddin Shamrat on 12/25/2017.
 */

public class FeedbackInfo {

    private int feedbackId;
    private float rate;
    private String comment;
    private int feedbackBookId;
    private int feedbackUserId;

    public FeedbackInfo(float rate, String comment, int feedbackBookId, int feedbackUserId) {
        this.rate = rate;
        this.comment = comment;
        this.feedbackBookId = feedbackBookId;
        this.feedbackUserId = feedbackUserId;
    }

    public FeedbackInfo(int feedbackId, float rate, String comment, int feedbackBookId, int feedbackUserId) {
        this.feedbackId = feedbackId;
        this.rate = rate;
        this.comment = comment;
        this.feedbackBookId = feedbackBookId;
        this.feedbackUserId = feedbackUserId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getFeedbackBookId() {
        return feedbackBookId;
    }

    public void setFeedbackBookId(int feedbackBookId) {
        this.feedbackBookId = feedbackBookId;
    }

    public int getFeedbackUserId() {
        return feedbackUserId;
    }

    public void setFeedbackUserId(int feedbackUserId) {
        this.feedbackUserId = feedbackUserId;
    }
}