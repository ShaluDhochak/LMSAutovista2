package com.example.user.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 3/3/2018.
 */

public class FeedbackListBean {

    public static class Select_Feedback{
        String feedbackStatusId;
        String feedbackStatusName;
        String process_id;
        String fstatus;

        public String getFeedbackStatusId() {
            return feedbackStatusId;
        }

        public void setFeedbackStatusId(String feedbackStatusId) {
            this.feedbackStatusId = feedbackStatusId;
        }

        public String getFeedbackStatusName() {
            return feedbackStatusName;
        }

        public void setFeedbackStatusName(String feedbackStatusName) {
            this.feedbackStatusName = feedbackStatusName;
        }

        public String getProcess_id() {
            return process_id;
        }

        public void setProcess_id(String process_id) {
            this.process_id = process_id;
        }

        public String getFstatus() {
            return fstatus;
        }

        public void setFstatus(String fstatus) {
            this.fstatus = fstatus;
        }

    }

    public ArrayList<Select_Feedback> getSelect_feedback() {
        return select_feedback;
    }

    public void setSelect_feedback(ArrayList<Select_Feedback> select_feedback) {
        this.select_feedback = select_feedback;
    }

    public ArrayList<Select_Feedback> select_feedback;

}
