package com.excell.lms.lmsautovista.Model;

/*
  Created by Shalu Dhochak on 4/30/2018.
*/

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class MessageListBean {
    public ArrayList<Message_List> message_list;

    public ArrayList<Message_List> getMessage_list() {
        return message_list;
    }

    public void setMessage_list(ArrayList<Message_List> message_list) {
        this.message_list = message_list;
    }

    public static class Message_List implements Parcelable {
        String message;
        String message_id;
        public ArrayList<Location> location;

        public String getMessage() {
            return message;
        }
        public void setMessage(String message) {
            this.message = message;
        }
        public String getMessage_id() {
            return message_id;
        }
        public void setMessage_id(String message_id) {
            this.message_id = message_id;
        }
        public ArrayList<Location> getLocation() {
            return location;
        }
        public void setLocation(ArrayList<Location> location) {
            this.location = location;
        }

        public static class Location{
            String location;
            String tl;
            String dse;
            public String getLocation() {
                return location;
            }
            public void setLocation(String location) {
                this.location = location;
            }
            public String getTl() {
                return tl;
            }
            public void setTl(String tl) {
                this.tl = tl;
            }
            public String getDse() {
                return dse;
            }
            public void setDse(String dse) {
                this.dse = dse;
            }
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.message);
            dest.writeString(this.message_id);
            dest.writeList(this.location);
        }

        public Message_List() {
        }

        protected Message_List(Parcel in) {
            this.message = in.readString();
            this.message_id = in.readString();
            this.location = new ArrayList<Location>();
            in.readList(this.location, Location.class.getClassLoader());
        }

        public static final Parcelable.Creator<Message_List> CREATOR = new Parcelable.Creator<Message_List>() {
            @Override
            public Message_List createFromParcel(Parcel source) {
                return new Message_List(source);
            }

            @Override
            public Message_List[] newArray(int size) {
                return new Message_List[size];
            }
        };
    }
}