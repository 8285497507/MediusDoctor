
package com.example.newdoctorsapp.models.NotificationModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("readDetails")
    @Expose
    private ReadDetails readDetails;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("notificationId")
    @Expose
    private String notificationId;
    @SerializedName("sender")
    @Expose
    private Sender sender;
    @SerializedName("receiver")
    @Expose
    private Receiver receiver;
    @SerializedName("sender_ref")
    @Expose
    private String senderRef;
    @SerializedName("receiver_ref")
    @Expose
    private String receiverRef;
    @SerializedName("notificationType")
    @Expose
    private NotificationType notificationType;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public ReadDetails getReadDetails() {
        return readDetails;
    }

    public void setReadDetails(ReadDetails readDetails) {
        this.readDetails = readDetails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public Sender getSender() {
        return sender;
    }

    public void setSender(Sender sender) {
        this.sender = sender;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public String getSenderRef() {
        return senderRef;
    }

    public void setSenderRef(String senderRef) {
        this.senderRef = senderRef;
    }

    public String getReceiverRef() {
        return receiverRef;
    }

    public void setReceiverRef(String receiverRef) {
        this.receiverRef = receiverRef;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public void setNotificationType(NotificationType notificationType) {
        this.notificationType = notificationType;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
