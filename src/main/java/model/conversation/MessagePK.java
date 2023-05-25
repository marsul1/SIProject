package model.conversation;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MessagePK implements Serializable {

   // @Column( insertable=false, updatable=false)
    private long conversation_id;

    private Integer massage_number;

    public MessagePK() {

    }
    public long getConversationId() {
        return this.conversation_id;
    }
    public void setConversationId(long conversationId) {
        this.conversation_id = conversationId;
    }


    public Integer getMassageNumber() {
        return this.massage_number;
    }
    public void setMassageNumber(Integer massageNumber) {
        this.massage_number = massageNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MessagePK)) {
            return false;
        }
        MessagePK castOther = (MessagePK)other;
        return
                (this.conversation_id == castOther.conversation_id)
                        && this.massage_number.equals(castOther.massage_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversation_id);
    }
}
