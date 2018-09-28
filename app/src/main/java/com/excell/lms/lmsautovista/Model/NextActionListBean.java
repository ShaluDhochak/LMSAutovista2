package com.excell.lms.lmsautovista.Model;

import java.util.ArrayList;

/**
 * Created by Shalu Dhochak on 3/3/2018.
 */

public class NextActionListBean {

    public static class Select_NextAction{

        String mapId,feedbackStatusName,nextActionName,process_id,map_next_to_feed_status;
        public String getMapId() {
            return mapId;
        }

        public void setMapId(String mapId) {
            this.mapId = mapId;
        }

        public String getFeedbackStatusName() {
            return feedbackStatusName;
        }

        public void setFeedbackStatusName(String feedbackStatusName) {
            this.feedbackStatusName = feedbackStatusName;
        }

        public String getNextActionName() {
            return nextActionName;
        }

        public void setNextActionName(String nextActionName) {
            this.nextActionName = nextActionName;
        }

        public String getProcess_id() {
            return process_id;
        }

        public void setProcess_id(String process_id) {
            this.process_id = process_id;
        }

        public String getMap_next_to_feed_status() {
            return map_next_to_feed_status;
        }

        public void setMap_next_to_feed_status(String map_next_to_feed_status) {
            this.map_next_to_feed_status = map_next_to_feed_status;
        }
    }

    public ArrayList<Select_NextAction> getSelect_nextaction() {
        return select_nextaction;
    }

    public void setSelect_nextaction(ArrayList<Select_NextAction> select_nextaction) {
        this.select_nextaction = select_nextaction;
    }

    public ArrayList<Select_NextAction> select_nextaction;

}
