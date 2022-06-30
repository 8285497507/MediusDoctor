
package com.example.newdoctorsapp.models.NotificationModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationType {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("Type")
    @Expose
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
